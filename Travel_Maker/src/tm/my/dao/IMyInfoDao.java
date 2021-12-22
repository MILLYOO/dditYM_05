package tm.my.dao;

import java.sql.SQLException;

import tm.member.vo.MemberVO;

public interface IMyInfoDao {

	public MemberVO getMember(String memId) throws SQLException;
}
