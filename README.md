## create a class at runtime using ASM 
* first install ASM plugin for idea

* write class as normal

* right click on the class just written, select `show bytecode outline`

* copy the content in the ASMified window in to a funciton like this:

```
    public static void main(String[] args) {
        ClassWriter cww = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        Textifier p = new Textifier();
        TraceClassVisitor cw = new TraceClassVisitor(cww, p, null);
        cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, "github/hotstu/nottdemo/Bean", null, "java/lang/Object", null);
        MethodVisitor mv;
        cw.visitSource("Bean.java", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(8, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lgithub/hotstu/nottdemo/Bean;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }

        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "hello", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(10, l0);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("hello,world");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(11, l1);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 0);
            mv.visitEnd();
        }
        cw.visitEnd();
        System.out.println(p.getText());
        }
    }

```

* using the generated class byte code at runtime:
```
        ClassLoader loader = new ByteClassLoader(cww.toByteArray(), "github.hotstu.nottdemo.Bean", ClassMaker.class.getClassLoader());
        try {
            Class<?> aClass = loader.loadClass("github.hotstu.nottdemo.Bean");
            Method hello = aClass.getDeclaredMethod("hello");
            hello.invoke(aClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
```