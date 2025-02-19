package com.gelin.exception;

import com.gelin.domain.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo handleException(){
        ResultVo resultVo=new ResultVo();
        resultVo.setMessage("系统异常");
        return resultVo;
    }

}
