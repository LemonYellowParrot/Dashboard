package util;

import java.security.MessageDigest;

public class SHA256 {

	public static String getSHA256(String input) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256"); //실제로 사용자가 입력한 값을 이 알고리즘을 적용하게 함.
			byte[] salt = "Hello! This is Salt.".getBytes(); //단순하게 256적용시 해킹 가능성이 높아져서 salt 적용.
			digest.reset();
			digest.update(salt); //salt update
			byte[] chars = digest.digest(input.getBytes("UTF-8")); //hash적용값을 이케
			for(int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]); //0xff=hex값, hex값 적용할 해당 인덱스
				if(hex.length()==1) result.append('0');
				result.append(hex); //hex뒤에 달아주기
			}
		} catch (Exception e) {
			e.printStackTrace(); //오류 발생시 출력
		}
		return result.toString(); //hash값 return하기
	}
}
