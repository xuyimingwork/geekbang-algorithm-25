```javascript
/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isAnagram = function(s, t) {
  if (s.length !== t.length) return false
  const sm = new Map()
  for (let i = 0; i < s.length; i++) {
    sm.set(s[i], (sm.get(s[i]) || 0) + 1)
    sm.set(t[i], (sm.get(t[i]) || 0) - 1)
  }
  for (let [, count] of sm) {
    if (count !== 0) return false
  }
  return true
};
```

34 / 34 test cases passed.
Status: Accepted
Runtime: 92 ms
Memory Usage: 40.9 MB

两个单词互为同字母异序词，说明字母长度一致且字母个数一致。因此构建 Map，一单词遇到字母时增加，一单词遇到字母时减少，最后检测 map 中字母均为 0 个即可。

刚开始想法是使用两个map，然后比较两个 map 中字母数量一致，但实际并不需要。