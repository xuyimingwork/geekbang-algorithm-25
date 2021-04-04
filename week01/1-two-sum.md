```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
  const map = new Map()

  for (let i = 0; i < nums.length; i++) {
    const expect = target - nums[i]
    if (map.has(expect)) return [i, map.get(expect)]
    map.set(nums[i], i)
  }

  return []
};
```

53/53 cases passed (76 ms)
Your runtime beats 81.15 % of javascript submissions
Your memory usage beats 77.84 % of javascript submissions (38.7 MB)

原本使用了两次循环，一次用于构建 map，另一次用于查找。但实际一次循环即可，因为对于两个均在数组中的值而言，当读取到一个，另一个要么已存在于 map 中，要么在剩余的项中