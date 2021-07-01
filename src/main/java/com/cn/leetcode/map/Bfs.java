package com.cn.leetcode.map;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/7/1
 * @Description:
 * 广度优先遍历
 * (1) 找出当前顶点的所有邻接点。如果有哪个是没访问过的，就把它标为“已访问”，并且将
 * 它入队。(尽管该顶点并未作为“当前顶点”被访问过。)
 * (2) 如果当前顶点没有未访问的邻接点，且队列不为空，那就再从队列中移出一个顶点作为当前定点
 * (3) 如果当前顶点没有未访问的邻接点，且队列里也没有其他顶点，那么算法完成
 *
 *
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 *
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 *
 * 示例 1：
 *
 * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 *
 * 输出：3
 *
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 *
 * 示例 2：
 *
 * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
 *
 * 输出：0
 *
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 *
 */
public class Bfs {
    public static void main(String[] args) {
        int n=5,k=3;
        int[][] relations = {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        int result = numWaysBfs(n,relations,k);
        System.out.println(result);
    }

    /**
     * 广度优先遍历的思想：
     *  1、起始点，放入队列
     *  2、当前顶点出列，寻找邻接点并放入队列
     *  3、递归2的步骤，直到第k层
     */
    public static int numWaysBfs(int n,int[][] relations,int k) {
        List<List<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new ArrayList<>(0));
        }
        //把节点信息放入队列
        for (int[] nums : relations) {
            nodes.get(nums[0]).add(nums[1]);
        }
        //遍历队列
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        queue.offer(0);
        int steps = 0;
        while (!queue.isEmpty() && steps < k) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int pollValue = queue.poll();
                List<Integer> subNodes = nodes.get(pollValue);
                if (subNodes.isEmpty()) continue;
                for (int node: subNodes) {
                    queue.offer(node);
                }
            }
        }
        int ways = 0;
        if (steps == k) {
            while (!queue.isEmpty()) {
                if (queue.poll() == n-1) {
                    ways++;
                }
            }
        }
        return ways;
    }


}
