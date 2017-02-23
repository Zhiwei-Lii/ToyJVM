package classpath;

import java.io.InputStream;
import java.util.zip.ZipFile;

public class ZipEntry implements Entry {
    String absPath;

    public ZipEntry(String path) {
	absPath = path;
    }

    public byte[] readClass(String className) {
	try {
	    ZipFile zFile = new ZipFile(absPath);
	    java.util.zip.ZipEntry classFile = zFile.getEntry(className);


	    byte[] result = new byte[(int) classFile.getSize()];

	    InputStream in = zFile.getInputStream(classFile);
	    in.read(result);

	    in.close();
	    zFile.close();

	    return result;

	} catch (Exception e) {
	    return null;
	}
    }
}
