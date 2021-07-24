package BolshovaSVdz3;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    }

    public static void changeArrElements(Object[] array, int a, int b) {
        Object tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
