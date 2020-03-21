<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO" %>
<%@ page import="user.UserDAO" %>
<%@ page import="util.SHA256" %>
<%@ page import="java.io.PrintWriter" %> <!-- 특정 스크립트 구문 출력 -->
<%
 	request.setCharacterEncoding("UTF-8"); //user로부터 입력받은 정보 처리 방식
	String userID = null;
	String userPassword = null;
	String userEmail = null;
	if(request.getParameter("userID")!=null) {
		userID = (String)request.getParameter("userID");
		}
	if(request.getParameter("userPassword")!=null) {
		userPassword = (String)request.getParameter("userPassword");
		}
	if(request.getParameter("userEmail")!=null) {
		userEmail = (String)request.getParameter("userEmail");
		}
	if(userID == null || userPassword == null || userEmail == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		}
	else {
		UserDAO userDAO = new UserDAO(); //회원가입 진행하기
		int result = userDAO.join(new UserDTO(userID, userPassword, userEmail, SHA256.getSHA256(userEmail), false));
	
		if(result == -1) { //회원가입이 되지 않았을 때
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 존재하는 아이디입니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		else { 
			session.setAttribute("userID", userID);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'emailSendAction.jsp';");//회원가입시 바로 이메일 인증하도록.
			script.println("</script>");
			script.close();
		}
	}
%>
	 <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="index.jsp">강의평가 웹 사이트</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbar">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="index.jsp">메인</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="dropdown" data-toggle="dropdown">
              회원 관리
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdown">
              <a class="dropdown-item" href="userLogin.jsp">로그인</a>
              <a class="dropdown-item" href="userJoin.jsp">회원가입</a>
              <a class="dropdown-item" href="userLogout.jsp">로그아웃</a>
            </div>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="search" placeholder="내용을 입력하세요." aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
        </form>
      </div>
    </nav>
	-->