package classfile;

public abstract class ConstantInfo {

}

// test
class ConstantIntegerInfo extends ConstantInfo {
    long val;

    public ConstantIntegerInfo(ClassReader reader) {
	val = reader.readU4();
    }
}

// untest
class ConstantFloatInfo extends ConstantInfo {
    double val;

    public ConstantFloatInfo(ClassReader reader) {
	// 这里的转换可能出问题, 待测试
	long bits = reader.readU4();
	val = Double.longBitsToDouble(bits);
    }
}

// untest
class ConstantLongInfo extends ConstantInfo {
    long val;

    // 可能出问题
    public ConstantLongInfo(ClassReader reader) {
	val = reader.readU8();
    }
}

// untest
class ConstantDoubleInfo extends ConstantInfo {
    double val;

    public ConstantDoubleInfo(ClassReader reader) {
	long bits = reader.readU8();
	val = Double.longBitsToDouble(bits);
    }
}

// test
class ConstantUtf8Info extends ConstantInfo {
    String str;

    public ConstantUtf8Info(ClassReader reader) {
	long length = reader.readU2();
	byte[] bytes = new byte[(int) length];

	for (int i = 0; i < length; i++) {
	    bytes[i] = (byte) reader.readU1();
	}

	try {
	    str = new String(bytes);
	} catch (Exception e) {
	    throw new Error("ConstantInfo :: ConstantUtf8Info wrong!");
	}
    }
}

// test
class ConstantStringInfo extends ConstantInfo {
    long strIndex;
    
    public ConstantStringInfo(ClassReader reader){
	strIndex = reader.readU2();
    }
}

// test
class ConstantClassInfo extends ConstantInfo {
    public long nameIndex;
    
    public ConstantClassInfo(ClassReader reader){
	nameIndex = reader.readU2();
    }

}

// test
class ConstantNameAndTypeInfo extends ConstantInfo {
    long nameIndex;
    long descriptorIndex;
    
    public ConstantNameAndTypeInfo(ClassReader reader){
	nameIndex = reader.readU2();
	descriptorIndex = reader.readU2();
    }
}

// test
class ConstantMemberrefInfo extends ConstantInfo {
    long classIndex;
    long nameAndTypeIndex;
    
    public ConstantMemberrefInfo(ClassReader reader){
	classIndex = reader.readU2();
	nameAndTypeIndex = reader.readU2();
    }
}

// test
class ConstantFieldrefInfo extends ConstantMemberrefInfo {
    public ConstantFieldrefInfo(ClassReader reader) {
	super(reader);
    }
}

// test
class ConstantMethodrefInfo extends ConstantMemberrefInfo {
    public ConstantMethodrefInfo(ClassReader reader) {
	super(reader);
    }
}

// test
class ConstantInterfaceMethodrefInfo extends ConstantMemberrefInfo {
    public ConstantInterfaceMethodrefInfo(ClassReader reader) {
	super(reader);
    }
}