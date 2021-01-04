package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAO {

	//	データソースを保存するフィールド
	//	何度も同じデータソースを使って接続を取得できる
	//	DAOのサブクラス間でデータソースは共有するので、staticにする
	static DataSource ds;

	//	データソースへ接続するオブジェクトを取得するメソッド
	public Connection getConnection() {
		try {
			//		データソースがない場合は新しく生成する
			if (ds == null) {
				InitialContext ic = new InitialContext();
				// データソースの生成
				ds = (DataSource) ic.lookup("java:comp/env/jdbc/ideabox");
			}

			// 戻り値として接続を返す
			return ds.getConnection();



		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("DAO#getConnection()での例外");
		} catch (SQLException e) {
			System.out.println("DAO#getConnection()での例外");
			e.printStackTrace();
		}
		// 取得できなかった場合、nullを返す
//		System.out.println("データベースへの接続を取得できませんでした@DAO#getConnection");
		return null;
	}
}
