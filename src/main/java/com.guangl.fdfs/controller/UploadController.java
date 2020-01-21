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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UploadController
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: 上传controllerf
 * @Date: Created in 2020-01-14 19:24
 * @Version: 1.0.0
 * @param: * @param null
 */
@Controller
@RequestMapping(value = ConstantsConfig.APPLICATION_NAME + "/upload")
public class UploadController extends FastDFSController {

    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

    private final static FastDFSClient fastDFSClient = new FastDFSClient();

    /**
     * 文件服务器地址
     */
    @Value("${file_server_addr}")
    private String fileServerAddr;

    @PostMapping("/doUpload")
    public ResponseStruct doUpload(@RequestParam("file") MultipartFile file, @ModelAttribute RequestUser requestUser) {
        if (file.isEmpty()) {
            logger.error(Strings.lenientFormat("【UPLOAD-CONTROLLER-DO-UPLOAD】：%s", ErrorCodeMsg.FILE_EMPTY_ERROR));
            throw new AttemptException(ErrorCodeMsg.FILE_EMPTY_ERROR);
        }
        // FastDFS存储
        Map data = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            logger.info(Strings.lenientFormat("BEHAVIOR:【UPLOAD-DO-UPLOAD】：用户：%s *{%s}*", String.valueOf(requestUser.getGuid()), objectMapper.writeValueAsString(file.getName())));
            String filePath = fastDFSClient.uploadFileWithMultipart(file);
            String fileName = file.getOriginalFilename();
            String fileType = FastDFSClient.getFilenameSuffix(file.getOriginalFilename());
            String httpUrl = fileServerAddr + "/" + filePath;
            data = new HashMap<>();
            data.put("filePath", filePath);
            data.put("fileName", fileName);
            data.put("fileType", fileType);
            data.put("httpUrl", httpUrl);
        } catch (FastDFSException e) {
            logger.error(Strings.lenientFormat("【UPLOAD-DO-UPLOAD】：%s; code: %d; message: %s", ErrorCodeMsg.UPLOAD_ERROR, e.getCode(), e.getMessage()));
            throw new AttemptException(ErrorCodeMsg.UPLOAD_ERROR, "code:" + e.getCode() + ";message:" + e.getMessage());
        } catch (IOException e) {
            logger.error(Strings.lenientFormat("【UPLOAD-DO-UPLOAD】：ERROR: %s", e.getMessage()));
            throw new AttemptException(ErrorCodeMsg.JSON_DECODE_ERROR);
        }
        return ResponseStruct.success(data);
    }
}
