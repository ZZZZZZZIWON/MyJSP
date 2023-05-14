package sec01.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO 
{
	private PreparedStatement pstmt;
	private Connection con;
	
	public List<MemberVO> listMembers()
	{

		List<MemberVO> list = new ArrayList<MemberVO>();
		try
		{
			connDB();
			String query = "select * from t_member";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			while (rs.next())
			{
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	private void connDB()
	{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String pwd = "1247";
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Oracle ����̹� �ε� ����");
			con=DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection ���� ����");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
