package classfile;

public class ClassReader {
    private byte[] data;
    private int index;
    
    public ClassReader(byte[] classData){
	this.data = classData;
	this.index = 0;
    }

    public long readU1() {
	System.out.println(Byte.toUnsignedLong(data[index]));
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
    
    /*
    public long[] readU2List(){
	long n = readU2();

	long[] list = new long[(int)n];
	for(int i=0; i<n; i++){
	    list[i] = readU2();
	}
	
	return list;
    }
    */
    
    public boolean hasMoreByte() {
	return index < data.length;
    }
}
