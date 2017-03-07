package rtda.heap;

import classfile.MemberInfo;
import classfile.attribute.CodeAttribute;

public class Method {
    int accessFlags;
    String name;
    String descriptor;
    Class class_;
    int maxStack;
    int maxLocals;
    byte[] code;

    public Method(Class class_, MemberInfo memberInfo) {
	this.accessFlags = memberInfo.accessFlags();
	this.name = memberInfo.name();
	this.descriptor = memberInfo.descriptorIndex();
	this.class_ = class_;
	
	CodeAttribute ca = memberInfo.codeAttribute();
	this.maxStack = ca.maxStack();
	this.maxLocals = ca.maxLocals();
	this.code = ca.code();
    }
}
