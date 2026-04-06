package Hot100.图论.canFinish;

import java.util.*;

class Solution {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 邻接表：edges[i] 存储课程i的所有后续课程（即学完课程i后可以学的课程）
        List<List<Integer>> edges = new ArrayList<>();

        // indeg[i] 记录课程i的入度（有多少门先修课程需要先学完）
        int[] indeg = new int[numCourses];

        // 队列：用于BFS遍历，存储当前入度为0的课程（可以立即学习的课程）
        LinkedList<Integer> queue = new LinkedList<>();

        // 1. 初始化邻接表
        for (int i = 0; i < numCourses; i++)
            edges.add(new ArrayList<>()); // 为每门课程创建空列表

        // 填充邻接表和入度数组
        for (int[] info : prerequisites) {
            int course = info[0];      // 当前课程
            int prereq = info[1];      // 先修课程

            // 添加边：先修课程 -> 当前课程
            // 表示需要先学完prereq才能学course
            edges.get(prereq).add(course);

            // 当前课程的入度+1（多了一个前置要求）
            indeg[course]++;
        }

        // 2. 初始化队列：将所有入度为0的课程加入队列
        // 这些课程没有先修要求，可以直接学习
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0)
                queue.addLast(i); // 将第i个课程加入队列
        }

        // 3. BFS拓扑排序
        int cnt = 0; // 记录已经成功学习的课程数量
        while (!queue.isEmpty()) {
            // 取出一个可以学习的课程
            int current = queue.removeFirst();
            cnt++; // 成功学习一门课程

            // 遍历当前课程的所有后续课程
            for (int nextCourse : edges.get(current)) {
                // 当前课程学完后，后续课程的入度减1
                indeg[nextCourse]--;

                // 如果后续课程的入度变为0，表示所有先修课程都已学完
                // 可以加入队列准备学习
                if (indeg[nextCourse] == 0)
                    queue.addLast(nextCourse);
            }
        }

        // 判断：如果成功学习的课程数等于总课程数，说明可以完成所有课程
        // 否则说明存在环（有些课程的先修要求相互依赖，无法满足）
        return cnt == numCourses;
    }

    // 主方法，用于测试图片中的用例
    public static void main(String[] args) {

        // 图片中的测试用例
        int numCourses = 2;
        int[][] prerequisites = {
                {1, 0},  // 学习课程1之前，需要先完成课程0
                {0, 1}   // 学习课程0之前，需要先完成课程1
        };

        System.out.println("输入：numCourses = " + numCourses +
                ", prerequisites = " + Arrays.deepToString(prerequisites));

        boolean result = canFinish(numCourses, prerequisites);

        System.out.println("输出：" + result);


    }
}