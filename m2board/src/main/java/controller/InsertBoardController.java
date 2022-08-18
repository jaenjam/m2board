package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;
import vo.Board;

@WebServlet("/insertBoard")
public class InsertBoardController extends HttpServlet {
	private IBoardService boardService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/insertBoard.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		boardService = new BoardService();
		Board board = new Board();
		
		String title = request.getParameter("boardTitle");
		String writer = request.getParameter("boardWriter");
		String content = request.getParameter("boardContent");
		
		board.setBoardTitle(title);
		board.setBoardWriter(writer);
		board.setBoardContent(content);
		System.out.println(board.getBoardTitle());
		System.out.println(board.getBoardWriter());
		System.out.println(board.getBoardContent());
		
		
		if(boardService.addBoard(board) == 0) {
			response.sendRedirect(request.getContextPath() + "/insertBoard");
		} else {
			response.sendRedirect(request.getContextPath() + "/boardList");
		}
	}
}