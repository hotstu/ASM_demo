package github.hotstu.asmdemo;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

//visit visitSource?visitOuterClass? (visitAnnotation|visitAttribute)*(visitInnerClass|visitField|visitMethod)*visitEnd
public class MyClazzVisitor extends ClassVisitor {

    public MyClazzVisitor() {
        super(ASM6);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println(String.format("%s extends %s {", name, superName));
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println(String.format("%s %s", desc, name));
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println(String.format("%s %s", name, desc));
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitSource(String source, String debug) {
        super.visitSource(source, debug);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        System.out.println("}");
    }
}
