package action;
// WEB-INF内の画面遷移に使う
// jsp側ではhref属性に"Move.action?path=WebContentから見た遷移したいページ"とすれば良い

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class MoveAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return request.getParameter("path");
	}

}
