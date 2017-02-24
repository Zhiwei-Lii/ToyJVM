package classfile;

public class MemberInfo {
    ConstantPool cp;
    long accessFlags;
    long nameIndex;
    long descriptorIndex;
    AttributeInfo[] attributes;
    
    public MemberInfo(ClassReader reader, ConstantPool cp){
	this.cp = cp;
	this.accessFlags = reader.readU2();
	this.nameIndex = reader.readU2();
	this.descriptorIndex = reader.readU2();
	setAttributes(reader);
    }
    
    private void setAttributes(ClassReader reader, ConstantPool cp){
	int n = (int)reader.readU2();

	attributes = new AttributeInfo[n];
	for(int i=0; i<n; i++){
	    attributes[i] = AttributeInfo(reader, cp);
	}
    }
}

