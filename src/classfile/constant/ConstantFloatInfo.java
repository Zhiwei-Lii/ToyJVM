package classfile.constant;

import classfile.ClassReader;

// untest
public class ConstantFloatInfo extends ConstantInfo {
    double val;

    public ConstantFloatInfo(ClassReader reader) {
        // 这里的转换可能出问题, 待测试
        long bits = reader.readU4();
        val = Double.longBitsToDouble(bits);
    }
}
