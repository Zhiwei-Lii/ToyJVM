package classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DirEntry implements Entry {
    String absDir;

    /**
     * path以"/"结尾, 如"./"
     * @param path
     */
    public DirEntry(String path) {
        absDir = path;
    }

    /**
     * className是class文件的相对路径, 如java/lang/Object.class
     * 
     * @param className
     * @return
     */
    public byte[] readClass(String className) {
        String absClassPath = joinPath(absDir, className);
        File file = new File(absClassPath);
        byte[] result = new byte[(int) file.length()];

        try {
            FileInputStream in = new FileInputStream(file);
            in.read(result);
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
