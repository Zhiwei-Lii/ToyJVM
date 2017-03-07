package classfile;

import classfile.attribute.AttributeFactory;
import classfile.attribute.AttributeInfo;
import classfile.attribute.CodeAttribute;

/**
 * field and method share this class
 * 
 * @author misen
 *
 */
public class MemberInfo {
    ConstantPool cp;
    long accessFlags;
    long nameIndex;
    long descriptorIndex;
    AttributeInfo[] attributes;

    public MemberInfo(ClassReader reader, ConstantPool cp) {
	this.cp = cp;
	this.accessFlags = reader.readU2();
	this.nameIndex = reader.readU2();
	this.descriptorIndex = reader.readU2();
	setAttributes(reader, cp);
    }

    public int accessFlags() {
	return (int) accessFlags;
    }

    public String name() {
	return cp.getUtf8(nameIndex);
    }

    public String descriptorIndex() {
	return cp.getUtf8(descriptorIndex);
    }

    public CodeAttribute codeAttribute() {
	for (AttributeInfo a : attributes) {
	    if (a instanceof CodeAttribute) {
		return (CodeAttribute) a;
	    }
	}
	return null;
    }
    
    private void setAttributes(ClassReader reader, ConstantPool cp) {
	int n = (int) reader.readU2();

	attributes = new AttributeInfo[n];
	for (int i = 0; i < n; i++) {
	    attributes[i] = AttributeFactory.newAttributeInfo(reader, cp);
	}
    }

}
