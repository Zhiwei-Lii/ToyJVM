package classfile.attribute;

import classfile.ClassReader;
import classfile.RawConstantPool;

// test
public class CodeAttribute extends AttributeInfo {
    RawConstantPool cp;
    long maxStack;
    long maxLocals;
    long[] code;
    ExceptionTableEntry[] exceptionTable;
    AttributeInfo[] attributes;

    public CodeAttribute(ClassReader reader, RawConstantPool cp) {
	reader.readU4();
	this.cp = cp;
	maxStack = reader.readU2();
	maxLocals = reader.readU2();

	setCode(reader);
	setExceptionTable(reader);
	setAttributeInfos(reader, cp);
    }

    public int maxStack() {
	return (int) maxStack;
    }

    public int maxLocals() {
	return (int) maxLocals;
    }

    public byte[] code() {
	byte[] bytecode = new byte[code.length];
	for (int i = 0; i < code.length; i++) {
	    bytecode[i] = (byte) code[i];
	}

	return bytecode;
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
	exceptionTable = new ExceptionTableEntry[exceptionLength];

	for (int i = 0; i < exceptionLength; i++) {
	    exceptionTable[i] = new ExceptionTableEntry(reader);
	}
    }

    private void setAttributeInfos(ClassReader reader, RawConstantPool cp) {
	int length = (int) reader.readU2();
	attributes = new AttributeInfo[length];
	for (int i = 0; i < length; i++) {
	    attributes[i] = AttributeFactory.newAttributeInfo(reader, cp);
	}
    }
}
