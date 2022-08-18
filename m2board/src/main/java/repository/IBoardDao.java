package repository;

import java.sql.Connection;
import java.util.List;

import vo.Board;

public interface IBoardDao {
	List<Board> selectBoardListByPage(Connection conn, final int rowPerPage, int beginRow) throws Exception;
	int selectBoardCnt();
	int updateBoardRead(Connection conn, int read, int boardNo) throws Exception;
	int updateBoardNice(Connection conn, int nice, int boardNo) throws Exception;
	int insertBoard(Connection conn, Board board) throws Exception;
	List<Board> selectBoardOne(Connection conn, int boardNo) throws Exception;
	int selectBoardCnt(Connection conn, int rowPerPage) throws Exception;
}
