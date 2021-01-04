package action;
// アカウント登録

// 呼び出し元 entry.jsp

import java.io.InputStream;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.User;
import dao.UserDAO;
import tool.Logic;

// ユーザー情報を登録する
@MultipartConfig(fileSizeThreshold = 1, maxFileSize = 1048576)
public class EntryAction extends Action {
	String resultMsg;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("user_name");
		String pass = request.getParameter("pass");
		Part part = request.getPart("icon");
		InputStream inputStream = part.getInputStream();
		byte[] icon = Logic.convertInputStreamToByteArray(inputStream);

		// ユーザー名とパスワードの判定
		boolean a = userName.matches(".{4,16}");
		boolean b = pass.matches("[a-zA-Z0-9]{8,16}");
		if (!a || !b) {
			if (!a) {
				resultMsg = "ユーザー名は4～16字以内にしてください";
			}
			if (!b) {
				resultMsg = "パスワードは8～16字以内の半角英数字にしてください";
			}
			request.setAttribute("resultMsg", resultMsg);
			return "entry.jsp";
		}

		// 登録するとそのままログイン

		User user = new User();
		user.setUserName(userName);
		user.setPass(pass);
		user.setIcon(icon);
		UserDAO dao = new UserDAO();

		if (dao.insert(user)) {

			// 登録成功
			System.out.println("登録成功");
			request.setAttribute("user_name",userName);
			request.setAttribute("pass",pass);
			return "/Login.action";
		} else {
			// 登録失敗
			resultMsg = "アカウントの登録に失敗しました。ユーザー名が他と被っている恐れがあります。";

		}
		request.setAttribute("resultMsg", resultMsg);

		return "entry.jsp";
	}

}
