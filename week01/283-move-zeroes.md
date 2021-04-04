```js
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function(nums) {
  let lastIndex = 0
  for (let i = 0; i < nums.length; i++) {
    if (nums[i] !== 0) {
      nums[lastIndex++] = nums[i]
      if (lastIndex - 1 !== i) nums[i] = 0
    }
  }
};
```

21/21 cases passed (84 ms)
Your runtime beats 80.06 % of javascript submissions
Your memory usage beats 96.03 % of javascript submissions (40.1 MB)