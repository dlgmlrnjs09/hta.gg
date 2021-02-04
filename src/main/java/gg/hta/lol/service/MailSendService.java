package gg.hta.lol.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import gg.hta.lol.dao.MemberDao;
import gg.hta.lol.vo.AuthVo;

@Service
public class MailSendService {
	@Autowired private JavaMailSender mailSender;
	@Autowired private MemberDao dao;
	
	// �̸��� ���� ����� �޼���
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;

		do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}
		} while (sb.length() < size);
		
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}

	// ������ �̿��� Ű ����
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}

	// ȸ������ �߼� �̸���(����Ű �߼�)
	public void mailSendWithUserKey(String email, String id, HttpServletRequest request) {
		String key = getKey(false, 20);
		AuthVo vo = new AuthVo(id, key);
		dao.insert(vo);
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h1>Ȩ������ ������ ���ּż� �����մϴ�.</h1><br>" +
                		 "���� ��ȣ�� " + key + "�Դϴ�.<br>" + 
                		 "�ش� ������ȣ�� ������ȣ Ȯ�ζ��� �����Ͽ� �ּ���.";
		try {
			mail.setSubject("[��������]"+ id + "���� ���������Դϴ�", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}	
	}
}