package classfile.attribute;

import classfile.ClassReader;

public class LocalVariableTableEntry {
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
