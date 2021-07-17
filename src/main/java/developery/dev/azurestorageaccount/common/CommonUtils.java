package developery.dev.azurestorageaccount.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonUtils {

	public static String readStringFromFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);

	    return Files.readAllLines(path).get(0);
	}
	
	public static boolean deleteIfExists(String fileName) throws IOException {
		Path path = Paths.get(fileName);

	    return Files.deleteIfExists(path);
	}
}
