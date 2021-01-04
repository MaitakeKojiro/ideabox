package action;
// Inspirationテーブルから全検索
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Inspiration;
import dao.InsDAO;

public class InsSelectAction extends Action{
	public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception {

		// アイディアの検索
		InsDAO iDao = new InsDAO();
		List<Inspiration> insList = iDao.select();

		/*MainViewDAO dao = new MainViewDAO();
		List<MainView> mainList = dao.select();*/



		// リクエスト属性にリストを設定
		request.setAttribute("insList",insList);

		return "/WEB-INF/jsp/contents/main.jsp";

	}


}
