package classfile;

import classfile.attribute.AttributeFactory;
import classfile.attribute.AttributeInfo;
import classfile.constant.ConstantClassInfo;

/**
 * This class is the model of JVM Class Specification
 * 
 * @author misen
 *
 */

public class ClassFile {
    long magic;
    long minorVersion;
    long majorVersion;
    RawConstantPool constantPool;
    long accessFlags;
    long thisClass;
    long superClass;
    long[] interfaces;
    MemberInfo[] fields;
    MemberInfo[] methods;
    AttributeInfo[] attributes;

    public ClassFile(byte[] classData) {
        ClassReader reader = new ClassReader(classData);
        set(reader);
    }

    public int accessFlags() {
        return (int) accessFlags;
    }

    public String thisClassName() {
        ConstantClassInfo c = (ConstantClassInfo) constantPool.getConstantInfo(thisClass);
        return constantPool.getUtf8(c.nameIndex);
    }

    public String superClassName() {
        if (!thisClassName().equals("java/lang/Object")) {
            ConstantClassInfo c = (ConstantClassInfo) constantPool.getConstantInfo(superClass);
            return constantPool.getUtf8(c.nameIndex);
        }
        return null;
    }

    public String[] interfaceNames() {
        String[] interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaceNames.length; i++) {
            ConstantClassInfo c = (ConstantClassInfo) constantPool.getConstantInfo(interfaces[i]);
            interfaceNames[i] = constantPool.getUtf8(c.nameIndex);
        }

        return interfaceNames;
    }

    public RawConstantPool rawConstantPool() {
        return constantPool;
    }

    public MemberInfo[] fields() {
        return fields;
    }

    public MemberInfo[] methods() {
        return methods;
    }

    public AttributeInfo[] attributes() {
        return attributes;
    }

    private void set(ClassReader reader) {
        setMagic(reader);
        setVersion(reader);
        setConstantPool(reader);
        setAccessFlags(reader);
        setThisClass(reader);
        setSuperClass(reader);
        setInterfaces(reader);
        setFields(reader);
        setMethods(reader);
        setAttributes(reader);
    }

    private void setConstantPool(ClassReader reader) {
        constantPool = new RawConstantPool(reader);
    }

    private void setMagic(ClassReader reader) {
        magic = reader.readU4();

        if (Long.toHexString(magic).equals("0xCAFEBABE")) {
            throw new Error("java.lang.ClassFormatError: magic!");
        }
    }

    private void setVersion(ClassReader reader) {
        minorVersion = reader.readU2();
        majorVersion = reader.readU2();
    }

    private void setAccessFlags(ClassReader reader) {
        accessFlags = reader.readU2();
    }

    private void setThisClass(ClassReader reader) {
        thisClass = reader.readU2();
    }

    private void setSuperClass(ClassReader reader) {
        superClass = reader.readU2();
    }

    private void setInterfaces(ClassReader reader) {
        int n = (int) reader.readU2();
        interfaces = new long[n];

        for (int i = 0; i < n; i++) {
            interfaces[i] = reader.readU2();
        }
    }

    private void setFields(ClassReader reader) {
        int n = (int) reader.readU2();
        fields = new MemberInfo[n];

        for (int i = 0; i < n; i++) {
            fields[i] = new MemberInfo(reader, constantPool);
        }
    }

    private void setMethods(ClassReader reader) {
        int n = (int) reader.readU2();
        methods = new MemberInfo[n];

        for (int i = 0; i < n; i++) {
            methods[i] = new MemberInfo(reader, constantPool);
        }
    }

    private void setAttributes(ClassReader reader) {
        int length = (int) reader.readU2();
        attributes = new AttributeInfo[length];
        for (int i = 0; i < length; i++) {
            attributes[i] = AttributeFactory.newAttributeInfo(reader, constantPool);
        }
    }
}
