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
	this.descriptor = memberInfo.descriptor();
	this.class_ = class_;

	CodeAttribute ca = memberInfo.codeAttribute();
	this.maxStack = ca.maxStack();
	this.maxLocals = ca.maxLocals();
	this.code = ca.code();
    }

    public String name() {
	return name;
    }

    public String descriptor() {
	return descriptor;
    }

    public Class class_() {
	return class_;
    }

    public int maxStack() {
	return maxStack;
    }

    public int maxLocals() {
	return maxLocals;
    }

    public byte[] code() {
	return code;
    }

    public boolean isStatic() {
	return 0 != (accessFlags & AccessFlags.ACC_STATIC);
    }
}
