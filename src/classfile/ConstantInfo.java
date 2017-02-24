package classfile;

public class ConstantInfo {
    static long CONSTANT_Class = 7;
    static long CONSTANT_Fieldref = 9;
    static long CONSTANT_Methodref = 10;
    static long CONSTANT_InterfaceMethodref = 11;
    static long CONSTANT_String = 8;
    static long CONSTANT_Integer = 3;
    static long CONSTANT_Float = 4;
    static long CONSTANT_Long = 5;
    static long CONSTANT_Double = 6;
    static long CONSTANT_NameAndType = 12;
    static long CONSTANT_Utf8 = 1;
    static long CONSTANT_MethodHandle = 15;
    static long CONSTANT_MethodType = 16;
    static long CONSTANT_InvokeDynamic = 18;
    
    public ConstantInfo(ClassReader reader, ConstantPool cp){
	long tag = reader.readU1();
    }

}
