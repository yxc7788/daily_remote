package algorithm;

/**
 * @author yangxc27652
 * @date 2020/12/20
 * @description 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 输入:
 * arr = [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class MinPathSum {

    public static int uniquePaths(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n]; //
        // 初始化
        // dp[i] [j] 中，如果 i 或者 j 有一个为 0，如果用状态方程推，这个时候把 i - 1 或者 j - 1，就变成负数了，
        // 数组就会出问题了，所以我们需要先把初始值写出来，计算出所有的 dp[0] [0….n-1] 和所有的 dp[0….m-1] [0]。
        dp[0][0] = arr[0][0];
        // 初始化最左边的列
        for (int i = 1; i < m; i++){
            dp[i][0] = dp[i-1][0] + arr[i][0];
        }
        // 初始化最上边的行
        for (int i = 1; i < n; i++){
            dp[0][i] = dp[0][i-1] + arr[0][i];
        }
        // 推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + arr[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
