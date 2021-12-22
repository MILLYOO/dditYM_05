package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.servlet.PKNotFoundException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MemberDAOImpl implements MemberDAO {
//	static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);

	@Override
	public MemberVO selectMemberForAuth(String memId) {
		
		MemberVO saved = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MEM_ID, MEM_PASS, MEM_NAME            ");
		sql.append(" FROM MEMBER                                  ");
		sql.append(" WHERE MEM_ID = ? ");
		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
			pstmt.setString(1, memId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				saved = new MemberVO();
				saved.setMemId(rs.getString("MEM_ID"));
				saved.setMemPass(rs.getString("MEM_PASS"));
				saved.setMemName(rs.getString("MEM_NAME"));
			}
			return saved;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();                           
		sql.append(" INSERT INTO MEMBER (                                  ");
		sql.append("    MEM_ID,    MEM_PASS,    MEM_NAME,                  ");
		sql.append("    MEM_REGNO1,    MEM_REGNO2,    MEM_BIR,             ");
		sql.append("    MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                 ");
		sql.append("    MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,             ");
		sql.append("    MEM_MAIL,    MEM_JOB,    MEM_LIKE,                 ");
		sql.append("    MEM_MEMORIAL,    MEM_MEMORIALDAY,    MEM_MILEAGE   ");
		sql.append(" ) VALUES (                                            ");
		sql.append("	?,    ?,    ?,                                     ");
		sql.append("    ?,    ?,    TO_DATE(?, 'YYYY-MM-DD'),              ");
		sql.append("    ?,    ?,    ?,                                     ");
		sql.append("    ?,    ?,    ?,                                     ");
		sql.append("    ?,    ?,    ?,                                     ");
		sql.append("    ?,    TO_DATE(?, 'YYYY-MM-DD'),    3000            ");
		sql.append(" )                                                     ");
		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				) {
			int idx = 1;
			stmt.setString(idx++, member.getMemId());
			stmt.setString(idx++, member.getMemPass());
			stmt.setString(idx++, member.getMemName());
			stmt.setString(idx++, member.getMemRegno1());
			stmt.setString(idx++, member.getMemRegno2());
			stmt.setString(idx++, member.getMemBir());
			stmt.setString(idx++, member.getMemZip());
			stmt.setString(idx++, member.getMemAdd1());
			stmt.setString(idx++, member.getMemAdd2());
			stmt.setString(idx++, member.getMemHometel());
			stmt.setString(idx++, member.getMemComtel());
			stmt.setString(idx++, member.getMemHp());
			stmt.setString(idx++, member.getMemMail());
			stmt.setString(idx++, member.getMemJob());
			stmt.setString(idx++, member.getMemLike());
			stmt.setString(idx++, member.getMemMemorial());
			stmt.setString(idx++, member.getMemMemorialday());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		List<MemberVO> memberList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MEM_ID,MEM_PASS,MEM_NAME,MEM_HP,MEM_MAIL,MEM_ADD1,MEM_MILEAGE");
		sql.append(" FROM MEMBER");
		log.debug("실행 쿼리문 {}", sql); // {} 메세지 agument
		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemPass(rs.getString("MEM_PASS"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemHp(rs.getString("MEM_HP"));
				member.setMemMail(rs.getString("MEM_MAIL"));
				member.setMemAdd1(rs.getString("MEM_ADD1"));
				member.setMemMileage(rs.getInt("MEM_MILEAGE"));
				memberList.add(member);
			}
			return memberList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		MemberVO member = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT                                                                           ");
		sql.append("     MEM_ID,    MEM_PASS,    MEM_NAME,                                            ");
		sql.append("     MEM_REGNO1,    MEM_REGNO2,    TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR,        ");
		sql.append("     MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                                           ");
		sql.append("     MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,                                       ");
		sql.append("     MEM_MAIL,    MEM_JOB,    MEM_LIKE,                                           ");
		sql.append("     MEM_MEMORIAL,    TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY,     ");
		sql.append("     MEM_MILEAGE,    MEM_DELETE                                                   ");
		sql.append(" FROM    MEMBER                                                                   ");
		sql.append(" WHERE MEM_ID = ?                                                                 ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());	
		){
			stmt.setString(1, memId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemPass(rs.getString("MEM_PASS"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemRegno1(rs.getString("MEM_REGNO1"));
				member.setMemRegno2(rs.getString("MEM_REGNO2"));
				member.setMemBir(rs.getString("MEM_BIR"));
				member.setMemZip(rs.getString("MEM_ZIP"));
				member.setMemAdd1(rs.getString("MEM_ADD1"));
				member.setMemAdd2(rs.getString("MEM_ADD2"));
				member.setMemHometel(rs.getString("MEM_HOMETEL"));
				member.setMemComtel(rs.getString("MEM_COMTEL"));
				member.setMemHp(rs.getString("MEM_HP"));
				member.setMemMail(rs.getString("MEM_MAIL"));
				member.setMemJob(rs.getString("MEM_JOB"));
				member.setMemLike(rs.getString("MEM_LIKE"));
				member.setMemMemorial(rs.getString("MEM_MEMORIAL"));
				member.setMemMemorialday(rs.getString("MEM_MEMORIALDAY"));
				member.setMemMileage(rs.getInt("MEM_MILEAGE"));
				member.setMemDelete(rs.getString("MEM_DELETE"));
			}
 			return member;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();                           
		sql.append(" UPDATE MEMBER SET                                  ");
		sql.append("    MEM_PASS = ?,    MEM_NAME = ?,                  ");
		sql.append("    MEM_REGNO1 = ?,    MEM_REGNO2 = ?,    MEM_BIR = ?,             ");
		sql.append("    MEM_ZIP = ?,    MEM_ADD1 = ?,    MEM_ADD2 = ?,                 ");
		sql.append("    MEM_HOMETEL = ?,    MEM_COMTEL = ?,    MEM_HP = ?,             ");
		sql.append("    MEM_MAIL = ?,    MEM_JOB = ?,    MEM_LIKE = ?,                 ");
		sql.append("    MEM_MEMORIAL = ?,    MEM_MEMORIALDAY = ?,    MEM_MILEAGE =?   ");
		sql.append(" WHERE MEM_ID = ?                                           ");
		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				) {
			int idx = 1;
			stmt.setString(idx++, member.getMemPass());
			stmt.setString(idx++, member.getMemName());
			stmt.setString(idx++, member.getMemRegno1());
			stmt.setString(idx++, member.getMemRegno2());
			stmt.setString(idx++, member.getMemBir());
			stmt.setString(idx++, member.getMemZip());
			stmt.setString(idx++, member.getMemAdd1());
			stmt.setString(idx++, member.getMemAdd2());
			stmt.setString(idx++, member.getMemHometel());
			stmt.setString(idx++, member.getMemComtel());
			stmt.setString(idx++, member.getMemHp());
			stmt.setString(idx++, member.getMemMail());
			stmt.setString(idx++, member.getMemJob());
			stmt.setString(idx++, member.getMemLike());
			stmt.setString(idx++, member.getMemMemorial());
			stmt.setString(idx++, member.getMemMemorialday());
			stmt.setString(idx++, member.getMemId());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMember(String memId) {
		return 0;
	}
}
