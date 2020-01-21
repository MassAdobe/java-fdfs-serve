package com.guangl.fdfs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.guangl.fdfs.client.FastDFSClient;
import com.guangl.fdfs.client.FastDFSException;
import com.guangl.fdfs.constants.ConstantsConfig;
import com.guangl.fdfs.controller.AbstractController.FastDFSController;
import com.guangl.fdfs.enums.ErrorCodeMsg;
import com.guangl.fdfs.exception.AttemptException;
import com.guangl.fdfs.pojo.RequestUser;
import com.guangl.fdfs.pojo.ResponseStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @ClassName: DeleteController
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: 删除controller
 * @Date: Created in 2020-01-14 19:24
 * @Version: 1.0.0
 * @param: * @param null
 */
@Controller
@RequestMapping(value = ConstantsConfig.APPLICATION_NAME + "/delete")
public class DeleteController extends FastDFSController {

    private final static Logger logger = LoggerFactory.getLogger(DeleteController.class);

    private final static FastDFSClient fastDFSClient = new FastDFSClient();

    @PostMapping("/doDelete")
    public ResponseStruct doDelete(String filePath, @ModelAttribute RequestUser requestUser) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            logger.info(Strings.lenientFormat("BEHAVIOR:【DELETE-DO-DELETE】：用户：%s *{%s}*", String.valueOf(requestUser.getGuid()), objectMapper.writeValueAsString(filePath)));
            fastDFSClient.deleteFile(filePath);
        } catch (FastDFSException e) {
            logger.error(Strings.lenientFormat("【DELETE-DO-DELETE】：%s; code: %d; message: %s", ErrorCodeMsg.UPLOAD_ERROR, e.getCode(), e.getMessage()));
            throw new AttemptException(ErrorCodeMsg.UPLOAD_ERROR, "code:" + e.getCode() + ";message:" + e.getMessage());
        } catch (IOException e) {
            logger.error(Strings.lenientFormat("【DELETE-DO-DELETE】：ERROR: %s", e.getMessage()));
            throw new AttemptException(ErrorCodeMsg.JSON_DECODE_ERROR);
        }
        return ResponseStruct.success();
    }

}
