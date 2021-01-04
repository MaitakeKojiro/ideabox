//アイディアのDAO
//データベースへ追加するメソッド
//データベースを検索するメソッドがある
package dao;

// TODO 写真を入れる処理も作る
// TODO 例外の設定をちゃんとする
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.Inspiration;

public class InsDAO extends DAO {

	/**
	 * データベースへアイディアを追加するメソッド
	 *
	 * 呼び出し元：InsInsertAction
	 *
	 *
	 * @param inspiration
	 * @return 成功 -> true : 失敗 -> false
	 * @throws Exception
	 */
	public boolean insert(Inspiration ins) {
		// 変数の用意
		String sql = "insert into inspiration(ins_id,user_id,title,body,img_path,img_name,date) value(null,?,?,?,?,?,now())";
		Connection con = null;
		PreparedStatement ps = null;
		try {

			con = getConnection(); // 接続
			con.setAutoCommit(false); // オートコミットモードOFF
			ps = con.prepareStatement(sql); // SQL文の格納

			// プレースホルダに値を格納
			ps.setInt(1, ins.getUserId());
			ps.setString(2, ins.getTitle());
			ps.setString(3, ins.getBody());
			ps.setString(4, ins.getImgPath());
			ps.setString(5, ins.getImgName());

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
		// 例外が発生した場合は失敗
		return false;
	}

	/**
	 * 全検索
	 * @throws Exception
	 * 呼び出し元：InsSelectAction
	 */
	public List<Inspiration> select() {
		// 変数の用意
		List<Inspiration> list = new ArrayList<>();
		Inspiration inspiration;
		String sql = "select * from inspiration order by ins_id desc";
		// 接続
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			// SQL実行
			ResultSet rs = ps.executeQuery();

			// リストに格納
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			while (rs.next()) {
				inspiration = new Inspiration();
				inspiration.setInsId(rs.getInt("ins_id"));
				inspiration.setUserId(rs.getInt("user_id"));
				inspiration.setTitle(rs.getString("title"));
				inspiration.setBody(rs.getString("body"));
				inspiration.setImgPath(rs.getString("img_path"));
				inspiration.setImgName(rs.getString("img_name"));
				inspiration.setDate(formatter.format(rs.getDate("date")));

				list.add(inspiration);
			}
		} catch (SQLException e) {
			System.out.println("InsDAO#select()での例外");
			e.printStackTrace();
		}

		return list;

	}

	/**
	 * Idから詳細を取り出すメソッド
	 * データベースからアイディアと完成品の両方の情報を取り出す
	 *
	 * 呼び出し元：InsIdSelectAction
	 */
	public Inspiration selectById(String insId) {

		// データを詰め込むためのBeanを生成
		Inspiration i = new Inspiration();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// 接続
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("select * from inspiration where ins_id=?");) {
			st.setString(1, insId);

			// SQL文を実行し結果を取得
			ResultSet rs = st.executeQuery();
			// Beanに各値を詰め込んでいく
			rs.next();
			i.setInsId(rs.getInt("ins_id"));
			i.setUserId(rs.getInt("user_id"));
			i.setTitle(rs.getString("title"));
			i.setBody(rs.getString("body"));
			i.setImgPath(rs.getString("img_path"));
			i.setImgName(rs.getString("img_name"));
			i.setDate(formatter.format(rs.getDate("date")));

			// データベースからの切断
			st.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Beanを返す
		return i;
	}

}
