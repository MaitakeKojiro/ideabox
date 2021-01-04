package bean;
// detail.jsp用で使うBean

import java.io.Serializable;

public class Detail implements Serializable{
	private int insId;
	private int persId;
	private String imgPath;
	private String imgName;
	private String persDate;
	private String userId;
	private String userName;
	private byte[] userIcon;

	public int getInsId() {
		return insId;
	}
	public void setInsId(int insId) {
		this.insId = insId;
	}
	public int getPersId() {
		return persId;
	}
	public void setPersId(int persId) {
		this.persId = persId;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getPersDate() {
		return persDate;
	}
	public void setPersDate(String date) {
		this.persDate = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public byte[] getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(byte[] userIcon) {
		this.userIcon = userIcon;
	}

}
