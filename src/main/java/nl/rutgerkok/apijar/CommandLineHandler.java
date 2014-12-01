package nl.rutgerkok.apijar;

import java.io.File;
import java.io.IOException;

/**
 * Version of the program that uses the command line.
 *
 */
final class CommandLineHandler {

    void execute(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: <in> <out>");
            System.exit(1);
        }

        File in = new File(args[0]);
        File out = new File(args[1]);

        if (!in.exists()) {
            System.err.println("Input file does not exist: " + in.getAbsolutePath());
            System.exit(1);
        }

        try {
            new JarConverter().convert(in, out);
            System.out.println("Success! Placed file at " + out.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + out.getAbsolutePath());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }
}
