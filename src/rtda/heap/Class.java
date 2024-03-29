package rtda.heap;

import classfile.ClassFile;
import rtda.Slot;
import rtda.heap.ClassLoader;
import rtda.heap.Object;

/**
 * This class is the refinement of ClassFile
 * 
 * @author misen
 *
 */

public class Class {
    int accessFlags;

    String name;
    String superClassName;
    String[] interfaceNames;

    ConstantPool constantPool;
    Field[] fields;
    Method[] methods;

    ClassLoader loader;
    Class superClass;
    Class[] interfaces;

    int instanceSlotCount;
    int staticSlotCount;
    Slot[] staticVars;

    boolean initStarted;

    public Class(ClassLoader loader, ClassFile cf) {
        this.accessFlags = cf.accessFlags();
        this.name = cf.thisClassName();
        this.loader = loader;
        this.superClassName = cf.superClassName();
        this.interfaceNames = cf.interfaceNames();
        this.constantPool = new ConstantPool(this, cf.rawConstantPool());
        this.fields = newFields(cf);
        this.methods = newMethods(cf);

        /*
         * staticVars和fields等的初始化在ClassLoader中 fields是包括static在内的所有field
         */
    }

    public Class[] interfaces() {
        return interfaces;
    }

    public Class superClass() {
        return superClass;
    }

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (accessFlags & AccessFlags.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (accessFlags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public String name() {
        return name;
    }

    public Field[] fields() {
        return fields;
    }

    public Method[] methods() {
        return methods;
    }

    public Slot[] staticVars() {
        return staticVars;
    }

    public ConstantPool constantPool() {
        return constantPool;
    }

    public Object newObject() {
        return new Object(this);
    }

    public boolean initStarted() {
        return initStarted;
    }

    public boolean startInit() {
        return initStarted = true;
    }

    /*
     * A <- B , A is AssignableFrom B
     */

    public boolean isAssignableFrom(Class cl) {
        if (name.equals(cl.name)) {
            return true;
        }

        if (!isInterface()) {
            return cl.isSubClassOf(this);
        }
        else {
            return cl.isImplements(this);
        }
    }

    public boolean isSuperClassOf(Class other) {
        return other.isSubClassOf(this);
    }

    private boolean isSubClassOf(Class cl) {
        if (this.superClass == null) {
            return false;
        }

        if (this.superClass.name.equals(cl.name)) {
            return true;
        }

        if (this.superClass != null) {
            return this.superClass.isSubClassOf(cl);
        }

        return false;
    }

    private boolean isImplements(Class iface) {
        for (Class inter : this.interfaces) {
            if (inter.name.equals(iface.name) || inter.isSubInterfaceOf(iface)) {
                return true;
            }
        }

        if (this.superClass != null) {
            return this.superClass.isImplements(iface);
        }

        return false;
    }

    private boolean isSubInterfaceOf(Class iface) {
        for (Class superInterface : this.interfaces) {
            if (superInterface.name.equals(iface.name) || superInterface.isSubClassOf(iface)) {
                return true;
            }
        }

        return false;
    }

    public Method getMainMethod() {
        return getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    public Method getClinitMethod() {
        return getStaticMethod("<clinit>", "()V");
    }

    public Method lookupMethodInClass(String methodName, String descriptor) {
        for (Method m : methods()) {
            if (m.name().equals(methodName) && m.descriptor().equals(descriptor)) {
                return m;
            }
        }

        if (superClass != null) {
            return superClass.lookupMethodInClass(methodName, descriptor);
        }

        return null;
    }

    private Method getStaticMethod(String name, String descriptor) {
        for (Method m : methods) {
            if (m.descriptor.equals(descriptor) && m.name.equals(name) && m.isStatic()) {
                return m;
            }
        }

        return null;
    }

    private Field[] newFields(ClassFile cf) {
        Field[] fs = new Field[cf.fields().length];
        for (int i = 0; i < fs.length; i++) {
            fs[i] = new Field(this, cf.fields()[i]);
        }
        return fs;
    }

    private Method[] newMethods(ClassFile cf) {
        Method[] ms = new Method[cf.methods().length];
        for (int i = 0; i < ms.length; i++) {
            ms[i] = new Method(this, cf.methods()[i]);
        }
        return ms;
    }

}
