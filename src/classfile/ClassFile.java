package classfile;

public class ClassFile {
    long magic;
    long minorVersion;
    long majorVersion;
    ConstantPool constantPool;
    long accessFlags;
    long thisClass;
    long superClass;
    long[] interfaces;
    MemberInfo[] fields;
    AttributeInfo[] attributes;
    
    public ClassFile(byte[] classData){
	ClassReader reader = new ClassReader(classData);
	set(reader);
    }
    
    private void set(ClassReader reader){
	setMagic(reader);
	setVersion(reader);
	setConstantPool(reader);
	setAccessFlags(reader);
	setThisClass(reader);
	setSuperClass(reader);
	setInterfaces(reader);
	setFields(reader);
	setAttributes(reader);
    }
    
    private void setMagic(ClassReader reader){
	magic = reader.readU4();

	if(Long.toHexString(magic).equals("0xCAFEBABE")){
	    throw new Error("java.lang.ClassFormatError: magic!");
	}
    }
    
    private void setVersion(ClassReader reader){
	minorVersion = reader.readU2();
	majorVersion = reader.readU2();
    }
    
    private void setAccessFlags(ClassReader reader){
	accessFlags = reader.readU2();
    }
    
    private void setThisClass(ClassReader reader){
	thisClass = reader.readU2();
    }
    
    private void setSuperClass(ClassReader reader){
	superClass = reader.readU2();
    }
    
    private void setInterfaces(ClassReader reader){
	int n = (int)reader.readU2();
	
	for(int i=0; i<n; i++){
	    interfaces[i] = reader.readU2();
	}
    }


}
