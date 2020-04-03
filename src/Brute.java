import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class Brute {
    private final String hash1 = "1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad";
    private final String hash2 = "3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b";
    private final String hash3 = "74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f";

    private byte[] byte1 = decodeHexString(hash1);
    private byte[] byte2 = decodeHexString(hash2);
    private byte[] byte3 = decodeHexString(hash3);

    public static byte hexToByte(String hexString){
        int firstDigit = toDigit(hexString.charAt(0));
        int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    public static byte[] decodeHexString(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException(
                    "Invalid hexadecimal String supplied.");
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }

    private static int toDigit(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if(digit == -1) {
            throw new IllegalArgumentException(
                    "Invalid Hexadecimal Character: "+ hexChar);
        }
        return digit;
    }

    public void init(){


        //apple
        //mmmmm
        //zyzzx

        new Thread(new MyThread("./dict1")).start();
        new Thread(new MyThread("./dict2")).start();
        new Thread(new MyThread("./dict3")).start();
        new Thread(new MyThread("./dict4")).start();



    }

    private class MyThread extends Thread{
        private String path = null;

        public MyThread(String path){
            this.path = path;
        }

        private void find(){

            File file = new File(path);

            Scanner scan = null;
            try {
                scan = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            String str;

            while (scan.hasNext()) {

                str = scan.nextLine();

                byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));

                if(Arrays.equals(byte1, hash)) System.out.println(str);
                if(Arrays.equals(byte2, hash)) System.out.println(str);
                if(Arrays.equals(byte3, hash)) System.out.println(str);
            }
            scan.close();
        }

        @Override
        public void run() {
            find();
        }
    }
}


