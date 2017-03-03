package rtda;

public class LocalVars {
    Slot[] localVars;
    
    public LocalVars(int length){
	localVars = new Slot[length];
    }
    
    public void setInt(int index, int val){
	localVars[index].num = val;
    }
    
    public int getInt(int index){
	return (int) localVars[index].num;
    }
    
    public void setRef(int index, Object ref){
	localVars[index].ref = ref;
    }
    
    public Object getRef(int index){
	return localVars[index].ref;
    }
    
    /*
    public void setFloat(int index, float val){
	// to do
    }
    
    public float getFloat(int index){
	//to do
    }
    
    public void setDouble(int index, double val){
	
    }
    
    public double getDouble(int index){
	
    }
    
    public void setLong(int index, long val){
	
    }
    
    public long getLong(int index){
	
    }
    */
}
