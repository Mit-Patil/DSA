# Hashing

Quick-revision notes for the **Hashing** pattern. Click a problem in the index to jump to its summary, or open the Java solution directly.

## When to use this pattern

- The question is "have I **seen this before**?" or "does a **duplicate** exist?"
- You need to **count** how often each value appears
- You need to find a **pair or value that matches** something, and can look it up instead of searching for it
- A nested loop `O(n^2)` can become one pass `O(n)` by remembering what you have already seen

## Styles

| Style | Structure | Used in |
|-------|-----------|---------|
| **Seen-before** | `HashSet`, `add` returns `false` if the value is already there | 217 |
| **Lookup** | `HashMap<value, index>`, look up the complement before storing the current value | 1 |
| **Frequency count** | `int[26]` array (or `HashMap<char, count>`), count first, then check or compare | 242, 387 |
| **Set + run start** | Put everything in a `HashSet`, only start counting from a number whose `x - 1` is missing | 128 |
| **Group by key** | `HashMap<key, List>`, build a key that is the same for every item in a group (for example the sorted letters) | 49 |

---

## Index

| # | Problem | Difficulty | Style | Solution |
|---|---------|------------|-------|----------|
| 217 | [Contains Duplicate](#217-contains-duplicate) | Easy | Seen-before | [Java](LC217_ContainsDuplicate.java) |
| 1 | [Two Sum](#1-two-sum) | Easy | Lookup | [Java](LC1_TwoSum.java) |
| 242 | [Valid Anagram](#242-valid-anagram) | Easy | Frequency count | [Java](LC242_ValidAnagram.java) |
| 387 | [First Unique Character in a String](#387-first-unique-character-in-a-string) | Easy | Frequency count | [Java](LC387_FirstUniqueCharacter.java) |
| 128 | [Longest Consecutive Sequence](#128-longest-consecutive-sequence) | Medium | Set + run start | [Java](LC128_LongestConsecutiveSequence.java) |
| 49 | [Group Anagrams](#49-group-anagrams) | Medium | Group by key | [Java](LC49_GroupAnagrams.java) |

**Total solved: 6**

---

## 217. Contains Duplicate

[LeetCode](https://leetcode.com/problems/contains-duplicate/) · Easy · [Solution](LC217_ContainsDuplicate.java)

**Problem:** Given an integer array `nums`, return `true` if any value appears at least twice, and `false` if every element is distinct.

**Key points**
- Only the existence of a duplicate matters, not its index or count
- A `HashSet` is enough, a `HashMap` is not needed
- `set.add(x)` returns `false` when `x` is already in the set

**Examples**
```
Input:  nums = [1,2,3,1]
Output: true

Input:  nums = [1,2,3,4]
Output: false

Input:  nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
```

**Constraints:** `1 <= n <= 10^5`, `-10^9 <= nums[i] <= 10^9`

**Approach:** Go through the array once. For each number, call `seen.add(x)`. If it returns `false`, the number was already seen, so return `true`. If the loop finishes, return `false`. (Alternative: sort first and compare neighbours, `O(n log n)` time but `O(1)` extra space.)

**Complexity:** Time `O(n)` · Space `O(n)`

[Back to index](#index)

---

## 1. Two Sum

[LeetCode](https://leetcode.com/problems/two-sum/) · Easy · [Solution](LC1_TwoSum.java)

**Problem:** Given an array `nums` and an integer `target`, return the indices of the two numbers that add up to `target`.

**Key points**
- Exactly one valid answer exists
- The same element cannot be used twice
- The answer can be returned in any order
- Look up the complement **before** storing the current number, so a number can't match itself (this also handles `[3,3]`)

**Examples**
```
Input:  nums = [2,7,11,15], target = 9
Output: [0,1]

Input:  nums = [3,2,4], target = 6
Output: [1,2]

Input:  nums = [3,3], target = 6
Output: [0,1]
```

**Constraints:** `2 <= n <= 10^4`, `-10^9 <= nums[i], target <= 10^9`, exactly one valid answer

**Approach:** Keep a `HashMap<value, index>`. For each `nums[i]`, look up `target - nums[i]`. If it is in the map, return its stored index and `i`. Otherwise store `nums[i]` with its index and continue.

**Complexity:** Time `O(n)` · Space `O(n)`

[Back to index](#index)

---

## 242. Valid Anagram

[LeetCode](https://leetcode.com/problems/valid-anagram/) · Easy · [Solution](LC242_ValidAnagram.java)

**Problem:** Given two strings `s` and `t`, return `true` if `t` is an anagram of `s` (same letters with the same counts, in any order), and `false` otherwise.

**Key points**
- Different lengths can never be anagrams, so check the length first
- Both strings contain only lowercase letters, so `int[26]` is enough
- `int[26]` is much faster than a `HashMap` (no boxing, no hashing)
- For Unicode input, 26 slots are not enough, so use a `HashMap`

**Examples**
```
Input:  s = "anagram", t = "nagaram"
Output: true

Input:  s = "rat", t = "car"
Output: false
```

**Constraints:** `1 <= s.length, t.length <= 5 * 10^4`, lowercase English letters only

**Approach:** If the lengths differ, return `false`. Use `count = new int[26]`. For each index, do `count[s[i] - 'a']++` and `count[t[i] - 'a']--`. Then every count must be `0`. Alternative: build a `HashMap` of character counts for each string and compare them with `equals` (slower, but works for any characters, including Unicode).

**Complexity:** Time `O(n)` · Space `O(1)` (26 slots), or `O(k)` for a `HashMap` with `k` distinct characters

[Back to index](#index)


---

## 387. First Unique Character in a String

[LeetCode](https://leetcode.com/problems/first-unique-character-in-a-string/) · Easy · [Solution](LC387_FirstUniqueCharacter.java)

**Problem:** Given a string `s`, return the index of the first character that does not repeat anywhere in the string. Return `-1` if there is none.

**Key points**
- Needs two passes: first count every character, then find the first one with a count of `1`
- The second pass must go through the **string** (not the map), because a `HashMap` has no guaranteed order
- Only lowercase letters, so `int[26]` is enough and much faster than a `HashMap`
- Removing a character from a map on its second sighting breaks when it appears 3 times

**Examples**
```
Input:  s = "leetcode"
Output: 0

Input:  s = "loveleetcode"
Output: 2

Input:  s = "aabb"
Output: -1
```

**Constraints:** `1 <= s.length <= 10^5`, lowercase English letters only

**Approach:** Count each letter with `count[c - 'a']++`. Then go through the string again and return the first index whose count is `1`. If none, return `-1`. Other ways: a `HashMap` with `merge(c, 1, Integer::sum)` for the counts, or one pass that stores the first index and marks repeated characters as `-1`, then takes the smallest remaining index.

**Complexity:** Time `O(n)` · Space `O(1)` (26 slots), or `O(k)` for a `HashMap`

[Back to index](#index)

---

## 128. Longest Consecutive Sequence

[LeetCode](https://leetcode.com/problems/longest-consecutive-sequence/) · Medium · [Solution](LC128_LongestConsecutiveSequence.java)

**Problem:** Given an unsorted array `nums`, return the length of the longest sequence of consecutive integers (for example `1, 2, 3, 4`).

**Key points**
- Must run in `O(n)`, so sorting (`O(n log n)`) is not allowed
- Order in the array does not matter, and duplicates do not count twice
- Only start counting from the **beginning** of a run (a number whose `x - 1` is not in the set), otherwise the same run is counted many times
- Loop over the **set**, not the array, so duplicates do not repeat the work
- The array can be empty, the answer is then `0`

**Examples**
```
Input:  nums = [100,4,200,1,3,2]
Output: 4      ([1,2,3,4])

Input:  nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

Input:  nums = [1,0,1,2]
Output: 3
```

**Constraints:** `0 <= n <= 10^5`, `-10^9 <= nums[i] <= 10^9`

**Approach:** Put all numbers into a `HashSet`. For each `x` in the set, if `x - 1` is **not** in the set, `x` starts a run. Walk upward (`x + 1`, `x + 2`, ...) while the next number is in the set, and track the longest length.

**Complexity:** Time `O(n)` · Space `O(n)`

[Back to index](#index)

---

## 49. Group Anagrams

[LeetCode](https://leetcode.com/problems/group-anagrams/) · Medium · [Solution](LC49_GroupAnagrams.java)

**Problem:** Given an array of strings `strs`, group the anagrams together. The groups can be returned in any order.

**Key points**
- Comparing every word with every other word is `O(n^2)` and too slow
- Anagrams have the **same letters when sorted**, so the sorted word works as a label (key)
- The map is `HashMap<label, list of words>`
- Use `new String(chars)` to turn the sorted `char[]` back into a `String` (`toString()` prints the array reference instead)
- An empty string is valid and forms its own group

**Examples**
```
Input:  strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Input:  strs = [""]
Output: [[""]]

Input:  strs = ["a"]
Output: [["a"]]
```

**Constraints:** `1 <= n <= 10^4`, `0 <= strs[i].length <= 100`, lowercase English letters only

**Approach:** For each word, sort its letters to make a label (`"eat"` becomes `"aet"`). If the label is not in the map yet, create an empty list for it. Add the word to that list. At the end, return all the lists in the map (`new ArrayList<>(map.values())`). Another way to build the key: count the letters in an `int[26]` and convert it to a `String` (do not use the `int[]` itself as a key, because arrays are compared by reference).

**Complexity:** Time `O(n * L log L)` where `L` is the longest word · Space `O(n * L)`

[Back to index](#index)