package classpath;

public class EntryFactory {

    public static Entry newEntry(String path) {
	if (isDirEntry(path)) {
	    return new DirEntry(path);

	} else if (isZipEntry(path)) {
	    return new ZipEntry(path);

	} else if (isCompositeEntry(path)) {
	    return new CompositeEntry(path);

	} else if (isWildcardEntry(path)) {
	    return new WildcardEntry(path);

	} else {
	    throw new Error("EntryFactory:: newEntry");
	}
    }

    private static boolean isDirEntry(String path) {
	return path.endsWith("/");
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
