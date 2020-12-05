package algorithm;

import java.util.*;

/**
 * @author yangxc27652
 * @date 2020/12/4
 * @description  二叉树前，中，后序遍历（递归和遍历的方式）
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/zhuan-ti-jiang-jie-er-cha-shu-qian-zhong-hou-xu--2/
 */
public class BinaryTreePreInPost {

    /**
     * 前序遍历
     * @param root
     * @return
     */

    public List<Integer> preorderTraversal(TreeNode root) {
        //对比代码， 前序遍历，唯一区别就是， 一个一直向左， 一个一直向右
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()){
            //go left down to the ground
            while (root != null){
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            //if we reach to the leaf, go back to the parent right, and repeat the go left down.
            TreeNode cur = stack.pop();
            root = cur.right;
        }
        return res;
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()){
            while (root != null){
                res.add(root.val);
                stack.push(root);
                root = root.right;
            }
            TreeNode cur = stack.pop();
            root = cur.left;
        }
        Collections.reverse(res);
        return res;
    }


}
