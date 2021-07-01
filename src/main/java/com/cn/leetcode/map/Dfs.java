package com.cn.leetcode.map;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/7/1
 * @Description:
 * 深度优先搜索
 */
public class Dfs {
    static List<List<Integer>> nodes = new ArrayList<>();
    static int ways = 0,k = 3,n = 5;
    public static void main(String[] args) {
        int[][] relations = {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        int result = numWaysDfs(5,relations,3);
        System.out.println(result);
    }
    /**
     * 深度优先搜索
     * 对于图搜索算法来说，它的数据结构都是一样的，即二维数组
     * 从根结点搜索最近的结点，沿着这条边往下搜索直到没有结点为止
     */
    public static int numWaysDfs(int _n,int[][] relations,int _k) {
        n = _n;
        k=_k;
        for (int i = 0; i < n; i++) {
            nodes.add(new ArrayList<>(0));
        }
        //把节点信息放入队列
        for (int[] nums : relations) {
            nodes.get(nums[0]).add(nums[1]);
        }
        dfs(0,0);
        return ways;
    }

    //深度优先搜索
    public static void dfs(int nodeValue,int stepCount) {
        if (stepCount == k) {
            if (nodeValue == n-1) {
                ways++;
            }
            return;
        }
        List<Integer> es = nodes.get(nodeValue);
        if (es.isEmpty()) return;
        for (int next: es) {
            dfs(next,stepCount+1);
        }
    }
}
