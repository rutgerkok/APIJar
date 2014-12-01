package nl.rutgerkok.apijar;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Simple graphical interface for the program.
 *
 */
final class GraphicalHandler {

    private static final String JAR_EXTENSION = "jar";
    private static final FileFilter JAR_FILTER = new FileNameExtensionFilter("JAR file", JAR_EXTENSION);

    void execute() {
        setNativeTheme();

        File in = promptOpenFile(new File("."));
        if (in == null) {
            System.exit(0);
        }
        File out = promptSaveFile(in.getParentFile());
        if (out == null) {
            System.exit(0);
        }

        try {
            new JarConverter().convert(in, out);
            showMessage("Succes!\n\nCreated output file at " + out.getAbsolutePath());
        } catch (IOException e) {
            showError("Failed to convert file: \n\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Sets the UI theme to the native theme of the system, discarding any
     * errors.
     */
    private void setNativeTheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Ignore
        }
    }

    private void showError(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
    }

    private File promptOpenFile(File defaultDirectory) {
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(JAR_FILTER);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    private File promptSaveFile(File defaultDirectory) {
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(JAR_FILTER);
        chooser.setCurrentDirectory(defaultDirectory);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return ensureExtension(chooser.getSelectedFile(), JAR_EXTENSION);
        }
        return null;
    }

    /**
     * If the file doesn't have the specified extension, a new file is returned
     * with the extension changed to the specified one.
     * 
     * @param file
     *            The file.
     * @param extension
     *            The required extension, without a dot.
     * @return The file with the required extension, or null if the input file
     *         is null.
     */
    private File ensureExtension(File file, String extension) {
        if (file == null) {
            return null;
        }
        String dotExtension = ".".concat(extension).toLowerCase();
        if (!file.getName().toLowerCase().endsWith(dotExtension)) {
            return new File(file.getAbsolutePath() + dotExtension);
        }
        return file;
    }
}
