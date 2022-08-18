package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;
import vo.Board;

@WebServlet("/boardOne")
public class BoardOneController extends HttpServlet {
	private IBoardService boardService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨트롤러
		// 1) 요청 받아 분석
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println(boardNo + "<-- boardNo");
		int boardRead = Integer.parseInt(request.getParameter("boardRead"));
		System.out.println(boardRead + "<-- boardRead");		
		// 2) 서비스 레이어를 요청 (메서드 호출) -> Model값 (자료구조) 구하기 위한 행동
		
		// 상세보기
		boardService = new BoardService();
		List<Board> list = boardService.getBoardOne(boardNo);
		
		if(list != null) {
			request.setAttribute("list", list);
			
			// 조회수 증가
			boardService = new BoardService();
			boardService.modifyBoardRead(boardRead, boardNo);
		}
		
		// 3) 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/boardOne.jsp").forward(request, response);
	}

}