package com.guli.ucenter.mapper;

import com.guli.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author guli
 * @since 2021-05-18
 */
public interface MemberMapper extends BaseMapper<Member> {
    public Integer countRegisterNum(String day);

}
