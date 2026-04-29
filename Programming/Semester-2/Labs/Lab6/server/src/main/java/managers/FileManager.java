package managers;

import java.io.*;

/**
 * The type File manager.
 */
public class FileManager {

    private final String filePath;

    /**
     * Instantiates a new File manager.
     *
     * @param filePath the file path
     */
// ✅ FIX: accept filePath from outside (DI principle)
    public FileManager(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new RuntimeException("File path is null or empty");
        }
        this.filePath = filePath;
    }

    /**
     * Read string.
     *
     * @return the string
     */
// ---------------- READ ----------------
    public String read() {
        try (FileInputStream stream = new FileInputStream(filePath);
             InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
             BufferedReader br = new BufferedReader(reader)) {

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Cannot read file: " + e.getMessage());
        }
    }

    /**
     * Write.
     *
     * @param content the content
     */
// ---------------- WRITE ----------------
    public void write(String content) {
        try (FileWriter fw = new FileWriter(filePath);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(content);

        } catch (Exception e) {
            throw new RuntimeException("Cannot write file: " + e.getMessage());
        }
    }
}