package algorithm;

import java.util.*;

/**
 * @author yangxc27652
 * @date 2020/12/4
 * @description  二叉树前，中，后序遍历（递归和遍历的方式）
 * https://blog.csdn.net/qq_40722284/article/details/82390488 */
public class BinaryTreePreInPost {

    /**
     * 前序遍历
     * @param root
     * @return
     */


    public void preorder(TreeNode root) {
        Stack<TreeNode>    stack=new Stack<>();
        while (root!=null||!stack.isEmpty()) {
            //当前节点不为空，则入栈，确保最后遍历到的节点没有左子节点
            //因为是前序遍历，所以再遍历到每个节点的时候，都可以先访问，再寻找其左右子节点。
            while (root!=null) {
                System.out.print(root.val+" ");
                stack.push(root);
                root=root.left;
            }
            if (!stack.empty()) {
                //把这两步看成是一步，找到右节点，并把已处理的中节点从stack当中去除
                root=stack.pop();
                root=root.right;
            }
        }
    }


    /**
     * 中序遍历
     * @param root
     * @return
     */

    public void inorder(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        while (root!=null||!stack.isEmpty()) {
            //当前节点不为空，则入栈，确保最后遍历到的节点没有左子节点
            while (root!=null) {
                stack.push(root);
                root=root.left;
            }
            //通过当前节点，跳到当前节点的右节点，因为是中序遍历，所以当前节点没有左节点的时候，就
            //可以访问当前节点
            if (!stack.empty()) {
                root=stack.pop();
                System.out.print(root.val+" ");
                root=root.right;
            }
        }
    }

    /**
     * 后序遍历
     * 借助两个栈来存储我们的节点以及标示位，过程如下：
     * 1.每遍历一个节点的时候，先节点入栈s，并且s2入栈一个标识位0，然后寻找当前节点的左子节点。
     * 2.当某个节点的左子节点，当左子节点不为空的时候，重复过程1.
     * 3.当左子节点为空的时候将当前节点peek出（即将节点拿出，但栈中还是有该节点），
     * 并且此时将s2对应栈顶的标识位改为1，通过其寻找右子节点，右子节点不为空的时候，重复过程1-2
     * 4.当右子节点也为空的时候，并且s2对应的标识符为1的时候，则弹出s1栈顶的当前节点，
     * 并且将s2的标识符弹出（即因为当前节点还没有出栈，所以现在在栈中最上层的节点是当前节），
     * 注意s1弹出当前节点并访问，但是不赋值给root，在这个root此时还是null
     * 5.进入过程3，此时root被peek赋值到当前节点的父节点（因为在过程4当中，
     * 已经pop出了当前节点，所以s1栈顶是当前节点的父节点）的右子节点。
     * 6.重复过程1-5
     */

    public void postorder(TreeNode root) {
        Stack<TreeNode> s =new Stack<>();
        Stack<Integer> s2 =new Stack<>();
        Integer i=new Integer(1);
        while (root!=null||!s.isEmpty()) {
            //只要当前节点非空，就入栈
            while (root!=null) {
                s.push(root);
                s2.push(new Integer(0));
                root=root.left;
            }
            //s2当中如果存1，则意味着当前s1对应的节点的左右子节点都已经遍历过了。
            while (!s.empty()&&s2.peek().equals(i)) {
                s2.pop();
                System.out.print(s.pop().val+" ");
            }
            if (!s.isEmpty()) {
                s2.pop();
                s2.push(new Integer(1));
                root=s.peek();
                root=root.right;
            }
        }
    }

    /**
     * 方法2
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

    /**
     * 方法3
     */
    public void postOrderTraverse3(TreeNode root) {
        TreeNode cur, pre = null;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            cur = stack.peek();
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                System.out.print(cur.val + "->");
                stack.pop();
                pre = cur;
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
    }


    /**
     * 递归实现前序
     */
    public void preOrderTraverse1(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + "->");
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
        }
    }
    /**
     * 这样写也可以
     */
    public void preOrderTraverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + "->");
        preOrderTraverse1(root.left);
        preOrderTraverse1(root.right);
    }

    /**
     * 递归中序
     */
    public void inOrderTraverse(TreeNode root) {
        if (root != null) {
            inOrderTraverse(root.left);
            System.out.print(root.val + "->");
            inOrderTraverse(root.right);
        }
    }


    /**
     *
     * 递归后序遍历
     */
    public void postOrderTraverse(TreeNode root) {
        if (root != null) {
            postOrderTraverse(root.left);
            postOrderTraverse(root.right);
            System.out.print(root.val + "->");
        }
    }
}
