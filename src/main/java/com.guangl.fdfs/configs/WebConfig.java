package com.guangl.fdfs.configs;

import com.guangl.fdfs.constants.ConstantsConfig;
import com.guangl.fdfs.interceptor.HeaderInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebConfig
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2020-01-16 15:21
 * @Version: 1.0.0
 * @param: * @param null
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final static Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HeaderInterceptor())
                .addPathPatterns(ConstantsConfig.APPLICATION_NAME + "/**");
    }

}
