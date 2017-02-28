package classfile;

public class ClassFile {
    public long magic;
    public long minorVersion;
    public long majorVersion;
    public ConstantPool constantPool;
    public long accessFlags;
    public long thisClass;
    public long superClass;
    public long[] interfaces;
    public MemberInfo[] fields;
    public MemberInfo[] methods;
    public AttributeInfo[] attributes;
    
    public ClassFile(byte[] classData){
	ClassReader reader = new ClassReader(classData);
	set(reader);
    }
    
    private void set(ClassReader reader){
	setMagic(reader);
	setVersion(reader);
	setConstantPool(reader);
	//printConstantPool();
	setAccessFlags(reader);
	setThisClass(reader);
	setSuperClass(reader);
	setInterfaces(reader);
	setFields(reader);
	setMethods(reader);
	setAttributes(reader);
    }
    
    private void printConstantPool(){
	System.out.println("hello");
	int i = 0;
	for(ConstantInfo c:constantPool.constantInfos){
	    System.out.println((i++)+" "+c.toString());
	}
    }

    private void setConstantPool(ClassReader reader) {
	constantPool = new ConstantPool(reader);
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
	interfaces = new long[n];
	
	for(int i=0; i<n; i++){
	    interfaces[i] = reader.readU2();
	}
    }
    
    private void setFields(ClassReader reader){
	int n = (int)reader.readU2();
	fields = new MemberInfo[n];
	
	for(int i=0; i<n; i++){
	    System.out.println("fields : "+i);
	    fields[i] = new MemberInfo(reader, constantPool);
	}
    }

    private void setMethods(ClassReader reader){
	int n = (int)reader.readU2();
	methods = new MemberInfo[n];
	
	for(int i=0; i<n; i++){
	    System.out.println("methods : "+i);
	    methods[i] = new MemberInfo(reader, constantPool);
	    System.out.println("------------------------");
	}
    }

    private void setAttributes(ClassReader reader) {
	int length = (int) reader.readU2();
	attributes = new AttributeInfo[length];
	for (int i = 0; i < length; i++) {
	    attributes[i] = AttributeFactory.newAttributeInfo(reader, constantPool);
	}
    }
}
