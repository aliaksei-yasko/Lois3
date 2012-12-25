package lois.lab2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cidyuk
 */
public class Program {

    public static Set<String> set = new HashSet<String>();
    public static String value = "";

    public static Set<String> showPermutations(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                value = "";
                for (int anArr : arr) {
                    value += anArr;
                }
                set.add(value);
                int tmp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = tmp;
            }
        }
        return set;
    }
}
