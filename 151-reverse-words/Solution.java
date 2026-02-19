import java.util.*;
public String P0151_reverseWords(String s) {
    if (s == null) return null;
    String[] splitString = s.trim().split("\\s+");
    List<String> reversed_List = new ArrayList<>(Arrays.asList(splitString));
    return String.join(" ", reversed_List.reversed());
}

void main() {
    System.out.println(P0151_reverseWords("   a  good   example   "));
}

/*
public static String P0151_reverseWords(String s) {
    if (s == null) return null;
    String[] parts = s.trim().split("\\s+");
    java.util.List<String> reversed_List = new java.util.ArrayList<>(java.util.Arrays.asList(parts));
    java.util.Collections.reverse(reversed_List);
    String result = String.join(" ", reversed_List);
    return result;
}

public static void main(String[] args) {
    System.out.println(P0151_reverseWords("a good   example "));
}

    for (String s1 : reversed_List){
        System.out.println(s1);
        if (s1.isEmpty()) {
            reversed_List.remove(s1);
        }
    }

String result = String.join(" ", reversed_List);
*/