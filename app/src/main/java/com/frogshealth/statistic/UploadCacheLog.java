package com.frogshealth.statistic;

import android.content.Context;
import android.util.ArrayMap;

import com.frogshealth.photoframe.common.logger.LoggerUtil;
import com.frogshealth.photoframe.common.util.SharedPreferencesUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FilenameFilter;

/**********************************************************************
 * 上传缓存工作类
 * @author sundi
 * @类名 UploadCacheLog
 * @包名 com.frogshealth.photoframe.common.statistics
 * @创建日期 16/12/1
 ***********************************************************************/
public class UploadCacheLog implements Runnable {

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 转换后的数据集
     */
    private ArrayMap<String, JSONArray> mObjectArrayMap;

    public UploadCacheLog(Context context) {
        super();
        mContext = context;
    }

    @Override
    public void run() {
        String baseDir = mContext.getCacheDir().getAbsolutePath()
                + StatisticsConstants.FILE_FOLDER;
        File folder = new File(baseDir);
        if (!folder.exists()) {
            folder.mkdir();
            LoggerUtil.getDefaultLogger().debug("make new folder:" + baseDir);
            return;
        }
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                LoggerUtil.getDefaultLogger().debug(filename);
                if (filename.contains(StatisticsConstants.FILE_NAME_PREFIX + StatisticsConstants.TYPE_EVENT)) {
                    return true;
                }
                return false;
            }
        });

        for (File eventFile : files) {
            postData(eventFile.getAbsolutePath(), StatisticsConstants.EVENT_URL,StatisticsConstants.TYPE_EVENT);
        }

    }


    /**
     * 上传数据
     * @param filePath 文件路径
     * @param url url
     * @param type 数据类型
     */
    private void postData(String filePath, String url, String type) {
        if (!CommonUtil.isNetworkAvailable(mContext)) {
            return;
        }

        JSONObject postData = new JSONObject();
        JSONArray arr = new JSONArray();
        try {
            arr = CommonUtil.getJSONData(filePath, type);
            if (arr.length() == 0) {
                return;
            }
            postData = rebuildData(arr);
//            postData.put("data", arr);
        } catch (Exception e) {
            LoggerUtil.getDefaultLogger().error(e.getMessage());
        }
        if (postData != null) {
            if (CommonUtil.isNetworkAvailable(mContext)) {
                //TODO
                LoggerUtil.getDefaultLogger().debug(postData.toString());

                boolean isSuccess = ConnectionUtil.sendPostRequest(mContext, url,postData.toString());

                if (!isSuccess) {
                    CommonUtil.saveInfoToFile(filePath,type, arr);
                    LoggerUtil.getDefaultLogger().debug("post request failed, save data to file");
                } else {
                    SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(mContext,StatisticsConstants.KEY_STATISTICS_LOG);
                    String currentFilePath = sharedPreferencesUtil.getString("current_save_file");
                    if (currentFilePath != null && filePath.equals(currentFilePath)) {
                        sharedPreferencesUtil.remove("current_save_file");
                    }
                    CommonUtil.removeReadWriteLock(filePath);
                    LoggerUtil.getDefaultLogger().debug("post request success:" + filePath);
                }

                mObjectArrayMap.clear();
            } else {
                CommonUtil.saveInfoToFile(filePath,type, arr);
            }
        }
    }


    /**
     * 按照接口格式重新组织数据
     * @param array JSONArray 对象
     * @return 重新组织后的数据对象
     */
    private JSONObject rebuildData(JSONArray array) {
        if (mObjectArrayMap == null) {
            mObjectArrayMap = new ArrayMap<>();
        }
        int count = array.length();
//        JSONObject result = new JSONObject();
        for (int i = 0; i < count; i++) {
            try {
                JSONObject object = new JSONObject(array.get(i).toString());
                String pageKey = (String)object.remove(StatisticsConstants.KEY_PAGE_ID);
                String value = buildValue(object);
                JSONArray target;
                if (pageKey != null && mObjectArrayMap.containsKey(pageKey)) {
                    target = mObjectArrayMap.get(pageKey);
                } else {
                    target = new JSONArray();
                    mObjectArrayMap.put(pageKey,target);
                }
                target.put(value);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject result = new JSONObject();
        for (String key: mObjectArrayMap.keySet()) {
            try {
                result.put(key,mObjectArrayMap.get(key));
//                result.put(StatisticsConstants.KEY_DEVICE_ID, DeviceSnUtil.getDeviceSn());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        LoggerUtil.getDefaultLogger().debug("rebuilt post data:" + result.toString());
        return result;
    }

    /**
     * 根据保存的数据重新拼接数据字串
     * @param jsonObject Json数据
     * @return 数据字串
     */
    private String buildValue(JSONObject jsonObject) {
        String result = "";
        StringBuffer buffer = new StringBuffer();
        try {
            buffer.append(jsonObject.get(StatisticsConstants.KEY_VIEW_TYPE))
                    .append(StatisticsConstants.STRING_SEP)
                    .append("") //elementType不需要传
                    .append(StatisticsConstants.STRING_SEP)
                    .append(jsonObject.get(StatisticsConstants.KEY_OPERATION_TYPE))
                    .append(StatisticsConstants.STRING_SEP)
                    .append(jsonObject.get(StatisticsConstants.KEY_EVENT_ID))
                    .append(StatisticsConstants.STRING_SEP)
                    .append(jsonObject.get(StatisticsConstants.KEY_TIME));
            result = buffer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
