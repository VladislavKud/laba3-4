import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Main {

    private static void setDict() throws IOException {
        File file = new File("./dict");

        StringBuilder str = new StringBuilder("aaaaa");

        FileWriter fw = new FileWriter(file);

        for (int i1 = 0; i1 < 26; i1++) {
            for (int i2 = 0; i2 < 26; i2++) {
                for (int i3 = 0; i3 < 26; i3++) {
                    for (int i4 = 0; i4 < 26; i4++) {
                        for (int i5 = 0; i5 < 26; i5++) {

                            fw.append(str + "\n");

                            str.setCharAt(4, (char) ((int) (str.charAt(4)) + 1));
                        }
                        str.setCharAt(4, 'a');

                        str.setCharAt(3, (char) ((int) (str.charAt(3)) + 1));
                    }
                    str.setCharAt(3, 'a');

                    str.setCharAt(2, (char) ((int) (str.charAt(2)) + 1));
                }
                str.setCharAt(2, 'a');

                str.setCharAt(1, (char) ((int) (str.charAt(1)) + 1));
            }
            str.setCharAt(1, 'a');

            str.setCharAt(0, (char) ((int) (str.charAt(0)) + 1));
        }

        fw.close();
    }

    public static void main(String[] args) throws IOException {


        System.out.print("Set dictionary?");
        Scanner scanner = new Scanner(System.in);

        String answer = scanner.nextLine();

        if (answer.toLowerCase().equals("yes")) setDict();

        Brute brute = new Brute();
        brute.init();

    }
}
