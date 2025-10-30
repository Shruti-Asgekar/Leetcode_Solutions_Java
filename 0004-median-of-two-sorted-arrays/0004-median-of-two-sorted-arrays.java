public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = merge(nums1, nums2);
        int n = merged.length;
        if (n % 2 == 1) {
            return merged[n / 2];
        } else {
            return (merged[n / 2 - 1] + merged[n / 2]) / 2.0;
        }
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] result = new int[n1 + n2];
        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) result[k++] = nums1[i++];
            else result[k++] = nums2[j++];
        }
        while (i < n1) result[k++] = nums1[i++];
        while (j < n2) result[k++] = nums2[j++];

        return result;
    }
}
