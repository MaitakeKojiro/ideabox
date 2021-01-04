package bean;
// ユーザー情報
import java.io.Serializable;

public class User implements Serializable{
	/* フィールド */
	private int id;
	private String userName;
	private String pass;
	private byte[] icon;

	/* ゲッター、セッター */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}


}
