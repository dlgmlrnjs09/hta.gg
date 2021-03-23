package gg.hta.lol.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gg.hta.lol.vo.BattingLogVo;
import gg.hta.lol.vo.BattingTeamInfoVo;
import gg.hta.lol.vo.MatchVo;
import gg.hta.lol.vo.PointVo;

@Repository
public class BattingDao {
	@Autowired SqlSession sqlSession;
	private static String NAMESPACE="gg.hta.lol.mapper.BattingMapper";
	
	public int AddTeam(String tname) {
		return sqlSession.insert(NAMESPACE+".addTeam",tname);
	}
	public List<BattingTeamInfoVo> teamlist(){
		return sqlSession.selectList(NAMESPACE+".teamlist");
	}
	public int AddMatch(MatchVo vo) {
		return sqlSession.insert(NAMESPACE+".addMatch",vo);
	}
	public List<HashMap<String, Object>> matchinfo(String mDate){
		return sqlSession.selectList(NAMESPACE+".matchinfo",mDate);
	}
	public HashMap<String, Object> teamName(int mnum3) {
		return sqlSession.selectOne(NAMESPACE+".teamName",mnum3);
	}
	public List<String> teamName2(int mnum3) {
		return sqlSession.selectList(NAMESPACE+".teamName2",mnum3);
	}
	public int winupdate(MatchVo vo) {
		return sqlSession.update(NAMESPACE+".winupdate",vo);
	}
	public int battingdeleteTeam(int tnum) {
		return sqlSession.delete(NAMESPACE+".battingdeleteTeam",tnum);
	}
	public int selectTeam(BattingLogVo vo) {
		return sqlSession.insert(NAMESPACE+".selectTeam",vo);
	}
	public List<BattingLogVo> battingchoice(BattingLogVo vo){
		return sqlSession.selectList(NAMESPACE+".battingchoice",vo);
	}
	public List<HashMap<String,Object>> pointGo(int mnum){
		return sqlSession.selectList(NAMESPACE+".pointGo",mnum);
	}
	public int pointGo2(int mnum) {
		return sqlSession.update(NAMESPACE+".pointGo2",mnum);
	}
	public List<HashMap<String, Object>> teamrank(){
		return sqlSession.selectList(NAMESPACE+".teamrank");
	}
	public String teamname(int tnum) {
		return sqlSession.selectOne(NAMESPACE+".teamname",tnum);
	}
	public int deletematch(int mnum) {
		return sqlSession.delete(NAMESPACE+".deletematch",mnum);
	}

}
