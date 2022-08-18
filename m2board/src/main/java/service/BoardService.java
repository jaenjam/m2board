package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import repository.BoardDao;
import repository.IBoardDao;
import vo.Board;

public class BoardService implements IBoardService {
	private IBoardDao boardDao;
	
	
	
	@Override
	public int modifyBoardNice(int nice, int boardNo) {
		int result = 0;
		Connection conn = null;
		
		try {
			new DBUtil();
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			boardDao = new BoardDao();
			
			result = boardDao.updateBoardNice(conn, nice, boardNo);
			
			if(result == 0) {
				throw new Exception(); 
			}
				
			conn.commit();		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(result + " <-- result");
		return result;
	}

	@Override
	public int modifyBoardRead(int read, int boardNo) {
		int result = 0;
		Connection conn = null;
		
		try {
			new DBUtil();
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			boardDao = new BoardDao();
			
			result = boardDao.updateBoardRead(conn, read, boardNo);
			
			if(result == 0) {
				throw new Exception(); 
			}
				
			conn.commit();		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(result + " <-- result");
		return result;
	}

	@Override
	public int addBoard(Board board) {
		int result = 0;
		Connection conn = null;
		
		try {
			new DBUtil();
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			boardDao = new BoardDao();
			
			result = boardDao.insertBoard(conn, board);
			
			if(result == 0) {
				throw new Exception(); 
			}
				
			conn.commit();		
		} catch (Exception e) {
			e.printStackTrace(); // console에 예외메세지 출력
			try {
				conn.rollback(); // 예외를 던지지말고 감싸야함
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public List<Board> getBoardOne(int boardNo) {
		List<Board> list = null;
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			boardDao = new BoardDao();
			list = new ArrayList<>();

			// select 메서드 list값에 담기
			list = boardDao.selectBoardOne(conn, boardNo);		
			
			if(list == null) {
				throw new Exception();
			} 
			
			conn.commit();		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> getBoardList(int rowPerPage, int currentPage) {
		Map<String, Object> map = null;
		List<Board> list = null;
		Connection conn = null;
		int lastPage = 0;
		int totalCount = 0;
		int beginRow = 0;
		
		try {
			new DBUtil();
			conn = DBUtil.getConnection();
			boardDao = new BoardDao();
			map = new HashMap<>();
			list = new ArrayList<>();
			// conn.setAutoCommit(false);
					 			
			
			// totalCount 및 lastPage, beginRow 계산
			totalCount = boardDao.selectBoardCnt(conn, rowPerPage);
			beginRow = (currentPage - 1) * rowPerPage;
			if(totalCount == 0) {
				throw new Exception();
			}
			
			lastPage = totalCount / rowPerPage;
			if (totalCount % rowPerPage != 0) {
				lastPage += 1;
			}

			// select 메서드 list값에 담기
			list = boardDao.selectBoardListByPage(conn, rowPerPage, beginRow);		
			
			if(list == null) {
				throw new Exception();
			} else {
				// map에 Object 담기
				map.put("lastPage", lastPage);
				map.put("list", list);
			}
			
			conn.commit();		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}	
}