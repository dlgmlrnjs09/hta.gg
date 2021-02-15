package gg.hta.lol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.hta.lol.dao.ReplyDao;
import gg.hta.lol.vo.ReplyVo;

@Service
public class ReplyService {
	@Autowired private ReplyDao dao;
	public int delete(int rNum) {
		return dao.delete(rNum);
	}
	public int insert(ReplyVo vo) {
		return dao.insert(vo);
	}
	public List<ReplyVo> list(int pNum){
		return dao.list(pNum);
	}
}