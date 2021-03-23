package gg.hta.lol.controller.batting;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import gg.hta.lol.service.BattingService;
import gg.hta.lol.service.PointService;
import gg.hta.lol.vo.BattingLogVo;

@Controller
public class BattingController {
	@Autowired
	private BattingService service;
	@Autowired
	private PointService pointservice;
	
	@GetMapping(value="/batting/home")
	public String battingHome() {
		return "batting/home";
	}
	
	@GetMapping(value="/selectTeam")
	@ResponseBody
	public String selectTeam(BattingLogVo vo) {
		
		service.selectTeam(vo);
		String username=vo.getUsername();
		pointservice.battingInsert(username);
	
		return "{\"code\":\"" + vo + "\"}";
		
	}
	@GetMapping(value="/battinglog/choice")
	@ResponseBody
	public Map<String, Object> choice(BattingLogVo vo) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("battingchoice",service.battingchoice(vo));
		return map;
	}
}
