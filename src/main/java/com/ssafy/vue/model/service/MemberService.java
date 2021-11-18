package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean join(MemberDto memberDto) throws Exception;
	
	public boolean userDelete(String userid) throws Exception;
	public boolean userModify(MemberDto memberDto) throws Exception;
	public List<MemberDto> userList() throws Exception;
}
