package com.guangl.fdfs.enums;

/**
 * @ClassName: ErrorCodeMsg
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 11:28
 * @Version: 1.0.0
 * @param: * @param null
 */
public enum ErrorCodeMsg {
    // 系统级别(0,0->999)
    SUCCESS(0, "成功"),
    UNKNOWN_ERROR(999, "未知错误"),
    SERVER_ERROR(998, "服务错误"),
    PARAM_ERROR(997, "参数错误"),
    PAGE_OR_API_ERROR(996, "页面或接口错误"),
    HEADER_USER_ERROR(995, "非过滤接口中头信息不含有用户要素"),
    REDIS_ERROR(994, "缓存错误"),
    REDIS_INCR_ERROR(993, "递增因子必须大于0"),
    USER_BEYOND_EXPIRE_TM_ERROR(992, "用户已经超出了系统给定的使用时间"),
    // 业务错误(xx业务:8000->8999)
    JSON_DECODE_ERROR(8999, "JSON解析错误"),
    LOGIN_PARAMS_ERROR(8998, "登录用户名或者密码错误"),
    BASE64_ENCRYPT_ERROR(8997, "Base64加密错误"),
    TOKEN_UNSUPPORT_ENCODE_ERROR(8996, "JWTToken认证解密出现UnsupportedEncodingException异常"),
    SIGN_UP_PARAMS_ERROR(8995, "注册用户名密码或手机验证码错误"),
    USER_NO_EXIST_ERROR(8994, "用户不存在或此手机号为非注册手机号"),
    USER_PASS_WORD_ERROR(8993, "用户密码不正确"),
    BASE64_DECRYPT_ERROR(8992, "Base64解密错误"),
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
    FILE_TYPE_ERROR_COMPRESS(8978, "压缩文件类型错误"),
    UPLOAD_ERROR(8977, "上传错误"),
    FORBIDDEN_REQUEST_ERROR(8976, "拒绝访问"),
    APP_ID_SECRET_NULL_ERROR(8975, "app的ID和Secret为空"),
    APP_ID_SECRET_ERROR(8974, "app的ID或Secret错误"),
    TOKEN_DECODE_ERROR(8973, "解密Token中的公共信息出现JWTDecodeException异常"),
    SESSION_TOKEN_ERROR(8972, "session-token错误"),
    DO_TOKEN_EMPTY_ERROR(8971, "两种Token为空的错误，不能执行相关fastDFS操作"),
    TOKEN_TIMEOUT_ERROR(8970, "令牌过期"),

    // 数据级别
    ;

    private int code;
    private String message;

    ErrorCodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
