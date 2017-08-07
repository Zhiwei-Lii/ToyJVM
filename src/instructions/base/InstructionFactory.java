package instructions.base;

import instructions.constants.*;
import instructions.base.*;
import instructions.comparisons.*;
import instructions.control.*;
import instructions.loads.*;
import instructions.stores.*;
import instructions.math.*;
import instructions.references.CHECK_CAST;
import instructions.references.GET_FIELD;
import instructions.references.GET_STATIC;
import instructions.references.INSTANCE_OF;
import instructions.references.INVOKE_INTERFACE;
import instructions.references.INVOKE_SPECIAL;
import instructions.references.INVOKE_STATIC;
import instructions.references.INVOKE_VIRTUAL;
import instructions.references.NEW;
import instructions.references.PUT_FIELD;
import instructions.references.PUT_STATIC;
import instructions.stack.*;

public class InstructionFactory {

    public static Instruction newInstruction(long opcode) {
        // System.out.printf("The opcode is %x", opcode);
        // System.out.println("");
        switch ((int) opcode) {
            case 0x00:
                return new NOP();
            case 0x01:
                return new ACONST_NULL();
            case 0x02:
                return new ICONST_M1();
            case 0x03:
                return new ICONST_0();
            case 0x04:
                return new ICONST_1();
            case 0x05:
                return new ICONST_2();
            case 0x06:
                return new ICONST_3();
            case 0x07:
                return new ICONST_4();
            case 0x08:
                return new ICONST_5();
            case 0x10:
                return new BIPUSH();
            case 0x11:
                return new SIPUSH();
            case 0x12:
                return new LDC();
            // return &LDC{}
            case 0x13:
                return new LDC_W();
            // return &LDC_W{}
            // case 0x14:
            // return &LDC2_W{}
            case 0x15:
                return new ILOAD();
            case 0x19:
                return new ALOAD();
            case 0x1a:
                return new ILOAD_0();
            case 0x1b:
                return new ILOAD_1();
            case 0x1c:
                return new ILOAD_2();
            case 0x1d:
                return new ILOAD_3();
            case 0x2a:
                return new ALOAD_0();
            case 0x2b:
                return new ALOAD_1();
            case 0x2c:
                return new ALOAD_2();
            case 0x2d:
                return new ALOAD_3();
            // case 0x2e:
            // return iaload
            // case 0x2f:
            // return laload
            // case 0x30:
            // return faload
            // case 0x31:
            // return daload
            // case 0x32:
            // return aaload
            // case 0x33:
            // return baload
            // case 0x34:
            // return caload
            // case 0x35:
            // return saload
            case 0x36:
                return new ISTORE();
            case 0x3a:
                return new ASTORE();
            case 0x3b:
                return new ISTORE_0();
            case 0x3c:
                return new ISTORE_1();
            case 0x3d:
                return new ISTORE_2();
            case 0x3e:
                return new ISTORE_3();
            case 0x4b:
                return new ASTORE_0();
            case 0x4c:
                return new ASTORE_1();
            case 0x4d:
                return new ASTORE_2();
            case 0x4e:
                return new ASTORE_3();
            // case 0x4f:
            // return iastore
            // case 0x50:
            // return lastore
            // case 0x51:
            // return fastore
            // case 0x52:
            // return dastore
            // case 0x53:
            // return aastore
            // case 0x54:
            // return bastore
            // case 0x55:
            // return castore
            // case 0x56:
            // return sastore
            case 0x57:
                return new POP();
            case 0x58:
                return new POP2();
            case 0x59:
                return new DUP();
            case 0x5a:
                return new DUP_X1();
            case 0x5b:
                return new DUP_X2();
            case 0x5c:
                return new DUP2();
            case 0x5d:
                return new DUP2_X1();
            case 0x5e:
                return new DUP2_X2();
            case 0x5f:
                return new SWAP();
            case 0x60:
                return new IADD();
            case 0x64:
                return new ISUB();
            case 0x68:
                return new IMUL();
            case 0x6c:
                return new IDIV();
            case 0x70:
                return new IREM();
            case 0x74:
                return new INEG();
            case 0x78:
                return new ISHL();
            case 0x7c:
                return new IUSHR();
            case 0x80:
                return new IOR();
            case 0x84:
                return new IINC();
            case 0x99:
                return new IFEQ();
            case 0x9a:
                return new IFNE();
            case 0x9b:
                return new IFLT();
            case 0x9c:
                return new IFGE();
            case 0x9d:
                return new IFGT();
            case 0x9e:
                return new IFLE();
            case 0x9f:
                return new IF_ICMPEQ();
            case 0xa0:
                return new IF_ICMPNE();
            case 0xa1:
                return new IF_ICMPLT();
            case 0xa2:
                return new IF_ICMPGE();
            case 0xa3:
                return new IF_ICMPGT();
            case 0xa4:
                return new IF_ICMPLE();
            case 0xa5:
                return new IF_ACMPEQ();
            case 0xa6:
                return new IF_ACMPNE();
            case 0xa7:
                return new GOTO();
            // case 0xa8:
            // return &JSR{}
            // case 0xa9:
            // return &RET{}
            case 0xaa:
                return new TABLE_SWITCH();
            case 0xab:
                return new LOOKUP_SWITCH();
            case 0xac:
                return new IRETURN();
            case 0xad:
                return new LRETURN();
            // case 0xae:
            // return freturn
            // case 0xaf:
            // return dreturn
            // case 0xb0:
            // return areturn
            case 0xb1:
                return new RETURN();
            case 0xb2:
                return new GET_STATIC();
            case 0xb3:
                return new PUT_STATIC();
            case 0xb4:
                return new GET_FIELD();
            case 0xb5:
                return new PUT_FIELD();
            case 0xb6:
                return new INVOKE_VIRTUAL();
            case 0xb7:
                return new INVOKE_SPECIAL();
            case 0xb8:
                return new INVOKE_STATIC();
            case 0xb9:
                return new INVOKE_INTERFACE();
            // case 0xba:
            // return &INVOKE_DYNAMIC{}
            case 0xbb:
                return new NEW();
            // case 0xbc:
            // return &NEW_ARRAY{}
            // case 0xbd:
            // return &ANEW_ARRAY{}
            // case 0xbe:
            // return arraylength
            // case 0xbf:
            // return athrow
            case 0xc0:
                return new CHECK_CAST();
            case 0xc1:
                return new INSTANCE_OF();
            // case 0xc2:
            // return monitorenter
            // case 0xc3:
            // return monitorexit
            // case 0xc9:
            // return &JSR_W{}
            // case 0xca: breakpoint
            // case 0xfe: impdep1
            // case 0xff: impdep2
            default: {
                System.out.printf("UnSupported instruction : %x", opcode);
                return null;
            }
        }

    }

}
