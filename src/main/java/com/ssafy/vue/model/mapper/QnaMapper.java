package com.ssafy.vue.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.AnswerDto;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.QnaDto;
import com.ssafy.vue.model.QnaParameterDto;

@Mapper
public interface QnaMapper {

	public boolean writeQna(QnaDto qnaDto) throws Exception;
	public List<QnaDto> list(QnaParameterDto qnaParameterDto) throws Exception;
	public PageNavigation makePageNavigation(QnaParameterDto qnaParameterDto) throws Exception;
	public QnaDto getQna(int qnano) throws Exception;
	public void answeredTrue(int qnano) throws Exception;
	public void answeredFalse(int qnano) throws Exception;
	public boolean modifyQna(QnaDto qnaDto) throws Exception;
	public boolean deleteQna(int qnano) throws Exception;
	
	public List<AnswerDto> listAnswer(int qnano) throws Exception;
	public AnswerDto getAnswer(int answerno) throws Exception;
	public boolean writeAnswer(AnswerDto answerDto) throws Exception;
	public boolean modifyAnswer(AnswerDto answerDto) throws Exception;
	public boolean deleteAnswer(int answerno) throws Exception;
}
