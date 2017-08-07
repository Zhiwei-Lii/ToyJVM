package classfile;

public class ClassReader {
    private byte[] data;
    private int index;

    public ClassReader(byte[] classData) {
        this.data = classData;
        this.index = 0;
    }

    public long readU1() {
        return Byte.toUnsignedLong(data[index++]);
    }

    public long readU2() {
        long high = readU1();
        long low = readU1();
        return (high << 8) | low;
    }

    public long readU4() {
        long high = readU2();
        long low = readU2();
        return (high << 16) | low;
    }

    // 可能出问题
    public long readU8() {
        long high = readU4();
        long low = readU4();
        return (high << 32) | low;
    }

    public boolean hasMoreByte() {
        return index < data.length;
    }
}
