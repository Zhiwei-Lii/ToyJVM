package classfile.attribute;

import classfile.ClassReader;

public class ExceptionTableEntry {
    long startPc;
    long endPc;
    long handlerPc;
    long catchType;

    public ExceptionTableEntry(ClassReader reader) {
        startPc = reader.readU2();
        endPc = reader.readU2();
        handlerPc = reader.readU2();
        catchType = reader.readU2();
    }
}
