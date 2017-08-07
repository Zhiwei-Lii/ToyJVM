package classfile.attribute;

import classfile.ClassReader;

// test
public class ConstantValueAttribute extends AttributeInfo {
    long constantValueIndex;

    public ConstantValueAttribute(ClassReader reader) {
        reader.readU4();
        constantValueIndex = reader.readU2();
    }

    public int constantValueIndex() {
        return (int) constantValueIndex;
    }
}
