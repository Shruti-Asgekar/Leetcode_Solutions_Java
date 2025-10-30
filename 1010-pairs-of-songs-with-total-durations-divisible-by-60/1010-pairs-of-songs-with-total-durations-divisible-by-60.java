class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] remainderCount = new int[60];
        int count = 0;

        for (int t : time) {
            int rem = t % 60;
            int complement = (60 - rem) % 60;
            count += remainderCount[complement];
            remainderCount[rem]++;
        }

        return count;
    }
}
