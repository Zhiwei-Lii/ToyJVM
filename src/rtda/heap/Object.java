package rtda.heap;

import rtda.Slot;
import rtda.heap.Class;

public class Object {
    Class cl;
    Slot[] fields;

    public Object(Class cl) {
	this.cl = cl;

	this.fields = new Slot[cl.fields.length];
	for (int i = 0; i < fields.length; i++) {
	    fields[i] = new Slot();
	}
    }

    public void setFieldInt(int id, int val) {
	fields[id].setNum(val);
    }

    public void setFieldRef(int id, Object ref) {
	fields[id].setRef(ref);
    }

    public int getFieldInt(int id) {
	return (int) fields[id].num();
    }

    public Object getFieldRef(int id) {
	return fields[id].ref();
    }

    public Class class_() {
	return cl;
    }

    public boolean isInstanceOf(Class class_) {
	return class_.isAssignableFrom(this.cl);
    }
}
