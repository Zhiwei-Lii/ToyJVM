package classfile;

public class ConstantFactory {
    static final int CONSTANT_Class = 7;
    static final int CONSTANT_Fieldref = 9;
    static final int CONSTANT_Methodref = 10;
    static final int CONSTANT_InterfaceMethodref = 11;
    static final int CONSTANT_String = 8;
    static final int CONSTANT_Integer = 3;
    static final int CONSTANT_Float = 4;
    static final int CONSTANT_Long = 5;
    static final int CONSTANT_Double = 6;
    static final int CONSTANT_NameAndType = 12;
    static final int CONSTANT_Utf8 = 1;
    static final int CONSTANT_MethodHandle = 15;
    static final int CONSTANT_MethodType = 16;
    static final int CONSTANT_InvokeDynamic = 18;

    public static ConstantInfo newConstantInfo(ClassReader reader, ConstantPool cp) {
	int tag = (int) reader.readU1();

	switch (tag) {
	case CONSTANT_Class: {
	    System.out.println("Constant:class");
	    return new ConstantClassInfo(reader);
	}
	case CONSTANT_Fieldref: {
	    System.out.println("Constant:fieldref");
	    return new ConstantFieldrefInfo(reader);
	}
	case CONSTANT_Methodref: {
	    System.out.println("Constant:methodref");
	    return new ConstantMethodrefInfo(reader);
	}
	case CONSTANT_InterfaceMethodref: {
	    System.out.println("Constant:interface");
	    return new ConstantInterfaceMethodrefInfo(reader);
	}
	case CONSTANT_String: {
	    System.out.println("Constant:string");
	    return new ConstantStringInfo(reader);
	}
	case CONSTANT_Integer: {
	    System.out.println("Constant:integer");
	    return new ConstantIntegerInfo(reader);
	}
	case CONSTANT_Float: {
	    System.out.println("Constant:float");
	    return new ConstantFloatInfo(reader);
	}
	case CONSTANT_Long: {
	    System.out.println("Constant:long");
	    return new ConstantLongInfo(reader);
	}
	case CONSTANT_Double: {
	    System.out.println("Constant:double");
	    return new ConstantDoubleInfo(reader);
	}
	case CONSTANT_NameAndType: {
	    System.out.println("Constant:nameAndType");
	    return new ConstantNameAndTypeInfo(reader);
	}
	case CONSTANT_Utf8: {
	    System.out.println("Constant:utf-8");
	    return new ConstantUtf8Info(reader);
	}
	default: {
	    throw new Error("ConstantFactory :: unSupported constant");
	}
	}
    }
}
