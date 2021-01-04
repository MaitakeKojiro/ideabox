package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.Detail;
import bean.Perspiration;
import tool.Logic;

/*
 * 完成品に関するDAO
 */
public class PersDAO extends DAO {
	/**
	 * perspirationテーブルにデータを追加
	 * 呼び出し元:PersInsertAction
	 * @param user
	 * @return true->成功 : false-> 失敗
	 */
	public boolean insert(Perspiration pers){
		// 変数の用意
		String sql = "insert into perspiration value(null,?,?,?,?,?,now())";
		Connection con = null;
		PreparedStatement ps = null;
		try {

			con = getConnection(); 			// 接続
			con.setAutoCommit(false);		// オートコミットモードOFF
			ps = con.prepareStatement(sql); // SQL文の格納

			// SQL文に値を格納
			ps.setInt(1, pers.getInsId());
			ps.setInt(2, pers.getUserId());
			ps.setString(3, pers.getImgPath());
			ps.setString(4, pers.getImgName());
			ps.setString(5, pers.getComment());

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
				e.printStackTrace();
			}
		}
		//失敗
		return false;
	}

	/**
	 * inspirationのIDに対応するPerspirationを検索するメソッド
	 *
	 * 呼出し元：InsIdSelectAction
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Perspiration> selectById(String id) {
		// 変数の用意
		List<Perspiration> list = new ArrayList<>();
		Perspiration pers = null;
		String sql = "select * from perspiration where ins_id = ? order by pers_id desc";
		// 接続
		try (
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);)
		{
			// プレースホルダに値を格納
			ps.setString(1,id);
			// SQL実行
			ResultSet rs = ps.executeQuery();
			// リストに格納
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			while (rs.next()) {
				pers = new Perspiration();
				pers.setPersId(rs.getInt("pers_id"));
				pers.setInsId(rs.getInt("ins_id"));
				pers.setUserId(rs.getInt("user_id"));
				pers.setImgPath(rs.getString("img_path"));
				pers.setImgName(rs.getString("img_name"));
				pers.setComment(rs.getString("comment"));
				pers.setDate(formatter.format(rs.getTimestamp("date")));



				list.add(pers);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 戻り値
		return list;

	}

	public List<Detail> selectPersWithUserByInsId(String insId) {
		// 変数の用意
		List<Detail> list = new ArrayList<>();
		Detail detail = null;
		String sql ="SELECT i.ins_id, p.pers_id, p.img_path, p.img_name, p.date, a.user_id, a.user_name, a.icon from inspiration AS i left outer join perspiration AS p on i.ins_id = p.ins_id left outer join ACCOUNT AS a on p.user_id = a.user_id WHERE p.ins_id = ? GROUP BY p.pers_id ORDER BY i.ins_id";
		// 接続
		try (
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);)
		{
			// プレースホルダに値を格納
			ps.setString(1,insId);
			// SQL実行
			ResultSet rs = ps.executeQuery();
			// リストに格納
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			while (rs.next()) {
				detail = new Detail();
				detail.setPersId(rs.getInt("i.ins_id"));
				detail.setInsId(rs.getInt("p.pers_id"));
				detail.setImgPath(rs.getString("p.img_path"));
				detail.setImgName(rs.getString("p.img_name"));
				detail.setPersDate(formatter.format(rs.getTimestamp("date")));
				detail.setUserId("a.user_id");
				detail.setUserName(rs.getString("a.user_name"));

				InputStream inputStream = rs.getBinaryStream("icon");
				// iconカラムに値があるときだけ
				if (inputStream != null) {
					byte[] icon = Logic.convertInputStreamToByteArray(inputStream);
					detail.setUserIcon(icon);
				}





				list.add(detail);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		// 戻り値
		return list;
	}
}
