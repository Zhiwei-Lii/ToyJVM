package classfile.constant;

import classfile.ClassReader;

// test
public class ConstantMemberrefInfo extends ConstantInfo {
    long classIndex;
    long nameAndTypeIndex;
    
    public ConstantMemberrefInfo(ClassReader reader){
	classIndex = reader.readU2();
	nameAndTypeIndex = reader.readU2();
    }
}
