```js
const reverse = (array, startIndex, endIndex) => {
  let temp = undefined
  while (startIndex < endIndex) {
    temp = array[startIndex]
    array[startIndex] = array[endIndex]
    array[endIndex] = temp
    startIndex++
    endIndex--
  }
}
/**
 * @param {number[]} nums1
 * @param {number} m
 * @param {number[]} nums2
 * @param {number} n
 * @return {void} Do not return anything, modify nums1 in-place instead.
 */
var merge = function(nums1, m, nums2, n) {
  if (m === 0) {
    for (let i = 0; i < n; i++) nums1[i] = nums2[i]
    return
  } else if (n === 0) {
    return
  }
  reverse(nums1, 0, m - 1)
  let toLeftIndex = m - 1
  let toRightIndex = 0
  let currentIndex = m + n - 1
  while (toLeftIndex !== -1 && toRightIndex !== n) {
    if (nums1[toLeftIndex] < nums2[toRightIndex]) {
      nums1[currentIndex] = nums1[toLeftIndex]
      toLeftIndex--
    } else {
      nums1[currentIndex] = nums2[toRightIndex]
      toRightIndex++
    }
    currentIndex--
  }
  if (toLeftIndex === -1) {
    while (toRightIndex !== n) {
      nums1[currentIndex] = nums2[toRightIndex]
      toRightIndex++
      currentIndex--
    }
  } else if (toRightIndex === n) {
    while (toLeftIndex === -1) {
      nums1[currentIndex] = nums1[toLeftIndex]
      toLeftIndex--
      currentIndex--
    }
  }
  return reverse(nums1, 0, m + n - 1)
};
```

59/59 cases passed (84 ms)
Your runtime beats 27.43 % of javascript submissions
Your memory usage beats 32.2 % of javascript submissions (38.9 MB)

---

```js
/**
 * @param {number[]} nums1
 * @param {number} m
 * @param {number[]} nums2
 * @param {number} n
 * @return {void} Do not return anything, modify nums1 in-place instead.
 */
var merge = function(nums1, m, nums2, n) {
  let i = m + n - 1
  m--; n--;
  while(n >= 0) nums1[i--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--]
};
```

59/59 cases passed (76 ms)
Your runtime beats 85.67 % of javascript submissions
Your memory usage beats 32.2 % of javascript submissions (38.9 MB)