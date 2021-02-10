package gg.hta.lol.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import gg.hta.lol.dao.MemberDao;
import gg.hta.lol.util.MailHandler;
import gg.hta.lol.vo.AuthVo;
import gg.hta.lol.vo.MemberVo;

@Service
public class MailService {
	@Autowired private JavaMailSender mailSender;
	@Autowired private MemberDao dao;

	public void regist(AuthVo vo, String email) throws Exception {
//      String key = new TempKey().generateKey(30);  // ��ȣȭ(�̸����ڵ�)
		dao.AuthDelte(vo.getUsername());
		Random random = new Random();
		String key = String.format("%04d", random.nextInt(10000));
		vo.setCode(key);
		dao.insert(vo);
	    //���� ����
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("�ȳ��ϼ��� hta.gg ���� �����Դϴ�.");
        sendMail.setText(
           new StringBuffer()
              .append("<h1>��������</h1>")
              .append("���� ��ȣ�� :")
              .append(key)
              .append("�Դϴ�.<br> Ȩ�������� ���ư��� �̸��� ������ȣ�� �Է����ּ���.")
              .toString());
        sendMail.setFrom("hta.gg@gmail.com", "hta.gg");
		if (email.substring(email.lastIndexOf("@")).trim().equals("@daum")) {
			sendMail.setTo(email + ".net");
			sendMail.send();
		} else {
			sendMail.setTo(email + ".com");
			sendMail.send();
		}
	}

	public String check(String username) {
		return dao.selectAuth(username).getCode();
	}
	
	public void idSearch(String email) throws Exception {
		Random random = new Random();
		String code = String.format("%04d", random.nextInt(10000));
		List<MemberVo> list = dao.emailList(email);
		for(MemberVo vo:list) {
			dao.authUpdate(new AuthVo(vo.getUsername(),code));
		}
	    //���� ����
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("�ȳ��ϼ��� hta.gg ���� �����Դϴ�.");
        sendMail.setText(
           new StringBuffer()
              .append("<h1>���̵� ã�� ��������</h1>")
              .append("���� ��ȣ�� :")
              .append(code)
              .append("�Դϴ�.<br> Ȩ�������� ���ư��� �̸��� ������ȣ�� �Է����ּ���.")
              .toString());
        sendMail.setFrom("hta.gg@gmail.com", "hta.gg");
        sendMail.setTo(email);
		sendMail.send();
	}
	
	public AuthVo idSeachCheck(String email) {
		return dao.EmailselectAuth(email);
	}
	
	public void pwdSearch(String id, String email) throws Exception {
		Random random = new Random();
		String code = String.format("%04d", random.nextInt(10000));
		dao.authUpdate(new AuthVo(id,code));
	    //���� ����
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("�ȳ��ϼ��� hta.gg ���� �����Դϴ�.");
        sendMail.setText(
           new StringBuffer()
              .append("<h1>��й�ȣã��/���� ��������</h1>")
              .append("���� ��ȣ�� :")
              .append(code)
              .append("�Դϴ�.<br> Ȩ�������� ���ư��� �̸��� ������ȣ�� �Է����ּ���.")
              .toString());
        sendMail.setFrom("hta.gg@gmail.com", "hta.gg");
        sendMail.setTo(email);
		sendMail.send();
	}
}
