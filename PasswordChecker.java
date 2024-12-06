import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PasswordChecker {
    public static void main(String[] args) throws IOException {
        //Load dictionary
        HashTableSeparateChaining dictSeparateChaining = new HashTableSeparateChaining(1000);
        HashTableLinearProbing dictLinearProbing = new HashTableLinearProbing(20000);

        Scanner fileScanner = new Scanner(new File("wordlist.txt"));
        while (fileScanner.hasNextLine()) {
            String word = fileScanner.nextLine();
            dictSeparateChaining.insert(word, 37); //multiplier 37
            dictLinearProbing.insert(word, 31); //multiplier 31
        }
        fileScanner.close();

        //Test passwords
        String[] passwords = {"account8", "accountability", "9a$D#qW7!uX&Lv3zT", "B@k45*W!c$Y7#zR9P", "X$8vQ!mW#3Dz&Yr4K5"};
        for (String password : passwords) {
            System.out.println("Testing password: " + password);

            boolean isStrong = isStrongPassword(password, dictSeparateChaining, dictLinearProbing);
            System.out.println("Strong: " + isStrong);

            //Display comparisons for both hash functions
            System.out.println("Separate Chaining (HashCode * 37): Comparisons = " + dictSeparateChaining.getComparisons());
            System.out.println("Linear Probing (HashCode * 31): Comparisons = " + dictLinearProbing.getComparisons());
            System.out.println();
        }
    }

    public static boolean isStrongPassword(String password, HashTableSeparateChaining dictSeparateChaining, HashTableLinearProbing dictLinearProbing) {
        // Check length
        if (password.length() < 8) return false;

        // Check dictionary
        if (dictSeparateChaining.search(password, 37) || dictLinearProbing.search(password, 31)) return false;

        // Check dictionary + digit
        for (int i = 0; i < 10; i++) {
            String modified = password + i;
            if (dictSeparateChaining.search(modified, 37) || dictLinearProbing.search(modified, 31)) return false;
        }
        return true;
    }
}
