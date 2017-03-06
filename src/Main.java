import classfile.ClassFile;
import classfile.Interpreter;
import classpath.ClassPath;

public class Main {

    public static void startJVM(Cmd cmd) {
	ClassPath cp = new ClassPath(cmd.xJreOption, cmd.cpOption);
	
	String className = cmd.class_.replaceAll("\\.", "/");
	byte[] result = cp.readClass(className);
	
	ClassFile classFile = new ClassFile(result);
	Interpreter.interpret(classFile.methods[1]);

    }

    public static void main(String[] args) {
	Cmd cmd = new Cmd(args);
	cmd.parseCmd();
	
	startJVM(cmd);
    }
}

