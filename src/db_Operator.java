import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class db_Operator {
	private Connection con = null;
	private Statement st = null;

	public db_Operator() {
	}
	//连接数据库
	public db_Operator(String dbtype, String dbName, String usrName, String pwd) {
		String url = null;
		if (dbtype.equals("sqlserver")) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("classNotFound");
			}
			url = "jdbc:sqlserver://localhost:1433;DatabaseName=" + dbName;
		} else if (dbtype.equals("mysql")) {
			try {
				Class.forName("org.gjt.mm.mysql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("classNotFound");
			}
			url = "jdbc:mysql://localhost:3306/" + dbName;
		} else {
			System.out.println("wrong db type");
		}
		try {
			con = DriverManager.getConnection(url, usrName, pwd);
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("connectionIsFaile");
		}

	}

	public Connection createConnetion(String dbtype, String dbName,
			String usrName, String pwd) {
		String url = null;
		if (dbtype.equals("sqlserver")) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("classNotFound");
			}
			url = "jdbc:sqlserver://localhost:1433;DatabaseName=" + dbName;
		} else if (dbtype.equals("mysql")) {
			try {
				Class.forName("org.gjt.mm.mysql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("classNotFound");
			}
			url = "jdbc:mysql://localhost:3306/" + dbName;
		} else {
			System.out.println("wrong db type");
		}
		try {
			con = DriverManager.getConnection(url, usrName, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("connectionIsFaile");
		}
		return con;
	}
	//执行sql语句
	public boolean db_Execute(String sql) {
		try {
			st.executeUpdate(sql);
			return true;
		} catch (Exception err) {
			System.out.println("插入、修改或删除出错，请检查sql语句");
			return false;

		}
	}
	//插入特征
	public boolean InsertFeature(double[] Feature, int FeatureDim,
			int FeatureTypeId, int BiImageId) {
		try {
			String sql = "insert into FeatureData values (?," + BiImageId
					+ ",?)";
			PreparedStatement pstmt = con.prepareStatement(sql);

			byte[] writeBuffer = new byte[FeatureDim << 3];
			for (int i = 0; i < FeatureDim; i++) {
				long v = Double.doubleToLongBits(Feature[i]);
				writeBuffer[8 * i + 7] = (byte) (v >>> 56);
				writeBuffer[8 * i + 6] = (byte) (v >>> 48);
				writeBuffer[8 * i + 5] = (byte) (v >>> 40);
				writeBuffer[8 * i + 4] = (byte) (v >>> 32);
				writeBuffer[8 * i + 3] = (byte) (v >>> 24);
				writeBuffer[8 * i + 2] = (byte) (v >>> 16);
				writeBuffer[8 * i + 1] = (byte) (v >>> 8);
				writeBuffer[8 * i + 0] = (byte) (v >>> 0);
			}
			InputStream inputStream = new ByteArrayInputStream(writeBuffer);
			pstmt.setInt(1, FeatureTypeId);
			pstmt.setBinaryStream(2, inputStream, inputStream.available());
			pstmt.executeUpdate();
			return true;
		} catch (Exception err) {
			System.out.println("插入特征出错");
			return false;

		}
	}
	//得到特征
	public boolean GetFeature(double[][] Feature, int[] DBDataId,
			int FeatureDim, int FeatureTypeId) {
		try {
			ResultSet rs = db_Query("select FeatureData,BiImageId from FeatureData where FeatureTypeId ="
					+ FeatureTypeId);
			int num = 0;
			while (rs.next()) {
				byte[] b = rs.getBytes(1);
				DBDataId[num] = rs.getInt(2);
				long l;
				int flag = 0;
				for (int i = 0; i < b.length;) {
					l = b[i++];
					l &= 0xff;
					l |= ((long) b[i++] << 8);
					l &= 0xffffl;
					l |= ((long) b[i++] << 16);
					l &= 0xffffffl;
					l |= ((long) b[i++] << 24);
					l &= 0xffffffffl;
					l |= ((long) b[i++] << 32);
					l &= 0xffffffffffl;
					l |= ((long) b[i++] << 40);
					l &= 0xffffffffffffl;
					l |= ((long) b[i++] << 48);
					l &= 0xffffffffffffffl;
					l |= ((long) b[i++] << 56);
					l &= 0xffffffffffffffffl;
					double tmp = Feature[num][(i - 1) >> 3] = Double
							.longBitsToDouble(l);
					if (Double.isNaN(tmp)) {
						flag = 1;
					}
				}
				if (flag == 1)
					System.out.println("BiImageId:" + DBDataId[num]);
				num++;
			}
			rs.close();
			return true;
		} catch (Exception err) {
			System.out.println("读取特征出错");
			return false;
		}
	}
	//查询
	public ResultSet db_Query(String sql) {
		try {
			return st.executeQuery(sql);
		} catch (Exception err) {
			System.out.println("查询出错，请检查sql语句");
			return null;
		}

	}
	//关闭
	public void db_Close() {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("数据库关闭失败");
		}

	}
}
