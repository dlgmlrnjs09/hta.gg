package gg.hta.lol.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import gg.hta.lol.vo.ChampionVo;

@Repository
public class ChampDao {
	@Autowired SqlSession sqlSession;
	private final String NAMESPACE ="gg.hta.lol.mapper.ChampMapper";
	public int insertChamp(ChampionVo vo) {
		return  sqlSession.insert(NAMESPACE+".insertChamp",vo);
	}
	public int deleteChamp() {
		return sqlSession.delete(NAMESPACE+".deleteChamp");
	}
	public List<ChampionVo> ChampAll_List(){
		return sqlSession.selectList(NAMESPACE+".ChampAll_List");
	}
	public ChampionVo selectList(int championid) {
		return sqlSession.selectOne(NAMESPACE+".selectList",championid);
	}
}