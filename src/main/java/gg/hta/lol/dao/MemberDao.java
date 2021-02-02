package gg.hta.lol.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gg.hta.lol.vo.MemberVo;

@Repository
public class MemberDao {
	@Autowired private SqlSession sqlSession;
	public final String NAMESPACE = "gg.hta.lol.mapper.MemberMapper";
	
	public int insert(MemberVo vo) {
		return sqlSession.insert(NAMESPACE + ".insert",vo);
	}
	public int insertAuth(HashMap<String, Object> map) {
		return sqlSession.insert(NAMESPACE + ".insertAuth", map); 
	}
	public int delete(int num) {
		return sqlSession.delete(NAMESPACE + ".delete",num); 
	}
	
	public int update(MemberVo vo) {
		return sqlSession.update(NAMESPACE + ".update", vo); 
	}
	
	public MemberVo selectOne(int num) {
		return sqlSession.selectOne(NAMESPACE + ".select", num); 
	}
	
	public List<MemberVo> selectList(){
		return sqlSession.selectList(NAMESPACE + ".list"); 
	}
}
