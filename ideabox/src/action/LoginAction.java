package action;
// ログイン
// 呼び出し元:index.jsp
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDAO;

public class LoginAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 名前とパスワードを取得
		String userName = request.getParameter("user_name");
		String pass = request.getParameter("pass");

		System.out.println(userName);
		System.out.println(pass);

		// 名前とパスワードが一致したらユーザの情報が返ってくる
		UserDAO dao = new UserDAO();
		User user = null;

		// ログイン成功
		if((user = dao.login(userName,pass))!=null) {
			// ユーザー情報をセッション属性に格納
			HttpSession session= request.getSession();
			session.setAttribute("user",user);
			response.sendRedirect("/ideabox/InsSelect.action");
			return "/InsSelect.action";
		}

		// ログイン失敗
		request.setAttribute("loginMsg","ユーザー名、またはパスワードが間違っています。");
		return "index.jsp";
	}

}
