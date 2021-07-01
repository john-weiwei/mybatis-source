package com.cn.leetcode.map;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/7/1
 * @Description:
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *
 * 算法推导
 * 如何得到这样的排列顺序？这是本文的重点。我们可以这样来分析：
 *
 * 我们希望下一个数比当前数大，这样才满足“下一个排列”的定义。因此只需要将后面的「大数」与前面的「小数」交换，就能得到一个更大的数。比如 123456，将 5 和 6 交换就能得到一个更大的数 123465。
 * 我们还希望下一个数增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。为了满足这个要求，我们需要：
 * 在尽可能靠右的低位进行交换，需要从后向前查找
 * 将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
 * 将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排列。以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 之后的数重置为升序，得到 123546。显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
 * 以上就是求“下一个排列”的分析过程。
 *
 * 算法过程
 * 标准的“下一个排列”算法可以描述为：
 *
 * 从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
 * 在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
 * 将 A[i] 与 A[k] 交换
 * 可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
 * 如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
 *
 *  1、从右往左，找num[i]<num[i+1]的值num[i]
 *  2、还是从右往左遍历，找num[i]<num[j]的值num[j]，将num[i]和num[j]做交换
 *  3、对(i+1,size-1)区间值做升序排序
 */
public class SubjectBfs {

    public static void main(String[] args) {

    }
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i+1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums,i,j);
        }
        //对（i+1，size-1）区间值做升序排序
        reverse(nums,i+1);
    }

    public static void swap(int[] nums,int start,int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    public static void reverse(int[] nums,int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums,left,right);
            left++;
            right--;
        }
    }
}
