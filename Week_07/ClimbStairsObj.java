package test20200926;

public class ClimbStairsObj {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        ClimbStairsObj climbStairsObj = new ClimbStairsObj();
        int n = 5;
        int res = climbStairsObj.climbStairs(n);
        System.out.println(res);
    }
}
