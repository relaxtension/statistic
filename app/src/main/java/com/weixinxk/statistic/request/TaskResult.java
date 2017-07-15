package com.weixinxk.statistic.request;

/**********************************************************************
 * 任务结果
 *
 * @类名 TaskResult
 * @包名 com.weixinxk.statistic.task
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public class TaskResult<SUCCESS, FAILURE> {

    private SUCCESS success;
    private FAILURE failure;
    private Exception exception;
    private boolean isSuccess;
    private boolean hasException;

    public SUCCESS getSuccess() {
        return success;
    }

    public void setSuccess(SUCCESS success) {
        this.success = success;
        this.isSuccess = true;
        this.hasException = false;
    }

    public FAILURE getFailure() {
        return failure;
    }

    public void setFailure(FAILURE failure) {
        this.failure = failure;
        this.isSuccess = false;
        this.hasException = false;
    }

    public void setFailure(FAILURE failure, Exception exception) {
        this.failure = failure;
        this.exception = exception;
        this.isSuccess = false;
        this.hasException = true;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
        this.isSuccess = false;
        this.hasException = true;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean hasException() {
        return hasException;
    }

    public static <SUCCESS, FAILURE> TaskResult<SUCCESS, FAILURE> buildSuccess(SUCCESS success) {
        TaskResult<SUCCESS, FAILURE> result = new TaskResult<SUCCESS, FAILURE>();
        result.setSuccess(success);
        return result;
    }

    public static <SUCCESS, FAILURE> TaskResult<SUCCESS, FAILURE> buildFailure(FAILURE failure) {
        TaskResult<SUCCESS, FAILURE> result = new TaskResult<SUCCESS, FAILURE>();
        result.setFailure(failure);
        return result;
    }

    public static <SUCCESS, FAILURE> TaskResult<SUCCESS, FAILURE> buildFailure(FAILURE failure, Exception exception) {
        TaskResult<SUCCESS, FAILURE> result = new TaskResult<SUCCESS, FAILURE>();
        result.setFailure(failure, exception);
        return result;
    }

    public static <SUCCESS, FAILURE> TaskResult<SUCCESS, FAILURE> buildException(Exception exception) {
        TaskResult<SUCCESS, FAILURE> result = new TaskResult<SUCCESS, FAILURE>();
        result.setException(exception);
        return result;
    }
}
