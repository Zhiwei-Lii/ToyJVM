package classfile.attribute;

import classfile.ClassReader;

public class LineNumberTableAttribute extends AttributeInfo {
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
