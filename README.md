<img src="https://capsule-render.vercel.app/api?type=transparent&color=778899&fontColor=FFFAFA&height=300&section=header&text=Button%Project&fontSize=90" />


<div align=center>버튼을 누를 때 인식되는 센서 값을 이용해 독거노인을<br> <b>모니터링 할 수 있는 웹 페이지</b>를 제작한 프로젝트 입니다.<br><br><br>
</div>

- 저희 팀이 제작한 스마트 버튼 영웅이는 노인의 위험 상황을 문자, 메일로 전송하는 기능과 <br>사용자의 건강 정보 등을 음성
으로 질문하고 버튼으로 응답받아 데이터를 수집하는 기능이 있는 IoT 기반 스마트 버튼입니다.
- 버튼을 사용하는 사용자인 노인이 버튼을 통해 위험상황을 전달하거나 설문에 응답을 하면<br> **보호자는 그 정보를 웹에서 모니터링 할 수 있습니다.**
- 또한 버튼을 소지한 노인의 보호자가 본인이 모니터링 하기 원하는 노인의 정보를 입력하며 <br> **관리 할 수 있는 대시보드 형태의 홈페이지를 제공**합니다.
- 프로젝트는 실제로 주 대상 고객인 노인의 일상 경험에 녹아들 수 있는 편리하고 직관적인 제품 사용법 및 서비스를 개발하여<br> 새로운 기기도 어렵지 않다는 긍정적인 경험
을 창출하며 최종적으로 저희 서비스에 대한 신뢰를 확보하는데 목표를 두고 있습니다.



## Description
- 개발 기간 :  2022.05.30 ~ 2022.06.20(약 3주 반)
- 참여 인원 : 6명(iot&통신 2명 front-end 2명 back-end 2명)
- 개발 목표 : Servlet과 JSP의 개발, 테스트 환경 구축방법을 파악하고 Servlet과 JSP를 활용한 Web 서버 응용 프로그램 개발 경험을 쌓는다.
- 사용 기술
  - JSP, Apache Tomcat 9.0, Mybatis, BootStrap 
  - Java, Ajax, Jquery, Git, MVC Pattern
- 담당 구현 파트
  - 테이블 설계, 구현 및 명세서 작성
  - crud 기반 회원가입, 로그인 기능 구현
  - crud 기반 노인 정보 등록 및 삭제, 조회 기능 구현
  - 응급 호출 리스트 호출 게시판 구현 (iot 기기에서 받아온 버튼 센서 값 호출)
  - ppt 제작 및 발표
 
 
📌 Languages  <br><br>
<img src="https://img.shields.io/badge/JAVA-007396?style=flat&logo=Java&logoColor=white"/>
<img src="https://img.shields.io/badge/JS-F7DF1E?style=flat&logo=JavaScript&logoColor=white"/>
<img src="https://img.shields.io/badge/Html-E34F26?style=flat&logo=Html5&logoColor=white"/>
<img src="https://img.shields.io/badge/Css-1572B6?style=flat&logo=CSS3&logoColor=white"/>  

📌 Tools <br><br>
<img src="https://img.shields.io/badge/eclipse-2C2255?style=flat&logo=Eclipse IDE&logoColor=white"/>
<img src="https://img.shields.io/badge/Github-181717?style=flat&logo=GitHub&logoColor=white"/>
<img src="https://img.shields.io/badge/Oracle-F80000?style=flat&logo=Oracle&logoColor=white"/>
<img src="https://img.shields.io/badge/Maven-c71A36?style=flat&logo=Apache Maven&logoColor=white"/>
<img src="https://img.shields.io/badge/Tomcat-F8DC75?style=flat&logo=Apache Tomcat&logoColor=white"/>




## Planning

유스케이스, 명세서 등등 기획단계에서 설계한 내용 보여주기



## Views

- iot 버튼으로 받은 센서의 값을 웹에서 모니터링을 할 수 있게 하는 기능이므로 한눈에 쉽게 여러 정보를 모니터링할 수 있도록 대시보드 형태의 메인 페이지를 채택




## Implementation
- Comaand Pattern 과 FrontController를 이용하여 클라이언트의 다양한 요청들을 한곳으로 집중시켜서 개발 및 유지보수에 효율성을 극대화

```
package kr.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}

```

```
package kr.smhrd.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.smhrd.controller.Command;
import kr.smhrd.controller.DeleteSeniorService;
import kr.smhrd.controller.DeleteService;
import kr.smhrd.controller.InputSeniorService;
import kr.smhrd.controller.JoinService;
import kr.smhrd.controller.LoginService;
import kr.smhrd.controller.LogoutService;
import kr.smhrd.controller.SelectAllSeniorService;
import kr.smhrd.controller.UpdateEmergencyActionService;
import kr.smhrd.controller.UpdateSeniorService;
import kr.smhrd.controller.UpdateService;

@WebServlet("*.do")
public class ButtonFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String project = request.getContextPath();
		String reqURL = uri.substring(project.length()+1);
	
		request.setCharacterEncoding("UTF-8");
		
		String moveURL = "";
		Command cm = null;
		
		if(reqURL.equals("JoinService.do")) {
			// 1. 회원가입 기능
			cm = new JoinService();
		}else if (reqURL.equals("LoginService.do")) {
			// 2. 로그인 기능
			cm = new LoginService();
		}else if (reqURL.equals("LogoutService.do")) {
			// 3. 로그아웃 기능
			cm = new LogoutService();
		}else if (reqURL.equals("DeleteService.do")) {
			// 4. 회원 삭제 기능
			cm = new DeleteService();
		}else if (reqURL.equals("UpdateService.do")) {
			// 5. 회원 정보 수정
			cm = new UpdateService();
		}else if (reqURL.equals("InputSeniorService.do")) {
			/// 6. 노인 정보 기입 기능
			cm = new InputSeniorService();
		}else if (reqURL.equals("DeleteSeniorService.do")) {
			// 7. 노인 정보 삭제 기능
			cm = new DeleteSeniorService();
		}else if (reqURL.equals("UpdateSeniorService.do")) {
			// 8. 노인 정보 수정 기능
			cm = new UpdateSeniorService();
		}else if(reqURL.equals("UpdateEmergencyActionService.do")) {
			// 9. 조치사항, 조시 시간 업데이트
			cm = new UpdateEmergencyActionService();
		}
		
		moveURL = cm.execute(request, response);
		RequestDispatcher rd = request.getRequestDispatcher(moveURL);
		rd.forward(request, response);

	}

}

```

- 로그인 페이지




- 회원가입 페이지




- 응급 호출 페이지

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="kr.smhrd.model.EmergencyDAO">

	<!-- 조치 사항 변경 -->
	<update id="emergencyactionupdate" parameterType="EmergencyVO">
		update emergency 
		set emergency_action = #{emergency_action}, emergency_action_date = sysdate
		where emergency_pk = #{emergency_pk}
	</update>

	<!-- 응급 호출 게시판에 넣을 데이터 불러오기 -->
	<select id="emergencylist" resultType="EmergencySenVO">
		select s.senior_name, e.emergency_pk, e.emergency_date, e.emergency_action, TO_CHAR(e.emergency_action_date + 9/24,'YYYY-MM-DD HH24:MI:SS') as emergency_action_date, m.member_id
		from senior s, emergency e, member_senior m 
		where s.senior_num
			in (select b.senior_num from button_senior b where b.button_id
			in (select e.button_id from emergency))
			and m.member_id= #{id}
	</select>

	<!-- 응급 정보 추가 -->
	<insert id="emergencyinsert" parameterType="EmergencyVO">
		insert into emergency
		values (emergency_seq.nextval, #{emergency_check}, sysdate, null, null, #{button_id})
	</insert>

	<!-- 응급 메일 : 응급 호출 시간 추출 -->
	<select id="emergencydateselect" parameterType="int"
		resultType="String">
		select emergency_date 
		from(select emergency_date 
			from emergency 
			where button_id = #{button_id} order by emergency_date desc)
		where rownum=1
	</select>

</mapper>
```

## 발전방향
- JSP의 내장객체인 session을 활용한 로그인 유지 기능 보완하기


 
