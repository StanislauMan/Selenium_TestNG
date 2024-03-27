package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public  static int getSum(int a, int b) {
        return a + b;
    }

    public static String getPad(String substr, int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += substr;
        }
        return result;
    }
}