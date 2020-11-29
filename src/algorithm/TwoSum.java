package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description  all
 */
public class TwoSum {

    /**
     *  two sum
     *  nums = [2, 7, 11, 15], target = 9，因为 nums[0] + nums[1] = 2 + 7 = 9，所以返回 [0, 1]
      * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap();
        map.put(nums[0] , 0);
        for (int i = 1 ; i < len ; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]) , i};
            }
            else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    /**
     * 暴力
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0 ; i < nums.length; i++) {
            for (int j = i+1;j<nums.length;j++ ){
                if (nums[i]+nums[j] == target) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }
}

