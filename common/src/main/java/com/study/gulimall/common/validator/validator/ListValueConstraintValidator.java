package com.study.gulimall.common.validator.validator;

import com.study.gulimall.common.validator.annotation.ListValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: name
 * @Date: 2023-08-03-4:22
 * @Description:
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private HashSet<Integer> set = new HashSet<>();

    /**
     * 初始化方法
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        Arrays.stream(constraintAnnotation.value()).forEach(value -> set.add(value));
    }

    /**
     * 判断是否校验成功
     *
     * @param integer                    需要校验的值
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(integer);
    }
}
