package gg.hta.lol.mapper;

import org.springframework.dao.DuplicateKeyException;

import gg.hta.lol.vo.MatchinfoVo;
import gg.hta.lol.vo.TeamInfoVo;

public interface TeamInfoMapper {
	
	public int addTeaminfo(TeamInfoVo vo);
	
}
