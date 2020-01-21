package com.guangl.fdfs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.guangl.fdfs.constants.ConstantsConfig;
import com.guangl.fdfs.controller.AbstractController.FastDFSController;
import com.guangl.fdfs.enums.ErrorCodeMsg;
import com.guangl.fdfs.exception.AttemptException;
import com.guangl.fdfs.pojo.RequestUser;
import com.guangl.fdfs.pojo.ResponseStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @ClassName: HelloController
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: testController
 * @Date: Created in 2020-01-16 15:22
 * @Version: 1.0.0
 * @param: * @param null
 */
@RestController
@RequestMapping(value = ConstantsConfig.APPLICATION_NAME + "/hello")
public class HelloController extends FastDFSController {

    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @PostMapping("/doHello")
    public ResponseStruct doHello(@RequestBody String testParams, @ModelAttribute RequestUser requestUser) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            logger.info(Strings.lenientFormat("BEHAVIOR:【HELLO-DO-HELLO】：用户：%s *{%s}*", String.valueOf(requestUser.getGuid()), objectMapper.writeValueAsString(testParams)));
        } catch (IOException e) {
            logger.error(Strings.lenientFormat("【HELLO-DO-HELLO】：ERROR: %s", e.getMessage()));
            throw new AttemptException(ErrorCodeMsg.JSON_DECODE_ERROR);
        }
        return ResponseStruct.success(testParams);
    }

}
