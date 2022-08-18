package service;

import repository.IMemberDao;
import repository.MemberDao;
import vo.Member;

public class MemberService implements IMemberService {
	
	private IMemberDao memberDao;
	@Override
	public Member getMemberLogin(Member pramMember) {
		System.out.println("MemberService getMemberLogin");
		
		memberDao = new MemberDao();
		Member member = null;
		
		try {
			member = memberDao.login(pramMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(member + "<-- member");
		return member;
	}

}
