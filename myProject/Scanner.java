import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
public class Scanner {
    int buffer_size = 4096;
    Reader reader;
    char[] buffer = new char[buffer_size];
    int b_len = 0;
    int l = 0;

    StringBuilder s = new StringBuilder();

    Scanner(InputStream sc) {
        reader = new InputStreamReader(sc);
    }

    Scanner(String sc) {
        reader = new StringReader(sc);
    }

    Scanner(FileInputStream sc) {
        reader = new InputStreamReader(sc, StandardCharsets.UTF_8);
    }

    Boolean hasNextLine() throws IOException {
        if (b_len < 1) {
            b_len = reader.read(buffer);
            l = 0;
        }
        if (b_len != -1) {
            return Character.isDefined(buffer[l]);
        } else {
            return false;
        }
    }

    Boolean hasNextInt() throws IOException {
        if (b_len < 1) {
            b_len = reader.read(buffer);
            l = 0;
        }
        while (b_len !=-1) {
            if (Character.DECIMAL_DIGIT_NUMBER == Character.getType(buffer[l]) || (buffer[l] == '-')) {
                return true;
            } else {
                b_len--;
                l++;
            }
            if (b_len < 1) {
                b_len = reader.read(buffer);
                l = 0;
            }
        }
        return false;

    }

    Boolean hasNext() throws IOException {
        if (b_len < 1) {
            b_len = reader.read(buffer);
            l = 0;
        }
        while (b_len != -1) {
            if ((buffer[l] != '\n'  || buffer[l] == '\r') && (!(Character.isWhitespace(buffer[l])))) {
                return true;
            } else {
                b_len--;
                l++;
            }
            if (b_len < 1) {
                b_len = reader.read(buffer);
                l = 0;
            }
        }
        return false;
    }

    String nextLine() throws IOException {
        s.setLength(0);
        while (b_len != -1) {
            char he=((l+1)<b_len? buffer[l+1]:buffer[l]);
            if (buffer[l] == '\n' || (buffer[l] == '\r')) {
                if((buffer[l] == '\r')){
                    b_len -= 1;
                    l++;
                    if (b_len < 1) {
                        b_len = reader.read(buffer);
                        l = 0;
                    }
                }
                if((buffer[l] == '\n')) {
                    b_len -= 1;
                    l++;
                }
                return s.toString();

            }
            s.append(buffer[l]);
            b_len -= 1;
            l++;
            if (b_len < 1) {
                b_len = reader.read(buffer);
                l = 0;
            }
        }
        return s.toString();
    }

    Integer nextInt() throws IOException {
        s.setLength(0);
        while (b_len != -1) {
            if (!((Character.DECIMAL_DIGIT_NUMBER == Character.getType(buffer[l]) || (buffer[l] == '-')))) {
                b_len -= 1;
                l++;
                return Integer.parseInt(s.toString());
            }
            s.append(buffer[l]);
            b_len -= 1;
            l++;
            if (b_len < 1) {
                b_len = reader.read(buffer);
                l = 0;
            }
        }
        return Integer.parseInt(s.toString());
    }

    String next() throws IOException {
        StringBuilder s = new StringBuilder();
        while (b_len != -1) {
            if ((Character.isWhitespace(buffer[l])) || (buffer[l] == '\n'  || (buffer[l] == '\r' && (buffer[l+1] != '\n')))) {
                b_len -= 1;
                l++;
                return s.toString();
            }
            s.append(buffer[l]);
            b_len -= 1;
            l++;
            if (b_len < 1) {
                b_len = reader.read(buffer);
                l = 0;
            }
        }
        return String.valueOf(s);
    }
}