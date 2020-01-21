package com.guangl.fdfs.constants;

/**
 * @ClassName: ConstantsConfig
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 12:04
 * @Version: 1.0.0
 * @param: * @param null
 */
public class ConstantsConfig {

    /**
     * log的配置名称
     */
    public final static String LOG_CONFIG_LOCATION_NAME = "logging.fileLocation";
    /**
     * windows系统log日志存放地址
     */
    public final static String WIN_LOG_PATH = "C:\\usr\\local\\data\\logs";
    /**
     * mac系统log日志存放地址
     */
    public final static String MAC_LOG_PATH = "/usr/local/data/logs";
    /**
     * linux系统log日志存放地址
     */
    public final static String LINUX_LOG_PATH = "/data/logs";
    /**
     * 服务名，在MVC声明时使用
     */
    public final static String APPLICATION_NAME = "/fdfs-oss-sys";
    /**
     * Token服务名前缀，在MVC声明使用
     */
    public final static String TOKEN_APPLICATION_NAME = "/fdfs-token";
    /**
     * Token服务的APP_ID的Key
     */
    public final static String APP_ID_KEY = "appId";
    /**
     * Token服务的APP_SECRET的Key
     */
    public final static String APP_SECRET_KEY = "appSecret";
    /**
     * Token中校验元素secret
     */
    public final static String TOKEN_VERIFY_KEY = "v_scrt";
    /**
     * Token中的用户KEY
     */
    public final static String TOKEN_USER_KEY = "g_uid";
    /**
     * Token中的OSS系统ID
     */
    public final static String TOKEN_OSS_KEY = "sys_id";
    /**
     * random随机的token字符串
     */
    public final static String TOKEN_RANDOM_STRING = "rd_str";
    /**
     * fastDFS的header的Token-Key
     */
    public final static String HEADER_FASTDFS_TOKEN_KEY = "Fdfs_Token";
    /**
     * 一般的session的token
     */
    public final static String ACCESS_TOKEN_KEY = "Access-Token";
}
