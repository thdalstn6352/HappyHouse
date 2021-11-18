package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.AnswerDto;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.QnaDto;
import com.ssafy.vue.model.QnaParameterDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean writeQna(QnaDto qnaDto) throws Exception {
		if(qnaDto.getSubject() == null || qnaDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnaMapper.class).writeQna(qnaDto);
	}

	@Override
	public List<QnaDto> list(QnaParameterDto qnaParameterDto) throws Exception {
		int start = qnaParameterDto.getPg() == 0 ? 0 : (qnaParameterDto.getPg() - 1) * qnaParameterDto.getSpp();
		qnaParameterDto.setStart(start);
		return sqlSession.getMapper(QnaMapper.class).list(qnaParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(QnaParameterDto qnaParameterDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QnaDto getQna(int qnano) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).getQna(qnano);
	}

	@Override
	public void updateAnswered(int qnano) throws Exception {
		sqlSession.getMapper(QnaMapper.class).updateAnswered(qnano);
	}

	@Override
	public boolean modifyQna(QnaDto qnaDto) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).modifyQna(qnaDto);
	}

	@Override
	public boolean deleteQna(int qnano) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).deleteQna(qnano);
	}

	@Override
	public List<AnswerDto> listAnswer(int qnano) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).listAnswer(qnano);
	}
	
	@Override
	public boolean writeAnswer(AnswerDto answerDto) throws Exception {
		if(answerDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnaMapper.class).writeAnswer(answerDto);
	}

	@Override
	public boolean modifyAnswer(AnswerDto answerDto) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).modifyAnswer(answerDto);
	}

	@Override
	public boolean deleteAnswer(int answerno) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).deleteAnswer(answerno);
	}

	@Override
	public AnswerDto getAnswer(int answerno) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).getAnswer(answerno);
	}
	

}
