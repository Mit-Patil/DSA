# Sliding Window

Quick-revision notes for the **Sliding Window** pattern. Click a problem in the index to jump to its summary, or open the Java solution directly.

## When to use this pattern

- You need something about a **contiguous subarray or substring**
- The question asks for the **longest, shortest, maximum or minimum** window
- A window of a **fixed size `k`** is given
- A window must satisfy a condition (no repeats, sum >= target, at most `k` changes)

## Two styles

| Style | Setup | Used in |
|-------|-------|---------|
| **Fixed size window** | Window length is always `k`. Add the new element on the right, remove the old one on the left | 643, 239, 567 |
| **Variable size window** | `right` expands the window. `left` shrinks it when the condition breaks | 3, 209, 424, 1004 |

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 643 | [Maximum Average Subarray I](#643-maximum-average-subarray-i) | Easy | Fixed | [Java](LC643_MaxAvgSubArrayI.java) |
| 239 | [Sliding Window Maximum](#239-sliding-window-maximum) | Hard | Fixed + deque | [Java](LC239_SlidingWindowMax.java) |
| 567 | [Permutation in String](#567-permutation-in-string) | Medium | Fixed | [Java](LC567_PermutationInString.java) |
| 3 | [Longest Substring Without Repeating Characters](#3-longest-substring-without-repeating-characters) | Medium | Variable | [Java](LC3_LongSubstringWithoutRepeatingChar.java) |
| 209 | [Minimum Size Subarray Sum](#209-minimum-size-subarray-sum) | Medium | Variable | [Java](LC209_MinSizeSubArraySum.java) |
| 424 | [Longest Repeating Character Replacement](#424-longest-repeating-character-replacement) | Medium | Variable | [Java](LC424_LongRepeatingCharReplacement.java) |
| 1004 | [Max Consecutive Ones III](#1004-max-consecutive-ones-iii) | Medium | Variable | [Java](LC1004_MaxConsecutiveOnesIII.java) |

**Total solved: 7**

---

## 643. Maximum Average Subarray I

[LeetCode](https://leetcode.com/problems/maximum-average-subarray-i/) · Easy · [Solution](LC643_MaxAvgSubArrayI.java)

**Problem:** Given an array `nums` and an integer `k`, find the contiguous subarray of length `k` with the maximum average. Return that average.

**Key points**
- Window length is exactly `k`
- Return the average, not the sum
- An answer with error less than `10^-5` is accepted

**Examples**
```
Input:  nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000      ((12 - 5 - 6 + 50) / 4)

Input:  nums = [5], k = 1
Output: 5.00000
```

**Constraints:** `1 <= k <= n <= 10^5`, `-10^4 <= nums[i] <= 10^4`

**Approach:** Sum the first `k` elements. Then slide: add the new right element, subtract the old left element, and track the max sum. Divide by `k` at the end.

**Complexity:** Time `O(n)` · Space `O(1)`

[Back to index](#index)

---

## 239. Sliding Window Maximum

[LeetCode](https://leetcode.com/problems/sliding-window-maximum/) · Hard · [Solution](LC239_SlidingWindowMax.java)

**Problem:** A window of size `k` moves from left to right over `nums`. Return the maximum of each window position.

**Key points**
- Window moves one step at a time
- Result has `n - k + 1` values
- Brute force (scan every window) is too slow

**Examples**
```
Input:  nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]

Input:  nums = [1], k = 1
Output: [1]
```

**Constraints:** `1 <= n <= 10^5`, `-10^4 <= nums[i] <= 10^4`, `1 <= k <= n`

**Approach:** Use a **monotonic decreasing deque** that stores indices. Before adding index `i`, pop from the back while the values are smaller than `nums[i]`. Pop from the front if its index is outside the window. The front of the deque is always the window maximum.

**Complexity:** Time `O(n)` · Space `O(k)`

[Back to index](#index)

---

## 567. Permutation in String

[LeetCode](https://leetcode.com/problems/permutation-in-string/) · Medium · [Solution](LC567_PermutationInString.java)

**Problem:** Given `s1` and `s2`, return `true` if some permutation of `s1` is a substring of `s2`.

**Key points**
- A permutation has the same letters with the same counts, in any order
- The substring must be contiguous
- Window size is always `s1.length()`

**Examples**
```
Input:  s1 = "ab", s2 = "eidbaooo"
Output: true      ("ba" is a permutation of "ab")

Input:  s1 = "ab", s2 = "eidboaoo"
Output: false
```

**Constraints:** `1 <= s1.length, s2.length <= 10^4`, lowercase English letters only

**Approach:** Count the letters of `s1` in a size-26 array. Slide a window of the same length over `s2` and keep its own count array. If both count arrays are equal, return `true`.

**Complexity:** Time `O(n)` · Space `O(1)` (26 letters)

[Back to index](#index)

---

## 3. Longest Substring Without Repeating Characters

[LeetCode](https://leetcode.com/problems/longest-substring-without-repeating-characters/) · Medium · [Solution](LC3_LongSubstringWithoutRepeatingChar.java)

**Problem:** Given a string `s`, return the length of the longest substring that has no repeated characters.

**Key points**
- It must be a **substring** (contiguous), not a subsequence
- Empty string is allowed, answer is `0`

**Examples**
```
Input:  s = "abcabcbb"
Output: 3      ("abc")

Input:  s = "bbbbb"
Output: 1      ("b")

Input:  s = "pwwkew"
Output: 3      ("wke", not "pwke" because that is a subsequence)
```

**Constraints:** `0 <= s.length <= 10^5`, letters, digits, symbols and spaces

**Approach:** Expand `right` one character at a time. If `s[right]` is already in the window, move `left` forward until the duplicate is removed (a `Set`, or a map of last seen index). Track the max window length.

**Complexity:** Time `O(n)` · Space `O(m)` where `m` is the size of the character set

[Back to index](#index)

---

## 209. Minimum Size Subarray Sum

[LeetCode](https://leetcode.com/problems/minimum-size-subarray-sum/) · Medium · [Solution](LC209_MinSizeSubArraySum.java)

**Problem:** Given positive integers `nums` and a `target`, return the minimal length of a subarray whose sum is `>= target`. Return `0` if none exists.

**Key points**
- All numbers are **positive**, which is why shrinking the window works
- Subarray must be contiguous
- Return `0` when no subarray reaches the target

**Examples**
```
Input:  target = 7, nums = [2,3,1,2,4,3]
Output: 2      ([4,3])

Input:  target = 4, nums = [1,4,4]
Output: 1

Input:  target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
```

**Constraints:** `1 <= target <= 10^9`, `1 <= n <= 10^5`, `1 <= nums[i] <= 10^4`

**Approach:** Add `nums[right]` to the running sum. While `sum >= target`, update the min length and shrink the window (subtract `nums[left]`, `left++`). If the min length never changed, return `0`.

**Complexity:** Time `O(n)` · Space `O(1)`

[Back to index](#index)

---

## 424. Longest Repeating Character Replacement

[LeetCode](https://leetcode.com/problems/longest-repeating-character-replacement/) · Medium · [Solution](LC424_LongRepeatingCharReplacement.java)

**Problem:** You may change at most `k` characters in `s` to any uppercase letter. Return the length of the longest substring with all the same letter after the changes.

**Key points**
- At most `k` replacements
- Only uppercase English letters
- Answer is the longest valid window length

**Examples**
```
Input:  s = "ABAB", k = 2
Output: 4

Input:  s = "AABABBA", k = 1
Output: 4      ("AABBBBA" -> "BBBB")
```

**Constraints:** `1 <= s.length <= 10^5`, uppercase letters only, `0 <= k <= s.length`

**Approach:** Keep a size-26 frequency array for the window and the highest frequency `maxFreq` inside it. A window is valid if `windowLength - maxFreq <= k`. If not, shrink from the left. Track the max window length.

**Complexity:** Time `O(n)` · Space `O(1)` (26 letters)

[Back to index](#index)

---

## 1004. Max Consecutive Ones III

[LeetCode](https://leetcode.com/problems/max-consecutive-ones-iii/) · Medium · [Solution](LC1004_MaxConsecutiveOnesIII.java)

**Problem:** Given a binary array `nums` and an integer `k`, return the maximum number of consecutive `1`s if you can flip at most `k` zeros.

**Key points**
- Array has only `0` and `1`
- At most `k` zeros can be flipped
- Same idea as "longest window with at most `k` zeros"

**Examples**
```
Input:  nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6

Input:  nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
```

**Constraints:** `1 <= n <= 10^5`, `nums[i]` is `0` or `1`, `0 <= k <= n`

**Approach:** Expand `right` and count the zeros in the window. If zeros `> k`, shrink from the left until zeros `<= k`. Track the max window length.

**Complexity:** Time `O(n)` · Space `O(1)`

[Back to index](#index)