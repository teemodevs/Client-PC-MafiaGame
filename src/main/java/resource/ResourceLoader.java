package resource;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class ResourceLoader {
	public static final String RESOURCE_ROOT_PATH = "src/main/resources/";

	private static Map<String, ImageIcon> imageIconMap = new HashMap<String, ImageIcon>();

	public static ImageIcon getImageIconResource(String filePath) {
		ImageIcon imageIcon = imageIconMap.get(filePath);

		if (imageIcon == null) {
			imageIcon = new ImageIcon(RESOURCE_ROOT_PATH + filePath);
			imageIconMap.put(filePath, imageIcon);
		}

		return imageIcon;
	}

}
