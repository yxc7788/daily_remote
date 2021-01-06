package algorithm;

/**
 * @author yangxc27652
 * @date 2020/12/21
 * @description 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * https://leetcode-cn.com/problems/word-search/solution/dan-ci-sou-suo-by-leetcode-solution/
 */
public class WordExist {

    /**
     * 仿照牛客
     */
    public boolean exist(char[][] board, String word) {
        char[] str = word.toCharArray();
        int rows= board.length;
        int cols= board[0].length;
        int flag[][] = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (helper(board, rows, cols, i, j, str, 0, flag)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean helper(char[][] board, int rows, int cols, int i, int j, char[] str, int k, int[][] flag) {


        if (i < 0 || i >= rows || j < 0 || j >= cols || board[i][j] != str[k] || flag[i][j] == 1) {
            return false;
        }
        if (k == str.length - 1) {
            return true;
        }
        flag[i][j] = 1;
        if (helper(board, rows, cols, i + 1, j, str, k + 1, flag)
                || helper(board, rows, cols, i - 1, j, str, k + 1, flag)
                || helper(board, rows, cols, i, j + 1, str, k + 1, flag)
                || helper(board, rows, cols, i, j - 1, str, k + 1, flag)) {
            return true;
        }

        flag[i][j] = 0;
        return false;
    }

}
