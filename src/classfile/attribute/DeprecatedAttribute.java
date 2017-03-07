package classfile.attribute;

import classfile.ClassReader;

//test
public class DeprecatedAttribute extends AttributeInfo {
    public DeprecatedAttribute(ClassReader reader) {
	reader.readU4();
    }
}
