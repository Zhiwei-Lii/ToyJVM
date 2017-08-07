package classfile.constant;

import classfile.ClassReader;

// test
public class ConstantUtf8Info extends ConstantInfo {
    String str;

    public ConstantUtf8Info(ClassReader reader) {
        long length = reader.readU2();
        byte[] bytes = new byte[(int) length];

        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) reader.readU1();
        }

        try {
            str = new String(bytes);
        } catch (Exception e) {
            throw new Error("ConstantInfo :: ConstantUtf8Info wrong!");
        }
    }

    public String string() {
        return str;
    }
}
