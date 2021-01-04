//全てのアクションのスーパークラス
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
//	継承したそれぞれのアクションに具体化させるメソッド
//	引数はリクエストパラメータの取得や、リクエスト属性の設定などに使う
//	レスポンスオブジェクトは基本JSPで出力するので使わない
//	戻り値は文字列にして、フォワード先のURL(JSP)を返す
//	フロントコントローラはこれを使い、指定されたJSPファイルへフォワードする
	public abstract String execute(HttpServletRequest request, HttpServletResponse response)throws Exception;


}
