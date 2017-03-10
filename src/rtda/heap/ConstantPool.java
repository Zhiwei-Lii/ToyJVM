package rtda.heap;

import classfile.RawConstantPool;
import classfile.constant.ConstantClassInfo;
import classfile.constant.ConstantFieldrefInfo;
import classfile.constant.ConstantInfo;
import classfile.constant.ConstantIntegerInfo;
import classfile.constant.ConstantInterfaceMethodrefInfo;
import classfile.constant.ConstantMethodrefInfo;
import classfile.constant.ConstantStringInfo;
import rtda.heap.constant.Constant;

/**
 * This class is the refinement of classfile.RawConstantPool
 * @author misen
 *
 */
public class ConstantPool {
    Class class_;
    Constant[] consts;
    
    public ConstantPool(Class class_, RawConstantPool rcp){
	this.class_ = class_;
	setConstants(rcp);
    }
    
    public Constant getConstant(int index){
	return consts[index];
    }
    
    private void setConstants(RawConstantPool rcp){
	for(ConstantInfo ci: rcp.constantInfos()){
	    if(ci instanceof ConstantIntegerInfo){
		// to do
	    }
	    else if(ci instanceof ConstantStringInfo){
		
	    }
	    else if(ci instanceof ConstantClassInfo){
		
	    }
	    else if(ci instanceof ConstantFieldrefInfo){
		lskfjlkdjflk
	    }
	    else if(ci instanceof ConstantMethodrefInfo){
		
	    }
	    else if(ci instanceof ConstantInterfaceMethodrefInfo){
		
	    }
	}
	
    }
}
