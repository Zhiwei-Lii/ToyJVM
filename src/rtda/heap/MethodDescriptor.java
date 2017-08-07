package rtda.heap;

import java.util.ArrayList;
import java.util.List;

/*
 * This class is to parse the descriptor of method
 */
public class MethodDescriptor {
    List<String> parameterTypes = new ArrayList<String>();
    String returnType;

    private String raw;
    private int pointer;

    public MethodDescriptor(String descriptor) {
        parseDescriptor(descriptor);
    }

    private void parseDescriptor(String descriptor) {
        this.raw = descriptor;

        startParams();
        parseParamTypes();
        endParams();
        parseReturnType();
        finish();
    }

    private void startParams() {
        if (read() != '(') {
            throw new Error("Uncorrect Descriptor of Method");
        }
    }

    private void parseParamTypes() {
        String type = parseFieldType();
        while (!type.equals("")) {
            parameterTypes.add(type);
            type = parseFieldType();
        }
    }

    private void endParams() {
        if (read() != ')') {
            throw new Error("Uncorrect Descriptor of Method");
        }
    }

    private void parseReturnType() {
        if (read() == 'V') {
            returnType = "V";
            return;
        }

        unread();
        returnType = parseFieldType();
    }

    private void finish() {
        if (pointer != raw.length()) {
            throw new Error("Uncorrect Descriptor of Method");
        }
    }

    private char read() {
        return raw.charAt(pointer++);
    }

    private void unread() {
        pointer--;
    }

    private String parseFieldType() {
        switch (read()) {
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'Z':
                return "Z";
            case 'S':
                return "S";
            case 'J':
                return "J";
            case 'L':
                return parseObjectType();
            case '[':
                return parseArrayType();
            case ')':
                unread();
                return "";
            default:
                throw new Error("Unsupported descriptor type");
        }
    }

    private String parseObjectType() {
        String unread = raw.substring(pointer);
        int semicolonIndex = unread.indexOf(';');

        if (semicolonIndex == -1) {
            throw new Error("Uncorrect Descriptor of Method");
        }
        else {
            int objStart = pointer - 1;
            int objEnd = pointer + semicolonIndex + 1;
            pointer = objEnd;

            return raw.substring(objStart, objEnd);
        }
    }

    private String parseArrayType() {
        int arrStart = pointer - 1;
        parseFieldType();
        int arrEnd = pointer;

        String descriptor = raw.substring(arrStart, arrEnd);
        return descriptor;
    }

}
