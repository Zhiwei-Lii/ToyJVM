package classpath;

import java.io.File;
import java.io.FileInputStream;

public class DirEntry implements Entry {
    String absDir;

    public DirEntry(String path) {
        absDir = path;
    }

    /**
     * 
     * @param className
     * 		className以.class结尾, 如java/lang/Object.class
     */
    public byte[] readClass(String className) {
        String absClassPath = joinPath(absDir, className);
        File file = new File(absClassPath);
        byte[] result = new byte[(int) file.length()];

        try {
            FileInputStream in = new FileInputStream(file);

	    int n = 0;
	    do{
		n = in.read(result, n, result.length);
	    } while(n!=-1);

            in.close();
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private String joinPath(String absDir, String className) {
        if (absDir.endsWith("/")) {
            return absDir + className;
        } else {
            return absDir + "/" + className;
        }
    }
}
