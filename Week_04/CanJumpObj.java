package test20200905;

public class CanJumpObj {
    public boolean canJump(int[] nums) {
        int n = nums.length - 1;
        int dist = 0;
        for (int i = 0; i <= n; i++) {
            if (dist < i || dist >= n) break;
            dist =  Math.max(dist, i + nums[i]);
        }
        return dist >= n;
    }
}
