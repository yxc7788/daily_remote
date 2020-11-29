package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description 二叉搜索树最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-26/
 */
public class BsTreeLowestCommonAncestor {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ancestor = root;
            while (true) {
                if (p.val < ancestor.val && q.val < ancestor.val) {
                    ancestor = ancestor.left;
                } else if (p.val > ancestor.val && q.val > ancestor.val) {
                    ancestor = ancestor.right;
                } else {
                    break;
                }
            }
            return ancestor;
        }


        /**
         * method2 时间复杂度：O(n)
         * * 空间复杂度：O(n)
         */
        public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
            List<TreeNode> path_p = getPath(root, p);
            List<TreeNode> path_q = getPath(root, q);
            TreeNode ancestor = null;
            for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
                if (path_p.get(i) == path_q.get(i)) {
                    ancestor = path_p.get(i);
                } else {
                    break;
                }
            }
            return ancestor;
        }

    public List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<TreeNode>();
        TreeNode node = root;
        while (node != target) {
            path.add(node);
            if (target.val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        path.add(node);
        return path;
    }

}
