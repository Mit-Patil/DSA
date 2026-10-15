class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int nums1Left = m - 1;
        int nums1Right = (m+n) - 1;
        int nums2ElementsPosition  =  n - 1;

        
    while (nums1Left >= 0 && nums2ElementsPosition >= 0) {
        if (nums1[nums1Left] > nums2[nums2ElementsPosition]) {
            nums1[nums1Right] = nums1[nums1Left];
            nums1Left--;
        } else {
            nums1[nums1Right] = nums2[nums2ElementsPosition];
            nums2ElementsPosition--;
        }
        nums1Right--;
    }
    while (nums2ElementsPosition >= 0) {
        nums1[nums1Right] = nums2[nums2ElementsPosition];
        nums2ElementsPosition--;
        nums1Right--;
    }

    }
}