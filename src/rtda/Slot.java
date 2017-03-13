package rtda;

import rtda.heap.Object;

/**
 * This class is used as an union in C
 * 
 * @author misen
 *
 */
public class Slot {
    long num;
    Object ref;

    public void setNum(int num) {
	this.num = num;
    }

    public void setRef(Object ref) {
	this.ref = ref;
    }

    public long num() {
	return num;
    }

    public Object ref() {
	return ref;
    }
}