package classfile.constant;

import classfile.ClassReader;
import classfile.RawConstantPool;

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

    public static ConstantInfo newConstantInfo(ClassReader reader, RawConstantPool cp) {
	int tag = (int) reader.readU1();

	switch (tag) {
	case CONSTANT_Class: {
	    return new ConstantClassInfo(reader);
	}
	case CONSTANT_Fieldref: {
	    return new ConstantFieldrefInfo(reader);
	}
	case CONSTANT_Methodref: {
	    return new ConstantMethodrefInfo(reader);
	}
	case CONSTANT_InterfaceMethodref: {
	    return new ConstantInterfaceMethodrefInfo(reader);
	}
	case CONSTANT_String: {
	    return new ConstantStringInfo(reader);
	}
	case CONSTANT_Integer: {
	    return new ConstantIntegerInfo(reader);
	}
	case CONSTANT_Float: {
	    return new ConstantFloatInfo(reader);
	}
	case CONSTANT_Long: {
	    return new ConstantLongInfo(reader);
	}
	case CONSTANT_Double: {
	    return new ConstantDoubleInfo(reader);
	}
	case CONSTANT_NameAndType: {
	    return new ConstantNameAndTypeInfo(reader);
	}
	case CONSTANT_Utf8: {
	    return new ConstantUtf8Info(reader);
	}
	default: {
	    throw new Error("ConstantFactory :: unSupported constant");
	}
	}
    }
}
