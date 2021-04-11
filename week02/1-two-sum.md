```javascript
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    const map = Object.create(null)
    for (let i = 0, length = nums.length; i < length; i++) {
        const other = target - nums[i]
        if (other in map) return [i, map[other]]
        map[nums[i]] = i
    }
};
```

29 / 29 test cases passed.
Status: Accepted
Runtime: 52 ms
Memory Usage: 34.5 MB
