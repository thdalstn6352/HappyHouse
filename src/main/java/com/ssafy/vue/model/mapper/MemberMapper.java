package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public boolean join(MemberDto memberDto) throws SQLException;
	public boolean userDelete(String userid) throws SQLException;
	public boolean userModify(MemberDto memberDto) throws SQLException;
	public List<MemberDto> userList() throws SQLException;
	
}
