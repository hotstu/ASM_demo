package github.hotstu.asmdemo.util;

import java.io.InputStream;

public class Util {
    public InputStream getClassByte(String classname) {
        return this.getClass().getClassLoader()
                .getResourceAsStream(classname.replace(".", "/") + ".class");
    }
}
