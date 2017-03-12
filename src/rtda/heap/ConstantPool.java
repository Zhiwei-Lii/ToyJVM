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
import rtda.heap.constant.ConstantInteger;
import rtda.heap.constant.ConstantString;
import rtda.heap.constant.ConstantClassRef;
import rtda.heap.constant.ConstantFieldRef;
import rtda.heap.constant.ConstantMethodRef;
import rtda.heap.constant.ConstantInterfaceMethodRef;

/**
 * This class is the refinement of classfile.RawConstantPool
 * 
 * @author misen
 *
 */
public class ConstantPool {
    Class class_;
    Constant[] consts;

    public ConstantPool(Class class_, RawConstantPool rcp) {
	this.class_ = class_;
	setConstants(rcp);
    }

    public Constant getConstant(int index) {
	return consts[index];
    }

    private void setConstants(RawConstantPool rcp){
	for(int i=0; i<rcp.constantInfos().length; i++){
	    ConstantInfo c = rcp.constantInfos()[i];

	    if(c instanceof ConstantIntegerInfo){
		consts[i] = new ConstantInteger((ConstantIntegerInfo)c);
	    }
	    else if(c instanceof ConstantStringInfo){
		consts[i] = new ConstantString(rcp, (ConstantStringInfo)c);
	    }
	    else if(c instanceof ConstantClassInfo){
		consts[i] = new ConstantClassRef(class_.loader, rcp, (ConstantClassInfo)c);
	    }
	    else if(c instanceof ConstantFieldrefInfo){
		consts[i] = new ConstantFieldRef(class_.loader, rcp, (ConstantFieldrefInfo)c);
	    }
	    else if(c instanceof ConstantMethodrefInfo){
		consts[i] = new ConstantMehtordRef(class_.loader, rcp, (ConstantMethodrefInfo)c);
	    }
	    else if(c instanceof ConstantInterfaceMethodrefInfo){
		consts[i] = new ConstantInterfaceMethodRef(class_.loader, rcp, (ConstantInterfaceMethodrefInfo)c);
	    }
	}
    }
}
