package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import commons.DBUtil;
import vo.Member;

public class MemberDao implements IMemberDao {

	@Override
	public Member login(Member member) throws Exception {
		Member loginMember = null;
		String sql = "SELECT member_id, member_name FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setMemberId(rs.getString("member_id"));
				loginMember.setMemberName(rs.getString("member_name"));
				
			}
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			if(conn!=null) {conn.close();}			
		}
		return loginMember;
	}

}
