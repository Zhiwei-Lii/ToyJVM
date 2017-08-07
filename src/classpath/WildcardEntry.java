package classpath;

import java.io.File;
import java.util.ArrayList;

public class WildcardEntry implements Entry {
    ArrayList<Entry> entries = new ArrayList<Entry>();

    public WildcardEntry(String path) {
        String baseDir = path.substring(0, path.length() - 1); // remove *
        File folder = new File(baseDir);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.getName().endsWith(".jar") || file.getName().endsWith(".JAR")) {
                String filePath = baseDir + file.getName();
                Entry entry = new ZipEntry(filePath);
                entries.add(entry);
            }
        }
    }

    public byte[] readClass(String className) {
        for (Entry entry : entries) {
            byte[] result = entry.readClass(className);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

}
