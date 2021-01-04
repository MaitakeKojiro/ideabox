package tool;

// ユーザーIDからアイコン画像を取得し、表示する
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// IDからアイコン画像を取得
		String id = request.getParameter("id");
		UserDAO dao = new UserDAO();
		BufferedImage img = dao.selectIconById(id);
		// アイコンがない場合、用意しているゲスト用のアイコンを使う
		if (img == null) {
			img = ImageIO.read(new File("/usr/local/tomcat/webapps/ideabox/image/user-icon/guest.png"));
		}
		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(img, "png", os);
		os.flush();
	}

}
