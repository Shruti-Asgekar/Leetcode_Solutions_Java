class Solution {
    public int[] singleNumber(int[] nums) {
        // Step 1: XOR all numbers — result is xor = a ^ b (where a and b are unique)
        int xor = 0;
        for (int n : nums) xor ^= n;

        int diff = xor & (-xor);

        int a = 0, b = 0;
        for (int n : nums) {
            if ((n & diff) == 0) a ^= n;
            else b ^= n;
        }

        return new int[]{a, b};
    }
}
