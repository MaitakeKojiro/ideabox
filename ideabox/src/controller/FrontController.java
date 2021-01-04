// フロントコントローラ
// urlを受け取るとexecuteメソッドを呼び出し、アクションを実行

package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;

// 末尾が.actionで終わるＵＲＬに対応付けする
// つまりhtml（ブラウザ）側から送られてくるformとかの宛先が.actionとなっている
@MultipartConfig
@WebServlet("*.action")
public class FrontController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			// パスの取得。先頭の一文字（/）を除去。
			String path = request.getServletPath().substring(1);
			// パスの加工。.aをAに、/を.に変える
			// ここまで加工することでアクションのクラス名が得られる
			String name = path.replace(".a", "A").replace('/', '.');
			// アクションの生成。ClassクラスのforNameメソッドとnewInstanceメソッドを使う
			// 加工したパスに対応するオブジェクトを返すforName
			// オブジェクトがあらわす、クラスの新しく割り当てられたインスタンスを生成するnewInsntace
//			System.out.println("アクションのインスタンス生成前");
			Action action = (Action) Class.forName("action." + name).getDeclaredConstructor().newInstance();
			// アクションの実行。生成したアクションのインスタンスでexecuteメソッド（実行）を呼び出す。
			// ついでに戻り値として返されるフォワード先のurlを取得しておく
//			System.out.println("アクションの実行前");
			String url = action.execute(request, response);
//			System.out.println("アクションの実行後");
			// 先ほど取得したフォワード先のurlを使いフォワードする
			request.getRequestDispatcher(url).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

	// GETリクエストで送られてきても結局doPostを実行する
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
