package action;
// idを参照して

// 遷移先：WEB-INF/jsp/contents/detail.jsp

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Detail;
import bean.Inspiration;
import dao.InsDAO;
import dao.PersDAO;

public class InsIdSelectAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String insId = request.getParameter("insId");
		// DAOを生成
		InsDAO iDao = new InsDAO();
		PersDAO pDao = new PersDAO();

		// idから検索するメソッド
		Inspiration i = iDao.selectById(insId);
		List<Detail> persList = pDao.selectPersWithUserByInsId(insId);

		// リクエスト属性に検索したアイディアと完成品を保存
		request.setAttribute("inspiration", i);
		request.setAttribute("persList", persList);

		return "/WEB-INF/jsp/contents/detail.jsp";
	}
}
