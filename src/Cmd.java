
public class Cmd {
    boolean helpFlag;
    boolean versionFlag;
    String cpOption = null; // -cp后面跟的那个path, 用户指定的path
    String xJreOption = null; // 指定的jre路径, 路径以"/"结尾
    String class_ = null; // 需要执行的class

    private String[] args;
    private int cur;

    public Cmd(String[] args) {
        this.args = args;
    }

    public void parseCmd() {
        while (hasMoreFlags()) {
            if (isHelp()) {
                printUsage();
                break;
            }
            else if (isClassPath()) {
                cur++;
                cpOption = args[cur++];
            }
            else if (isXjre()) {
                cur++;
                xJreOption = args[cur++];
            }
            else if (isUndefinedFlag()) {
                throw new Error("Undefined Flag");
            }
            else {
                class_ = args[cur++];
            }
        }
    }

    private void printUsage() {
        System.out.println("This is a simple version of JVM");
        System.out.println("  -?/-help");
        System.out.println("  -cp/-classpath");
    }

    private boolean isHelp() {
        return args[cur].equals("-?") || args[cur].equals("-help");
    }

    private boolean isClassPath() {
        return args[cur].equals("-cp") || args[cur].equals("-classpath");
    }

    private boolean isXjre() {
        return args[cur].equals("-Xjre");
    }

    private boolean isUndefinedFlag() {
        return args[cur].startsWith("-");
    }

    private boolean hasMoreFlags() {
        return cur < args.length;
    }

}
