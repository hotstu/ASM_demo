package github.hotstu.asmdemo;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class MyMain {
    public static void main(String[] args) throws IOException {
        ClassReader reader = new ClassReader("java.lang.Object");
        reader.accept(new MyClazzVisitor(), ClassReader.SKIP_FRAMES);
        //ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        //byte[] bytes = writer.toByteArray();
    }

    public static void premain(String agentArgs, Instrumentation inst) {

    }
}
