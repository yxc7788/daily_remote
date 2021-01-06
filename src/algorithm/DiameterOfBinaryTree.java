package algorithm;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description   a
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/si-lu-qing-xi-ming-liao-kan-wan-bu-xin-ni-huan-bu-/
 */
public class DiameterOfBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }


    /**
     *  code 2
     */
    int ans;
    public int diameterOfBinaryTree2(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L+R+1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }

}
