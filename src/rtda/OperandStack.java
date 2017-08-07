package rtda;

import rtda.heap.Object;

public class OperandStack {
    public int top;
    public Slot[] slots;

    public OperandStack(int maxSize) {
        slots = new Slot[maxSize];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot();
        }

        top = 0;
    }

    public void print() {
        System.out.println("size is "+(top));
        for (int i = top - 1; i > -1; i--) {
            if(slots[i].ref==null){
                System.out.println("null");
            }
            else{
                System.out.println(slots[i].ref.class_().name());
            }
        }
    }

    public void pushInt(int val) {
        slots[top].num = val;
        top++;
    }

    public int popInt() {
        top--;
        return (int) slots[top].num;
    }

    // 可能修改的是同一个引用
    public void pushRef(Object ref) {
        slots[top].setRef(ref);
        top++;
    }

    // GC can be better in this method
    public Object popRef() {
        top--;
        return slots[top].ref;
    }

    public void pushSlot(Slot slot) {
        slots[top] = slot;
        top++;
    }

    public Slot popSlot() {
        top--;
        return slots[top];
    }

    public Object getRefFromTop(int n) {
        return slots[top - n - 1].ref;
    }

}
