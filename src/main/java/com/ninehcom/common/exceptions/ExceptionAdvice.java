/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.common.exceptions;

import com.ninehcom.common.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理
 *
 * @author Administrator
 */
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result processUnauthenticatedException(
            HttpServletRequest request, 
            Throwable ex, 
            HttpServletResponse response) {
        ex.printStackTrace();
        return Result.Fail(Result.UnknownCode, "未知错误", ex);
    }
}
