import classpath.ClassPath;
import rtda.heap.ClassLoader;
import rtda.heap.Class;
import rtda.heap.Method;

public class Main {

    public static void startJVM(Cmd cmd) {
        ClassPath cp = new ClassPath(cmd.xJreOption, cmd.cpOption);
        ClassLoader loader = new ClassLoader(cp);

        String className = cmd.class_.replaceAll("\\.", "/");
        Class mainClass = loader.loadClass(className);
        Method mainMethod = mainClass.getMainMethod();
        Interpreter.interpret(mainMethod);
    }

    public static void main(String[] args) {
        Cmd cmd = new Cmd(args);
        cmd.parseCmd();

        startJVM(cmd);
    }
}
