package com.study.gulimall.product.exception;

import com.study.gulimall.common.exception.ErrorCodeEnum;
import com.study.gulimall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: name
 * @Date: 2023-08-03-3:17
 * @Description: 集中处理所有异常
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.study.gulimall.product.controller")
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题：{}，异常类型：{}", e.getMessage(), e.getClass());

        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach(item -> map.put(item.getField(), item.getDefaultMessage()));

        return R.error(ErrorCodeEnum.VALID_EXCEPTION.getCode(), ErrorCodeEnum.VALID_EXCEPTION.getMsg()).put("data", map);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        log.error("出现异常：{}，异常类型：{}", throwable.getMessage(), throwable.getClass());

        return R.error(ErrorCodeEnum.UNKNOW_EXCEPTION.getCode(), ErrorCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }
}
