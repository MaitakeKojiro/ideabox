package dao;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import bean.User;
import tool.Logic;

// ユーザー情報(ACCOUNTテーブル)
public class UserDAO extends DAO {
	/**
	 * ログイン
	 * ログイン処理、成功時はセッション属性にユーザーの情報を格納したBeanを返す
	 * @param name
	 * @return
	 */
	public User login(String name, String pass) {
		// ユーザー名とパスワードが一致していないとエラー
		String sql = "select * from account where user_name = ? and pass = ?";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, name);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			// ログイン成功：ユーザーのBeanを返す
			if (rs.next()) {
				int id = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				InputStream inputStream = rs.getBinaryStream("icon");

				User user = new User();
				user.setId(id);
				user.setUserName(userName);

				// アイコンはカラムに値があるときだけ
				if (inputStream != null) {
					byte[] icon = Logic.convertInputStreamToByteArray(inputStream);
					user.setIcon(icon);

				}
				return user;
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		// ログイン失敗：nullを返す
		return null;
	}

	/**
	 * ユーザー情報を登録
	 * 呼び出し元:EntryAction
	 * @param user
	 * @return true->成功 : false-> 失敗
	 */
	public boolean insert(User user) {
		// 変数の用意
		String sql = "insert into account(user_name,pass,icon) values(?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {

			con = getConnection(); // 接続
			con.setAutoCommit(false); // オートコミットモードOFF
			ps = con.prepareStatement(sql); // SQL文の格納

			// SQL文に値を格納
			String name = user.getUserName();
			String pass = user.getPass();
			byte[] icon = user.getIcon();
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.setBinaryStream(3, new ByteArrayInputStream(icon));

			// 実行
			int line = ps.executeUpdate();

			// 結果を示す変数
			boolean result;

			// 成否で処理を分ける
			switch (line) {
			case 0: // 失敗
				con.rollback();
				result = false;
				break;
			default: // 成功
				con.commit();
				result = true;
				break;
			}

			con.setAutoCommit(true); // オートコミットモードON

			return result;

		} catch (SQLException e) {
			System.out.println("UserDAO#insert()での例外");
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true); // オートコミットモードON
				con.close(); // 切断
				ps.close();
			} catch (SQLException e) {
				System.out.println("finallyブロックでの例外");
				e.printStackTrace();
			}
		}
		//失敗
		return false;
	}

	/**
	 * 全ユーザー情報を取得
	 * @return
	 */
	public List<User> select() {
		// 各変数の準備
		List<User> userList = new ArrayList<>();
		User user;
		String sql = "select * from account";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();

			// Beanに値を格納
			while (rs.next()) {
				user = new User();
				int id = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				String pass;
				pass = rs.getString("pass");
				InputStream inputStream = rs.getBinaryStream("icon");

				user.setId(id);
				user.setUserName(userName);
				user.setPass(pass);
				// iconカラムに値があるときだけ
				if (inputStream != null) {
					byte[] icon = Logic.convertInputStreamToByteArray(inputStream);
					user.setIcon(icon);
				}

				userList.add(user);
			}

		} catch (SQLException e) {
			System.out.println("UserDAO#select()での例外");
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println("UserDAO#convertInputStream()での例外");
			e.printStackTrace();
		}

		return userList;

	}

	/**
	 * ユーザーIDからアイコン画像を取得
	 * @param id
	 * @return BufferedImage
	 *
	 * 呼び出し元:tool.GetImageServlet
	 */
	public BufferedImage selectIconById(String id) {
		// 各変数の準備
		String sql = "select icon from account where user_id = ?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			// iconにデータがあるときだけ
			while (rs.next()) {
				InputStream is = rs.getBinaryStream("icon");
				BufferedInputStream bis = new BufferedInputStream(is);
				return ImageIO.read(bis);
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// icon列がnullの場合
		return null;

	}
}
