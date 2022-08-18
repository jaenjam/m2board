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
import vo.Member;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {
   private IMemberService memberService;
   // login form
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      if(session.getAttribute("loginMember") != null) { // 로그인 되어 있는 상태
         response.sendRedirect(request.getContextPath() + "/index");
         return;
      }
      RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
   }
   
   
   
   
   // login action
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      if(session.getAttribute("loginMember") != null) { // 로그인 되어 있는 상태
         response.sendRedirect(request.getContextPath() + "/index");
         return;
      }
      
		/*
		select board_no, title
		from board b
		inner join
		(select board_no, count(*) cnt
		from 좋아요
		group by board_no)t
		on b.board_no = t.board_no
		
		*/
      
      request.setCharacterEncoding("utf-8");
      String id = request.getParameter("id");
      String pw = request.getParameter("pw");
      Member paramMember = new Member();
      // new
      Member member = memberService.getMemberLogin(paramMember);
      if(member == null) {
         System.out.println("로그인 실패");
         response.sendRedirect(request.getContextPath() + "/loginController");
         return;
      }
      
      
      session.setAttribute("loginMember", member);
      response.sendRedirect(request.getContextPath() + "/index");
   }
}