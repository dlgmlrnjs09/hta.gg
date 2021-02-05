package gg.hta.lol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import gg.hta.lol.dao.MemberDao;
import gg.hta.lol.util.MailHandler;
import gg.hta.lol.util.TempKey;
import gg.hta.lol.vo.AuthVo;
import gg.hta.lol.vo.AuthoritiesVo;

@Service
public class MailService {
    @Autowired private JavaMailSender mailSender;
    @Autowired private MemberDao dao;
//	    ------------------------------------- Member --------------------------------------
public void regist(AuthVo vo, String email) throws Exception {
    String key = new TempKey().generateKey(30);  // ����Ű ����
    vo.setCode(key);
    dao.insert(vo);
//member.setAuthkey(key);
    System.out.println("key : " + key);


    //���� ����
    MailHandler sendMail = new MailHandler(mailSender);
    sendMail.setSubject("���� �̸��� ����");
    sendMail.setText(
        new StringBuffer()
        	.append("<h1>��������</h1>")
        	.append("<a href='http://localhost:8080/email_test/emailConfirm?authKey=")
        	.append(key)
        	.append("' target='_blank'>�̸��� ���� Ȯ��</a>")
        	.toString());

    	//�ؿ� �����ϱ� (���ΰɷ�)
//    	sendMail.setFrom("����ID@gmail.com", "���� �̸�");
    	
    	
    	if(email.substring(email.lastIndexOf("@")).trim().equals("@daum")) {
    		sendMail.setTo(email+".net");
            sendMail.send();
    	}else {
    		sendMail.setTo(email+".com");
            sendMail.send();
    	}
    }
 
//    //�̸��� ���� Ű ����
//    public Member userAuth(String authkey) throws Exception {
//        Member member = new Member();
//        member = dao.chkAuth(authkey);
//   
//        if(member!=null){
//            try{
//                dao.successAuthkey(member);
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return member;
//    }

}
