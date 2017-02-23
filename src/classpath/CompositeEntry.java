package classpath;

import java.util.ArrayList;

public class CompositeEntry implements Entry {
    private ArrayList<Entry> entries = new ArrayList<Entry>();

    public CompositeEntry(String pathList) {
        String[] paths = pathList.split(":|;");

        for (String path : paths) {
            Entry entry = EntryFactory.newEntry(path);
            entries.add(entry);
        }
    }

    public byte[] readClass(String className) {
        byte[] result = null;

        for (Entry entry : entries) {
            result = entry.readClass(className);

            if (result != null) { // If readClass succeed
                return result;
            }
        }

        return result;
    }

}
