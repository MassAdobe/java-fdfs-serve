package com.guangl.fdfs.controller;

import com.guangl.fdfs.constants.CmnConstants;
import com.guangl.fdfs.constants.ConstantsConfig;
import com.guangl.fdfs.controller.AbstractController.BasicContoller;
import com.guangl.fdfs.utils.CommonUtils;
import com.guangl.fdfs.utils.JwtUtils;
import com.guangl.fdfs.utils.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TokenController
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: 获取token的controller
 * @Date: Created in 2020-01-14 19:21
 * @Version: 1.0.0
 * @param: * @param null
 */
@RestController
@RequestMapping(value = ConstantsConfig.TOKEN_APPLICATION_NAME + "/token")
public class TokenController extends BasicContoller {

    private final static Logger logger = LoggerFactory.getLogger(TokenController.class);

    private static final Long EXPIRE_DURATION = 60L;

    @Autowired
    private RedisClient redisClient;

    /**
     * @ClassName: TokenController
     * @Author: MassAdobe
     * @Email: massadobe8@gmail.com
     * @Description: 获取token
     * @Date: Created in 2020-01-16 12:06
     * @Version: 1.0.0
     * @param: * @param null
     */
    @PostMapping("/getToken")
    public String getToken() {
        String randomString = System.currentTimeMillis() + CommonUtils.getRandomString(16);
        String token = JwtUtils.geneToken(randomString);
        this.redisClient.set(randomString, CmnConstants.EMPTY, EXPIRE_DURATION);
        return token;
    }


}
