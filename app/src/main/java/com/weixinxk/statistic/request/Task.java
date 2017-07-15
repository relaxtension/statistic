package com.weixinxk.statistic.request;

/**********************************************************************
 * 任务接口
 *
 * @类名 Task
 * @包名 com.weixinxk.statistic.task
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public interface Task<SUCCESS, FAILURE> extends Runnable {

    /**
     * 执行任务
     *
     * @param progressSender 进度更新发送者
     * @return 任务结果
     * @throws Exception 异常
     */
    public TaskResult<SUCCESS, FAILURE> execute(ProgressSender progressSender) throws Exception;

    /**
     * 取消任务
     *
     * 注意cancle 和 execute 有可能不在同一个线程，cancle可能在UI线程被调用
     */
    public void cancle();

}
