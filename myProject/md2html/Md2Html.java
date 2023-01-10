package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) throws IOException {
        BufferedReader re = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8), 1024);
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));
        StringBuilder s = new StringBuilder();
        String ss = re.readLine();
        Convert z = new Convert();
        int o = 0;
        while (ss != null) {
            if (ss.isEmpty()) {
                if (!s.isEmpty()) {
                    s.deleteCharAt(s.length() - 1);
                    z.convert(new StringBuilder(s.toString()));
                    wr.write(String.valueOf(z.s));
                    wr.newLine();
                    z.s = new StringBuilder();
                    s = new StringBuilder("");
                }
            }
            else{
                s.append(ss);
                s.append("\n");
            }
            ss = re.readLine();
        }
        if (!s.isEmpty()) {
            z.convert(new StringBuilder(s.toString()));
            wr.write(String.valueOf(z.s));
        }
        wr.close();
        re.close();
    }
}