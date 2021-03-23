package gg.hta.lol.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.hta.lol.dao.BattingDao;
import gg.hta.lol.vo.BattingLogVo;
import gg.hta.lol.vo.BattingTeamInfoVo;
import gg.hta.lol.vo.MatchVo;
import gg.hta.lol.vo.PointVo;

@Service
public class BattingService {
	@Autowired BattingDao dao;
	public int addTeam(String tname) {
		return dao.AddTeam(tname);
	}
	public List<BattingTeamInfoVo> teamlist(){
		return dao.teamlist();
	}
	public int addMatch(MatchVo vo) {
		return dao.AddMatch(vo);
	}
	public List<HashMap<String, Object>> matchinfo(String mDate){
		return dao.matchinfo(mDate);
	}
	public HashMap<String, Object> teamName(int mnum3){
		return dao.teamName(mnum3);
	}
	public List<String> teamName2(int mnum3){
		return dao.teamName2(mnum3);
	}
	public int winupdate(MatchVo vo) {
		return dao.winupdate(vo);
	}
	public int battingdeleteTeam(int tnum) {
		return dao.battingdeleteTeam(tnum);
	}
	public int selectTeam(BattingLogVo vo) {
		return dao.selectTeam(vo);
	}
	public List<BattingLogVo> battingchoice(BattingLogVo vo){
		return dao.battingchoice(vo);
	}
	public List<HashMap<String,Object>> pointGo(int mnum){
		return dao.pointGo(mnum);
	}public int pointGo2(int mnum) {
		return dao.pointGo2(mnum);
	}
	public List<HashMap<String, Object>> teamrank(){
		return dao.teamrank();
	}
	public String teamname(int tnum) {
		return dao.teamname(tnum);
	}
	public int deletematch(int mnum) {
		return dao.deletematch(mnum);
	}
}
