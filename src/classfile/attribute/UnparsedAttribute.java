package classfile.attribute;

import classfile.ClassReader;

public class UnparsedAttribute extends AttributeInfo {
    public UnparsedAttribute(ClassReader reader) {
	long length = reader.readU4();
	for (long i = 0L; i < length; i++) {
	    reader.readU1();
	}
    }
}
