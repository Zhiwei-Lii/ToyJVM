package classfile.attribute;

import classfile.ClassReader;
import classfile.ConstantPool;

//test
public class SourceFileAttribute extends AttributeInfo {
    long sourceFileIndex;
    ConstantPool cp;

    public SourceFileAttribute(ClassReader reader, ConstantPool cp) {
	reader.readU4(); // pass attribute_length
	this.cp = cp;
	sourceFileIndex = reader.readU2();
    }

    public String FileName() {
	return cp.getUtf8(sourceFileIndex);
    }
}