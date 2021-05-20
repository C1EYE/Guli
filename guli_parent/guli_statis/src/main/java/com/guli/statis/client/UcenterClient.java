package com.guli.statis.client;

import com.guli.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author c1eye
 */
@Component
@FeignClient("guli-ucenter")
public interface UcenterClient {

    @GetMapping("/ucenter/member/countRegister/{day}")
    Result countRegisterNum(@PathVariable("day") String day);

}
