package github.hotstu.asmdemo.util;

public class ByteClassLoader extends ClassLoader {
    private final byte[] bytes;
    private final String name;

    public ByteClassLoader(byte[] bytes, String name, ClassLoader parent) {
        super(parent);
        this.bytes = bytes;
        this.name = name;
    }

    @Override
    public Class<?> loadClass(String s) throws ClassNotFoundException {
        if (name.equals(s)) {
            return defineClass(s, bytes, 0, bytes.length);
        }
        return getParent().loadClass(s);
    }
}