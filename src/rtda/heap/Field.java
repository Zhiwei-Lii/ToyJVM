package rtda.heap;

import classfile.MemberInfo;

public class Field {
    int accessFlags;
    String name;
    String descriptor;
    Class class_;

    public Field(Class class_, MemberInfo memberInfo) {
	this.accessFlags = memberInfo.accessFlags();
	this.name = memberInfo.name();
	this.descriptor = memberInfo.descriptorIndex();
	this.class_ = class_;
    }
}
