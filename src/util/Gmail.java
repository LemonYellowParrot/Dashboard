package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator { //인증, 수행을 돕는 class
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("관리자 id", "관리자 password"); //자신의 google id, 비번. 관리자 자신의 비밀번호와 계정 넣기.
	} //보안수준이 낮은 페이지이므로 구글 계정에서 로그인하게 설정해줘야 함.
}
