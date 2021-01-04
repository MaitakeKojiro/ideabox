//Inspirationテーブルにアイディアを登録
//戻り値には確認ページを返す
package action;
// TODO 失敗したときの処理も考える

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Inspiration;
import bean.User;
import dao.InsDAO;

public class InsInsertAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		// 各リクエストパラメータの受け取り
		//		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getId(); 						// 投稿したユーザーのID
		String title = request.getParameter("title"); 	// タイトル
		String body = request.getParameter("body"); 	// 本文


		// タイトルが空文字はいけない
		if(title.isEmpty()) {
			request.setAttribute("msg","タイトルは空では送信できません");
			return "/WEB-INF/jsp/form/ins-form.jsp";
		}


		// Beanを生成
		Inspiration inspiration = new Inspiration();

		// Beanに値を格納
		inspiration.setUserId(userId);
		inspiration.setTitle(title);
		inspiration.setBody(body);

		// データベースへアイディアを登録
		InsDAO dao = new InsDAO();
		dao.insert(inspiration);

		// リダイレクト
		response.sendRedirect("InsSelect.action");

		// 結果画面へ
		return "/WEB-INF/jsp/form/result/ins-result.jsp";
	}
}
