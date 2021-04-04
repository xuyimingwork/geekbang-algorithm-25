链接：[189. Rotate Array](https://leetcode.com/problems/rotate-array/)

问题：给定一个数组，将该数组向右移动 `k` 步，`k` 非负。即从数组尾部弹出 `k` 个元素并将其依次插入数组头部

解法（暴力）：

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var rotate = function(nums, k) {
    for (let i = 0; i < k; i++) {
      nums.unshift(nums.pop())
    }
};
```

35/35 cases passed (120 ms)
Your runtime beats 26.32 % of javascript submissions
Your memory usage beats 58.69 % of javascript submissions (39.7 MB)

暴力解，若换成 while 更简洁

---

解法（旋转）：

```js
const reverse = (array, startIndex, endIndex) => {
  let temp
  while (startIndex < endIndex) {
    temp = array[startIndex]
    array[startIndex] = array[endIndex]
    array[endIndex] = temp
    startIndex++
    endIndex--
  }
}
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var rotate = function(nums, k) {
  const step = k % nums.length
  reverse(nums, 0, nums.length - 1)
  reverse(nums, 0, step - 1)
  reverse(nums, step, nums.length - 1)
};
```

35/35 cases passed (88 ms)
Your runtime beats 82.21 % of javascript submissions
Your memory usage beats 24.63 % of javascript submissions (40 MB)
