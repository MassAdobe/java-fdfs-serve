package com.guangl.fdfs.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.Strings;
import com.guangl.fdfs.constants.ConstantsConfig;
import com.guangl.fdfs.enums.ErrorCodeMsg;
import com.guangl.fdfs.exception.AttemptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: JwtUtils
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2020-01-08 20:48
 * @Version: 1.0.0
 * @param: * @param null
 */
@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static String fastDfsTokenKey;
    private static String fastDfsTokenSecret;
    private static String encryptJWTKey;
    private static String verifySecret;

    @Value("${fastdfs.token.key}")
    public void setFastDfsTokenKey(String fastDfsTokenKey) {
        JwtUtils.fastDfsTokenKey = fastDfsTokenKey;
    }

    @Value("${fastdfs.token.secret}")
    public void setFastDfsTokenSecret(String fastDfsTokenSecret) {
        JwtUtils.fastDfsTokenSecret = fastDfsTokenSecret;
    }

    @Value("${jwt.config.encrypt.jwt-key}")
    public void setEncryptJWTKey(String encryptJWTKey) {
        JwtUtils.encryptJWTKey = encryptJWTKey;
    }

    @Value("${jwt.config.verify.secert}")
    public void setVerifySecret(String verifySecret) {
        JwtUtils.verifySecret = verifySecret;
    }

    public static void main(String[] args) {
        String id = "guang2020lian";
        String secret = "iefa*&Y^873hfcd";
        String idKey = "idjekdi873kj1ad198cid28yee21jd87";
        String secretKey = "ifjekd7309kj1ad198cid28abb21jd65";
        String enId = Base64Util.encrypt(id, idKey);
        String enSecret = Base64Util.encrypt(secret, secretKey);
        String deId = Base64Util.decrypt(enId, idKey);
        String deSecret = Base64Util.decrypt(enSecret, secretKey);
        System.out.println("ID加密结果：" + enId);
        System.out.println("Secret加密结果：" + enSecret);
        System.out.println("ID解密结果：" + deId);
        System.out.println("Secret解密结果：" + deSecret);
    }

    /**
     * 生成fastDFS的Token
     */
    public static String geneToken(String randomStr) {
        try {
            String secret = fastDfsTokenSecret + Base64Util.decodeThrowsException(fastDfsTokenKey);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withClaim(ConstantsConfig.TOKEN_VERIFY_KEY, fastDfsTokenSecret).withClaim(ConstantsConfig.TOKEN_RANDOM_STRING, randomStr).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            logger.error(Strings.lenientFormat("【JWT-UTILS-GENE-TOKEN】：生成JWTTOKEN失败！%s", e.getMessage()));
            throw new AttemptException(ErrorCodeMsg.TOKEN_UNSUPPORT_ENCODE_ERROR);
        }
    }

    /**
     * 校验token是否正确
     */
    public static boolean verify(String token, String key) {
        try {
            // 帐号加JWT私钥解密
            String aSecret = getClaim(token, ConstantsConfig.TOKEN_VERIFY_KEY, 2) + Base64Util.decodeThrowsException(key);
            Algorithm algorithm = Algorithm.HMAC256(aSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            logger.error(Strings.lenientFormat("【JWT-UTILS-VERIFY】：JWTToken认证解密出现UnsupportedEncodingException异常！%s", e.getMessage()));
            throw new AttemptException(ErrorCodeMsg.TOKEN_UNSUPPORT_ENCODE_ERROR);
        }
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     * 1.int;2.string;3.long;4.array;5.boolean;6.date;7.double;8.list;9.map
     */
    public static Object getClaim(String token, String claim, int type) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            switch (type) {
                case 1:
                    return jwt.getClaim(claim).asInt();
                case 2:
                    return jwt.getClaim(claim).asString();
                case 3:
                    return jwt.getClaim(claim).asLong();
                case 4:
                    return jwt.getClaim(claim).asArray(Object.class);
                case 5:
                    return jwt.getClaim(claim).asBoolean();
                case 6:
                    return jwt.getClaim(claim).asDate();
                case 7:
                    return jwt.getClaim(claim).asDouble();
                case 8:
                    return jwt.getClaim(claim).asList(Object.class);
                case 9:
                    return jwt.getClaim(claim).asMap();
            }
        } catch (JWTDecodeException e) {
            logger.error(Strings.lenientFormat("【JWT-UTILS-getClaim】：解密Token中的公共信息出现JWTDecodeException异常！%s", e.getMessage()));
        }
        throw new AttemptException(ErrorCodeMsg.TOKEN_DECODE_ERROR);
    }

    public static String getFastDfsTokenKey() {
        return fastDfsTokenKey;
    }

    public static String getFastDfsTokenSecret() {
        return fastDfsTokenSecret;
    }

    public static String getEncryptJWTKey() {
        return encryptJWTKey;
    }

    public static String getVerifySecret() {
        return verifySecret;
    }
}
