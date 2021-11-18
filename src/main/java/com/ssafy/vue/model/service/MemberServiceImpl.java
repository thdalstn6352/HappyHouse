package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public boolean join(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).join(memberDto);
	}

	@Override
	public boolean userDelete(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userDelete(userid);
	}

	@Override
	public boolean userModify(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userModify(memberDto);
	}

	@Override
	public List<MemberDto> userList() throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userList();
	}
}
