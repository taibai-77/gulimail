package com.study.gulimall.member.dao;

import com.study.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author Knight
 * @email 20tzzhang@stu.edu.cn
 * @date 2023-07-20 16:13:24
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
