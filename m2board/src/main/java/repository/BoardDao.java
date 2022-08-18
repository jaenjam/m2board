package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Board;

public class BoardDao implements IBoardDao {
	
	@Override
	public int updateBoardNice(Connection conn, int boardNice, int boardNo) throws Exception {
		System.out.println("BoardDao updateBoardNice");
		
		int row = 0;
		String sql = "UPDATE board"
				+ " SET board_nice = ?"
				+ " WHERE board_no = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNice);
			stmt.setInt(2, boardNo);
			
			row = stmt.executeUpdate();
		} finally {
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}
		}
		System.out.println(row + " <-- updateBoardNice row");
		
		return row;
	}
	
	@Override
	public int updateBoardRead(Connection conn, int read, int boardNo) throws Exception {
		System.out.println("BoardDao updateBoardRead");
		
		int row = 0;
		String sql = "UPDATE board "
				+ " SET board_read = ?"
				+ " WHERE board_no = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, read);
			stmt.setInt(2, boardNo);
			
			row = stmt.executeUpdate();
		} finally {
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}
		}
		System.out.println(row + " <-- updateBoardRead row");
		
		return row;
	}

	
	@Override
	public int insertBoard(Connection conn, Board board) throws Exception {
		System.out.println("BoardDao insertBoard");
		
		int row = 0;
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO board"
				+ " (board_title"
				+ ", board_writer"
				+ ", board_content"
				+ ", create_date) "
				+ " VALUES (?, ?, ?, NOW())";
		

		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, board.getBoardTitle());
			stmt.setString(2, board.getBoardWriter());
			stmt.setString(3, board.getBoardContent());
			
			row = stmt.executeUpdate();
			
		} finally {
			if(stmt != null) {stmt.close();}
		}
		System.out.println(row + " <-- row");
		return row;
	}

	@Override
	public List<Board> selectBoardOne(Connection conn, int boardNo) throws Exception {
		System.out.println("BoardDao selectBoardOne");
		
		List<Board> list = null;
		Board board = null;
		String sql = "SELECT board_no boardNo"
				+ ", board_title boardTitle"
				+ ", board_writer boardWriter"
				+ ", board_content boardContent"
				+ ", create_date createDate"
				+ ", board_read boardRead"
				+ ", board_nice boardNice"
				+ " FROM board"
				+ " WHERE board_no = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			list = new ArrayList<Board>();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setBoardNo(rs.getInt("boardNo"));
				board.setBoardTitle(rs.getString("boardTitle"));
				board.setBoardWriter(rs.getString("boardWriter"));
				board.setBoardContent(rs.getString("boardContent"));
				board.setCreateDate(rs.getString("createDate"));
				board.setBoardRead(rs.getInt("boardRead"));
				board.setBoardNice(rs.getInt("boardNice"));
				
				list.add(board);
			}
		} finally {
			if(rs!=null)   {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
	
		return list;
	}

	@Override
	public List<Board> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws Exception{
		System.out.println("BoardDao selectBoardListByPage");
		
		List<Board> list = null;
		Board board = null;
		String sql = "SELECT board_no boardNo"
				+ ", board_title boardTitle"
				+ ", board_writer boardWriter"
				+ ", create_date createDate"
				+ ", board_read boardRead"
				+ ", board_nice boardNice"
				+ " FROM board"
				+ " ORDER BY create_date"
				+ " LIMIT ?, ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			list = new ArrayList<Board>();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				board = new Board();
				
				board.setBoardNo(rs.getInt("boardNo"));
				board.setBoardTitle(rs.getString("boardTitle"));
				board.setBoardWriter(rs.getString("boardWriter"));
				board.setCreateDate(rs.getString("createDate"));
				board.setBoardRead(rs.getInt("boardRead"));
				board.setBoardNice(rs.getInt("boardNice"));
				
				list.add(board);
			}
		} finally {
			if(rs!=null)   {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
	
		return list;
	}

	@Override
	public int selectBoardCnt(Connection conn, int rowPerPage) throws Exception{
		System.out.println("BoardDao selectBoardCnt");
		
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM board";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt("COUNT(*)");
			}
		} finally {
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}
		}		
		return totalCount;
	}

	@Override
	public int selectBoardCnt() {
		// TODO Auto-generated method stub
		return 0;
	}	
}