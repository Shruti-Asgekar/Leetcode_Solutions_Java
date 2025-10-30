class Solution {
    private int[][] grid;
    private int n;
    private Integer[][][] memo;

    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.memo = new Integer[n][n][n];
        
        int result = Math.max(0, dp(0, 0, 0));
        return result;
    }

    private int dp(int r1, int c1, int r2) {
        int c2 = r1 + c1 - r2;

        // Out of bounds
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n) return -1;
        // Thorn
        if (grid[r1][c1] == -1 || grid[r2][c2] == -1) return -1;

        // Reached bottom-right
        if (r1 == n - 1 && c1 == n - 1) return grid[r1][c1];

        if (memo[r1][c1][r2] != null) return memo[r1][c1][r2];

        int cherries = grid[r1][c1];
        if (r1 != r2 || c1 != c2) cherries += grid[r2][c2]; // Avoid double-counting same cell

        int bestNext = Math.max(
            Math.max(dp(r1 + 1, c1, r2 + 1), dp(r1, c1 + 1, r2)),  // (A down, B down) or (A right, B right)
            Math.max(dp(r1 + 1, c1, r2), dp(r1, c1 + 1, r2 + 1))   // mixed moves
        );

        if (bestNext < 0) return memo[r1][c1][r2] = -1; // No valid path
        return memo[r1][c1][r2] = cherries + bestNext;
    }
}
