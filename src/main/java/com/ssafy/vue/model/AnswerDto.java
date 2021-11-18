package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AnswerDto : 게시글정보", description = "QnA 답변의 상세 정보를 나타낸다.")
public class AnswerDto {
	@ApiModelProperty(value = "qna번호")
	private int qnano;
	@ApiModelProperty(value = "작성자 아이디")
	private String userid;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	
	public int getQnano() {
		return qnano;
	}
	public void setQnano(int qnano) {
		this.qnano = qnano;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	
}
