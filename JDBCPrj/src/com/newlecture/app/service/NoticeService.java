package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.app.entitiy.Notice;

public class NoticeService {
	private String url = ""; // url=jdbc:oracle:thin:@ip주소:포트:SID
	private String uid = "";
	private String pwd = "",";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	public List<Notice> getList(int page,String field, String query) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM NOTICE_VIEW WHERE " + field +  " LIKE ? AND RN BETWEEN ? AND ?";

		Class.forName(driver); // 드라이버 로드(객체화) -> 메모리에 드라이버 올라감
		Connection con = DriverManager.getConnection(url, uid, pwd); // 드라이버 매니저를 통해 연결 객체 생성
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2,10*(page-1)+1);
		st.setInt(3, 10*page);

		ResultSet rs = st.executeQuery();

		List<Notice> list = new ArrayList<Notice>();

		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writer_id = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			Notice notice = new Notice(id, title, writer_id, regDate, content, hit, files);
			list.add(notice); 
		}

		rs.close();
		st.close();
		con.close();
		return list;
	}

	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO NOTICE(TITLE,WRITER_ID,CONTENT,FILES) VALUES(?,?,?,?)";

		String title = notice.getTitle();
		String writer_id = notice.getWriter_id();
		String content = notice.getContent();
		String files = notice.getFiles();

		Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드(객체화) -> 메모리에 드라이버 올라감
		Connection con = DriverManager.getConnection(url, uid, pwd); // 드라이버 매니저를 통해 연결 객체 생성
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,title);
		st.setString(2, writer_id);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate();

		st.close();
		con.close();

		return result;
	}

	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE NOTICE SET TITLE=?,CONTENT=?,FILES=? WHERE ID = ?";

		String title = notice.getTitle();
		String content = notice.getContent();
		String files=  notice.getFiles();
		int id =notice.getId();

		Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드(객체화) -> 메모리에 드라이버 올라감
		Connection con = DriverManager.getConnection(url, uid, pwd); // 드라이버 매니저를 통해 연결 객체 생성
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,title);
		st.setString(2, content);
		st.setString(3, files);
		int result = st.executeUpdate();

		st.close();
		con.close();

		return result;
	}

	public int delete(int id) throws ClassNotFoundException, SQLException {

		String sql = "DELETE FROM NOTICE WHERE ID=?";
	
		Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드(객체화) -> 메모리에 드라이버 올라감
		Connection con = DriverManager.getConnection(url, uid, pwd); // 드라이버 매니저를 통해 연결 객체 생성
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,id);
		int result = st.executeUpdate();

		st.close();
		con.close();
		return result;

	}

	
	public int getCount(String searchField, String searchWord) throws ClassNotFoundException, SQLException {
		int count=0;
		String sql = "SELECT COUNT(*) COUNT FROM NOTICE WHERE " + searchField+ " LIKE ? ";
		Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드(객체화) -> 메모리에 드라이버 올라감
		Connection con = DriverManager.getConnection(url, uid, pwd); // 드라이버 매니저를 통해 연결 객체 생성
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,"%"+ searchWord + "%");
		ResultSet rs = st.executeQuery();
		if(rs.next())
			count = rs.getInt("COUNT");
		
		return count;

	}

}
