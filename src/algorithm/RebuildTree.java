package algorithm;

import java.util.Arrays;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description  preorder = [3,9,20,15,7]   inorder = [9,3,15,20,7]
 */
public class RebuildTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
            if (pre.length == 0 || in.length == 0) {
                return null;
            }
            TreeNode node = new TreeNode(pre[0]);
            for (int i = 0; i < in.length; i++) {
                if (pre[0] == in[i]) {
                    // 这里的Arrays.copyOfRange （） 是左闭右开的
                    node.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                    node.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                }
            }
            return node;
        }
    }
}
