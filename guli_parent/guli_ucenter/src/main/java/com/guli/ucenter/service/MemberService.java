package com.guli.ucenter.service;

import com.guli.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author guli
 * @since 2021-05-18
 */
public interface MemberService extends IService<Member> {
    public Integer registerCount(String day);
}
