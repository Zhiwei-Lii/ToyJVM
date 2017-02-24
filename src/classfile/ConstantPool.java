package classfile;

public class ConstantPool {
    ConstantInfo[] constantInfos;

    public ConstantPool(ClassReader reader) {
	int n = (int) reader.readU2();

	constantInfos = new ConstantInfo[n];

	/*
	 * according to the spec, 0 is an invalid index long_info, double_info
	 * will cost two indexes
	 */
	for (int i = 1; i < n; i++) {
	    constantInfos[i] = ConstantInfo(reader);
	    if(isLong || isDouble){
		i++;
	    }
	}
    }

    public getConstantInfo(long index){
	return constantInfos[(int)index];
    }
}
