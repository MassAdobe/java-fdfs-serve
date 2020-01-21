package com.guangl.fdfs.client;

/**
 * FastDFS 上传下载时可能出现的一些异常信息
 * <p>
 *
 * @author jiangzhou.bo@hand-china.com
 * @version 1.0
 * @name FastDFSException
 * @date 2017-10-13 15:12
 */
public class FastDFSException extends Exception {

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误消息
     */
    private String message;

    public FastDFSException(){}

    public FastDFSException(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
