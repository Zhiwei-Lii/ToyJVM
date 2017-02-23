package classpath;

public class ClassPath {
    Entry bootClassPath;
    Entry extClassPath;
    Entry userClassPath;

    public ClassPath(String jreOption, String cpOption) {
	parseBootAndExtClassPath(jreOption);
	parseUserClassPath(cpOption);
    }

    public byte[] readClass(String className) {
	className = className + ".class";

	if (bootClassPath.readClass(className) != null) {
	    return bootClassPath.readClass(className);

	} else if (extClassPath.readClass(className) != null) {
	    return extClassPath.readClass(className);

	} else {
	    return userClassPath.readClass(className);
	}
    }

    private void parseBootAndExtClassPath(String jreOption) {
	String jreLibPath = jreOption + "lib" + "/*";
	bootClassPath = EntryFactory.newEntry(jreLibPath);

	String jreExtPath = jreOption + "lib/ext" + "/*";
	extClassPath = EntryFactory.newEntry(jreExtPath);
    }

    private void parseUserClassPath(String cpOption) {
	if (cpOption == null) {
	    cpOption = "./";
	}

	userClassPath = EntryFactory.newEntry(cpOption);
    }

}
