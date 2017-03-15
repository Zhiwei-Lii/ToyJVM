package classfile.attribute;

import classfile.ClassReader;
import classfile.RawConstantPool;

public class AttributeFactory {

    public static AttributeInfo newAttributeInfo(ClassReader reader, RawConstantPool cp){
	long attrNameIndex = reader.readU2();
	String attrName = cp.getUtf8(attrNameIndex);
	
	if(attrName.equals("Code")){
	    return new CodeAttribute(reader, cp);
	}
	else if(attrName.equals("ConstantValue")){
	    return new ConstantValueAttribute(reader);
	}
	else if(attrName.equals("Deprecated")){
	    return new DeprecatedAttribute(reader);
	}
	else if(attrName.equals("Exceptions")){
	    return new ExceptionsAttribute(reader);
	}
	else if(attrName.equals("LineNumberTable")){
	    return new LineNumberTableAttribute(reader);
	}
	else if(attrName.equals("LocalVariableTable")){
	    return new LocalVariableTableAttribute(reader);
	}
	else if(attrName.equals("SourceFile")){
	    return new SourceFileAttribute(reader, cp);
	}
	else if(attrName.equals("Synthetic")){
	    return new SyntheticAttribute(reader);
	}
	else{
	    return new UnparsedAttribute(reader);
	}
    }
}
