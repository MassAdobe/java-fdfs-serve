package com.guangl.fdfs.controller;

import com.google.common.base.Strings;
import com.guangl.fdfs.constants.ConstantsConfig;
import com.guangl.fdfs.controller.AbstractController.FastDFSController;
import com.guangl.fdfs.enums.ErrorCodeMsg;
import com.guangl.fdfs.exception.AttemptException;
import com.guangl.fdfs.pojo.RequestUser;
import com.guangl.fdfs.pojo.ResponseStruct;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName: DownloadController
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: 下载controller，暂时没有用
 * @Date: Created in 2020-01-14 19:24
 * @Version: 1.0.0
 * @param: * @param null
 */
@Controller
@RequestMapping(value = ConstantsConfig.APPLICATION_NAME + "/download")
public class DownloadController extends FastDFSController {

    private final static Logger logger = LoggerFactory.getLogger(DownloadController.class);

    @RequestMapping(value = "/doDownload")
    public ResponseStruct doDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute RequestUser requestUser) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            File file = new File("G:\\config.ini");
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            logger.error(Strings.lenientFormat("【DOWNLOAD-CONTROLLER-DO-DOWNLOAD】:%s", ErrorCodeMsg.FILE_NOT_EXIST_ERROR));
            throw new AttemptException(ErrorCodeMsg.FILE_NOT_EXIST_ERROR);
        } catch (IOException e) {
            logger.error(Strings.lenientFormat("【DOWNLOAD-CONTROLLER-DO-DOWNLOAD】:%s", e.getMessage()));
            throw new AttemptException(ErrorCodeMsg.SERVER_ERROR);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error(Strings.lenientFormat("【DOWNLOAD-CONTROLLER-DO-DOWNLOAD】:%s", e.getMessage()));
                    throw new AttemptException(ErrorCodeMsg.SERVER_ERROR);
                }
            }
        }
        return ResponseStruct.success();
    }

}
