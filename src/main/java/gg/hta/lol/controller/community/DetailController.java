package gg.hta.lol.controller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import gg.hta.lol.service.CommunityService;
import gg.hta.lol.vo.CommunityVo;

@Controller
public class DetailController {
	@Autowired private CommunityService service;
	
	@GetMapping("/community/detail")
	public ModelAndView detail(int num) {
		ModelAndView mv=new ModelAndView("detail");
		service.addHit(num);
		
		CommunityVo vo=service.select(num);
		CommunityVo prev=service.prev(num);
		CommunityVo next=service.next(num);
			
		String content=vo.getContent();
		content=content.replaceAll("\n","<br>");
		vo.setContent(content);
		
		mv.addObject("vo",vo);
		mv.addObject("prev",prev);
		mv.addObject("next",next);
		
		return mv;
		
	}
}