package classfile.attribute;

import classfile.ClassReader;

// test
public class SyntheticAttribute extends AttributeInfo {
    public SyntheticAttribute(ClassReader reader) {
        reader.readU4();
    }
}
