package action;
// ログアウト
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		// ログインしている証明としてセッション属性にUserの情報を格納しているので、それを消す
		if(session.getAttribute("user")!=null) {
			session.removeAttribute("user");
		}


		return "index.jsp";
	}

}
