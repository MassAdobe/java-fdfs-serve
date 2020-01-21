package com.guangl.fdfs.controller.AbstractController;

import com.google.common.base.Strings;
import com.guangl.fdfs.constants.ConstantsConfig;
import com.guangl.fdfs.enums.ErrorCodeMsg;
import com.guangl.fdfs.exception.AttemptException;
import com.guangl.fdfs.utils.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: BasicContoller
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2020-01-06 20:48
 * @Version: 1.0.0
 * @param: * @param null
 */
@RestController
@RequestMapping(value = ConstantsConfig.TOKEN_APPLICATION_NAME)
public class BasicContoller {

    private final static Logger logger = LoggerFactory.getLogger(BasicContoller.class);

    /**
     * @ClassName: BasicContoller
     * @Author: MassAdobe
     * @Email: massadobe8@gmail.com
     * @Description: 校验系统的相关appId和appSecret
     * @Date: Created in 2020-01-16 10:56
     * @Version: 1.0.0
     * @param: * @param null
     */

    private static String appIdKey;
    private static String appSecretKey;
    private static String appId;
    private static String appSecret;

    @Value("${app.id-key}")
    public void setAppIdKey(String appIdKey) {
        BasicContoller.appIdKey = appIdKey;
    }

    @Value("${app.secret-key}")
    public void setAppSecretKey(String appSecretKey) {
        BasicContoller.appSecretKey = appSecretKey;
    }

    @Value("${app.id}")
    public void setAppId(String appId) {
        BasicContoller.appId = appId;
    }

    @Value("${app.secret}")
    public void setAppSecret(String appSecret) {
        BasicContoller.appSecret = appSecret;
    }

    @ModelAttribute
    public void gainAppStuff(HttpServletRequest request) {
        String headerAppId = request.getHeader(ConstantsConfig.APP_ID_KEY);
        String headerAppSecret = request.getHeader(ConstantsConfig.APP_SECRET_KEY);
        if (Strings.isNullOrEmpty(headerAppId) || Strings.isNullOrEmpty(headerAppSecret)) {
            logger.error(Strings.lenientFormat("【BASIC-CONTROLLER】：%s", ErrorCodeMsg.APP_ID_SECRET_NULL_ERROR));
            throw new AttemptException(ErrorCodeMsg.FORBIDDEN_REQUEST_ERROR);
        }
        String deAppId = Base64Util.decrypt(headerAppId, appIdKey);
        String deAppSecret = Base64Util.decrypt(headerAppSecret, appSecretKey);
        if (!appId.equals(deAppId) || !appSecret.equals(deAppSecret)) {
            logger.error(Strings.lenientFormat("【BASIC-CONTROLLER】：%s", ErrorCodeMsg.APP_ID_SECRET_ERROR));
            throw new AttemptException(ErrorCodeMsg.FORBIDDEN_REQUEST_ERROR);
        }
    }

}
