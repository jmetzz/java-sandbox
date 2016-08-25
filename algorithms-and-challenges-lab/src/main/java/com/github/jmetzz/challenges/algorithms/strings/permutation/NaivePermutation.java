package com.github.jmetzz.challenges.algorithms.strings.permutation;


public class NaivePermutation {


    public static void main(String[] args) {
        permutation("abc");
    }

    public static void permutation(String str){
        permutation(str, "");
    }

    /**
     * The time complexity of this method is O(n^2 * n!)
     * How many times does permutation gets called in its base case?
     *  the total number of options is 7 * 6 * 5 * 4 * 3 * 2 * 1,which is also expressed as 7! (7 factorial).
     *  Thus, permutation is called n ! times in its base case (when prefix is the full permutation)
     *
     * How many times does permutation get called before its base case?
     *      There are n ! leaves, as shown above. Each leaf is attached to a path of length n.
     *      Therefore, we know there will be no more than n * n ! nodes (function calls) in this tree.
     *
     * What is the total runtime?
     *      Since we are calling permutation 0(n * n ! ) times (as an upper bound),and each one takes 0(n) time,
     *      the total runtime will not exceed O ( n2 * n ! ).
     *
     * @param str
     * @param prefix
     */
    private static void permutation(String str, String prefix) {
        if(str.length() == 0){
            System.out.println(prefix);
        } else {
            for (int i=0; i <  str.length(); i++){
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }
}
