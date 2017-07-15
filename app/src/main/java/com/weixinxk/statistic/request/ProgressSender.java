package com.weixinxk.statistic.request;

/**********************************************************************
 * 任务进度发送器
 *
 * @类名 ProgressSender
 * @包名 com.weixinxk.statistic.task
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public interface ProgressSender {

	/**
	 * 发送进度
	 *
	 * @param current 当前进度数
	 * @param total 总共进度数
	 * @param exras 传递参数
	 */
	public void send(long current, long total, Object exras);
}
