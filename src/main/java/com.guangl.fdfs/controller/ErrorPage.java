package com.guangl.fdfs.controller;

import com.guangl.fdfs.constants.CmnConstants;
import com.guangl.fdfs.controller.AbstractController.BasicContoller;
import com.guangl.fdfs.enums.ErrorCodeMsg;
import com.guangl.fdfs.pojo.ResponseStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ErrorPage
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: 页面错误处理
 * @Date: Created in 2019-12-19 17:41
 * @Version: 1.0.0
 * @param: * @param null
 */
@RestController
public class ErrorPage extends BasicContoller implements ErrorController {

    private final static Logger logger = LoggerFactory.getLogger(ErrorPage.class);

    @RequestMapping("/error")
    public ResponseStruct ReturnErrorInfo() {
        return ResponseStruct.failure(ErrorCodeMsg.PAGE_OR_API_ERROR.getCode(), ErrorCodeMsg.PAGE_OR_API_ERROR.getMessage(), CmnConstants.EMPTY);
    }


    @Override
    public String getErrorPath() {
        return null;
    }
}
