package rtda.heap;

import classfile.MemberInfo;

public class Field {
    int accessFlags;
    String name;
    String descriptor;
    Class class_;
    int constantValueIndex; // the value of the field
    int slotId;

    public Field(Class class_, MemberInfo memberInfo) {
	this.accessFlags = memberInfo.accessFlags();
	this.name = memberInfo.name();
	this.descriptor = memberInfo.descriptor();
	this.class_ = class_;
	this.constantValueIndex = memberInfo.constantValueAttribute().constantValueIndex();
    }

    public String name() {
	return name;
    }

    public String descriptor() {
	return descriptor;
    }

    public int slotId() {
	return slotId;
    }

    public boolean isStatic() {
	return 0 != (accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
	return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }
}
