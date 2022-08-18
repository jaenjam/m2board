package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;
import service.MemberService;
import vo.Member;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	
	// login form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginController - form");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") != null) { // 로그인 되어 있는 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		rd.forward(request, response);
	}
	
	
	// login action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginController - action");
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") != null) { // 로그인 되어 있는 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		Member paramMember = new Member();
		
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		//login 입력한 memberId, memberPw 받아오기
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		
		
		System.out.println(paramMember.getMemberId());
		System.out.println(paramMember.getMemberPw());
		
		MemberService memberService = new MemberService();
		// new
		Member member = memberService.getMemberLogin(paramMember);
		
		if(member == null) {
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath() + "/loginController");
			return;
		}
		
		//session에 값넣어주기
		session.setAttribute("id", memberId);
		session.setAttribute("name", member.getMemberName());
		
		//로그인 성공시
		System.out.println("로그인 성공");
		session.setAttribute("loginMember", member);
		System.out.println(member + " <-- LoginController member");
		
		//인덱스로 이동
		request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
	}
}