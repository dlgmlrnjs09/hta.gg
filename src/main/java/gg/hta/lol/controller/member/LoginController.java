package gg.hta.lol.controller.member;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

import gg.hta.lol.dao.MemberDao;
import gg.hta.lol.security.CustomUserDetailService;
import gg.hta.lol.service.MemberService;
import gg.hta.lol.util.NaverLoginBO;
import gg.hta.lol.vo.AuthVo;
import gg.hta.lol.vo.MemberVo;

@Controller
public class LoginController {
	@Autowired private MemberService service;
	@Autowired private MemberDao dao;
	@Autowired private CustomUserDetailService serviceUser;
	/* NaverLoginBO */
	@Autowired private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	//�α��� ù ȭ�� ��û �޼ҵ�
	@RequestMapping(value = "/member/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		//System.out.println("���̹�:" + naverAuthUrl); //���̹� �α���ȭ�� URL �ּ�
		model.addAttribute("url", naverAuthUrl);
		return ".header2.member.login";
	}
	//���̹� �α��� ������ callbackȣ�� �޼ҵ�
	@RequestMapping(value = "/member/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request) throws IOException, ParseException {
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		//1. �α��� ����� ������ �о�´�.
		apiResult = naverLoginBO.getUserProfile(oauthToken); //String������ json������
		/** apiResult json ����
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		**/
		//2. String������ apiResult�� json���·� �ٲ�
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		//3. ������ �Ľ�
		//Top���� �ܰ� _response �Ľ�
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		//response�� nickname�� �Ľ�
		String id = (String)response_obj.get("id");
		String email = (String)response_obj.get("email");
		String nickname = (String)response_obj.get("nickname");	
		MemberVo member = new MemberVo();
		UUID uuid = UUID.randomUUID();
		member.setUsername("NAVER_"+id);
		member.setPassword(uuid.toString());
		member.setEmail(email);
		member.setNickname(nickname);
		if(service.selectOne(member.getUsername()) == null) {
			dao.insert(new AuthVo(member.getUsername(), code));
			service.insert(member);
		}
		//Spring security ���� �α���
		UserDetails userDetail = serviceUser.loadUserByUsername("NAVER_"+id);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, member.getPassword(), userDetail.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(authentication);
	    session = request.getSession(true);
	    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
	    return ".header.home";
	}
	@GetMapping("/member/id")
	public String id() {
		return ".header2.member.idSearch";
	}
	@GetMapping("/member/pwd")
	public String pwd() {
		return ".header2.member.pwdSearch";
	}
	@PostMapping("/member/pwdChange")
	public String change(String username, String password) {
		int n = service.updatePwd(username, password);
		if(n>0) {
			return ".header2.member.login";
		}else {
			return ".header2.member.error";
		}
	}
	@GetMapping("/member/all/main")
	public String all() {
		return ".header.home";
	}
	@GetMapping("/member/member/main")
	public String member() {
		return ".header.home";
	}
	@GetMapping("/member/admin/main")
	public String admin() {
		return ".header.home";
	}
}
