package com.guangl.fdfs.client;

/**
 * <p>
 *
 * @author jiangzhou.bo@hand-china.com
 * @version 1.0
 * @name FastDFSErrorCode
 * @date 2017-10-17 22:35
 */
public enum ErrorCode {

    FILE_EMPTY_ERROR(8991, "上传文件为空"),
    FILE_NOT_EXIST_ERROR(8990, "文件不存在"),
    FILE_PATH_ISNULL(8989, "文件路径为空"),
    FILE_ISNULL(8988, "文件为空"),
    FILE_UPLOAD_FAILED(8987, "文件上传失败"),
    FILE_NOT_EXIST(8986, "文件不存在"),
    FILE_DOWNLOAD_FAILED(8985, "文件下载失败"),
    FILE_DELETE_FAILED(8984, "删除文件失败"),
    FILE_SERVER_CONNECTION_FAILED(8983, "文件服务器连接失败"),
    FILE_OUT_SIZE(8982, "文件超过大小"),
    FILE_TYPE_ERROR_IMAGE(8981, "图片类型错误"),
    FILE_TYPE_ERROR_DOC(8980, "文档类型错误"),
    FILE_TYPE_ERROR_VIDEO(8979, "音频类型错误"),
    FILE_TYPE_ERROR_COMPRESS(8978, "压缩文件类型错误");


    public int CODE;

    public String MESSAGE;

    ErrorCode(int CODE, String MESSAGE){
        this.CODE = CODE;
        this.MESSAGE = MESSAGE;
    }

}
