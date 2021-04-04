```js
/**
 * @param {number[]} height
 * @return {number}
 */
var trap = function(height) {
  if (height.length === 0) return 0;

  let leftIndex = 0;
  let rightIndex = height.length - 1;

  let leftMax = 0;
  let rightMax = 0;

  let result = 0;

  while (leftIndex < rightIndex) {
    leftMax = Math.max(leftMax, height[leftIndex]);
    // 当前左列左侧存在更高列
    if (height[leftIndex] < leftMax) {
      result += leftMax - height[leftIndex];
    }

    rightMax = Math.max(rightMax, height[rightIndex]);
    // 当前右列右侧存在更高列
    if (height[rightIndex] < rightMax) {
      result += rightMax - height[rightIndex];
    }

    // 左右当前列中小的一方向中间推进
    height[leftIndex] < height[rightIndex]
      ? leftIndex++ : rightIndex--;
  }

  return result;
};
```