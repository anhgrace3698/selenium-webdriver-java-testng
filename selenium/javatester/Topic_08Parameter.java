package javatester;

import java.util.Random;

public class Topic_08Parameter {

    static String fullNameGlobal = "To Lan Anh";
    public static void main(String[] args) {
        homeName("Lan Anh");  //doi so
        System.out.println(getFullNameGlobal());
    }

    public static  void homeName(String ten_o_nha) { // tham so
        System.out.println(fullNameGlobal);
        fullNameGlobal = ten_o_nha;
        System.out.println(fullNameGlobal);
    }

    public static String getFullNameGlobal() {
        return fullNameGlobal;
    }

}

