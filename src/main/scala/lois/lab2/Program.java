package lois.lab2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cidyuk
 */
public class Program {

    public static Set<String> set = new HashSet<String>();
    public static StringBuffer strBuf = new StringBuffer("");

    public static void main(String[] args) throws Exception {
        String str = "11111000";
        StringBuffer strBuf = new StringBuffer(str);
        doPerm(strBuf, str.length());
    }

    public static Set<String> showPermutations(int[] arr) {
        strBuf = new StringBuffer();
        for (int a : arr) {
            strBuf.append(a);
        }
        doPerm(strBuf, strBuf.length());
        return set;
    }

    public static void doPerm(StringBuffer str, int index) {
        if (index == 0) {
            set.add(str.toString());
        } else { //recursively solve this by placing all other chars at current first pos
            doPerm(str, index - 1);
            int currPos = str.length() - index;
            for (int i = currPos + 1; i < str.length(); i++) {//start swapping all other chars with current first char
                swap(str, currPos, i);
                doPerm(str, index - 1);
                swap(str, i, currPos);//restore back my string buffer
            }
        }
    }

    private static void swap(StringBuffer str, int pos1, int pos2) {
        char t1 = str.charAt(pos1);
        str.setCharAt(pos1, str.charAt(pos2));
        str.setCharAt(pos2, t1);
    }

//    public static Set<String> showPermutations(int[] arr) {
////        for (int i = 0; i < arr.length; i++) {
////            for (int j = 0; j < arr.length - 1; j++) {
////                value = "";
////                for (int anArr : arr) {
////                    value += anArr;
////                }
////                System.out.println(" - " + value);
////                set.add(value);
////                int tmp = arr[j];
////                arr[j] = arr[j + 1];
////                arr[j + 1] = tmp;
////            }
////        }
//
//        return set;
//    }
}
