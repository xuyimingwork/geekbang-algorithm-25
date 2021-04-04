```js
/**
 * @param {number[]} digits
 * @return {number[]}
 */
var plusOne = function(digits) {
  let waitForPlus = 1
  let i = digits.length
  const result = [] 
  while(i--) {
    if (digits[i] === 9 && waitForPlus) {
      result.unshift(0)
    } else {
      result.unshift(digits[i] + waitForPlus)
      waitForPlus = 0
    }
  }
  if (waitForPlus) result.unshift(1)
  return result
};
```

111/111 cases passed (80 ms)
Your runtime beats 57.27 % of javascript submissions
Your memory usage beats 81.38 % of javascript submissions (38.5 MB)

---

```js
const reverse = array => {
  let i = 0;
  let j = array.length - 1
  let temp = undefined
  
  while(i < j) {
    temp = array[i]
    array[i] = array[j]
    array[j] = temp
    i++
    j--
  }
}
/**
 * @param {number[]} digits
 * @return {number[]}
 */
var plusOne = function(digits) {
  const LENGTH = digits.length
  let waitForPlus = 1
  let i = LENGTH
  const result = new Array(LENGTH + 1)
  
  while(i--) {
    const resultIndex = LENGTH - i - 1
    if (!waitForPlus) {
      result[resultIndex] = digits[i]
      continue
    }

    if (digits[i] === 9) {
      result[resultIndex] = 0
    } else {
      result[resultIndex] = digits[i] + 1
      waitForPlus = 0
    }
  }
  
  if (waitForPlus) result[LENGTH] = 1
  else result.length = LENGTH
  
  reverse(result)

  return result
};
```

111/111 cases passed (72 ms)
Your runtime beats 94.38 % of javascript submissions
Your memory usage beats 62.49 % of javascript submissions (38.6 MB)