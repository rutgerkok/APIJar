package nl.rutgerkok.apijar;

import java.io.File;
import java.io.IOException;

import net.md_5.specialsource.Jar;
import net.md_5.specialsource.JarMapping;
import net.md_5.specialsource.JarRemapper;

/**
 * The converter that strips the method implementations.
 *
 */
public final class JarConverter {

    /**
     * Converts the given file, stripping all method implementations.
     * 
     * @param in
     *            Input file.
     * @param out
     *            Output file.
     * @throws IOException
     */
    public void convert(File in, File out) throws IOException {
        Jar inJar = Jar.init(in);
        JarRemapper remapper = new JarRemapper(null, new JarMapping());
        remapper.setGenerateAPI(true);
        remapper.remapJar(inJar, out);
    }

}
