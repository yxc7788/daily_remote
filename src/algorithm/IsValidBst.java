package algorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangxc27652
 * @date 2021/1/13
 * @description 98. 验证二叉搜索树
 * https://leetcode-cn.com/problems/validate-binary-search-tree/solution/zhong-xu-bian-li-qing-song-na-xia-bi-xu-miao-dong-/
 */
public class IsValidBst {


    /**
     * self
     */
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (!isValidBST(root.left)) {
            return false;
        }

        if (root.val <= pre) {
            return false;
        }
        pre = root.val;

        if(!isValidBST(root.right)) {
            return false;
        }
        return true;
    }

    /**
     * s
     */
    List<Integer> res = new ArrayList<>();
    public boolean isValidBST3(TreeNode root) {
        if (root==null) {
            return true;
        }
        inOrder(root);
        for (int i=1;i<res.size();i++){
            if (res.get(i)<=res.get(i-1)){
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode root){
        if (root!=null){
            inOrder(root.left);
            res.add(root.val);
            inOrder(root.right);
        }
    }


    /**
     * method2
     */
    long pre1 = Long.MIN_VALUE;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre1 = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

    /**
     * 3
     * @param root
     * @return
     */
    public boolean isValidBST4(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


}
