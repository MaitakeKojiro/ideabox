package tool;
// staticメソッドを保管
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Logic {

	/**
	 * InputStreamをバイト配列にする
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16777215];
		while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		return buffer.toByteArray();
	}
}
