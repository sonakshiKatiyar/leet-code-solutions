package DsaCodingPattern.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abba"));
    }
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, count = 0;
        Map<Character,Integer> map = new HashMap<>();
        while (right < s.length()) {
            char ch = s.charAt(right);
            if(map.containsKey(ch))
                left=Math.max(left,map.get(ch)+1);
            map.put(ch,right);
            count = Math.max(count, right - left + 1);
            right++;
        }
        return count;
    }
}
