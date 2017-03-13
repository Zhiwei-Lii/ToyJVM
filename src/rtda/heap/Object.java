package rtda.heap;

import rtda.Slot;
import rtda.heap.Class;

public class Object {
    Class cl;
    Slot[] fields;

    public Object(Class cl) {
	this.cl = cl;

	for (int i = 0; i < fields.length; i++) {
	    fields[i] = new Slot();
	}
    }

    public void setInt(int id, int val) {
	fields[id].setNum(val);
    }

    public void setRef(int id, Object ref) {
	fields[id].setRef(ref);
    }
}
