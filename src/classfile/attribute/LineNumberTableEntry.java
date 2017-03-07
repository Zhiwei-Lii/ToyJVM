package classfile.attribute;

import classfile.ClassReader;

public class LineNumberTableEntry {
    long startPc;
    long lineNumber;

    public LineNumberTableEntry(ClassReader reader) {
	startPc = reader.readU2();
	lineNumber = reader.readU2();
    }
}
