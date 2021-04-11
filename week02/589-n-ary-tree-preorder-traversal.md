```js
/**
 * // Definition for a Node.
 * function Node(val, children) {
 *    this.val = val;
 *    this.children = children;
 * };
 */

/**
 * @param {Node} root
 * @return {number[]}
 */
var preorder = function(root) {
  const result = []
  const run = (node, array) => {
    if (!node) return
    array.push(node.val)
    for (let i = 0, length = node.children.length; i < length; i++) {
      run(node.children[i], array)
    }
  }
  run(root, result)
  return result
};
```

递归方式，非递归方式即自建栈。