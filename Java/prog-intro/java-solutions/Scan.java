import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scan implements AutoCloseable{
    private final InputStreamReader inputStreamReader;
    private String buffer;

    public Scan(InputStream inputStream) {
        inputStreamReader = new InputStreamReader(inputStream);
    }

    public Scan(String file) throws FileNotFoundException {
        inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
    }

    public int read(char[] buffer) throws IOException {
        int readed = inputStreamReader.read(buffer, 0 ,1024);
        return readed;
    }

    public String nextLine() throws IOException {
        StringBuilder stringBuilder;
        if(bufferIsReady()) {
            stringBuilder = new StringBuilder(buffer);
            buffer = null;
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        char ch = (char) inputStreamReader.read();
        while(ch != 10 && ch != 65535) {
            stringBuilder.append(ch);
            ch = (char) inputStreamReader.read();
        }
        if (ch == 65535 && stringBuilder.length() == 0) {
            return null;
        }
        return stringBuilder.toString();
    }

    public boolean hasNextLine() throws IOException {
        buffer = nextLine();
        return bufferIsReady();
    }

    public ArrayList<String> getWords(StringBuilder sb) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int k = 0; k < sb.length(); k++) {
            if (sb.charAt(k) == '\t' ||  !isLetter(sb.charAt(k))) {
                sb.deleteCharAt(k);
                sb.insert(k, " ");
            }
        }
        String line = sb.toString().toLowerCase();
        Collections.addAll(arrayList, line.split(" "));
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals("")) {
                arrayList.remove(i);
                i--;
            }
        }
        return arrayList;
    }

    private boolean bufferIsReady() {
        return buffer != null;
    }

    private boolean isLetter(char ch) {
        if (Character.isLetter(ch)) {
            return true;
        }
        if (ch == 39 || Character.isLetter(ch) || Character.DASH_PUNCTUATION == Character.getType(ch)) {
            return true;
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        this.inputStreamReader.close();
    }
}