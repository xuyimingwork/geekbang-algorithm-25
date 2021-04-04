链接：[26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

问题：给定一个已排序数组，原地移除数组中的重复元素并返回新数组长度。

分析：排序数组意味着重复元素是在一起的，如果每次遇到一个重复的元素就将后面的所有元素向前挪动一格，这种操作过于昂贵。由于重复元素是需要舍弃的，因此该空间可以用来放置后续的非重复元素，直至遍历完数组。

解法：

```js
/*
 * @lc app=leetcode id=26 lang=javascript
 *
 * [26] Remove Duplicates from Sorted Array
 */

// @lc code=start
/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function(nums) {
    let lastIndex = 0
    for (let i = 1; i < nums.length; i++) {
      if (nums[i] !== nums[lastIndex]) nums[++lastIndex] = nums[i]
    }
    return nums.length = lastIndex + 1
};
```