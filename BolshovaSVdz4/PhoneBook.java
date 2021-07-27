package BolshovaSVdz4;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();

    public void add(String lastName, String phoneNumber) {
        ArrayList<String> phonesForLastName = phoneBook.getOrDefault(lastName, new ArrayList<>());
        phonesForLastName.add(phoneNumber);
        phoneBook.put(lastName, phonesForLastName);
    }

    public ArrayList<String> get(String lastName) {
        return phoneBook.get(lastName);
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Bolshova", "0123456789");
        phoneBook.add("Petrov", "1234567890");
        phoneBook.add("Ivanov", "2345678901");
        phoneBook.add("Gribov", "0123456789");
        phoneBook.add("Bolshova", "3456789012");

        System.out.println(phoneBook.get("Bolshova"));
    }
}
