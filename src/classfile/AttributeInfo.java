package classfile;

public abstract class AttributeInfo {

}

class DeprecatedAttribute extends AttributeInfo {
    public DeprecatedAttribute(ClassReader reader){
	reader.readU4();
    }
}

class SyntheticAttribute extends AttributeInfo {
    public SyntheticAttribute(ClassReader reader){
	reader.readU4();
    }
}

/* The name of source file */
class SourceFileAttribute extends AttributeInfo {
    long sourceFileIndex;
    ConstantPool cp;

    public SourceFileAttribute(ClassReader reader, ConstantPool cp) {
	reader.readU4(); // pass attribute_length
	this.cp = cp;
	sourceFileIndex = reader.readU2();
    }

    public String FileName() {
	return cp.getUtf8(sourceFileIndex);
    }
}

class ConstantValueAttribute extends AttributeInfo {
    long constantValueIndex;

    public ConstantValueAttribute(ClassReader reader) {
	reader.readU4();
	constantValueIndex = reader.readU2();
    }
}

class CodeAttribute extends AttributeInfo {
    ConstantPool cp;
    long maxStack;
    long maxLocals;
    long[] code;
    ExceptionTableEntry[] exceptionTable;
    AttributeInfo[] attributes;

    public CodeAttribute(ClassReader reader, ConstantPool cp) {
	reader.readU4();
	this.cp = cp;
	maxStack = reader.readU2();
	maxLocals = reader.readU2();

	setCode(reader);
	setExceptionTable(reader);
	setAttributeInfos(reader, cp);
    }

    private void setCode(ClassReader reader) {
	int codeLength = (int) reader.readU4();
	code = new long[codeLength];

	for (int i = 0; i < codeLength; i++) {
	    code[i] = reader.readU1();
	}
    }

    private void setExceptionTable(ClassReader reader) {
	int exceptionLength = (int) reader.readU2();
	System.out.println("ExceptionLength is " + exceptionLength);
	exceptionTable = new ExceptionTableEntry[exceptionLength];

	for (int i = 0; i < exceptionLength; i++) {
	    exceptionTable[i] = new ExceptionTableEntry(reader);
	}
    }

    private void setAttributeInfos(ClassReader reader, ConstantPool cp) {
	int length = (int) reader.readU2();
	System.out.println("Code::attribute num is "+length);
	attributes = new AttributeInfo[length];
	for (int i = 0; i < length; i++) {
	    attributes[i] = AttributeFactory.newAttributeInfo(reader, cp);
	}
    }
}

class ExceptionTableEntry {
    long startPc;
    long endPc;
    long handlerPc;
    long catchType;

    public ExceptionTableEntry(ClassReader reader) {
	startPc = reader.readU2();
	endPc = reader.readU2();
	handlerPc = reader.readU2();
	catchType = reader.readU2();
	
	System.out.println("startPc "+startPc);
	System.out.println("endPc "+endPc);
	System.out.println("handlerPc "+handlerPc);
	System.out.println("catchType "+catchType);
    }
}

class ExceptionsAttribute extends AttributeInfo {
    long[] exceptionIndexTable;

    public ExceptionsAttribute(ClassReader reader) {
	reader.readU4();
	int length = (int) reader.readU2();
	exceptionIndexTable = new long[length];

	for (int i = 0; i < length; i++) {
	    exceptionIndexTable[i] = reader.readU2();
	}
    }
}

class LineNumberTableAttribute extends AttributeInfo {
    LineNumberTableEntry[] lineNumberTable;

    public LineNumberTableAttribute(ClassReader reader) {
	reader.readU4();
	int length = (int) reader.readU2();
	lineNumberTable = new LineNumberTableEntry[length];

	for (int i = 0; i < length; i++) {
	    lineNumberTable[i] = new LineNumberTableEntry(reader);
	}
    }
}

class LineNumberTableEntry {
    long startPc;
    long lineNumber;

    public LineNumberTableEntry(ClassReader reader) {
	startPc = reader.readU2();
	lineNumber = reader.readU2();
    }
}

class LocalVariableTableAttribute extends AttributeInfo {
    LocalVariableTableEntry[] localVariableTable;

    public LocalVariableTableAttribute(ClassReader reader) {
	reader.readU4();
	int length = (int) reader.readU2();
	localVariableTable = new LocalVariableTableEntry[length];

	for (int i = 0; i < length; i++) {
	    localVariableTable[i] = new LocalVariableTableEntry(reader);
	}
    }
}

class LocalVariableTableEntry {
    long startPc;
    long length;
    long nameIndex;
    long descriptorIndex;
    long index;

    public LocalVariableTableEntry(ClassReader reader) {
	startPc = reader.readU2();
	length = reader.readU2();
	nameIndex = reader.readU2();
	descriptorIndex = reader.readU2();
	index = reader.readU2();
    }
}

class UnparsedAttribute extends AttributeInfo {
    public UnparsedAttribute(ClassReader reader) {
	long length = reader.readU4();
	for (long i = 0L; i < length; i++) {
	    reader.readU1();
	}
    }
}
