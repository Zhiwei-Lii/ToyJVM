package rtda.heap;

import classfile.MemberInfo;

/**
 * To model fields and methods
 * 
 * @author misen
 *
 */

public class ClassMember {
    int accessFlags;
    String name;
    String descriptor;
    Class class_;

    public ClassMember(MemberInfo memberInfo) {
	accessFlags = memberInfo.accessFlags();
	name = memberInfo.name();
	descriptor = memberInfo.descriptor();
    }
}
