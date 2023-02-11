import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatLineIndex {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        ArrayList<String> arrayList;
        StringBuilder checkLine = new StringBuilder();
        try (Scan scan = new Scan(args[0])) {
            for (int j = 0; scan.hasNextLine(); j++) {
                checkLine.append(scan.nextLine().trim());
                arrayList = scan.getWords(checkLine);
                checkLine.setLength(0);
                for (int i = 0; i < arrayList.size(); i++) {
                    String word = arrayList.get(i).trim();
                    if (map.containsKey(word)) {
                        map.put(word, map.get(word) + " " + (j + 1) + ":" + (i + 1));
                    } else {
                        map.put(word, (j + 1) + ":" + (i + 1));
                    }
                }
                arrayList.clear();
            }
        } catch (Exception e) {
            System.out.println("File not found: " + e.getMessage());
        }


        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                int j = 1;
                for (char ch : value.toCharArray()) {
                    if (ch == 32) {
                        j++;
                    }
                }
                writer.write(key + " " + j + " " + value);
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}