public class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder result = new StringBuilder();
        int cycleLength = 2 * numRows - 2;

        for (int row = 0; row < numRows; row++) {
            for (int i = row; i < s.length(); i += cycleLength) {
                result.append(s.charAt(i));
                if (row != 0 && row != numRows - 1) {
                    int mid = i + cycleLength - 2 * row;
                    if (mid < s.length()) result.append(s.charAt(mid));
                }
            }
        }
        return result.toString();
    }
}