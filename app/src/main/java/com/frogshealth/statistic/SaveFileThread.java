package com.frogshealth.statistic;

import com.frogshealth.photoframe.common.logger.LoggerUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**********************************************************************
 * 文件保存线程
 * @author sundi
 * @类名 SaveFileThread
 * @包名 com.frogshealth.photoframe.common.statistics
 * @创建日期 16/12/1
 ***********************************************************************/
public class SaveFileThread extends Thread {
    /**
     * JSON数组
     */
    private JSONArray mJSONArray;

    /**
     * 数据类型
     */
    private String mType;

    /**
     * 文件路径
     */
    private String mFilePath;

    public SaveFileThread(String filePath, JSONArray data, String type) {
        super();
        mFilePath = filePath;
        mJSONArray = data;
        mType = type;
    }

    @Override
    public void run() {
//        LoggerUtil.DEFAULT_LOGGER.debug("Save cache file :" + mFilePath);
//        LoggerUtil.DEFAULT_LOGGER.debug("json data " + mJSONArray.toString());
        if (mJSONArray.length() == 0) {
            return;
        }

        saveToFile(mJSONArray);
    }

    /**
     * 将数据保存到文件
     * @param array json数据
     */
    public void saveToFile(JSONArray array) {
        FileWriter writer = null;
        ReentrantReadWriteLock rwl = CommonUtil.getReadWriteLock(mFilePath);
        while (true) {
            if (!rwl.writeLock().tryLock()) {
                continue;
            }
//            rwl.writeLock().lock();
            try {
                writer = new FileWriter(mFilePath, true);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put(mType, array.get(i));
                        writer.write(StatisticsConstants.FILE_SEP + jsonObject.toString());
                    } catch (JSONException e) {
                        LoggerUtil.getDefaultLogger().error(e.getMessage());
                    }
                }
            } catch (IOException e) {
                LoggerUtil.getDefaultLogger().error(e.getMessage());
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    LoggerUtil.getDefaultLogger().error(e.getMessage());
                }
                rwl.writeLock().unlock();
            }
            break;
        }
    }
}
