package info.kgeorgiy.ja.bondarev.walk;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Walk {

    private static final String HASH_ALGORITHM = "SHA-1";
    // :NOTE: 40 zeros
    private static final String FAILED_HASH = "0".repeat(40);
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final int BYTES_TO_READ = 1024;

    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null
                || args[0].isEmpty() || args[1].isEmpty()) {
            // System.err
            System.err.println("Wrong arguments. Expected: input_file output_file");
            return;
        }
        Path inputPath;
        Path outputPath;
        try {
            inputPath = Paths.get(args[0]);
            outputPath = Paths.get(args[1]);
        } catch (InvalidPathException e) {
            System.err.println("Invalid file path: " + e.getMessage());
            return;
        }

        try {
            // :NOTE: notExists, getParent move to a variable
            Path parent = outputPath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
        } catch (IOException e) {
            System.err.println("Cannot create directories for output file: " + e.getMessage());
            return;
        }

        try (BufferedReader bufferedReader = Files.newBufferedReader(inputPath, CHARSET);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath, CHARSET)) {
            String nameOfFile;
            while ((nameOfFile = bufferedReader.readLine()) != null) {
                bufferedWriter.write(getHash(nameOfFile) + " " + nameOfFile + "\n");
            }
        } catch (NoSuchFileException e) {
            System.err.println("No such file: " + e.getMessage());
        } catch (AccessDeniedException e) {
            System.err.println("Access to the file denied: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Something went wrong with reading or writing: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Wrong name of the hash algorithm: " + e.getMessage());
            // :NOTE: RuntimeException
        }
    }

    public static String getHash(String nameOfFile) throws NoSuchAlgorithmException, SecurityException, AccessDeniedException {
        Path file;
        try {
            file = Paths.get(nameOfFile);
            // :NOTE: e
        } catch (InvalidPathException e) {
            System.err.println("Invalid path for file you want to hash: " + e.getMessage());
            return FAILED_HASH;
        }

        try (InputStream inputStream = Files.newInputStream(file)) {
            MessageDigest sha1 = MessageDigest.getInstance(HASH_ALGORITHM);
            // :NOTE: 1024 to a const
            byte[] bytes = new byte[BYTES_TO_READ];
            int read;
            while ((read = inputStream.read(bytes)) >= 0) {
                sha1.update(bytes,0, read);
            }
            byte[] hashedBytes = sha1.digest();
            StringBuilder hash = new StringBuilder();
            for (byte b : hashedBytes) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        } catch (IOException e) {
            System.err.println("No such file: " + e.getMessage());
            return FAILED_HASH;
        }
    }
}