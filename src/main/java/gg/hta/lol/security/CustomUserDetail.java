package gg.hta.lol.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import gg.hta.lol.vo.AuthoritiesVo;

public class CustomUserDetail implements UserDetails{ //������ü
	private String username;
	private String password;
	private String enabled;
	private List<AuthoritiesVo> authoList;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for(AuthoritiesVo vo:authoList) {
			auths.add(new SimpleGrantedAuthority(vo.getAuthority()));
		}
		return auths;
	}
	//���� ��й�ȣ
	@Override
	public String getPassword() {
		return password;
	}
	//���� ���̵�
	@Override
	public String getUsername() {
		return username;
	}
	//���� ����
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	//������ ������
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	//��й�ȣ ����Ǿ�����
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	//��밡������
	@Override
	public boolean isEnabled() {
		return true;
	}	
}
