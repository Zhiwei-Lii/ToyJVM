package rtda;

public class OperandStack {
    int top;
    Slot[] slots;
    
    public OperandStack(int maxSize){
	slots = new Slot[maxSize];
	for(int i=0; i<slots.length; i++){
	    slots[i] = new Slot();
	}
	top = 0;
    }
    
    public void print(){
	for(int i=top-1; i>-1; i--){
	    System.out.println(slots[i].num);
	}
    }
    
    public void pushInt(int val){
	slots[top].num = val;
	top++;
    }
    
    public int popInt(){
	top--;
	return (int)slots[top].num;
    }
    
    public void pushRef(Object ref){
	slots[top].ref = ref;
	top++;
    }

    // GC can be better in this method
    public Object popRef(){
	top--;
	return slots[top].ref;
    }
    
    public void pushSlot(Slot slot){
	slots[top] = slot;
	top++;
    }
    
    public Slot popSlot(){
	top--;
	return slots[top];
    }
    
    /*
    public void pushFloat(float val){
    
    }
    
    public float popFloat(){
	
    }
    
    public void pushDouble(double val){
	
    }
    
    public double popDouble(){
	
    }
    
    public void pushLong(long val){
	
    }
    
    public long popLong(){
	
    }
    */

}
