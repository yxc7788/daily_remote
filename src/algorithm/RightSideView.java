package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yangxc27652
 * @date 2021/1/14
 * @description  二叉树右视图
 * //给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * //
 * // 示例:
 * //
 * // 输入: [1,2,3,null,5,null,4]
 * //输出: [1, 3, 4]
 * //解释:
 * //
 * //   1            <---
 * // /   \
 * //2     3         <---
 * // \     \
 * //  5     4       <---
 */
public class RightSideView {


    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int start = 0;
        int end = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            start++;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (start == end) {
                end = queue.size();
                start = 0;
                res.add(node.val);
            }
        }
        return res;
    }
}
