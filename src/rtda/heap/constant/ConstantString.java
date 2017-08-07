package rtda.heap.constant;

import classfile.RawConstantPool;
import classfile.constant.ConstantStringInfo;

public class ConstantString implements Constant {
    String str;

    public ConstantString(RawConstantPool rcp, ConstantStringInfo i) {
        this.str = rcp.getUtf8(i.index());
    }
}
