package algorithm;

/**
 * @author yangxc27652
 * @date 2020/12/24
 * @description 134. 加油站
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * 说明: 
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 *
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 *
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * 输出: -1
 */
public class CanCompleteCircuit {
    /**
     * 时间复杂度：O(N)O(N)，其中 NN 为数组的长度。我们对数组进行了单次遍历。
     * 空间复杂度：O(1)O(1)。
     */
    public int canCompleteCircuit0(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] >= cost[i]) {
                int cur = gas[i];
                int j = i;
                while (cur >= cost[j%gas.length]) {
                    cur = cur + gas[(j + 1)%gas.length] - cost[j%gas.length];
                    j++;
                    if (j == i + gas.length) {
                        return j % gas.length;
                    }
                }
            }
        }
        return  -1;
    }


    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;

        // 从头到尾遍历每个加油站，并且检查以该加油站为起点，能否行驶一周
        while (i < n){
            int sumOfGas  = 0; // 总共加的油
            int sumOfCost = 0; // 总共消费的油
            int count = 0;     // 记录能走过几个站点
            while (count < n){  // 退出循环的条件是走过所有的站点
                int j = (i + count) % n; // 加油站是环形的
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas){ // 如果这个站点发现油不够了
                    break;
                }
                count++; // 这个站点满足情况
            }

            if (count == n){  // 如果能环绕一圈
                return i;
            }else{ // 不行的话 从下一个站点开始 检查
                i = i + count + 1;
            }
        }
        // 所有加油站作为起点都不满足
        return -1;
    }


    /**
     * 方法2
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        //考虑从每一个点出发
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            //当前剩余的油能否到达下一个点
            while (remain - cost[j] >= 0) {
                //减去花费的加上新的点的补给
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                //j 回到了 i
                if (j == i) {
                    return i;
                }
            }
        }
        //任何点都不可以
        return -1;
    }


    /**
     * self
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int cur = 0;
        for (int i = 0; i<gas.length; i++) {
            int j = i;
            int count = 0;
            while (count <= gas.length -1) {
                cur = cur + gas[j];
                if (cur - cost[j] >=0) {
                    cur = cur - cost[j];
                    j = (j + 1) % gas.length;
                } else {
                    cur = 0;
                    break;
                }
                if (j == i) {
                    return i;
                }
                count ++;
            }
        }
        return -1;
    }


    /**
     * self2
     */
    public int canCompleteCircuit4(int[] gas, int[] cost) {
        int cur = 0;
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] >= cost[i]) {
                cur = 0;
                for (int j = i; j < gas.length + i + 1; j++) {
                    cur = cur + gas[j%gas.length] - cost[j%gas.length];
                    if (j == gas.length + i && j >= gas.length) {
                        return i;
                    }
                    if (cur < 0) {
                        break;
                    }
                }
            }

        }
        return -1;
    }


}
