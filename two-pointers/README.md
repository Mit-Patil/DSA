# Two Pointer

Quick-revision notes for the **Two Pointer** pattern. Click a problem in the index to jump to its summary, or open the Java solution directly.

## When to use this pattern

- Array or string is **sorted**, or can be sorted first
- You need a **pair / triplet** with a target sum
- You need to check something from **both ends** (palindrome)
- You need to modify an array **in-place** (remove, move, merge)

## Two styles

| Style | Setup | Used in |
|-------|-------|---------|
| **Opposite ends** | `left = 0`, `right = n - 1`, move toward each other | 167, 125, 11, 15 |
| **Same direction** (slow / fast) | `slow` writes, `fast` reads, both move forward | 283, 26 |
| **Merge from the back** | pointers at the end of each array, fill from the end | 88 |

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 167 | [Two Sum II](#167-two-sum-ii) | Medium | Opposite ends | [Java](LC167_TwoSumII.java) |
| 125 | [Valid Palindrome](#125-valid-palindrome) | Easy | Opposite ends | [Java](LC125_ValidPalindrome.java) |
| 11 | [Container With Most Water](#11-container-with-most-water) | Medium | Opposite ends | [Java](LC11_ContainerWithMostWater.java) |
| 15 | [3Sum](#15-3sum) | Medium | Sort + opposite ends | [Java](LC15_3Sum.java) |
| 283 | [Move Zeroes](#283-move-zeroes) | Easy | Slow / fast | [Java](LC283_MoveZeroes.java) |
| 26 | [Remove Duplicates from Sorted Array](#26-remove-duplicates-from-sorted-array) | Easy | Slow / fast | [Java](LC26_RemoveDuplicates.java) |
| 88 | [Merge Sorted Array](#88-merge-sorted-array) | Easy | Merge from back | [Java](LC88_MergeSortedArray.java) |

**Total solved: 7**

---

## 167. Two Sum II

[LeetCode](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) · Medium · [Solution](LC167_TwoSumII.java)

**Problem:** Sorted array (1-indexed). Find two numbers that add up to `target`. Return their 1-based indices `[index1, index2]`.

**Key points**
- Array is sorted in non-decreasing order
- Exactly one solution exists
- Cannot use the same element twice
- Must use **constant extra space**

**Examples**
```
Input:  numbers = [2,7,11,15], target = 9
Output: [1,2]

Input:  numbers = [2,3,4], target = 6
Output: [1,3]

Input:  numbers = [-1,0], target = -1
Output: [1,2]
```

**Constraints:** `2 <= n <= 3 * 10^4`, values and target in `[-1000, 1000]`

**Approach:** `left` at start, `right` at end. Sum too big → `right--`. Sum too small → `left++`. Equal → answer.

**Complexity:** Time `O(n)` · Space `O(1)`

**My mistakes / remember:** _(fill in)_

[Back to index](#index)

---

## 125. Valid Palindrome

[LeetCode](https://leetcode.com/problems/valid-palindrome/) · Easy · [Solution](LC125_ValidPalindrome.java)

**Problem:** Given a string `s`, return `true` if it is a palindrome after lowercasing and removing all non-alphanumeric characters.

**Key points**
- Ignore case
- Ignore everything except letters and digits
- An empty string (after cleaning) is a palindrome

**Examples**
```
Input:  s = "A man, a plan, a canal: Panama"
Output: true      ("amanaplanacanalpanama")

Input:  s = "race a car"
Output: false     ("raceacar")

Input:  s = " "
Output: true      (empty after cleaning)
```

**Constraints:** `1 <= s.length <= 2 * 10^5`, printable ASCII only

**Approach:** `left` and `right` move inward. Skip non-alphanumeric characters on each side, then compare lowercase characters. Mismatch → `false`.

**Complexity:** Time `O(n)` · Space `O(1)`

**My mistakes / remember:** _(fill in)_

[Back to index](#index)

---

## 11. Container With Most Water

[LeetCode](https://leetcode.com/problems/container-with-most-water/) · Medium · [Solution](LC11_ContainerWithMostWater.java)

**Problem:** `height[i]` is the height of a vertical line at position `i`. Pick two lines that, with the x-axis, hold the most water. Return that maximum area.

**Key points**
- Area = `min(height[left], height[right]) * (right - left)`
- Container cannot be slanted

**Examples**
```
Input:  height = [1,8,6,2,5,4,8,3,7]
Output: 49

Input:  height = [1,1]
Output: 1
```

**Constraints:** `2 <= n <= 10^5`, `0 <= height[i] <= 10^4`

**Approach:** Start with the widest container. Always move the pointer at the **shorter** line inward, because moving the taller one can never increase the area. Track the max.

**Complexity:** Time `O(n)` · Space `O(1)`

**My mistakes / remember:** _(fill in)_

[Back to index](#index)

---

## 15. 3Sum

[LeetCode](https://leetcode.com/problems/3sum/) · Medium · [Solution](LC15_3Sum.java)

**Problem:** Return all unique triplets `[nums[i], nums[j], nums[k]]` with distinct indices such that the sum is `0`.

**Key points**
- No duplicate triplets in the result
- Order of triplets and order inside a triplet does not matter

**Examples**
```
Input:  nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Input:  nums = [0,1,1]
Output: []

Input:  nums = [0,0,0]
Output: [[0,0,0]]
```

**Constraints:** `3 <= n <= 3000`, `-10^5 <= nums[i] <= 10^5`

**Approach:** Sort the array. Fix one number `i`, then run two pointers (`left = i + 1`, `right = n - 1`) to find pairs summing to `-nums[i]`. **Skip duplicates** for `i`, `left` and `right` to avoid repeated triplets.

**Complexity:** Time `O(n^2)` · Space `O(1)` extra (ignoring sort and output)

**My mistakes / remember:** _(fill in)_

[Back to index](#index)

---

## 283. Move Zeroes

[LeetCode](https://leetcode.com/problems/move-zeroes/) · Easy · [Solution](LC283_MoveZeroes.java)

**Problem:** Move all `0`s to the end of the array, keeping the relative order of non-zero elements.

**Key points**
- Must be done **in-place** (no copy of the array)
- Order of non-zero elements must stay the same

**Examples**
```
Input:  nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Input:  nums = [0]
Output: [0]
```

**Constraints:** `1 <= n <= 10^4`, `-2^31 <= nums[i] <= 2^31 - 1`

**Approach:** `slow` marks where the next non-zero goes. `fast` scans the array. When `nums[fast] != 0`, swap it with `nums[slow]` and `slow++`.

**Complexity:** Time `O(n)` · Space `O(1)`

**My mistakes / remember:** _(fill in)_

[Back to index](#index)

---

## 26. Remove Duplicates from Sorted Array

[LeetCode](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) · Easy · [Solution](LC26_RemoveDuplicates.java)

**Problem:** Sorted array. Remove duplicates in-place so each value appears once. Return `k`, the number of unique elements.

**Key points**
- Array is sorted, so duplicates are next to each other
- First `k` elements must hold the unique values in order
- Anything after index `k - 1` is ignored

**Examples**
```
Input:  nums = [1,1,2]
Output: 2, nums = [1,2,_]

Input:  nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
```

**Constraints:** `1 <= n <= 3 * 10^4`, `-100 <= nums[i] <= 100`, sorted non-decreasing

**Approach:** `slow` points at the last unique element. `fast` scans ahead. When `nums[fast] != nums[slow]`, do `slow++` and copy `nums[fast]` into `nums[slow]`. Return `slow + 1`.

**Complexity:** Time `O(n)` · Space `O(1)`

**My mistakes / remember:** _(fill in)_

[Back to index](#index)

---

## 88. Merge Sorted Array

[LeetCode](https://leetcode.com/problems/merge-sorted-array/) · Easy · [Solution](LC88_MergeSortedArray.java)

**Problem:** Merge sorted `nums2` (size `n`) into sorted `nums1` (size `m + n`, last `n` slots are `0` placeholders). Result is stored **inside `nums1`**.

**Key points**
- Nothing is returned, `nums1` is modified in-place
- `nums1` has extra empty space at the end
- `m` or `n` can be `0`

**Examples**
```
Input:  nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]

Input:  nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]

Input:  nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
```

**Constraints:** `0 <= m, n <= 200`, `1 <= m + n <= 200`, values in `[-10^9, 10^9]`

**Approach:** Fill from the **back** so nothing gets overwritten. Pointers: `i = m - 1`, `j = n - 1`, `k = m + n - 1`. Put the larger of `nums1[i]` and `nums2[j]` at `k`. After the loop, copy any leftover `nums2` elements.

**Complexity:** Time `O(m + n)` · Space `O(1)`

**My mistakes / remember:** _(fill in)_

[Back to index](#index)
