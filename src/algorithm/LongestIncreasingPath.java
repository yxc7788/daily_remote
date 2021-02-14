package algorithm;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description 329. 矩阵中的最长递增路径
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * 输入: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 *
 * 输入: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/javashi-xian-shen-du-you-xian-chao-ji-jian-dan-yi-/
 */
public class LongestIncreasingPath {

    /**
     * self 基本用例过了，但是有一个特殊的用例超时
     */
    int res = 1;
    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] flag = new int[row][col];
        for (int i = 0 ; i < matrix.length; i++) {
            for (int j = 0 ; j < matrix[0].length; j++) {
                helper(row,col,i,j,flag,Integer.MIN_VALUE,0,matrix);
            }
        }
        return res;
    }
    public void helper (int row, int col, int i, int j , int[][] flag, int pre, int index, int[][] matrix) {

        if (i < 0 || i >= row || j < 0 || j >= col || flag[i][j] == 1) {
            return;
        }
        if (matrix[i][j] > pre) {
            flag[i][j] = 1;
            index = index + 1;
            res = Math.max(index, res);
            pre = matrix[i][j];
            helper(row, col, i + 1, j, flag, pre, index, matrix);
            helper(row, col, i - 1, j, flag, pre, index, matrix);
            helper(row, col, i, j + 1, flag, pre, index, matrix);
            helper(row, col, i, j - 1, flag, pre, index, matrix);
            flag[i][j] = 0;
        }
        return;

    }
}
