package action;
// Perspirationテーブルにデータを追加
// 呼び出し元 detail.jsp
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Perspiration;
import bean.User;
import dao.PersDAO;
public class PersInsertAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 各リクエストパラメータの受け取り
		String insId = request.getParameter("ins_id");
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		Part part = request.getPart("image");
		String comment = request.getParameter("comment");
		String imgName=null;
		String imgPath=null;


		// 画像はファイルとして保存。保存先のパスが返ってくるのでそれを取得
		if(part.getSize()!=0) {
		imgName = part.getSubmittedFileName();
		imgPath = fileUp(part,request);


		}else {
			// ファイル(画像)が空の場合は、追加せず、失敗処理を行う
			request.setAttribute("msg","追加に失敗しました。画像がセットされているか確認してください");
			return "InsIdSelect.action?insId="+insId;
		}


		// Beanに値を格納
		Perspiration pers = new Perspiration();

		pers.setInsId(Integer.parseInt(insId));
		pers.setUserId(userId);
		pers.setImgPath(imgPath);
		pers.setImgName(imgName);
		pers.setComment(comment);

		// データベースへBeanを登録
		PersDAO dao = new PersDAO();
		if(dao.insert(pers)) {
			request.setAttribute("msg","追加に成功しました");
			response.sendRedirect("InsIdSelect.action?insId="+insId);
		}else {
			request.setAttribute("msg","追加に失敗しました");
		}

		// Eclipse が自動リフレッシュしてくれるまでの待機時間
		 Thread.sleep(3000);
		// 元の画面へ
		return "InsIdSelect.action?ins_id="+insId;

	}
	/**
	 * 画像をパスが被らないように保存するメソッド
	 */
	public String fileUp(Part part,HttpServletRequest request) {
		//UUIDというかぶらないランダムなID名を生成
		String filePath = UUID.randomUUID().toString();
		try {
			// 実行環境によってイラストの保存場所を変える
//			Path dir = FileSystems.getDefault().getPath("/usr/local/tomcat/webapps/ideabox/image/perspiration/"+filePath);
			Path dir = FileSystems.getDefault().getPath("/usr/local/tomcat/webapps/ideabox/image/perspiration/"+filePath);
			Files.createDirectory(dir);
			//そのディレクトリの下に画像を保存
			part.write(dir+"/"+part.getSubmittedFileName());
//			System.out.println("画像の保存先"+dir+"\\"+part.getSubmittedFileName());

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//保存したディレクトリ名を返す
		return filePath;

	}
}
