package rtda.heap.constant;

import classfile.constant.ConstantIntegerInfo;

public class ConstantInteger implements Constant {
    int val;

    public ConstantInteger(ConstantIntegerInfo i) {
	this.val = i.value();
    }

    public int val() {
	return val;
    }
}
