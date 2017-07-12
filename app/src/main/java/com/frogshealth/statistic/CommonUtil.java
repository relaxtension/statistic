/**********************************************************************
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 **********************************************************************/
package com.frogshealth.statistic;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.ArrayMap;

import com.frogshealth.photoframe.common.logger.LoggerUtil;
import com.frogshealth.photoframe.common.util.SharedPreferencesUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**********************************************************************
 * 工具类
 *  @author sundi
 * @类名 CommonUtil
 * @包名 com.frogshealth.photoframe.common.statistics
 * @创建日期 16/12/1
 ***********************************************************************/
public class CommonUtil {

    /**
     * 写入同步锁
     */
    private static ReentrantReadWriteLock sReadWriteLock  = new ReentrantReadWriteLock();

    /**
     * 同步锁集合
     */
    private static ArrayMap<String,ReentrantReadWriteLock> sLockArrayMap = new ArrayMap<>();

    /**
     * 取得写入同步锁
     * @return 写入同步锁
     */
    public static ReentrantReadWriteLock getReadWriteLock() {
        return sReadWriteLock;
    }

    /**
     * 线程池
     */
    private static ExecutorService sInternalExecutor;

    /**
     * 按照文件取得读写同步锁
     * @param filePath 文件路径
     * @return 写入同步锁
     */
    public static synchronized ReentrantReadWriteLock getReadWriteLock(String filePath) {
        ReentrantReadWriteLock lock;
        if (sLockArrayMap.containsKey(filePath)) {
            lock = sLockArrayMap.get(filePath);
        } else {
            lock = new ReentrantReadWriteLock();
            sLockArrayMap.put(filePath, lock);
        }
        return lock;
    }

    /**
     * 移除同步锁
     * @param filePath 文件路径
     */
    public static synchronized void removeReadWriteLock(String filePath) {
        sLockArrayMap.remove(filePath);
    }

    /**
     * 判断是否有网络连接
     *
     * @param context 上下文
     * @return true or false
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            LoggerUtil.getDefaultLogger().error("context is null");
            return false;
        }
        if (checkPermissions(context, "android.permission.INTERNET")) {
            ConnectivityManager cManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cManager == null) {
                return false;
            }
            NetworkInfo info = cManager.getActiveNetworkInfo();

            if (info != null && info.isAvailable()) {
                LoggerUtil.getDefaultLogger().debug( "Network is available.");
                return true;
            } else {
                LoggerUtil.getDefaultLogger().debug("Network is not available.");
                return false;
            }

        } else {
            LoggerUtil.getDefaultLogger().error(
                    "android.permission.INTERNET permission should be "
                            + "added into AndroidManifest.xml.");

            return false;
        }
    }

    /**
     * 检查是否有指定的许可
     *
     * @param context 上下文
     * @param permission 许可
     * @return true or false
     */
    public static boolean checkPermissions(Context context, String permission) {
        if (context == null || permission == null || permission.equals("")) {
            LoggerUtil.getDefaultLogger().error("Incorrect parameters");
            return false;
        }
        PackageManager pm = context.getPackageManager();
        return pm.checkPermission(permission, context.getPackageName())
                == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 从文件中得到数据，并转换成JSON数据
     * @param filePath 文件路径
     * @param type 数据类型
     * @return Json 数组
     */
    public static synchronized JSONArray getJSONData(String filePath, String type) {
        JSONArray jsonArray = new JSONArray();

        String data = readDataFromFile(filePath);
        if (data.length() > 0) {
            String[] datas = data.split(StatisticsConstants.FILE_SEP);

            for (String dataStr : datas) {
                if (dataStr.equals("")) {
                    continue;
                }
                try {
                    JSONObject obj = new JSONObject(dataStr).getJSONObject(type);
                    jsonArray.put(obj);
                } catch (Exception e) {
                    LoggerUtil.getDefaultLogger().error(e.getMessage());
                }
            }
        }

        return jsonArray;
    }

    /**
     * 读取文件
     * @param fileName 文件路径
     * @return 文件内容
     */
    static String readDataFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return "";
        }
        FileInputStream in = null;
        StringBuffer dataBuffer = new StringBuffer();
        ReentrantReadWriteLock rwl = getReadWriteLock(fileName);
        if (rwl.readLock().tryLock()) {
            try {
                in = new FileInputStream(file);

                byte[] buffer = new byte[2048];
                int readByte;
                while ((readByte = in.read(buffer)) != -1) {
                    dataBuffer.append(new String(buffer, 0, readByte));
                }
            } catch (Exception e) {
                LoggerUtil.getDefaultLogger().error(e.getMessage());
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        LoggerUtil.getDefaultLogger().error(e.getMessage());
                    }
                }
                //解读锁
                rwl.readLock().unlock();
                //删除缓存文件
                file.delete();
            }
        }
        return dataBuffer.toString();
    }

    /**
     * 保存数据到文件
     * @param type 文件类型
     * @param info 数据
     * @param context 上下文
     */
    public static void saveInfoToFile(Context context, String type, JSONObject info) {
        JSONArray array = new JSONArray();
        array.put(info);
        saveInfoToFile(context, type, array);
    }

    /**
     * 保存数据到文件
     * @param type 文件类型
     * @param info 数据
     * @param context 上下文
     */
    public static void saveInfoToFile(Context context, String type, JSONArray info) {
        try {
            String baseDir = context.getCacheDir().getAbsolutePath()
                    + StatisticsConstants.FILE_FOLDER;
            File dir = new File(baseDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(context,StatisticsConstants.KEY_STATISTICS_LOG);
            String currentFilePath = baseDir + StatisticsConstants.FILE_NAME_PREFIX + StatisticsConstants.TYPE_EVENT + System.currentTimeMillis();
            String filePath = sharedPreferencesUtil.getString("current_save_file");

            if (filePath == null) {
                sharedPreferencesUtil.save("current_save_file",currentFilePath);
                filePath = currentFilePath;
            }

            File file = new File(filePath);
            //超出默认值则使用新文件
            if (file.length() + info.toString().getBytes().length > StatisticsConstants.DEFAULT_FILE_SIZE) {
//                file.delete();
                sharedPreferencesUtil.save("current_save_file",currentFilePath);
                filePath = currentFilePath;
            }

            saveInfoToFile(filePath,type,info);
        } catch (Exception e) {
            LoggerUtil.getDefaultLogger().error(e.getMessage());
        }
    }

    /**
     * 保存数据到文件
     * @param filePath 文件路径
     * @param type 数据类型
     * @param info 数据
     */
    public static void saveInfoToFile(String filePath , String type, JSONArray info) {
        if (sInternalExecutor == null) {
            sInternalExecutor = Executors.newFixedThreadPool(2);
        }
        sInternalExecutor.execute(new SaveFileThread(filePath, info, type));
//        Thread t = new SaveFileThread(filePath, info, type);
//        t.start();
    }

    /**
     * 取得应用版本
     *
     * @param context 上下文
     * @return 版本
     */
    public static String getCurVersionName(Context context) {
        if (context == null) {
            LoggerUtil.getDefaultLogger().error("context is null");
            return "";
        }
        String version = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            version = pi.versionName;
        } catch (Exception e) {
            LoggerUtil.getDefaultLogger().error(e.getMessage());
        }

        return version;
    }
}
