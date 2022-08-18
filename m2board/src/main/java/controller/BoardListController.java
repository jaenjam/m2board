package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;


@WebServlet("/boardList")
public class BoardListController extends HttpServlet {
	//정보은닉을위한 private
	private IBoardService boardService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//컨트롤러
		//1) 요청 받아 분석
		int rowPerPage = 10;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		boardService = new BoardService();
		//2) 서비스 레이어를 요청(매서드 호출) -> 모델값(자료구조) 구하기 위함
		//new
		Map<String, Object> map = boardService.getBoardList(rowPerPage, currentPage);
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("list", map.get("list"));
		request.setAttribute("currentPge", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		
		//3)view가 있다면 view 포워딩
		request.getRequestDispatcher("/WEB-INF/view/boardList.jsp").forward(request, response);
	}
}
