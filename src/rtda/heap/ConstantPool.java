package rtda.heap;

/**
 * This class is the refinement of classfile.ConstantPool
 * @author misen
 *
 */
public class ConstantPool {
    Class class_;
    Constant[] consts;
    
    public ConstantPool(Class class_, classfile.ConstantPool cfCp){
	
    }
    
    public Constant getConstant(int index){
	return consts[index];
    }
}
