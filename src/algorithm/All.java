package algorithm;

import java.util.*;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description
 */
public class All {


/**
 * 3.在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完
 *     成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *     思路：从右上角或左下角开始找，逐行删除，或者用二分法查找
 *      
 *
 */
    public boolean find(int[][] array,int target) {
        if (array == null) {
            return false;
        }
        int row = 0;
        int column = array[0].length-1;
        while (row < array.length && column >= 0) {
            if (array[row][column] == target) {
                return true;
            }
            if (array[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

    /**
     * 4.将一个字符串中的空格替换成“%20”。
     * 问题1：替换字符串，是在原来的字符串上做替换，还是新开辟一个字符串做替换！
     * 问题2：在当前字符串替换，怎么替换才更有效率（不考虑java里现有的replace方法）。
     * 从前往后替换，后面的字符要不断往后移动，要多次移动，所以效率低下
     *  从后往前，先计算需要多少空间，然后从后往前移动，则每个字符只为移动一次，这样效率更高一点。
     */

    public String replaceSpace(StringBuffer str) {

        int spacenum = 0;//spacenum为计算空格数
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)==' ') {
                spacenum++;
            }
        }
        int indexold = str.length()-1; //indexold为为替换前的str下标
        int newlength = str.length() + spacenum*2;//计算空格转换成%20之后的str长度
        int indexnew = newlength-1;//indexold为为把空格替换为%20后的str下标
        // 注意：stringBuilder是可以设置长度的来扩容的
        // 这里有个坑，indexold一定要在setLength之前
        str.setLength(newlength);//使str的长度扩大到转换成%20之后的长度,防止下标越界
        while (indexold>=0)
        {
            if (str.charAt(indexold) == ' '){  //
                str.setCharAt(indexnew--, '0');
                str.setCharAt(indexnew--, '2');
                str.setCharAt(indexnew--, '%');
            }
            else {
                str.setCharAt(indexnew--, str.charAt(indexold));
            }
            --indexold;
        }
        return str.toString();

    }


    /**
     * 方法二
     * @param str
     * @return
     */
    public String replaceSpace2(StringBuffer str) {
        String str1=str.toString();
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<str1.length();i++){
            if (str1.charAt(i)==' '){
                sb.append("%20");
            }else {
                sb.append(str1.charAt(i));
            }

        }
        return sb.toString();
    }

    /**
     *  5.输入一个链表，从尾到头打印链表每个节点的值。
     *     思路：借助栈实现，或使用递归的方法。
     */

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        if (listNode == null) {
            return list;
        }
        Stack<ListNode> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop().val);
        }
        return list;
    }

    /**
     * 6.输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中
     *     都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并
     *     返回。
     *     思路：先找出根节点，然后利用递归方法构造二叉树
     */


    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {

        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < pre.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(
                        // Arrays.copyOfRange是左闭右开的
                        Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
                root.right = reConstructBinaryTree(
                        Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
            }
        }
        return root;
    }

    /**
     * 7.用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     *     思路：一个栈压入元素，而另一个栈作为缓冲，将栈1的元素出栈后压入栈2中。也可以将栈1中的最后一个元
     *     素直接出栈，而不用压入栈2中再出栈。
     */

    Stack<Integer> stack1 = new Stack();
    Stack<Integer> stack2 = new Stack();
    public void push1(int node) {
        stack1.push(node);
    }
    public int pop1() throws Exception {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new Exception("栈为空！");
        }
        if (stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     * 8.把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一
     *     个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     *     NOTE：给出的所有元素都大于0，若数组大小为0，请返回0
     *     思路：利用二分法，找到中间的数，然后和最左边的值进行比较，若大于最左边的数，则最左边从mid开始，
     *     若小于最右边值，则最右边从mid开始。若左中右三值相等，则取mid前后值中较小的数。
     */
    /*
     * 采用二分法解答这个问题，mid = low + (high - low)/2，需要考虑三种情况：(1)array[mid] > array[high]:
     * 出现这种情况的array类似[3,4,5,6,0,1,2]，此时最小数字一定在mid的右边。low = mid + 1
     * (2)array[mid] == array[high]:
     * 出现这种情况的array类似 [1,0,1,1,1] 或者[1,1,1,0,1]，此时最小数字不好判断在mid左边
     * 还是右边,这时只好一个一个试 ，high = high - 1
     * (3)array[mid] < array[high]，出现这种情况的array类似[2,2,3,4,5,6,6],此时最小数字一定就是array[mid]或者在mid的左
     * 边。因为右边必然都是递增的， high = mid
     * 注意这里有个坑：如果待查询的范围最后只剩两个数，那么mid 一定会指向下标靠前的数字
     * 比如 array = [4,6]，array[low] = 4 ;array[mid] = 4 ; array[high] = 6 ;
     * 如果high = mid - 1，就会产生错误， 因此high = mid，但情形(1)中low = mid + 1就不会错误
     */

    public int minNumberInRotateArray(int [] array) {
        int low = 0 ; int high = array.length - 1;
        while (low < high){
            // 注意mid赋值写在循环内部
            int mid = low + (high - low) / 2;
            if (array[mid] > array[high]){
                low = mid + 1;
            }else if (array[mid] == array[high]){
                // 重点
                high = high - 1;
            }else {
                high = mid;
            }
        }
        return array[low];
    }

    /**
     *   9.1现在要求输入一个整数n，请你输出斐波那契数列的第n项。n<=39
     *     思路：递归的效率低，使用循环方式。
     */

    public int fibonacci(int n) {
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 1;
        }
        int temp = 0;
        int pre2 = 1;
        int pre1 = 1;
        for (int i = 3; i < n; i++){
            temp = pre2 +pre1;
            pre2 = pre1;
            pre1 = temp;
        }
        return temp;
    }

    /**
     *  9.2.一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     *             9.2代码与9.1相同
     * 9.3我们可以用21的小矩形横着或者竖着去覆盖更大的矩形。请问用n个21的小矩形无重叠地覆盖一个2*n的大
     *     矩形，总共有多少种方法？
     *     思路：斐波那契数列思想
     */

    public int JumpFloor(int target) {
        if (target <= 2){
            return target;
        }
        int pre2 = 1, pre1 = 2;
        for (int i = 3; i <= target; i++){
            int cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    /**
     * 方法2
     * @param n
     * @return
     */
    public int JumpFloor2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return JumpFloor(n - 1) + JumpFloor(n - 2);
    }

    /**
     * 9.4一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共
     *     有多少种跳法。
     *     根据f[n] = 2*f[n-1]，和初始条件f[0] = f[1] = 1，可以得出结果是2^（n-1）
     */

    public int jumpFloor3(int target) {
        return (int) Math.pow(2,target-1);
    }

    /**
     * 或者
     */
    public int jumpFloor4(int target) {
        if (target==0)
            return 0;
        if (target==1)
            return 1;
        if (target==2)
            return 2;
        return 2*jumpFloor4(target-1);
    }

    /**
     * 9.5 题目描述
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * 比如n=3时，2*3的矩形块有3种覆盖方法：
     */

    public int rectCover(int target) {
        if (target <= 2){
            return target;
        }
        int pre1 = 1;
        int pre2 = 2;
        for (int i = 3; i <= target; i++){
            int cur = pre1 + pre2;
            pre1 = pre2;
            pre2 = cur;
        }
        return pre2;

    }

    /**
     * 10.输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     *     思路：a&(a-1)的结果会将a最右边的1变为0，直到a = 0，还可以先将a&1 != 0，然后右移1位，但不能计算负
     *     数的值
     */

    public int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n-1) & n;
        }
        return count;
    }

    /**
     * 11.给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。不得使用库函数，
     *     不需要考虑大数问题
     *     思路：不能用==比较两个浮点数是否相等，因为有误差。考虑输入值的多种情况。
     */

    public double Power(double base, int exponent) {
        if (base == 0.0){
            return 0.0;
        }
        // 前置结果设为1.0，即当exponent=0 的时候，就是这个结果
        double result = 1.0d;
        // 获取指数的绝对值
        int e = exponent > 0 ? exponent : -exponent;
        // 根据指数大小，循环累乘
        for(int i = 1 ; i <= e; i ++){
            result *= base;
        }
        // 根据指数正负，返回结果
        return exponent > 0 ? result : 1 / result;
    }


    /**
     * 12.打印1到最大的n位数
     *     思路：考虑大数问题，使用字符串或数组表示。
     */


    public void printToMaxOfNDigits(int n) {
        int[] array=new int[n];
        if (n <= 0) {
            return;
        }
        printArray(array, 0);
    }
    private void printArray(int[] array, int n) {
        for (int i = 0; i < 10; i++) {
            if (n != array.length) {
                array[n] = i;
                printArray(array, n+1);
            } else {
                boolean isFirstNo0 = false;
                for (int j = 0; j < array.length; j++) {
                    if (array[j] != 0) {
                        System.out.print(array[j]);
                        if (!isFirstNo0) {
                            isFirstNo0 = true;
                        }
                    } else {
                        if (isFirstNo0) {
                            System.out.print(array[j]);
                        }
                    }
                }
                System.out.println();
                return ;
            }
        }
    }

    /**
     * 13.O(1)时间删除链表节点
     *     思路：将要删除节点的下一个节点的值赋给要删除的节点，然后指向下下一个节点
     */

    public void deleteNode(ListNode head, ListNode deListNode) {
        if (deListNode == null || head == null)
            return;
        if (head == deListNode) {
            head = null;
        } else {
        // 若删除节点是末尾节点，往后移一个
            if (deListNode.next == null) {
                ListNode pointListNode = head;
                while (pointListNode.next.next != null) {
                    pointListNode = pointListNode.next;
                }
                pointListNode.next = null;
            } else {
                deListNode.val = deListNode.next.val;
                deListNode.next = deListNode.next.next;
            }
        }
    }

//14.输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所
//    有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变
//    思路：每次只和前面一个数交换位置。或者利用辅助数组

    public void reOrderArray(int [] array) {
        int oldIndex=0;
        int evenIndex=0;
        int[] newArray=new int[array.length];
        for (int i=0;i<array.length;i++){
            if (array[i]%2!=0){
                newArray[oldIndex]=array[i];
                oldIndex++;
            }
        }
        evenIndex=oldIndex;
        for (int j=0;j<array.length;j++){
            if (array[j]%2==0){
                newArray[evenIndex]=array[j];
                evenIndex++;
            }
        }
        for (int i=0;i<array.length;i++){
            array[i]=newArray[i];
        }
    }


    /**
     * 方法2
     * @return 类似冒泡排序
     */
    public void reOrderArray2(int [] array) {

        for (int i = 0; i < array.length;i++){
            for (int j = array.length - 1; j>i;j--)
            {
                if (array[j] % 2 == 1 && array[j - 1]%2 == 0) //前偶后奇交换
                {
                    int tmp = array[j];
                    array [j] = array[j-1];
                    array[j-1] = tmp;
                }
            }
        }
    }


    
//15.输入一个链表，输出该链表中倒数第k个结点。
//    扩展题：找中间节点，使用两个指针，一个走一步，一个走两步。找到中间节点
//    思路：定义一快一慢两个指针，快指针走K步，然后慢指针开始走，快指针到尾时，慢指针就找到了倒数第K个
//    节点。 快指针要先走k-1步

    public ListNode findKthToTail(ListNode head, int k) {
        ListNode first=head;
        ListNode second=head;
        if (head==null||k<=0){
            return null;
        }
        for (int i=1;i<k;i++){
            if (second.next==null){
                return null;
            }
            second=second.next;
        }
        while (second.next!=null){
            first=first.next;
            second=second.next;
        }
        return first;

    }
    
//16.输入一个链表，反转链表后，输出链表的所有元素。
//    扩展题：输出反转后链表的头节点，定义三个指针反向输出。
//    思路：定义两个指针，反向输出

    public ListNode reverseList(ListNode head) {
        ListNode pre=null;
        ListNode next=null;
        while (head!=null){
            next = head.next;
            head.next = pre;
            pre  = head;
            head = next;
        }return pre;
    }


    
//17.输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
//    思路：递归与非递归求解，小数放在前面。

    public ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } 
        if (list2 == null) {
            return list1;
        } 
        ListNode newHead = null;
        if (list1.val <= list2.val) {
            newHead = list1;
            newHead.next = merge(list1.next,list2);
        }else {
            newHead = list2;
            newHead.next = merge(list1,list2.next);
        } 
        return newHead;
    }


    /**
     * method2
     */
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }


    
//18.输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
//    思路：若根节点相等，利用递归比较他们的子树是否相等，若根节点不相等，则利用递归分别在左右子树中查
//    找。

    /**
     * s
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B!= null && A != null) {

            if ( A.val == B.val && isSame(A, B)) {
                return true;
            }
            else {
                return isSubStructure(A.left, B) || isSubStructure(A.right, B);
            }
        }
        return false;

    }
    private boolean isSame(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val == B.val) {
            return isSame(A.left, B.left) && isSame(A.right, B.right);
        }
        else {
            return false;
        }

    }

    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root2 != null && root1 != null) {
            if (root1.val == root2.val){
                result = doesTree1HaveTree2(root1,root2);
            } 
            if (!result) {
                return hasSubtree(root1.left,root2) || hasSubtree(root1.right,root2);
            }
        } return result;
    } 
    public boolean doesTree1HaveTree2(TreeNode node1, TreeNode node2) {
        if (node2 == null) {
            return true;
        } 
        if (node1 == null) {
            return false;
        } 
        if (node1.val != node2.val) {
            return false;
        } 
        return doesTree1HaveTree2(node1.left,node2.left) &&
        doesTree1HaveTree2(node1.right,node2.right);
    }
    
    
//19.操作给定的二叉树，将其变换为源二叉树的镜像。
//    思路：使用递归或非递归方式交换每个节点的左右子树位置。


    public void mirror1(TreeNode root) {
        if (root == null){
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror1(root.left);
        mirror1(root.right);
    }

    /**
     * 方法2
     * @param root
     */
    public void mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                TreeNode temp = root.left;
                root.left = root.right;
                root.right = temp;
                stack.push(root);
                root = root.left;
            } 
            if (!stack.isEmpty()) {
                root = stack.pop();
                root = root.right;
            }
        }
    }
    
    
//20.输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下矩阵： 1 2 3 4
//            5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
//    思路：终止行号大于起始行号，终止列号大于起始列号，

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return list;
        }
        // 定义边界
        int up = 0;
        int down = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        while (true){
            // 最上面一行
            for (int col=left;col<=right;col++){
                list.add(matrix[up][col]);
            }
            // 向下逼近
            up++;
            // 判断是否越界
            if (up > down){
                break;
            }
            // 最右边一行
            for (int row=up;row<=down;row++){
                list.add(matrix[row][right]);
            }
            // 向左逼近
            right--;
            // 判断是否越界
            if (left > right){
                break;
            }
            // 最下面一行
            for (int col=right;col>=left;col--){
                list.add(matrix[down][col]);
            }
            // 向上逼近
            down--;
            // 判断是否越界
            if (up > down){
                break;
            }
            // 最左边一行
            for (int row=down;row>=up;row--){
                list.add(matrix[row][left]);
            }
            // 向右逼近
            left++;
            // 判断是否越界
            if (left > right){
                break;
            }
        }
        return list;
    }

    /**
     * 第二种写法
     */
    public ArrayList<Integer> printMatrix2(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (matrix ==null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        int up = 0;
        int down = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;

        while (true) {
            for (int col = left; col <= right; col++) {
                list.add(matrix[up][col]);
            }
            up ++;
            if (up > down) {
                return list;
            }
            for (int row = up; row <= down; row++) {
                list.add(matrix[row][right]);
            }
            right --;
            if (right < left) {
                return list;
            }
            for (int col = right; col>=left; col--) {
                list.add(matrix[down][col]);
            }
            down --;
            if (down < up) {
                return list;
            }
            for (int row = down; row>=up; row--) {
                list.add(matrix[row][left]);
            }
            left ++;
            if (left > right) {
                return list;
            }
        }
    }

//21.定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
//    思路：定义两个栈，一个存放入的值。另一个存最小值。

    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> min = new Stack<>();

    public void push(int node) {
        data.push(node);
        if (min.isEmpty()){
            min.push(node);
        }else{
            min.push(node <= min.peek()? node : min.peek());
        }
    }

    public void pop() {
        data.pop();
        min.pop();
    }

    public int top() {
        return data.peek();
    }
    public int min() {
        return min.peek();
    }
    
    
//22.输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入
//    栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出
//    序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
//    思路：用栈来压入弹出元素，相等则出栈。

    public boolean isPopOrder(int [] pushA, int [] popA) {
        if (pushA == null || popA == null) {
            return false;
        } 
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            // 注意这里需要判断stack是否是null
            while (!stack.isEmpty() && stack.peek() == popA[index]) {
                stack.pop();
                index++;
            }
        } 
        return stack.isEmpty();
    }
    

//23.从上往下打印出二叉树的每个节点，同层节点从左至右打印。
//    思路：利用队列（链表）辅助实现。

    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList();
        if (root ==null) {
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList();
        queue.push(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left!=null) {
                queue.add(node.left);
            }
            if (node.right!=null) {
                queue.add(node.right);
            }
        }
        return list;
    }
    
//24.输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。
//    假设输入的数组的任意两个数字都互不相同。
//    思路：先找到右子树的开始位置，然后分别进行左右子树递归处理//


    /**
     * 视频 https://www.bilibili.com/video/BV1X7411q7Bw?from=search&seid=8618154083623109926
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0){
            return false;
        }
        if (sequence.length == 1){
            return true;
        }
        return judge(sequence,0,sequence.length-1);
    }

    public boolean judge(int[] a, int start, int end){
        if (start >= end){
            return true;
        }
        int i = start;
        while (a[i] < a[end]){
            ++i;
        }
        for (int j=i;j<end;j++){
            if (a[j] < a[end]){
                return false;
            }
        }
        return judge(a,start,i-1) && judge(a,i,end-1);
    }


//25.输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结
//    点开始往下一直到叶结点所经过的结点形成一条路径。
//    思路：先保存根节点，然后分别递归在左右子树中找目标值，若找到即到达叶子节点，打印路径中的值
//    https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/solution/mian-shi-ti-34-er-cha-shu-zhong-he-wei-mou-yi-zh-5/


    /**
     * 方法 方法中存在先序遍历
     */

    private ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    private Stack<Integer> path = new Stack<Integer>();
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root,int target) {
        if (root == null){
            return paths;
        }
        path.push(root.val);
        target = target -root.val;
        // 注意这里必须减完了才能判断
        if (target == 0 && root.left == null && root.right == null){
            paths.add(new ArrayList<Integer>(path));
        }
        findPath(root.left, target);
        findPath(root.right, target);
        path.pop();
        return paths;
    }

    /**
     * 牛客通过的
     */
    ArrayList<ArrayList<Integer>> res = new ArrayList();
    ArrayList<Integer> list = new ArrayList();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if (root == null) {
            return res;
        }
        list.add(root.val);
        if (root.val == target && root.left == null && root.right == null) {
            res.add(new ArrayList(list));
        }
        FindPath(root.left, target - root.val);
        FindPath(root.right, target - root.val);
        list.remove(path.size() - 1);
        return res;
    }
//26.输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任
//    意一个节点），返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则
//    判题程序会直接返回空）
//    思路：先复制链表的next节点，将复制后的节点接在原节点后，然后复制其它的节点，最后取偶数位置的节点
//（复制后的节点）。

    class RandomListNode {
        int val;
        RandomListNode next;
        RandomListNode random;

        public RandomListNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 方法1 https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/solution/tong-guo-hashmaplai-shi-xian-by-try-62/
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode,RandomListNode> map = new HashMap<>(); //创建HashMap集合
        RandomListNode cur=head;
        //复制结点值
        while (cur!=null){
            //存储put:<key,value1>
            map.put(cur,new RandomListNode(cur.val)); //顺序遍历，存储老结点和新结点(先存储新创建的结点值)
            cur=cur.next;
        }
        //复制结点指向
        cur = head;
        while (cur!=null){
            //得到get:<key>.value2,3
            map.get(cur).next = map.get(cur.next); //新结点next指向同旧结点的next指向
            map.get(cur).random = map.get(cur.random); //新结点random指向同旧结点的random指向
            cur = cur.next;
        }
        //返回复制的链表
        return map.get(head);

    }


    /**
     * 方法2 解题思路：
     * 1、遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
     * 2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
     * 3、拆分链表，将链表拆分为原链表和复制后的链表
     * @param pHead
     * @return
     */
    public RandomListNode clone2(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }

        RandomListNode currentNode = pHead;
        //1、复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
        while (currentNode != null){
            RandomListNode cloneNode = new RandomListNode(currentNode.val);
            RandomListNode nextNode = currentNode.next;
            currentNode.next = cloneNode;
            cloneNode.next = nextNode;
            currentNode = nextNode;
        }

        currentNode = pHead;
        //2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
        while (currentNode != null) {
            currentNode.next.random = currentNode.random==null?null:currentNode.random.next;
            currentNode = currentNode.next.next;
        }

        //3、拆分链表，将链表拆分为原链表和复制后的链表
        currentNode = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (currentNode != null) {
            RandomListNode cloneNode = currentNode.next;
            currentNode.next = cloneNode.next;
            cloneNode.next = cloneNode.next==null?null:cloneNode.next.next;
            currentNode = currentNode.next;
        }

        return pCloneHead;
    }



//27.二叉搜索树与双向链表，输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调
//    整树中结点指针的指向。
//    思路：中序遍历 https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/solution/mian-shi-ti-36-er-cha-sou-suo-shu-yu-shuang-xian-5/

    public TreeNode convert1(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        midOrder(pRootOfTree,list);
        // 遍历list中的节点，改变指向
        for (int i = 0; i < list.size() - 1; ++i){
            list.get(i).right = list.get(i + 1);
            list.get(i + 1).left = list.get(i);
        }
        // 如果需要双向循环链表，需要下面两行代码
        // list.get(0).left = list.get(list.size()-1);
        // list.get(list.size()-1).right =list.get(0);//进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的
        return list.get(0);
    }
    /**
     * 中序遍历二叉树，然后讲元素添加到list
     */
    public void midOrder(TreeNode pRootOfTree, ArrayList<TreeNode> list){
        if (pRootOfTree.left != null){
            midOrder(pRootOfTree.left,list);
        }
        list.add(pRootOfTree);
        if (pRootOfTree.right != null){
            midOrder(pRootOfTree.right,list);
        }
    }

    /**
     * 注意下面这个是双向循环链表，注意区分
     */
    TreeNode head, pre;
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root==null) return null;
        convert(root);

        pre.right = head;
        head.left =pre;//进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的

        return head;

    }

    public void convert(TreeNode cur){
        if(cur==null) {
            return;
        }
        convert(cur.left);
        //pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
        if (pre==null) {
            head = cur;
        }
        //反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
        else {
            pre.right = cur;
        }
        cur.left = pre;// pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。
        pre = cur;// pre指向当前的cur
        convert(cur.right);// 全部迭代完成后，pre指向双向链表中的尾节点
    }





//    28.输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所
//    能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
//    思路：将当前位置的字符和前一个字符位置交换，递归。 参考了leetcode 46题 全排列


   // https://www.nowcoder.com/questionTerminal/fe6b651b66ae47d7acce78ffdd9a96c7

    // 注意leetcode 46道题是无重复的，本题可以有重复

    // 对于无重复值的情况
    //     *
    //     * 固定第一个字符，递归取得首位后面的各种字符串组合；
    //     * 再把第一个字符与后面每一个字符交换，并同样递归获得首位后面的字符串组合； *递归的出口，就是只剩一个字符的时候，递归的循环过程，就是从每个子串的第二个字符开始依次与第一个字符交换，然后继续处理子串。
    //     *
    //     * 假如有重复值呢？
    //     * *由于全排列就是从第一个数字起，每个数分别与它后面的数字交换，我们先尝试加个这样的判断——如果一个数与后面的数字相同那么这两个数就不交换了。
    //     * 例如abb，第一个数与后面两个数交换得bab，bba。然后abb中第二个数和第三个数相同，就不用交换了。
    //     * 但是对bab，第二个数和第三个数不 同，则需要交换，得到bba。
    //     * 由于这里的bba和开始第一个数与第三个数交换的结果相同了，因此这个方法不行。
    //     *
    //     * 换种思维，对abb，第一个数a与第二个数b交换得到bab，然后考虑第一个数与第三个数交换，此时由于第三个数等于第二个数，
    //     * 所以第一个数就不再用与第三个数交换了。再考虑bab，它的第二个数与第三个数交换可以解决bba。此时全排列生成完毕！
   public ArrayList<String> permutation(String str){

       ArrayList<String> list = new ArrayList<String>();
       if (str!=null && str.length()>0){
           permutationHelper(str.toCharArray(),0,list);
           Collections.sort(list);
       }
       return list;
   }
    private void permutationHelper(char[] chars, int i, ArrayList<String> list){
        if (i == chars.length-1){
            list.add(String.valueOf(chars));
        }else{
            Set<Character> charSet = new HashSet<Character>();
            for (int j=i;j<chars.length;++j){
                if (j==i || !charSet.contains(chars[j])){
                    charSet.add(chars[j]);
                    swap(chars,i,j);
                    permutationHelper(chars,i+1,list);
                    swap(chars,j,i);
                }
            }
        }
    }

    private void swap(char[] cs, int i, int j){
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }


    /**
     * 笑抽，采用leetcode全排列那种方法
     * 深度优先搜索，结果包含重复值，然后，对于结果中元素toString然后去重
     * @param array
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        char [] array = str.toCharArray();
        int len = array.length;
        // 使用一个动态数组保存所有可能的全排列
        ArrayList<String> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        Stack<Character> path = new Stack<Character>();

        dfs(array, len, 0, path, used, res);
        return removeDuplicate(res);
    }

    private void dfs(char[] array, int len, int depth,
                     Stack<Character> path, boolean[] used,
                     ArrayList<String> res) {
        if (depth == len) {
            StringBuilder sb = new StringBuilder();
            ArrayList strList = new ArrayList<>(path);
            for (int i = 0; i< strList.size(); i++) {
                sb.append(strList.get(i));
            }
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.push(array[i]);
                used[i] = true;

                System.out.println("  递归之前 => " + path);
                dfs(array, len, depth + 1, path, used, res);

                used[i] = false;
                path.pop();
                System.out.println("递归之后 => " + path);
            }
        }
    }
    // 简单方法遍历两次
    public static ArrayList removeDuplicate(ArrayList list){
        for(int i =0;i<list.size()-1;i++){
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).toString().equals(list.get(j).toString()))
                    list.remove(j);
            }
        }
        return list;
    }



//29.数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字, 没有的话返回0
//     输入 [1,2,3,2,2,2,5,4,2]  输出 2
//    思路：将首次出现的数count+1，与之后的数进行比较，相等则+1，否则—1，最后进行校验是否超过长度的一
//    半。

    public int MoreThanHalfNum_Solution(int [] array) {
        int res=array[0];
        int count=1;
        for (int i=1;i<array.length;i++){
            if (array[i]==res){
                count++;
            }else{
                count--;
            }
            if (count==0){
                res=array[i];
                count=1;
            }
        }
        count=0;
        for (int i=0;i<array.length;i++){
            if (res==array[i]){
                count++;
            }
        }
        if (count>array.length/2){
            return res;
        }
        return 0;
    }


    /**
     * 方法2
     */
    public int moreThanHalfNum_Solution(int [] array) {
        Arrays.sort(array);
        int count=0;
        int half=array.length/2;
        for (int i=0;i<array.length;i++)
        {
            if (array[i]==array[half]) {
                count++;
            }
        }
        if (count>half) {
            return array[half];
        }
        else {
            return 0;
        }

    }

//30.输入n个整数，找出其中最小的K个数。
//    思路：先将前K个数放入数组，进行堆排序，若之后的数比它还小，则进行调整
     
    public ArrayList<Integer> getLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || k <= 0 || k > input.length) {
            return list;
        }
        int[] kArray = Arrays.copyOfRange(input,0,k);

        // 创建大根堆
        buildHeap(kArray);
        for (int i = k; i < input.length; i++) {
            if (input[i] < kArray[0]) {
                kArray[0] = input[i];
                maxHeap(kArray, 0);
            }
        }
        for (int i = kArray.length - 1; i >= 0; i--) {
            list.add(kArray[i]);
        }
        return list;
    }
    public void buildHeap(int[] input) {
        for (int i = input.length/2 - 1; i >= 0; i--) {
            maxHeap(input,i);
        }
    }
    private void maxHeap(int[] array,int i) {
        int left=2*i+1;
        int right=left+1;
        int largest=0;
        if (left < array.length && array[left] > array[i])
            largest=left;
        else
            largest=i;
        if (right < array.length && array[right] > array[largest])
            largest = right;
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            maxHeap(array, largest);
        }
    }

    /**
     * 方法2
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> getLeastNumbers_Solution2(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k > input.length || k <= 0 || input == null){
            return list;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(input.length);
        for (int i = 0; i < input.length; i++){
            pq.offer(input[i]);
        }
        while (k > 0){
            list.add(pq.poll());
            k--;
        }
        return list;
    }

    /**
     * 方法3
     * @param input

     */
    public ArrayList<Integer> getLeastNumbers_Solution0(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(input);
        int i = 0;
        while (i < k && k <= input.length){
            list.add(input[i]);
            i++;
        }
        return list;
    }


//31.求连续子数组（包含负数）的最大和   输入[1,-2,3,10,-4,7,2,-5]  返回18
//    思路：若和小于0，则将最大和置为当前值，否则计算最大和。
     
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int cur = array[0];
        int greast = array[0];
        for (int i = 1; i < array.length; i++) {
            if (cur < 0) {
                cur = array[i];
            }else {
                cur += array[i];
            }
            if (cur > greast) {
                greast = cur;
            }
        }
        return greast;
    }

    /**
     * 方法2  用例：输入[1,-2,3,10,-4,7,2,-5]  返回18
     * @param array
     * @return
     */
    public int findGreatestSumOfSubArray(int[] array) {
        int maxSum = array[0], curSum = array[0];
        for (int i=1; i<array.length; i++){
            curSum = Math.max(array[i], curSum + array[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

//32.从1到整数n中1出现的次数。 用例 1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
// 1~13中包含1的数字有1、10、11、12、13因此共出现6次
// 思路：若百位上数字为0，百位上可能出现1的次数由更高位决定；若百位上数字为1，百位上可能出现1的次数
// 不仅受更高位影响还受低位影响；若百位上数字大于1，则百位上出现1的情况仅由更高位决定


    /*
    设N = abcde ,其中abcde分别为十进制中各位上的数字。
    如果要计算百位上1出现的次数，它要受到3方面的影响：百位上的数字，百位以下（低位）的数字，百位以上（高位）的数字。
    ① 如果百位上数字为0，百位上可能出现1的次数由更高位决定。比如：12013，
    则可以知道百位出现1的情况可能是：100~199，1100~1199,2100~2199，，...，11100~11199，
    一共1200个。可以看出是由更高位数字（12）决定，并且等于更高位数字（12）乘以 当前位数（100）。
    ② 如果百位上数字为1，百位上可能出现1的次数不仅受更高位影响还受低位影响。比如：12113，
    则可以知道百位受高位影响出现的情况是：100~199，1100~1199,2100~2199，，....，11100~11199，一共1200个。
    和上面情况一样，并且等于更高位数字（12）乘以 当前位数（100）。但同时它还受低位影响，百位出现1的情况是：12100~12113,一共114个，
    等于低位数字（113）+1。
    ③如果百位上数字大于1（2~9），则百位上出现1的情况仅由更高位决定，比如12213，
    则百位出现1的情况是：100~199,1100~1199，2100~2199，...，11100~11199,12100~12199,
    一共有1300个，并且等于更高位数字+1（12+1）乘以当前位数（100）。
    */
    public class Solution {
        public int NumberOf1Between1AndN_Solution(int n) {
            int count = 0;//1的个数
            int i = 1;//当前位
            int current = 0,after = 0,before = 0;
            while ((n/i)!= 0){
                current = (n/i)%10; //高位数字
                before = n/(i*10); //当前位数字
                after = n-(n/i)*i; //低位数字
                //如果为0,出现1的次数由高位决定,等于高位数字 * 当前位数
                if (current == 0)
                    count += before*i;
                    //如果为1,出现1的次数由高位和低位决定,高位*当前位+低位+1
                else if(current == 1)
                    count += before * i + after + 1;
                    //如果大于1,出现1的次数由高位决定,//（高位数字+1）* 当前位数
                else{
                    count += (before + 1) * i;
                }
                //前移一位
                i = i*10;
            }
            return count;
        }
    }

    /**
     * 方法3, 比较直观，不到万不得已不用
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        while (n>0){
            String str = String.valueOf(n);
            char [] chars = str.toCharArray();
            for (int i=0;i<chars.length;i++){
                if(chars[i]=='1')
                    count++;
            }
            n--;
        }
        return count;
    }



//33.输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个
//    思路：先将整型数组转换成String数组，然后将String数组排序，最后将排好序的字符串数组拼接出来。关键就
//    是制定排序规则。或使用比较和快排的思想，将前面的数和最后的数比较，若小则放到最前面，最后再递归调
//    用。
     
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        int len = numbers.length;
        String[] str = new String[len];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++){
            str[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(str,new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                String c1 = s1 + s2;
                String c2 = s2 + s1;
                return c1.compareTo(c2);
            }
        });
        for (int i = 0; i < len; i++){
            sb.append(str[i]);
        }
        return sb.toString();
    }

    /**
     * 方法2
     */
    public String printMinNumber2(int [] numbers) {

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                String left = String.valueOf(numbers[i]);
                String right = String.valueOf(numbers[j]);
                if ((left + right).compareTo(right + left) >= 0) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            s.append(numbers[i]);
        }
        return s.toString();

    }

//34.丑数是只包含因子2、3和5的数，求从小到大的第N个丑数。
//    思路：乘2或3或5，之后比较取最小值。
     
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) {
            return 0;
        }
        int[] arr = new int[index];
        arr[0] = 1;
        int multiply2 = 0;
        int multiply3 = 0;
        int multiply5 = 0;
        for (int i = 1; i < index; i++) {
            int min = Math.min(arr[multiply2] * 2,Math.min(arr[multiply3] * 3,arr[multiply5] * 5));
            arr[i] = min;
            if (arr[multiply2] * 2 == min) {
                multiply2++;
            }
            if (arr[multiply3] * 3 == min) {
                multiply3++;
            }
            if (arr[multiply5] * 5 == min) {
                multiply5++;
            }
        }
        return arr[index - 1];
    }

    /**
     * 方法2
     * 所以只需要把得到的丑数不断地乘以2、3、5之后并放入他们应该放置的位置即可，
     * 而此题的难点就在于如何有序的放在合适的位置。
     * 1乘以 （2、3、5）=2、3、5；2乘以（2、3、5）=4、6、10；3乘以（2、3、5）=6,9,15；5乘以（2、3、5）=10、15、25；
     * 从这里我们可以看到如果不加策略地添加丑数是会有重复并且无序，
     * 而在2x，3y，5z中，如果x=y=z那么最小丑数一定是乘以2的，但关键是有可能存在x》y》z的情况，
     * 所以我们要维持三个指针来记录当前乘以2、乘以3、乘以5的最小值，然后当其被选为新的最小值后，要把相应的指针+1；
     * 因为这个指针会逐渐遍历整个数组，因此最终数组中的每一个值都会被乘以2、乘以3、乘以5，也就是实现了我们最开始的想法，
     * 只不过不是同时成乘以2、3、5，而是在需要的时候乘以2、3、5.
     */

    public int getUglyNumber_Solution(int index) {
        if(index <= 0)return 0;
        int p2=0,p3=0,p5=0;//初始化三个指向三个潜在成为最小丑数的位置
        int[] result = new int[index];
        result[0] = 1;//
        for(int i=1; i < index; i++){
            result[i] = Math.min(result[p2]*2, Math.min(result[p3]*3, result[p5]*5));
            if(result[i] == result[p2]*2)p2++;//为了防止重复需要三个if都能够走到
            if(result[i] == result[p3]*3)p3++;//为了防止重复需要三个if都能够走到
            if(result[i] == result[p5]*5)p5++;//为了防止重复需要三个if都能够走到
        }
        return result[index-1];
    }


//35.在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
//    思路：利用LinkedHashMap保存字符和出现次数。
     
    public int firstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0)
            return -1;
        char[] c = str.toCharArray();
        LinkedHashMap<Character,Integer> hash=new LinkedHashMap<Character,Integer>();
        for(char item : c) {
            if(hash.containsKey(item))
                hash.put(item, hash.get(item)+1);
            else
                hash.put(item, 1);
        }
        for(int i = 0;i < str.length(); i++){
            if (hash.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法2，与方法1类似，用数组保存
     */
    public int firstNotRepeatingChar2(String str) {
        int res = -1;
        if(str == null || str.length() == 0) {
            return res;
        }
        if(str.length() == 1) {
            return 1;
        }
        char[] charArr = str.toCharArray();
        int[] count = new int[128];

        for(int i = 0; i < charArr.length ;i++){
            count[charArr[i]]++;
        }
        for(int i = 0; i < count.length;i++){
            if(count[charArr[i]] == 1){
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     * 牛客pass
     */
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        char [] ch = new char[256];
        for (int i = 0; i < str.length(); i++) {
            ch[str.charAt(i)] ++ ;
        }

        for(int i = 0; i < str.length(); i++) {
            if (ch[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

//36.在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
// 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
//    思路：本质是归并排序，在比较时加入全局变量count进行记录逆序对的个数，若data[start] >= data[index] ，
//    则count值为mid+1-start

    /**
     * 方法2
     */
    int count = 0;
    public int InversePairs(int [] array) {
        if(array==null)
        return 0;
        mergeSort(array,0,array.length-1);
        return count;
    }
    private void mergeSort(int[] data,int start,int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            mergeSort(data, start, mid);
            mergeSort(data, mid + 1, end);
            merge(data, start, mid, end);
        }
    }
    public void merge(int[] data,int start,int mid,int end) {
        int arr[] = new int[end - start + 1];
        int c = 0;
        int s = start;
        int index = mid + 1;
        while (start <= mid && index <= end) {
            if (data[start] < data[index]) {
                arr[c++] = data[start++];
            } else {
                arr[c++] = data[index++];
                count += mid +1 - start;
                count %= 1000000007;
            }
        }
        while (start <= mid) {
            arr[c++] = data[start++];
        }
        while (index <= end) {
            arr[c++] = data[index++];
        }
        for (int d : arr) {
            data[s++] = d;
        }
    }




    //统计逆序对的个数
    int cnt;
    public int InversePairs2(int [] array) {
        if(array.length != 0){
            divide(array,0,array.length-1);
        }
        return cnt;
    }

    //归并排序的分治---分
    private void divide(int[] arr,int start,int end){
        //递归的终止条件
        if(start >= end)
            return;
        //计算中间值，注意溢出
        int mid = start + (end - start)/2;

        //递归分
        divide(arr,start,mid);
        divide(arr,mid+1,end);

        //治
        merge2(arr,start,mid,end);
    }

    private void merge2(int[] arr,int start,int mid,int end){
        int[] temp = new int[end-start+1];

        //存一下变量
        int i=start,j=mid+1,k=0;
        //下面就开始两两进行比较，若前面的数大于后面的数，就构成逆序对
        while(i<=mid && j<=end){
            //若前面小于后面，直接存进去，并且移动前面数所在的数组的指针即可
            if(arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            }else{
                temp[k++] = arr[j++];
                //a[i]>a[j]了，那么这一次，从a[i]开始到a[mid]必定都是大于这个a[j]的，因为此时分治的两边已经是各自有序了
                cnt = (cnt+mid-i+1)%1000000007;
            }
        }
        //各自还有剩余的没比完，直接赋值即可
        while(i<=mid)
            temp[k++] = arr[i++];
        while(j<=end)
            temp[k++] = arr[j++];
        //覆盖原数组
        for (k = 0; k < temp.length; k++)
            arr[start + k] = temp[k];
    }

    /**
     * 笨方法，冒泡排序，牛客时间复杂度不通过
     */
    public int inversePairs2(int [] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1 ;j < array.length; j++) {
                if (array[i] > array[j]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                    count ++;
                }
            }
        }
        return count;
    }


//37.输入两个链表，找出它们的第一个公共结点。
//    思路：先求出链表长度，然后长的链表先走多出的几步，然后两个链表同时向下走去寻找相同的节点，代码量
//    少的方法需要将两个链表遍历两次，然后从头开始相同的节点。
     
    // 不需要遍历链表的解法
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 这里必须新建两个变量，不能用参数给的pHead1 和 pHead2
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2){
            p1 = (p1 != null ? p1.next : pHead2);
            p2 = (p2 != null ? p2.next : pHead1);
        }
        return p1;
    }




//38.统计一个数字在排序数组中出现的次数。 输入[1,2,3,3,3,3,4,5],3 返回4
//    思路：利用二分查找+递归思想，进行寻找。当目标值与中间值相等时进行判断
//    https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/si-lu-hen-jian-dan-xi-jie-fei-mo-gui-de-er-fen-cha/

    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return new int[]{-1, -1};
        }

        int firstPosition = findFirstPosition(nums, target);
        if (firstPosition == -1) {
            return new int[]{-1, -1};
        }

        int lastPosition = findLastPosition(nums, target);
        return new int[]{firstPosition, lastPosition};
    }

    private int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 小于一定不是解
            if (nums[mid] < target) {
                // 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1;
            } else if (nums[mid] == target) {
                // 下一轮搜索区间是 [left, mid]
                right = mid;
            } else {
                // nums[mid] > target，下一轮搜索区间是 [left, mid - 1]
                right = mid - 1;
            }
        }

        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 注意这里是 right - left + 1
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] > target) {
                // 下一轮搜索区间是 [left, mid - 1]
                right = mid - 1;
            } else if (nums[mid] == target){
                // 下一轮搜索区间是 [mid, right]
                left = mid;
            } else {
                // nums[mid] < target，下一轮搜索区间是 [mid + 1, right]
                left = mid + 1;
            }
        }
        return left;
    }


    /**
     * 方法2
      * @param nums
     * @param K
     * @return
     */
    public int GetNumberOfK2(int[] nums, int K) {
        if(nums.length <= 0 || nums == null) {
            return 0;
        }
        int first = binarySearch(nums, K);
        int last = binarySearch(nums, K+1);
        return (first == nums.length || nums[first] != K)? 0 : last - first;
    }
    private int binarySearch(int[] nums, int K){
        int low = 0, high = nums.length;
        while(low < high){
            int mid = low + (high - low)/2;
            if(nums[mid] >= K) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    /**
     * 方法2
     */
    public int getNumberOfK3(int [] array , int k) {
        if (array == null && array.length == 0) {
            return 0;
        }

        return btSearch(array, k + 0.5) - btSearch(array, k - 0.5);
    }
    int btSearch(int [] array, double k){
        int low = 0; int high = array.length -1;
        while (low<=high){
            //计算mid位置要放在循环里面
            int mid = low + (high-low)/2;
            if (array[mid] < k){
                low = mid +1;
            }
            else if (array[mid] > k){
                high = mid-1;
            }
            else{
                return low;
            }
        }
        return low;//return high;也可以
    }

//39.输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路
//    径，最长路径的长度为树的深度。
//    思路：利用递归遍历分别返回左右子树深度
     
    public int TreeDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }


//39.1输入一棵二叉树，判断该二叉树是否是平衡二叉树。
//    思路：平衡因子的绝对值<= 1.
     
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if (Math.abs(left - right) <= 1) {
            return true;
        }
        return false;
    }

    public int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        int leftNode = getDepth(root.left);
        int rightNode = getDepth(root.right);
        depth = leftNode > rightNode ? leftNode : rightNode;
        return depth + 1;
    }


    /**
     * 牛客pass
     * 下面这种解法时间复杂度高，多了很多重复计算
     * https://www.nowcoder.com/questionTerminal/8b3b95850edb4115918ecebdf1b4d222
     * @return
     */
    public boolean isBalanced(TreeNode root) {

        if (root == null) {
            return true;
        }
        if (Math.abs(depth(root.left) - depth(root.right)) <= 1) {
            return  isBalanced (root.left) && isBalanced(root.right);
        }
        else {
            return false;
        }
    }
    public int depth (TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    /**
     * 这种方法比较好，从底到上
     */

    public boolean IsBalanced_Solution3(TreeNode root) {
        return getDepth(root) != -1;
    }

    private int getDepth3(TreeNode root) {
        if (root == null) return 0;
        int left = getDepth3(root.left);
        if (left == -1) return -1;
        int right = getDepth3(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }



//40.一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
//    思路：两个相同的数异或后为0，将所有数异或后得到一个数，然后求得1在该数最右边出现的index，然后判
//    断每个数右移index后是不是1。
//   https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/jie-di-qi-jiang-jie-fen-zu-wei-yun-suan-by-eddievi/

    public int[] singleNumbers(int[] nums) {
        //xor用来计算nums的异或和
        int xor = 0;

        // 计算异或和 并存到xor
        // e.g. [2,4,2,3,3,6] 异或和：(2^2)^(3^3)^(4^6)=2=010
        for(int num : nums) xor ^= num;

        //设置mask为1，则二进制为0001
        // mask是一个二进制数，且其中只有一位是1，其他位全是0，比如000010，
        // 表示我们用倒数第二位作为分组标准，倒数第二位是0的数字分到一组，倒数第二位是1的分到另一组
        int mask = 1;

        // & operator只有1&1时等于1 其余等于0
        // 用上面的e.g. 4和6的二进制是不同的 我们从右到左找到第一个不同的位就可以分组 4=0100 6=0110
        // 根据e.g. 010 & 001 = 000 = 0则 mask=010
        // 010 & 010 != 0 所以mask=010
        // 之后就可以用mask来将数组里的两个数分区分开
        while((xor & mask)==0){
            mask <<= 1;
        }

        //两个只出现一次的数字
        int a=0, b=0;

        for(int num : nums){
            //根据&是否为0区分将两个数字分区，并分别求异或和
            if((num & mask)==0) a ^= num;
            else{
                b ^= num;
            }
        }
        return new int[]{a,b};
    }


    /**
     * 如果是只有一个出现一次的数字，就用这种方法
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }


//41.输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
    /**
     思路：
     输入sum=20（1，2，3，4，5，6，7，8，9，10，11，12，13，14，15
     1，定义两个指针，左指针从1开始，右指针从2开始
     循环开始
     2，求和（1+2 = 3
     3，如果判断3小于20，右指针++，2变为3，求和3+3=6。循环一直到右指针=6，和为21。
     4，else if 判断21大于20，左指针++，1变为2，和减去左指针值，和为21-1=20。
     5，else 和与输入一样，存数。   【再把右指针++，求和，求剩余组合】
     循环结束
     */

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> resp = new ArrayList<>();
        if(sum <= 0){
            return resp;
        }
        int leftP = 1;
        int rightP = 2;
        int sumVal = leftP + rightP;

        while(sum > rightP){
            if(sumVal < sum){
                rightP++;
                sumVal += rightP;
            } else if (sumVal > sum){
                sumVal -= leftP;
                leftP++;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i=leftP; i<=rightP; i++) {
                    list.add(i);
                }
                resp.add(list);

                rightP++;
                sumVal += rightP;
            }
        }

        return resp;

    }


//41.1输入一个递增排序的数组和一个数字S，在数组中查找两个数，是的他们的和正好是S，如果有多对数字的
//    和等于S，输出两个数的乘积最小的。
//    思路：定义两个指针，分别从前面和后面进行遍历。间隔越远乘积越小，所以是最先出现的两个数乘积最小
     
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null )
            return list;
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int s = array[left] + array[right];
            if (s == sum) {
                list.add(array[left]);
                list.add(array[right]);
                return list;
            }else {
                if (s > sum) {
                    right--;
                }else {
                    left++;
                }
            }
        }
        return list;
    }


// 41.1题目描述
// 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
// 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
// 请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。

        public String leftRotateString(String str,int n) {
            if (n > str.length()) {
                return "";
            }
            String a = str.substring(0,n);
            String b = str.substring(n,str.length());
            return b+a;
        }


//42.翻转字符串  “student. a am I” 变成 “I am a student.”
//    思路：先将整个字符串翻转，然后将每个单词翻转。
     
    public String ReverseSentence(String str) {
        if (str == null || str.length() == 0)
            return str;
        if (str.trim().length() == 0)
            return str;
        StringBuilder sb = new StringBuilder();
        String re = reverse(str);
        String[] s = re.split(" ");
        for (int i = 0; i < s.length - 1; i++) {
            sb.append(reverse(s[i]) + " ");
        }
        sb.append(reverse(s[s.length-1]));
        return String.valueOf(sb);
    }
    public String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0 ; i--) {
            sb.append(str.charAt(i));
        }
        return String.valueOf(sb);
    }

    /**
     * 方法2
     * @param str
     * @return
     */
    public String reverseSentence2(String str) {
        if (str == null || str.length() == 0)
            return str;
        char[] arr = str.toCharArray();//转换成字符数组
        reverse(arr, 0, arr.length - 1);//先全部翻转一次
        int start = 0;//指向单词第一个字母
        int end = 0;//指向单词最后一个字母
        while (start < arr.length) {
            if (arr[start] == ' ') {
                //如果start指向的是空格，就换下一个，因为指针要指向的是单词。
                start++;
                end++;
            } else if (end == arr.length || arr[end] == ' ') {
                //要么尾部是空格，要么end刚刚超过数组最后一个角标，这两种情况就应该翻转了
                //end之所以会超过最后角标，是因为当最后一个字符不是空格时，会让end++，所以会越界
                reverse(arr, start, end-1);
                //翻转后，应该重新记录下个单词，因此更新end和start。
                end++;
                start = end;//这句和上一句可以写成start = ++end;
            } else {
                //到这里就说明，start指的不是空格，end也指的不是空格，说明是正常单词，end++即可
                end++;
            }
        }
        return String.valueOf(arr);
    }
    private void reverse(char[] arr, int begin, int end){
        while(begin<end){
            char temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp;
            begin++;
            end--;
        }
    }




//43.把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
//你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
//输入: 1
//输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
//输入: 2
//输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]

     


//44.扑克牌的顺子。LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
// 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,
// 嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,
// 他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
// 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
// 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，
// 否则就输出false。为了方便起见,你可以认为大小王是0。
// 思路：用数组记录五张扑克牌，将数组调整为有序的，若0出现的次数>=顺子的差值，即为顺子。
// 视频 https://www.bilibili.com/video/BV1h7411776x?from=search&seid=15979322832952047082

    public boolean isContinuous(int [] numbers) {
        if(numbers.length!=5){
            return false;
        }
        Arrays.sort(numbers);
        int zero = 0;
        // 因为只有5张牌，4个0，所以最后一个肯定不是0
        for(int i =0;i<numbers.length-1;i++){
            if(numbers[i] == 0){
                zero++;
                continue;
            }
            if(numbers[i] == numbers[i+1]){
                return false;
            }
        }
        if(numbers[numbers.length-1] - numbers[zero]<5){
            return true;
        }else{
            return false;
        }
    }


    public boolean isContinuous1(int [] numbers) {
        if (numbers == null || numbers.length == 0)
            return false;
        int count = 0;
        int diff = 0;
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                count++;
                continue;
            }
            if (numbers[i] != numbers[i+1]) {
                diff += numbers[i+1] - numbers[i] - 1;
            } else {
                return false;
            }
        }
        if (diff <= count) return true;
        return false;
    }


    /**
     * 方法2
     * 可以这么理解，简单来说就是要是5个数字，最大和最小差值在5以内，并且没有重复数值。
     * 用一个set来填充数据，0不要放进去。set的大小加上0的个数必须为5个。此外set中数值差值在5以内。
     * @return
     */
    public boolean isContinuous0(int [] n) {
        if (n.length < 5 || n.length > 5) {
            return false;
        }
        int num = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n.length; i++) {
            if (n[i] == 0) {
                num++;
            } else {
                set.add(n[i]);
            }
        }
        if ((num + set.size()) != 5) {
            return false;
        }
        if ((set.last() - set.first()) < 5) {
            return true;
        }
        return false;
    }

//45.圆圈中最后剩下的数字（约瑟夫环）
// 给定一个由[0...n-1]构成的数组，第一次从0开始数m个数，然后删除，
// 以后每次都从删除的数下一个位置开始数m个数，然后删除，直到剩余一个数字，找出那个数字。
//比如：arr = [0 1 2 3 4]， m = 3
//第一次：删除2 ，变成 arr = [0 1 3 4]
//第二次，删除0，变成 arr = [1 3 4]
//第三次，删除4，变成 arr = [1 3]
//第四次，删除1，变成 arr = [3]
//https://blog.nowcoder.net/n/1578f938852b4c7c87aa3720a940647d?f=comment


    public int LastRemaining_Solution ( int n, int m){
        LinkedList<Integer> list = new LinkedList<Integer>();
        int bt = 0;
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        while (list.size() > 1) {
            bt = (bt + m - 1) % list.size();
            list.remove(bt);
        }
        return list.size() == 1 ? list.get(0) : -1;
    }

    /**
     * 方法2
     * f[1] = 0
     * f[2] = (f{1] + m) % 2
     * f[3] = (f[2] + m) % 3
     * ...
     * f[n] = (f[n-1] + m) % n
     */
    public int LastRemaining_Solution1 ( int n, int m){
        if (n == 0 || m == 0) return -1;
        int s = 0;
        for (int i = 2; i <= n; i++) {
            s = (s + m) % i;
        }
        return s;
    }


//46.求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?
//    B:C）。
//    思路：巧用递归（返回值类型为Boolean）

        public int Sum_Solution ( int n){
            int sum = 0;
            if (n <= 1) {
                return n;
            } else {
                sum = n;
                sum += Sum_Solution(n - 1);
            }
            return sum;
        }


//47.写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
// 思路：利用位运算 使用位运算实现二位加法：
// 执行加法 x ^ y
// 进位操作 ( x & y ) << 1
// 例子：
// 正确的加法计算：11+01 = 100
// 使用位运算实现二位加法：
// 按位加法： res1 = 11 ^ 01 = 10
// 与运算进位： res2 = (11 & 01) << 1 = ( 01 ) << 1 = 010
// res1 ^ res2 = 10 ^ 010 = 00
// (10 & 10) << 1 = 100

// https://blog.nowcoder.net/n/07f2bd03162d40ddaebefd666e0d71b2?f=comment

    public int Add ( int num1, int num2){
        while (num2 != 0) {
            // 执行加法
            int temp = num1 ^ num2;
            // 计算进位（1+1）
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }
        return num1;
    }

    public int Add1(int num1,int num2) {
        int result = 0;
        int carry = 0;
        do{
            result = num1 ^ num2;       //不带进位的加法
            carry = (num1 & num2) << 1; //进位
            num1 = result;
            num2 = carry;
        }while(carry != 0); // 进位不为0则继续执行加法处理进位
        return result;
    }


//49.将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法
//            的数值则返回0
//    思路：若为负数，则输出负数，字符0对应48,9对应57，不在范围内则返回false
//    “A”的ASCII码值为65；“a”的ASCII码值为97；大Z的ASCII码是90， 小z的ASCII码是122， “0”的ASCII码值为 48。 “9”的ascii是57
//    ASCII码的大小规则为0~9<A~Z<a~z。
     
    public int StrToInt(String str) {
        if (str == null || str.length() == 0)
            return 0;
        int mark = 0;
        int number = 0;
        char[] chars = str.toCharArray();
        if (chars[0] == '-')
            mark = 1; // 表示正负
        for (int i = mark; i < chars.length; i++) {
            if (chars[i] == '+') {
                continue;
            }
            if (chars[i] < 48 || chars[i] > 57) {
                return 0;
            }
            number = number * 10 + chars[i] - 48;
        }
        return mark == 0 ? number : -number;
    }

    /**
     * 方法2
     */
    public int StrToInt2(String str) {
        if (str == null || "".equals(str)){
            return 0;
        }
        boolean flag = true;
        if (str.charAt(0)=='-' || str.charAt(0)=='+'){
            flag = str.charAt(0) =='-' ? false:true;
            str = str.substring(1);
        }
        char[] chs = str.toCharArray();
        int res = 0;
        for (int i = 0; i < chs.length; i++) {
            int cur = chs[i] - '0';
            if (cur>9){
                return 0;
            }
            res = res*10 + cur;
        }
        if (!flag){
            return -res;
        }
        return res;
    }

//50.求树中两个节点的最低公共祖先
//（1）树是二叉搜索树
//    思路：从树的根节点开始遍历，如果根节点的值大于其中一个节点，小于另外一个节点，则根节点就是最低公
//    共祖先。否则如果根节点的值小于两个节点的值，则递归求根节点的右子树，如果大于两个节点的值则递归求
//    根的左子树。如果根节点正好是其中的一个节点，那么说明这两个节点在一条路径上，所以最低公共祖先则是
//    根节点的父节点，时间复杂度是O(logn)，空间复杂度是O(1)

    public algorithm.TreeNode lowestCommonAncestor(algorithm.TreeNode root, algorithm.TreeNode p, algorithm.TreeNode q) {
        algorithm.TreeNode ancestor = root;
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
     * 普通二叉树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // 如果树为空，直接返回null
        if (root == null) {
            return null;
        }
        // 如果 p和q中有等于 root的，那么它们的最近公共祖先即为root（一个节点也可以是它自己的祖先）
        if (root == p || root == q) {
            return root;
        }
        // 递归遍历左子树，只要在左子树中找到了p或q，则先找到谁就返回谁
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 递归遍历右子树，只要在右子树中找到了p或q，则先找到谁就返回谁
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果在左子树中 p和 q都找不到，则 p和 q一定都在右子树中，右子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
        if (left == null) {
            return right;
        }
        else if (right == null) {
            // 否则，如果 left不为空，在左子树中有找到节点（p或q），这时候要再判断一下右子树中的情况，如果在右子树中，p和q都找不到，则 p和q一定都在左子树中，左子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
            return left;
        }
        else {
            //否则，当 left和 right均不为空时，说明 p、q节点分别在 root异侧, 最近公共祖先即为 root
            return root;
        }
    }




//51.在一个长度为n的数组里的所有数字都在0到n-1的范围内，找出数组中任意一个重复的数字


    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        int [] nums  = new int [10];
        for (int i = 0; i < numbers.length; i++) {
            if (nums[numbers[i]] == 0) {
                nums[numbers[i]] ++;
            }else {
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }

// 52.给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]A[1]...A[i-1]A[i+1]...A[n-1]。
// 输入: [1,2,3,4,5]
// 输出: [120,60,40,30,24]
// 其中A[i] = 1。不能使用除法， https://www.nowcoder.com/questionTerminal/94a4d381a68b47b7a8bed86f2975db46


    public int[] multiply1(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        if(length != 0 ){
            B[0] = 1;
            //计算下三角连乘
            for(int i = 1; i < length; i++){
                B[i] = B[i-1] * A[i-1];
            }
            int temp = 1;
            //计算上三角
            for(int j = length-2; j >= 0; j--){
                temp *= A[j+1];
                B[j] *= temp;
            }
        }
        return B;
    }


//53.请实现一个函数用来匹配包括'.'和''的正则表达式。模式中的字符'.'表示任意一个字符，而''表示它前面的字符
//    可以出现任意次（包含0次）
//    思路：当字符串只有一个字符时，进行判断，否则就有两种递归情况，（1）当模式中的第二个字符不是“*”时：
//    如果字符串第一个字符和模式中的第一个字符相匹配或是点那么字符串和模式都后移一个字符，然后匹配剩余
//    的；如果 字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。（2）当模式中的第二个字符
//    是“*”时：如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配；如果字符串第一个
//    字符跟模式第一个字符匹配或是点，可以有3种匹配方式：1 >模式后移2字符，相当于x被忽略；2>字符串后移1
//    字符，模式后移2字符；3>字符串后移1字符，模式不变，即继续匹配字符下一位，因为 可以匹配多位；
     
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null)
            return false;
        // 若字符串的长度为1
        if (str.length == 1) {
            if (pattern.length == 1){
                if (str[0] == pattern[0] || pattern[0] == '.')
                    return true;
                return false;
            }
        }
        int sindex = 0;
        int pindex = 0;
        return matchIndex(str,sindex,pattern,pindex);
    }
    public boolean matchIndex(char[] str,int sindex, char[] pattern, int pindex) {
        // str和pattern同时到达末尾，则匹配成功2017/9/10

        if (sindex == str.length && pindex == pattern.length)
            return true;
        // 若pattern先到尾，而str没有到达末尾，则匹配失败
        if (sindex != str.length && pindex == pattern.length)
            return false;
        // 若pattern第二个字符是*
        if (pindex + 1 < pattern.length && pattern[pindex + 1] == '*') {
            if (sindex != str.length && pattern[pindex] == str[sindex] ||
                    sindex != str.length && pattern[pindex] == '.') {
                return matchIndex(str,sindex+1,pattern,pindex+2)
                        || matchIndex(str,sindex,pattern,pindex+2)
                        || matchIndex(str,sindex+1,pattern,pindex);
            } else {
                return matchIndex(str,sindex,pattern,pindex+2);
            }
        }
        // 若pattern第二个字符不是*
        if (sindex != str.length && pattern[pindex] == str[sindex] ||
                sindex != str.length && pattern[pindex] == '.')
            return matchIndex(str,sindex+1,pattern,pindex+1);
        return false;
    }


//54.请实现一个函数用来判断字符串是否表示数值（包括整数和小数）
//    思路：逐个字符进行判断，e或E和小数点最多出现一次，而e或E的前一个必须是数字，且不能是第一个或最
//    后一个字符，符号的前一个字符不能是e或E。也可用正则表达式判断！
     
    public boolean isNumeric(char[] str) {
        if (str == null)
            return false;
        int index = 0;
        int ecount = 0;
        int point = 0;
        // 如果第一个字符是符号就跳过
        if (str[0] == '-' || str[0] == '+')
            index++;
        for (int i = index; i < str.length; i++) {
            if (str[i] == '-' || str[i] == '+') {
                if (str[i-1] != 'e' && str[i-1] != 'E')
                    return false;
                continue;
            }
            if (str[i] == 'e' || str[i] == 'E') {
                ecount++;
                if (ecount > 1)
                    return false;
                if (i == 0 || str[i-1] < 48 || str[i-1] > 57 || i == str.length-1)
                return false;
                point++;
                continue;
            }
            if (str[i] == '.') {
                point++;
                if (point > 1)
                    return false;
                continue;
            }
            // 出现非数字且不是e/E则返回false（小数点和符号用continue跳过了）
            if ((str[i] < 48 || str[i] > 57) && (str[i] != 'e') && (str[i] != 'E'))
                return false;
        }
        return true;
    }



    public boolean isNumeric2(char[] str) {

        if(str.length==0)
            return false;
        if(str.length==1)
            if(str[0] < '0' || str[0] > '9')
                return false;

        // 标记符号、小数点、e是否出现过
        boolean sign = false, decimal = false, hasE = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'e' || str[i] == 'E') {
                if (i == str.length-1) return false; // e后面一定要接数字
                if (hasE) return false;  // 不能同时存在两个e
                hasE = true;
            } else if (str[i] == '+' || str[i] == '-') {
                // 第二次出现+-符号，则必须紧接在e之后
                if (sign && str[i-1] != 'e' && str[i-1] != 'E') return false;
                // 第一次出现+-符号，且不是在字符串开头，则也必须紧接在e之后
                if (!sign && i > 0 && str[i-1] != 'e' && str[i-1] != 'E') return false;
                sign = true;
            } else if (str[i] == '.') {
                // e后面不能接小数点，小数点不能出现两次
                if (hasE || decimal) return false;
                decimal = true;
            } else if (str[i] < '0' || str[i] > '9') // 不合法字符
                return false;
        }
        return true;
    }

//55.请实现一个函数用来找出字符流中第一个只出现一次的字符。
//    思路：借助辅助空间进行判断，如字符数组。
     
    char[] chars = new char[256];
    StringBuilder sb = new StringBuilder();
    public void Insert(char ch) {
        sb.append(ch);
        chars[ch]++;
    }
    public char FirstAppearingOnce() {
        char[] str = sb.toString().toCharArray();
        for (char c : str) {
            if (chars[c] == 1) {
                return c;
            }
        }
        return '#';
    }


//56.一个链表中包含环，请找出该链表的环的入口结点。
//    思路：定义快慢两个指针，相遇后（环中相汇点）将快指针指向pHead 然后一起走，每次往后挪一位，相遇的
//    节点即为所求。详细分析：相遇即p1==p2时，p2所经过节点数为2x,p1所经过节点数为x,设环中有n个节点,p2
//            比p1多走一圈有2x=n+x; n=x;可以看出p1实际走了一个环的步数，再让p2指向链表头部，p1位置不变，p1,p2
//    每次走一步直到p1==p2; 此时p1指向环的入口。

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null)
            return null;
        ListNode slow = pHead;
        ListNode fast = pHead;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                fast = pHead;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        } return null;
    }


//57.在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针
// 注意这道题区别于删除后保留重复节点，以及删除链表中指定的节点
//  思路：先新建一个头节点，然后向后查找值相同的节点，重复查找后删除
    // 链表1->2->3->3->4->4->5 处理后为 1->2->5

    public ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = pHead;
        ListNode pre = dummy;
        ListNode last = dummy.next;
        while (last != null && last.next != null) {
            if (last.next != null && last.val == last.next.val) {
                // 找到最后的一个相同节点
                while (last.next != null && last.val == last.next.val) {
                    last = last.next;
                }
                pre.next = last.next;
                last = last.next;
            } else {
                pre = pre.next;
                last = last.next;
            }
        }
        return dummy.next;
    }


    public ListNode deleteDuplication(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode cur = head;
        ListNode pre = node;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                int val = cur.val;
                //只需把下面的while改为注释的while，则会实现
                // 链表1->2->3->3->4->4->5 处理后为 1->2->3->4->5
                /*while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }*/
                while (cur != null && cur.val == val) {
                    cur = cur.next;
                }
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return node.next;
    }


//  上面的题要区别下面的题：删除链表中的重复元素
//  输入: 1->1->2->3->3
//  输出: 1->2->3


    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while (p != null){
            // 注意这里 p.next 要判断为null，从空指针角度也能想到
            if (p.next != null && p.val == p.next.val){
                p.next = p.next.next;
            }else{
                p = p.next;
            }
        }
        return head;
    }



    /**
     * @description 26. 删除排序数组中的重复项
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * 示例 1:
     * 给定数组 nums = [1,1,2],
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * 你不需要考虑数组中超出新长度后面的元素。。
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/shuang-zhi-zhen-shan-chu-zhong-fu-xiang-dai-you-hu/
     */

    /**
     * s
     */
    public int removeDuplicates(int[] nums) {
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[index];
            if (val != nums[i]) {
                index = index + 1;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int p = 0;
        int q = 1;
        while (q < nums.length){
            if (nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

//58.给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅
//    包含左右子结点，同时包含指向父结点的指针。
//    思路：若节点右孩子存在，则设置一个指针从该节点的右孩子出发，一直沿着指向左子结点的指针找到的叶子
//    节点即为下一个节点；若节点不是根节点。如果该节点是其父节点的左孩子，则返回父节点；否则继续向上遍
//    历其父节点的父节点，重复之前的判断，返回结果

    class TreeLinkNode {
        TreeLinkNode right;
        TreeLinkNode left;
        TreeLinkNode next;
    }
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null)
            return null;
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while (pNode.next != null) {
            // 找第一个当前节点是父节点左孩子的节点
            if (pNode.next.left == pNode)
                return pNode.next;
            pNode = pNode.next;
        }
        return null;
    }


//59.请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样
//    的，定义其为对称的。
//    思路：利用递归进行判断，若左子树的左孩子等于右子树的右孩子且左子树的右孩子等于右子树的左孩子，并
//    且左右子树节点的值相等，则是对称的。
     
    public boolean isSymmetrical(TreeNode pRoot){
        if (pRoot == null)
            return true;
        return isCommon(pRoot.left, pRoot.right);
    }

    public boolean isCommon(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null)
        return true;
        if (leftNode != null && rightNode != null)
            return leftNode.val == rightNode.val &&
                    isCommon(leftNode.left,rightNode.right) &&
                    isCommon(leftNode.right,rightNode.left);
        return false;
    }

//60.请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序
//    打印，第三行按照从左到右的顺序打印，依此类推。
//    思路：利用两个栈的辅助空间分别存储奇数偶数层的节点，然后打印输出。或使用链表的辅助空间来实现，利
//    用链表的反向迭实现逆序输出。

    /**
     * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/solution/jiao-ti-shi-yong-zhan-jian-dan-shi-xian-ju-chi-xin/
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        //栈1来存储右节点到左节点的顺序
        Stack<TreeNode> stack1 = new Stack<>();
        //栈2来存储左节点到右节点的顺序
        Stack<TreeNode> stack2 = new Stack<>();

        //根节点入栈
        stack1.push(root);

        //注意这里是或，每次循环中，都是一个栈为空，一个栈不为空，结束的条件两个都为空
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> subList = new ArrayList<>(); // 存储这一个层的数据
            TreeNode cur = null;

            if (!stack1.isEmpty()) { //栈1不为空，则栈2此时为空，需要用栈2来存储从下一层从左到右的顺序
                while (!stack1.isEmpty()) {	//遍历栈1中所有元素，即当前层的所有元素
                    cur = stack1.pop();
                    subList.add(cur.val);	//存储当前层所有元素

                    if (cur.left != null) {	//左节点不为空加入下一层
                        stack2.push(cur.left);
                    }
                    if (cur.right != null) {	//右节点不为空加入下一层
                        stack2.push(cur.right);
                    }
                }
                list.add(subList);
            }else {//栈2不为空，则栈1此时为空，需要用栈1来存储从下一层从右到左的顺序
                while (!stack2.isEmpty()) {
                    cur = stack2.pop();
                    subList.add(cur.val);

                    if (cur.right != null) {//右节点不为空加入下一层
                        stack1.push(cur.right);
                    }
                    if (cur.left != null) { //左节点不为空加入下一层
                        stack1.push(cur.left);
                    }
                }
                list.add(subList);
            }
        }
        return list;
    }


    /**
     * 方法2
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null)
            return res;
        Stack<TreeNode> s1 = new Stack<>(); // s1表示奇数，从右向左输出
        Stack<TreeNode> s2 = new Stack<>(); // s2表示偶数，从左向右输出
        s1.push(pRoot);
        int level = 1;
        while (!s1.empty() || !s2.empty()) {
            if (level % 2 != 0) {
                ArrayList<Integer> list = new ArrayList<>();
                while (!s1.empty()) {
                    TreeNode node = s1.pop();
                    if (node != null) {
                        list.add(node.val);
                        s2.push(node.left);
                        s2.push(node.right);
                    }
                }
                if (!list.isEmpty()) {
                    res.add(list);
                    level++;
                }
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                while (!s2.empty()) {
                    TreeNode node = s2.pop();
                    if (node != null) {
                        list.add(node.val);
                        s1.push(node.right);
                        s1.push(node.left);
                    }
                }
                if (!list.isEmpty()) {
                    res.add(list);
                    level++;
                }
            }
        }
        return res;
    }

//61.从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。输入{8,6,10,5,7,9,11}，输出 {8,6,10,5,7,9,11}
//    思路：利用辅助空间链表或队列来存储节点，每层输出。
     
    public ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        ArrayList<Integer> list = new ArrayList<>();
        int start = 0;
        int end = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            list.add(node.val);
            start++;
            if (node.left != null)
                // 注意queue不能用push方法，要用offer和add,切记
                queue.offer(node.left);
            if (node.right != null)
                queue.push(node.right);
            if (start == end) {
                start = 0;
                end = queue.size();
                res.add(new ArrayList<>(list));
                list.clear();
            }
        } return res;
    }

    /**
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * [3,9,20,15,7]
     */
    public int[] levelOrder(TreeNode root) {
        if(root == null) return new int[0];
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        int[] array = new int[res.size()];
        for (int i=0; i < array.length; i++) {
            array[i] = res.get(i);
        }
        return array;
    }

// 62.请实现两个函数，分别用来序列化和反序列化二叉树，二叉树的序列化是指：
// 把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。
// 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，
// 序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
// 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
// 例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树
//  思路：序列化：前序遍历二叉树存入字符串中；反序列化：根据前序遍历重建二叉树。
// https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/er-cha-shu-de-xu-lie-hua-yu-fan-xu-lie-hua-by-le-2/


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "X,";
        int rootVal = root.val;
        String leftSerialize = serialize(root.left); //左子树的序列化字符串
        String rightSerialize = serialize(root.right); //右子树的序列化字符串
        return rootVal + "," + leftSerialize + rightSerialize;
    }

    // Decodes your encoded data to tree.
    /**
     * 构建树的函数 buildTree 接收的 “状态” 是 list 数组，由序列化字符串转成
     * 按照前序遍历的顺序：先构建根节点，再构建左子树、右子树
     * 将 list 数组的首项弹出，它是当前子树的 root 节点
     * 如果它为 'X' ，返回 null
     * 如果它不为 'X'，则为它创建节点，并递归调用 buildTree 构建左右子树，当前子树构建完毕，向上返回
     */
    public TreeNode deserialize(String data) {
        String[] temp = data.split(",");
        Deque<String> dp = new LinkedList<>(Arrays.asList(temp));
        return buildTree(dp);
    }
    private TreeNode buildTree(Deque<String> dq){
        String s = dq.poll(); //返回当前结点
        if (s.equals("X")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(s));
        node.left = buildTree(dq); //构建左子树
        node.right = buildTree(dq); //构建右子树
        return node;
    }


//63.给定一棵二叉搜索树，请找出其中的第k小的结点。
//    思路：二叉搜索树按照中序遍历的顺序打印出来正好就是排序好的顺序，第k个结点就是第K大的节点，分别递
//    归查找左右子树的第K个节点，或使用非递归借用栈的方式查找，当count=k时返回根节点。


    /**
     * 非递归
     */
    TreeNode KthNode(TreeNode root, int k) {
        if(root == null || k == 0) return null;
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || ! stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            count ++;
            if(count == k) return root;
            root = root.right;
        }
        return null;
    }

    /**
     * 方法2
     */
    int index=0;
    TreeNode KthNode2(TreeNode pRoot, int k){
        if(pRoot!=null){
            TreeNode node = KthNode(pRoot.left, k);
            if(node!=null){
                return node;
            }
            index++;
            if(k==index){
                return pRoot;
            }
            node = KthNode(pRoot.right, k);
            if(node!=null){
                return node;
            }
        }
        return null;
    }

//64.如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位
//    于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
//    思路：创建优先级队列维护大顶堆和小顶堆两个堆，并且小顶堆的值都大于大顶堆的值，2个堆个数的差值小
//    于等于1，所以当插入个数为奇数时：大顶堆个数就比小顶堆多1，中位数就是大顶堆堆头；当插入个数为偶数
//    时，使大顶堆个数跟小顶堆个数一样，中位数就是 2个堆堆头平均数。也可使用集合类的排序方法。
     
    int count3 = 0;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(16, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });
    public void Insert(Integer num) {
        count3++;
        // 当数据的个数为奇数时，进入大根堆
        if ((count3 & 1) == 1) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
    }
    public Double GetMedian() {
        if (count3 == 0)
            return null;
        // 当数据个数是奇数时，中位数就是大根堆的顶点
        if ((count3 & 1) == 1) {
            return Double.valueOf(maxHeap.peek());
        } else {
            // 注意这里 / 2 一定要在外面
            return Double.valueOf(minHeap.peek() + maxHeap.peek()) / 2;
        }
    }


//65.给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值
//    思路：两个for循环，第一个for循环滑动窗口，第二个for循环滑动窗口中的值，寻找最大值。还可以使用时间
//    复杂度更低的双端队列求解。

    public int[] maxSlidingWindow(int[] nums, int k) {
        //单调队列
        //下面是要注意的点：
        //队列按从大到小放入
        //如果首位值（即最大值）不在窗口区间，删除首位
        //如果新增的值小于队列尾部值，加到队列尾部
        //如果新增值大于队列尾部值，删除队列中比新增值小的值，如果在把新增值加入到队列中
        //如果新增值大于队列中所有值，删除所有，然后把新增值放到队列首位，保证队列一直是从大到小
        if (nums.length == 0)   {
            return nums;
        }

        Deque<Integer> deque = new LinkedList<>();
        int[] arr = new int[nums.length - k + 1];
        int index = 0;  //arr数组的下标
        //未形成窗口区间
        for (int i = 0; i < k; i++) {
            //队列不为空时，当前值与队列尾部值比较，如果大于，删除队列尾部值
            //一直循环删除到队列中的值都大于当前值，或者删到队列为空
            while (!deque.isEmpty() && nums[i] > deque.peekLast())  {
                deque.removeLast();
            }
            //执行完上面的循环后，队列中要么为空，要么值都比当前值大，然后就把当前值添加到队列中
            deque.addLast(nums[i]);
        }
        //窗口区间刚形成后，把队列首位值添加到队列中
        //因为窗口形成后，就需要把队列首位添加到数组中，而下面的循环是直接跳过这一步的，所以需要我们直接添加
        arr[index++] = deque.peekFirst();
        //窗口区间形成
        for (int i = k; i < nums.length; i++) {
            //i-k是已经在区间外了，如果首位等于nums[i-k]，那么说明此时首位值已经不再区间内了，需要删除
            if (deque.peekFirst() == nums[i - k])   {
                deque.removeFirst();
            }
            //删除队列中比当前值大的值
            while (!deque.isEmpty() && nums[i] > deque.peekLast())  {
                deque.removeLast();
            }
            //把当前值添加到队列中
            deque.addLast(nums[i]);
            //把队列的首位值添加到arr数组中
            arr[index++] = deque.peekFirst();
        }
        return arr;
    }


    /**
     * 方法2 这个双向队列是保存当前最大值在数组中的位置，区别上一个方法
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {


        // 这里注意k为0的情况
        if(nums == null || nums.length < 2 || k == 0) {
            return nums;
        }
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        // 结果数组
        int[] result = new int[nums.length-k+1];
        // 遍历nums数组
        for (int i = 0;i < nums.length;i++){
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效
            if (queue.peek() <= i-k){
                queue.poll();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if (i+1 >= k){
                result[i+1-k] = nums[queue.peek()];
            }
        }
        return result;
    }


//66.请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中
//    的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵
//    中的某一个格子，则该路径不能再进入该格子。
//    思路：回溯法，双层for循环，判断每一个点，每次递归调用上下左右四个点，用flag标志是否已经匹配
//（return），进行判断点的位置是否越界，是否已经正确匹配，判断矩阵的路径与模式串的第index个字符是否
//    匹配。

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int flag[] = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (helper(matrix, rows, cols, i, j, str, 0, flag))
                    return true;
            }
        }
        return false;
    }

    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
        // 注意idex取值为 i * cols + j， 因为每一行的个数为cols。
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
            return false;
        if(k == str.length - 1) return true;
        flag[index] = 1;
        if (helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag))
            return true;

        flag[index] = 0;
        return false;
    }

//67. 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
//    每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
//    例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），
//    因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
//    思路：利用递归实现，每次只能走上下左右四个点，进行判断点的位置是否越界，点数之和是否大于K，是否
//    已经走过了。  时间复杂度 O(MN)O(MN) ： 最差情况下，机器人遍历矩阵所有单元格，此时时间复杂度为 O(MN)O(MN) 。
//    空间复杂度 O(MN)O(MN) ： 最差情况下，Set visited 内存储矩阵所有单元格的索引，使用 O(MN)O(MN) 的额


     
    public int movingCount(int threshold, int rows, int cols) {
        int flag[][] = new int[rows][cols]; //记录是否已经走过
        return helper(0, 0, rows, cols, flag, threshold);
    }
    private int helper(int i, int j, int rows, int cols, int[][] flag, int threshold) {
        if (i < 0 || i >= rows || j < 0 || j >= cols ||
                numSum(i) + numSum(j) > threshold || flag[i][j] == 1)
            return 0;
        flag[i][j] = 1;
        return helper(i - 1, j, rows, cols, flag, threshold)
                + helper(i + 1, j, rows, cols, flag, threshold)
                + helper(i, j - 1, rows, cols, flag, threshold)
                + helper(i, j + 1, rows, cols, flag, threshold) + 1;
    }
    private int numSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i = i / 10;
        }
        return sum;
    }

    /**
     * k个一组翻转链表
     * https://zhuanlan.zhihu.com/p/90170262  这个例子不错
     */
    ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) return head;
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    /** 反转区间 [a, b) 的元素，注意是左闭右开 */
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    /**
     * 剪绳子：给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），
     * 每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，
     * 我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/shu-xue-zhi-shi-he-dong-tai-gui-hua-liang-chong-fa/
     */


    public int cuttingRope(int n) {
        if (n == 2 || n == 3)
            return n - 1;
        else if (n % 3 == 0) {
            //如果n是3的倍数，绳子全部剪为3
            return (int) Math.pow(3, n / 3);
        } else if (n % 3 == 1) {
            //如果n对3求余等于1，我们剪出一个长度为4的，其他长度都是3
            return 4 * (int) Math.pow(3, (n - 4) / 3);
        } else {
            //如果n对3求余等于2，我们剪出一个长度为2的，其他长度都是3
            return 2 * (int) Math.pow(3, n / 3);
        }
    }

    /**
     * 方法2 动态规划
     */
    public int cuttingRope2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], (Math.max(j, dp[j])) * (Math.max(i - j, dp[i - j])));
            }
        }
        return dp[n];
    }


    /**
     * 整数的拆分，（和减绳子一样，用在剪绳子也能过）给定一个正整数n， 将其拆分成至少两个正整数的和，
     * 并使得这些整数的乘积最大化，返回可以获得的最大乘积
     * 输入 2 输出1 【因为 2 = 1+1】， 输入10，返回36  【因为10 = 3+3+4】
     */

    public int integerBreak(int n) {
        // 注意这里是n + 1， 因为 dp[1] = 1 dp[2] = 2  dp[3] = 3，下标是从0开始的
        int [] dp = new int [n+1];
        if (n<2) {
            return 1;
        }
        if(n==2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        // 大于等于4的值， 2 3 不拆分，因为拆分后乘积变小
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4 ; i <= n ; i ++) {
            for (int j = 1; j <= i/2; j ++) {
                dp[i] = Math.max(dp[i], dp[j]*dp[i-j]);
            }
        }
        return dp[n];

    }


    /**
     * 二叉树的下一个节点，给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
     * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     *
     */
    TreeLinkNode getNext(TreeLinkNode node)
    {
        if(node==null) return null;
        if(node.right!=null){    //如果有右子树，则找右子树的最左节点
            node = node.right;
            while(node.left!=null) node = node.left;
            return node;
        }
        while(node.next!=null){ //没右子树，则找第一个当前节点是父节点左孩子的节点
            if(node.next.left==node) return node.next;
            node = node.next;
        }
        return null;   //退到了根节点仍没找到，则返回null
    }


    /**
     * 方法2
     */
    public TreeLinkNode getNext2(TreeLinkNode pNode)
    {
        if (pNode == null) return null;
        // 若给定节点 有右子树， 则返回的一定是 右子树 最左边的节点
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }

        // 若没有右子树 则返回的是 父亲节点
        while (pNode.next != null) {
            if (pNode.next.left == pNode) { // 父亲节点的左节点等于本身，且本身没有右节点，那么直接返回父节点
                return pNode.next;
            }
            pNode = pNode.next; // 父亲节点的左节点不等于本身，说明本身在父节点的右子节点，继续遍历父亲节点的父节点
        }
        return null;
    }

    /**
     一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     问总共有多少条不同的路径？
     */
    public static int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n];

        // 初始值如下：
        //dp[0] [0….n-1] = 1; // 相当于最上面一行，机器人只能一直往左走
        //dp[0…m-1] [0] = 1; // 相当于最左面一列，机器人只能一直往下走
        for (int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        // 推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 状态转移方程
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 01背包问题：https://mp.weixin.qq.com/s/AMAYrutpuXQyRM178HJeZA
     */

}



