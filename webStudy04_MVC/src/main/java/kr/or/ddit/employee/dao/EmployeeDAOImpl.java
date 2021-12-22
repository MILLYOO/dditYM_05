package kr.or.ddit.employee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;

public class EmployeeDAOImpl implements EmployeeDAO{
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertEmployee(EmployeeVO employee) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
			EmployeeDAO mapper = sqlSession.getMapper(EmployeeDAO.class);
			int cnt = mapper.insertEmployee(employee);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int selectTotalRecord(PagingVO<EmployeeVO> pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
			EmployeeDAO mapper = sqlSession.getMapper(EmployeeDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}

	@Override
	public List<EmployeeVO> selectEmployeeList(PagingVO<EmployeeVO> pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
			EmployeeDAO mapper = sqlSession.getMapper(EmployeeDAO.class);
			return mapper.selectEmployeeList(pagingVO);
		}
	}

	@Override
	public EmployeeVO selectEmployee(String empId) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
			EmployeeDAO mapper = sqlSession.getMapper(EmployeeDAO.class);
			return mapper.selectEmployee(empId);
		}
	}

	@Override
	public int updateEmployee(EmployeeVO employee) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
			EmployeeDAO mapper = sqlSession.getMapper(EmployeeDAO.class);
			int cnt = mapper.updateEmployee(employee);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteEmployee(String empId) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
			EmployeeDAO mapper = sqlSession.getMapper(EmployeeDAO.class);
			int cnt = mapper.deleteEmployee(empId);
			sqlSession.commit();
			return cnt;
		}
	}
}
