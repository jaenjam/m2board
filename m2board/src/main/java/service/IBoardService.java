package service;

import java.util.List;
import java.util.Map;

import vo.Board;

public interface IBoardService {
	//List<Board>, int lastPage
	Map<String, Object> getBoardList(int rowPerPage, int currentPage);

	int modifyBoardRead(int read, int boardNo);

	int modifyBoardNice(int nice, int boardNo);

	int addBoard(Board board);

	List<Board> getBoardOne(int boardNo);
}
