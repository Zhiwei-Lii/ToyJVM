package classfile;

public class AttributeFactory {

    public static AttributeInfo newAttributeInfo(ClassReader reader, ConstantPool cp){
	long attrNameIndex = reader.readU2();
	System.out.println("attrNameIndex is "+attrNameIndex);
	String attrName = cp.getUtf8(attrNameIndex);
	System.out.println("attrName is "+attrName);
	
	if(attrName.equals("Code")){
//	    System.out.println("Code");
	    return new CodeAttribute(reader, cp);
	}
	else if(attrName.equals("ConstantValue")){
//	    System.out.println("ConstantValue");
	    return new ConstantValueAttribute(reader);
	}
	else if(attrName.equals("Deprecated")){
//	    System.out.println("Deprecated");
	    return new DeprecatedAttribute(reader);
	}
	else if(attrName.equals("Exceptions")){
//	    System.out.println("Exceptions");
	    return new ExceptionsAttribute(reader);
	}
	else if(attrName.equals("LineNumberTable")){
//	    System.out.println("LineNumberTable");
	    return new LineNumberTableAttribute(reader);
	}
	else if(attrName.equals("LocalVariableTable")){
//	    System.out.println("LocalVariableTable");
	    return new LocalVariableTableAttribute(reader);
	}
	else if(attrName.equals("SourceFile")){
	    //System.out.println("SourceFile");
	    return new SourceFileAttribute(reader, cp);
	}
	else if(attrName.equals("Synthetic")){
	    //System.out.println("Synthetic");
	    return new SyntheticAttribute(reader);
	}
	else{
	    //System.out.println("Unparsed");
	    return new UnparsedAttribute(reader);
	}
    }

}
