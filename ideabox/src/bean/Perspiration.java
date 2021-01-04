package bean;
// 作品
import java.io.Serializable;

public class Perspiration implements Serializable{
	private int persId;		// 作品自身のID
	private int insId;			// 対応するアイディアのID
	private int userId;		// 制作者のID
	private String imgPath;	// 作品を保管したディレクトリ
	private String imgName;	// 作品のファイル名
	private String comment;	// 作者のコメント
	private String date;		// 追加された日時



	public int getPersId() {
		return persId;
	}
	public void setPersId(int persId) {
		this.persId = persId;
	}
	public int getInsId() {
		return insId;
	}
	public void setInsId(int insId) {
		this.insId = insId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int uesrId) {
		this.userId = uesrId;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}



}
