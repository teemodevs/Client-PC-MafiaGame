package resource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * 파일, 이미지 등 리소스 로더 클래스
 */
public class ResourceLoader {
	private static final String RESOURCE_ROOT_PATH = "src/main/resources/";

	private static Map<String, ImageIcon> imageIconMap = new HashMap<>();
	private static Map<String, File> fileMap = new HashMap<>();
	
	public static ImageIcon getImageIconResource(String filePath) {
		ImageIcon imageIcon = imageIconMap.get(filePath);

		if (imageIcon == null) {
			imageIcon = new ImageIcon(RESOURCE_ROOT_PATH + filePath);
			imageIconMap.put(filePath, imageIcon);
		}

		return imageIcon;
	}
	
	public static File getFileResource(String filePath) {
		File file = fileMap.get(filePath);
		
		if (file == null) {
			file = new File(RESOURCE_ROOT_PATH + filePath);
			fileMap.put(filePath, file);
		}
		
		return file;
	}

}
