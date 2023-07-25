package com.study.gulimall.member.feign;

import com.study.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: name
 * @Date: 2023-07-26-1:37
 * @Description:
 */
@FeignClient("coupon") //告诉spring cloud这个接口是一个远程客户端，要调用coupon服务，再去调用coupon服务/coupon/coupon/member/list对应的方法
public interface CouponFeignService {
    @RequestMapping("/coupon/coupon/member/list")
    public R membercoupons();//得到一个R对象
}
