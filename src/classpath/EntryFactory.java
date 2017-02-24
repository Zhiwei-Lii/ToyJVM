package classpath;

public class EntryFactory {

    public static Entry newEntry(String path) {
	if (isZipEntry(path)) {
	    return new ZipEntry(path);

	} else if (isCompositeEntry(path)) {
	    return new CompositeEntry(path);

	} else if (isWildcardEntry(path)) {
	    return new WildcardEntry(path);

	} else {
	    return new DirEntry(path);
	}
    }

    private static boolean isZipEntry(String path) {
	return path.endsWith(".jar") || path.endsWith(".JAR") || path.endsWith(".zip") || path.endsWith(".ZIP");
    }

    private static boolean isCompositeEntry(String path) {
	return path.contains(":") || path.contains(";");
    }

    private static boolean isWildcardEntry(String path) {
	return path.endsWith("*");
    }

}
