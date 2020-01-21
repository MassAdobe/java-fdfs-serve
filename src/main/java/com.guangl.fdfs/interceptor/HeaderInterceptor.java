package com.guangl.fdfs.interceptor;

import com.google.common.base.Strings;
import com.guangl.fdfs.constants.ConstantsConfig;
import com.guangl.fdfs.enums.ErrorCodeMsg;
import com.guangl.fdfs.exception.AttemptException;
import com.guangl.fdfs.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: HeaderInterceptor
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: 过滤器
 * @Date: Created in 2020-01-14 19:16
 * @Version: 1.0.0
 * @param: * @param null
 */
public class HeaderInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(HeaderInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 除了token的api，其他都要校验token的问题
        String fdfsToken = request.getHeader(ConstantsConfig.HEADER_FASTDFS_TOKEN_KEY);
        String sessionToken = request.getHeader(ConstantsConfig.ACCESS_TOKEN_KEY);
        if (Strings.isNullOrEmpty(fdfsToken) || Strings.isNullOrEmpty(sessionToken)) {
            logger.error(Strings.lenientFormat("【HEADER-INTERCEPTOR】：SEESION OR FDFS-TOKEN %s", ErrorCodeMsg.DO_TOKEN_EMPTY_ERROR));
            throw new AttemptException(ErrorCodeMsg.FORBIDDEN_REQUEST_ERROR);
        }
        if (!JwtUtils.verify(sessionToken, JwtUtils.getEncryptJWTKey())) {// 如果不正确(SESSION)
            logger.error(Strings.lenientFormat("【HEADER-INTERCEPTOR】：SEESION %s", ErrorCodeMsg.SESSION_TOKEN_ERROR));
            throw new AttemptException(ErrorCodeMsg.FORBIDDEN_REQUEST_ERROR);
        }
        if (!JwtUtils.verify(fdfsToken, JwtUtils.getFastDfsTokenKey())) {// 如果不正确(FDFS-TOKEN)
            logger.error(Strings.lenientFormat("【HEADER-INTERCEPTOR】：FDFS-TOKEN %s", ErrorCodeMsg.SESSION_TOKEN_ERROR));
            throw new AttemptException(ErrorCodeMsg.FORBIDDEN_REQUEST_ERROR);
        }
        return super.preHandle(request, response, handler);
    }

}
