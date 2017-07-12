package com.weixinxk.statistic.task;

/**********************************************************************
 * 任务回调
 *
 * @类名 TaskCallback
 * @包名 com.weixinxk.statistic.task
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public interface TaskCallback<SUCCESS, FAILURE> {

	// 任务异常结果码
	public static final int RESULT_CODE_EXCEPTION = -1;
	// 任务失败结果码
	public static final int RESULT_CODE_FAILURE = 0;
	// 任务成功结果码
	public static final int RESULT_CODE_SUCCESS = 1;
	// 任务取消结果码
	public static final int RESULT_CODE_CANCEL = 2;

	/**
	 * 执行task之前的回调
	 */
	public void onPreExecute();

	/**
	 * 进度更新回调，用于上传文件任务
	 *
	 * @param percent 进度百分比
	 * @param current 当前进度数
	 * @param total 总共进度数
	 * @param extras 传递参数
	 */
	public void onProgressUpdate(int percent, long current, long total, Object extras);

	/**
	 * 执行task结束的回调，通过resultCode判断是什么情况结束task，（成功，失败，异常，取消）
	 * 
	 * @param resultCode 返回码
	 * @param result 执行结果
	 */
	public void onPostExecute(int resultCode, TaskResult<SUCCESS, FAILURE> result);

}