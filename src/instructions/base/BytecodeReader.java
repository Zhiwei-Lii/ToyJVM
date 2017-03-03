package instructions.base;

public class BytecodeReader {
    byte[] code;
    int pc;
    
    public void reset(byte[] code, int pc){
	this.code = code;
	this.pc = pc;
    }
    
    public BytecodeReader(byte[] code) {
	this.code = code;
	this.pc = 0;
    }

    public long readU1() {
	return Byte.toUnsignedLong(code[pc++]);
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
}
