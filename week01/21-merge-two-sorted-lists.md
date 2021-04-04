```js
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var mergeTwoLists = function(l1, l2) {
  if (!l1 || !l2) return l1 || l2
  const head = {}
  let current = head
  let [i, j] = [l1, l2]
  while(i || j) {
    if (!i) {
      current.next = j
      break
    } else if (!j) {
      current.next = i
      break
    }

    if (i.val < j.val) {
      current.next = new ListNode(i.val, i.next)
      i = i.next
    } else {
      current.next = new ListNode(j.val, j.next)
      j = j.next
    }

    current = current.next
  }
  return head.next
};
```

208/208 cases passed (84 ms)
Your runtime beats 92.75 % of javascript submissions
Your memory usage beats 51.1 % of javascript submissions (40.5 MB)

