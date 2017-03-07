package classfile.attribute;

import classfile.ClassReader;

public class LocalVariableTableAttribute extends AttributeInfo {
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
