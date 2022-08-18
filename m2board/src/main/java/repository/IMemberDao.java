package repository;

import vo.Member;

public interface IMemberDao {
	Member login(Member pramMember) throws Exception;
}
