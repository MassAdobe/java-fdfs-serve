package com.guangl.fdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

/**
 * @ClassName: FdfsOssSysApplication
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2020-01-14 17:25
 * @Version: 1.0.0
 * @param: * @param null
 */
@SpringBootApplication
public class FdfsOssSysApplication {
    public static void main(String[] args) {
        Properties properties = new Properties();
        if (com.guangl.fdfs.configs.OSinfo.isWindows()) {
            properties.setProperty(com.guangl.fdfs.constants.ConstantsConfig.LOG_CONFIG_LOCATION_NAME, com.guangl.fdfs.constants.ConstantsConfig.WIN_LOG_PATH);
        } else if (com.guangl.fdfs.configs.OSinfo.isMacOSX() || com.guangl.fdfs.configs.OSinfo.isMacOS()) {
            properties.setProperty(com.guangl.fdfs.constants.ConstantsConfig.LOG_CONFIG_LOCATION_NAME, com.guangl.fdfs.constants.ConstantsConfig.MAC_LOG_PATH);
        } else {
            properties.setProperty(com.guangl.fdfs.constants.ConstantsConfig.LOG_CONFIG_LOCATION_NAME, com.guangl.fdfs.constants.ConstantsConfig.LINUX_LOG_PATH);
        }
        SpringApplication app = new SpringApplication(FdfsOssSysApplication.class);
        app.setDefaultProperties(properties);
        app.run(args);
    }
}
