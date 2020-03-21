package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {	
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null; /*특정 sql문장 성공적 실행 위해*/
		ResultSet rs = null; //특정 sql 실행 이후 결과값에 대해 처리 위해.
		try {
			conn = DatabaseUtil.getConnection(); //객체 자체 반환
			pstmt = conn.prepareStatement(SQL); //con에서 sql이 실행 가능하게 준비
			pstmt.setString(1, userID);//user에게 입력받은 id값 넣어주기
			rs = pstmt.executeQuery(); //sql문장 실행한 결과 담기 //data 조회 시 사용
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;//로그인 성공
				}
				else {
					return 0;//비밀번호 틀림
				}
			}
			return -1;//아이디 없음
		} catch (Exception e) {
			e.printStackTrace();
		} finally { //이렇게 다 해제해야 서버 무리가 안감.+외부 util패키지에 정의해서 안정적 모듈화.
			try { if(conn != null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) {e.printStackTrace();}
			try { if(rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();}
		}
		return -2; //db 오류
	}
	
	public int join(UserDTO user) {
		String SQL = "Insert INTO USER VALUES (?, ?, ?, ?, false)";
		Connection conn = null;
		PreparedStatement pstmt = null; /*특정 sql문장 성공적 실행 위해*/
		ResultSet rs = null; //특정 sql 실행 이후 결과값에 대해 처리 위해.
		try {
			conn = DatabaseUtil.getConnection(); //객체 자체 반환
			pstmt = conn.prepareStatement(SQL); //con에서 sql이 실행 가능하게 준비
			pstmt.setString(1, user.getUserID());//user에게 입력받은 id값 넣어주기
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			return pstmt.executeUpdate();//영향받은 데이터 갯수 반환 action에서 1 반환-->회원가입됨 증명.
		} catch (Exception e) {
			e.printStackTrace();
		} finally { //이렇게 다 해제해야 서버 무리가 안감.+외부 util패키지에 정의해서 안정적 모듈화.
			try { if(conn != null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) {e.printStackTrace();}
			try { if(rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();}
		}
		return -1; //회원가입 실패 primary키가 userid에 걸려서 -1 반환됨.
	}
	
	public String getUserEmail(String userID) {
		String SQL = "SELECT userEmail FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null; /*특정 sql문장 성공적 실행 위해*/
		ResultSet rs = null; //특정 sql 실행 이후 결과값에 대해 처리 위해.
		try {
			conn = DatabaseUtil.getConnection(); //객체 자체 반환
			pstmt = conn.prepareStatement(SQL); //con에서 sql이 실행 가능하게 준비
			pstmt.setString(1, userID);//user에게 입력받은 id값 넣어주기
			rs = pstmt.executeQuery(); //sql문장 실행한 결과 담기
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { //이렇게 다 해제해야 서버 무리가 안감.+외부 util패키지에 정의해서 안정적 모듈화.
			try { if(conn != null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) {e.printStackTrace();}
			try { if(rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();}
		}
		return null;
	}
	
	public boolean getUserEmailChecked(String userID) { //이메일 검증 안되면 강의평가 작성 x.
		String SQL = "SELECT userEmailChecked FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null; /*특정 sql문장 성공적 실행 위해*/
		ResultSet rs = null; //특정 sql 실행 이후 결과값에 대해 처리 위해.
		try {
			conn = DatabaseUtil.getConnection(); //객체 자체 반환
			pstmt = conn.prepareStatement(SQL); //con에서 sql이 실행 가능하게 준비
			pstmt.setString(1, userID);//user에게 입력받은 id값 넣어주기
			rs = pstmt.executeQuery(); //sql문장 실행한 결과 담기
			if(rs.next()) {
				return rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { //이렇게 다 해제해야 서버 무리가 안감.+외부 util패키지에 정의해서 안정적 모듈화.
			try { if(conn != null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) {e.printStackTrace();}
			try { if(rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();}
		}
		return false; //회원가입 실패 primary키가 userid에 걸려서 -1 반환됨.
	}
	
	public boolean setUserEmailChecked(String userID) {
		String SQL = "UPDATE USER SET userEmailChecked = true WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null; /*특정 sql문장 성공적 실행 위해*/
		ResultSet rs = null; //특정 sql 실행 이후 결과값에 대해 처리 위해.
		try {
			conn = DatabaseUtil.getConnection(); //객체 자체 반환
			pstmt = conn.prepareStatement(SQL); //con에서 sql이 실행 가능하게 준비
			pstmt.setString(1, userID);//user에게 입력받은 id값 넣어주기
			pstmt.executeUpdate();//영향받은 데이터 갯수 반환 action에서 1 반환-->회원가입됨 증명.
			return true; //한번 인증이 되어도 추가적 인증을 위해 이렇게 만듦.(이미 인증되어도 인증되어야 하므로 true 반환
		} catch (Exception e) {
			e.printStackTrace();
		} finally { //이렇게 다 해제해야 서버 무리가 안감.+외부 util패키지에 정의해서 안정적 모듈화.
			try { if(conn != null) conn.close(); } catch (Exception e) {e.printStackTrace();}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) {e.printStackTrace();}
			try { if(rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();}
		}
		return false; //회원가입 실패 primary키가 userid에 걸려서 -1 반환됨.
	}

}