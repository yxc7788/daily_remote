package algorithm;

/**
 * @author yangxc27652
 * @date 2020/12/21
 * @description 695. 岛屿的最大面积
 * https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
 */
public class MaxAreaOfIsland {



    /**
     * method2 这个是岛屿最大面积
     * @param grid
     * @return
     */


    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    int a = area(grid, r, c);
                    res = Math.max(res, a);
                }
            }
        }
        return res;
    }

    int area(int[][] grid, int r, int c) {
        if (!inArea(grid, r, c)) {
            return 0;
        }
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;

        return 1
                + area(grid, r - 1, c)
                + area(grid, r + 1, c)
                + area(grid, r, c - 1)
                + area(grid, r, c + 1);
    }

    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }



    /**
     * 这个是岛屿个数
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int res = 0;
        for (int i = 0; i < rows; i++ ){
            for (int j = 0; j < cols; j++ ) {
                res += helper(rows, cols, i, j, grid);
            }
        }
        return res;
    }
    private int helper(int rows, int cols, int i, int j, char[][] grid) {

        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == '0') {
            return 0;
        }

        grid[i][j] = '0';
        helper(rows, cols, i-1, j,grid);
        helper(rows, cols, i+1, j,grid);
        helper(rows, cols, i, j-1,grid);
        helper(rows, cols, i, j+1,grid);
        return 1;
    }



}
