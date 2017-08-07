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
    int argSlotCount;
    byte[] code;

    public Method(Class class_, MemberInfo memberInfo) {
        this.accessFlags = memberInfo.accessFlags();
        this.name = memberInfo.name();
        this.descriptor = memberInfo.descriptor();
        this.class_ = class_;

        CodeAttribute ca = memberInfo.codeAttribute();

        /* abstract method没有code属性 */
        if (ca != null) {
            this.maxStack = ca.maxStack();
            this.maxLocals = ca.maxLocals();
            this.code = ca.code();
        }
        this.argSlotCount = calcArgSlotCount();
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

    public int argSlotCount() {
        return argSlotCount;
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

    public boolean isNative() {
        return 0 != (accessFlags & AccessFlags.ACC_NATIVE);
    }

    /* 解析descriptor来获取参数和返回值的信息 */
    private int calcArgSlotCount() {
        int count = 0;
        MethodDescriptor des = new MethodDescriptor(descriptor);
        for (String param : des.parameterTypes) {
            count++;
        }

        if (!isStatic()) {
            count++;
        }

        return count;
    }
}
