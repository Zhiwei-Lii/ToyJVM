package classfile.attribute;

import classfile.ClassReader;

public class ExceptionsAttribute extends AttributeInfo {
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
