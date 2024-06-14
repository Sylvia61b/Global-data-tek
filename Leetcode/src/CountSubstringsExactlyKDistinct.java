public class CountSubstringsExactlyKDistinct {

    /*
    Given a string of lowercase alphabets, count all possible substrings (not necessarily distinct) that has exactly k distinct characters.
    Examples:
    Input: abc, k = 2
    Output: 2
    Possible substrings are {“ab”, “bc”}
    Input: aba, k = 2
    Output: 3
    Possible substrings are {“ab”, “ba”, “aba”}
     */
    public static void main(String[] args) {
        String s1 = "abc";
        int k1 = 2;
        System.out.println("Total substrings with exactly "
                + k1 + " distinct characters : "
                + countSubstringsWithAtExactlyKDistinct(s1,k1));

        String s2 = "aba";
        int k2 = 2;
        System.out.println("Total substrings with exactly "
                + k2 + " distinct characters : "
                + countSubstringsWithAtExactlyKDistinct(s2, k2));
    }


    public static int countSubstringsWithAtExactlyKDistinct(String s, int k){
        return countSubstringsWithAtMostKDistinct(s,k)-countSubstringsWithAtMostKDistinct(s,k-1);
    }



    public static int countSubstringsWithAtMostKDistinct(String s, int k){
        int n= s.length();
        if (n == 0 || k == 0) {
            return 0;
        }

        int[] freqs= new int[26];
        int left = 0;
        int distinctCount =0;
        int count =0;

        for(int right= 0;right<n;right++){
            if(freqs[s.charAt(right)-'a']==0){
                distinctCount++;

            }
            freqs[s.charAt(right)-'a']++;
            while (distinctCount > k) {
                freqs[s.charAt(left) - 'a']--;
                if (freqs[s.charAt(left) - 'a'] == 0) {
                    distinctCount--;
                }
                left++;
            }

            count += right - left + 1;
        }
        return count;

    }


}