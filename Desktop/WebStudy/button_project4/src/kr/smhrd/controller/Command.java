package kr.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	// 객체를 생성해서 메소드 만드는 과정을 단순화 하기 위해 interface 생성
	public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}
