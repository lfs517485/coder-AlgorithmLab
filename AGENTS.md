# AGENTS.md

## 编译运行

```bash
# 编译项目
mvn compile

# 运行指定类（调整路径）
mvn exec:java -Dexec.mainClass="cn.bugstack.Main"

# 使用 java 直接运行
cd target/classes && java cn.bugstack.Main
```

## 项目结构

- `src/main/java/` - 源代码
- `Hot100/` - LeetCode Hot100 题目解答（链表、二叉树、动态规划、贪心等）
- `设计模式/` - 设计模式示例


## 注意事项

- 需要 Java 16+（pom.xml 中指定了 source/target 为 16）
- 没有测试框架 - 通过 main 方法手动测试
- 大多数文件是独立的 LeetCode 解答类（类之间无依赖）
