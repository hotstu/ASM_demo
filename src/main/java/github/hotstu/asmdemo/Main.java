package github.hotstu.asmdemo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File input = new File("res/input.jar");
        File output = new File("res/output.jar");

        List<Relocation> rules = new ArrayList<>();
        rules.add(new Relocation("com.google.gson", "me.lucko.test.lib.gson"));

        JarRelocator relocator = new JarRelocator(input, output, rules);

        try {
            relocator.run();
        } catch (IOException e) {
            throw new RuntimeException("Unable to relocate", e);
        }
    }
}
