package managers;

import exceptions.FileLoadException;
import java.io.*;


public class FileManager {
    /** Path to the data file from environment variable. */
    private String filePath;

    /**
     * Constructs a FileManager using the MUSIC_FILE environment variable.
     */

    public FileManager() {
        this.filePath = System.getenv("MUSIC_FILE");
        if (filePath == null) {
            throw new RuntimeException("Environment variable MUSIC_FILE is not set");
        }
    }

    /**
     * Reads the file and returns its content as a String.
     * @return file content as String
     */
    public String read() {
        try {
            FileInputStream stream = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            BufferedReader br = new BufferedReader(reader);

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            br.close();
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Cannot read file: " + e.getMessage());
        }
    }

    /**
     * Writes the given content to the file.
     * @param content the content to write
     */
    public void write(String content) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(content);
            bw.close();

        } catch (Exception e) {
            throw new RuntimeException("Cannot write file: " + e.getMessage());
        }
    }
}
