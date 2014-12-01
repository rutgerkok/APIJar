package nl.rutgerkok.apijar;

import java.awt.GraphicsEnvironment;

/**
 * Main class of the program, decides between graphical or command-line version.
 *
 */
public final class APIJar {

    private APIJar() {
        // No instances
    }

    public static void main(String[] args) {
        if (args.length == 0 && !GraphicsEnvironment.isHeadless()) {
            new GraphicalHandler().execute();
        } else {
            new CommandLineHandler().execute(args);
        }
    }
}
