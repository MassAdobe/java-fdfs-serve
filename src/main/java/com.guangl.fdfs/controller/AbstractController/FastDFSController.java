package com.guangl.fdfs.controller.AbstractController;

import com.google.common.base.Strings;
import com.guangl.fdfs.constants.ConstantsConfig;
import com.guangl.fdfs.enums.ErrorCodeMsg;
import com.guangl.fdfs.exception.AttemptException;
import com.guangl.fdfs.pojo.RequestUser;
import com.guangl.fdfs.utils.JwtUtils;
import com.guangl.fdfs.utils.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: FastDFSController
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: /fdfs-oss-sys
 * @Date: Created in 2020-01-16 10:48
 * @Version: 1.0.0
 * @param: * @param null
 */
@RestController
@RequestMapping(ConstantsConfig.APPLICATION_NAME)
public class FastDFSController {

    private final static Logger logger = LoggerFactory.getLogger(FastDFSController.class);

    @Autowired
    private RedisClient redisClient;

    /**
     * @ClassName: FastDFSController
     * @Author: MassAdobe
     * @Email: massadobe8@gmail.com
     * @Description: 校验此次请求的token是否正确，同时记录相关用户的操作
     * @Date: Created in 2020-01-16 10:54
     * @Version: 1.0.0
     * @param: * @param null
     */
    @ModelAttribute
    public RequestUser gainTokenSign(HttpServletRequest request) {
        String token = request.getHeader(ConstantsConfig.HEADER_FASTDFS_TOKEN_KEY);
        String claim = JwtUtils.getClaim(token, ConstantsConfig.TOKEN_RANDOM_STRING, 2).toString();
        if (this.redisClient.hasKey(claim)) {
            this.redisClient.del(claim);
            String sessionToken = request.getHeader(ConstantsConfig.ACCESS_TOKEN_KEY);
            Long userId = (Long) JwtUtils.getClaim(sessionToken, ConstantsConfig.TOKEN_USER_KEY, 3);
            Long sysId = (Long) JwtUtils.getClaim(sessionToken, ConstantsConfig.TOKEN_OSS_KEY, 3);
            return new RequestUser(userId, sysId);
        }
        logger.error(Strings.lenientFormat("【FASTDFS-CONTROLLER】：%s", ErrorCodeMsg.TOKEN_TIMEOUT_ERROR));
        throw new AttemptException(ErrorCodeMsg.TOKEN_TIMEOUT_ERROR);
    }

}
