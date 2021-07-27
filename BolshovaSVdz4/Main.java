package BolshovaSVdz4;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String[] words = {"red", "yellow", "orange", "blue", "green","purple",
        "pink ","black","white","gray","brown","red", "yellow", "gray", "orange"};

        HashMap<String, Integer> uniqueWords = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            if (uniqueWords.containsKey(words[i])) {
                uniqueWords.put(words[i], uniqueWords.get(words[i]) + 1);
            } else {
                uniqueWords.put(words[i], 1);
            }
        }

        System.out.println(uniqueWords);
    }
}
