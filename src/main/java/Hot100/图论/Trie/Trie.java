package Hot100.图论.Trie;

class Trie {
    // 子节点数组，每个位置对应一个小写字母（a-z）
    // children[0] 对应 'a'，children[1] 对应 'b'，...，children[25] 对应 'z'
    Trie[] children;

    // 标记当前节点是否为某个单词的结束节点
    // true: 当前节点是一个完整单词的结尾
    // false: 当前节点只是某个单词的前缀
    boolean isEnd;

    // 初始化前缀树对象
    // 创建一个空的根节点，根节点不存储字符
    public Trie() {
        children = new Trie[26];  // 26个字母，所以数组大小为26
        isEnd = false;            // 根节点本身不是任何单词的结尾
    }

    // 向前缀树中插入字符串 word
    // 时间复杂度：O(n)，n为word的长度
    public void insert(String word) {
        Trie node = this;  // 从根节点开始

        // 遍历word中的每个字符
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);       // 获取当前字符
            int index = ch - 'a';           // 计算字符对应的数组索引，'a'->0, 'b'->1, ..., 'z'->25

            // 如果当前字符对应的子节点不存在，则创建新节点
            if(node.children[index] == null)
                node.children[index] = new Trie();

            // 移动到下一个节点（沿着字符路径向下）
            node = node.children[index];
        }

        // 遍历完所有字符后，标记最后一个节点为单词结尾
        node.isEnd = true;
    }

    // 查找当前字符串是否出现在前缀树中（必须是完整单词）
    // 返回true：word存在于前缀树中
    // 返回false：word不存在于前缀树中
    public boolean search(String word) {
        Trie node = searchPrefix(word);  // 先找到word对应的节点

        // 节点必须存在，并且该节点是某个单词的结尾
        return node != null && node.isEnd;
    }

    // 查看当前字符串是否是前缀树中某字符串的前缀
    // 返回true：前缀树中存在以prefix为前缀的单词
    // 返回false：前缀树中不存在以prefix为前缀的单词
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);  // 找到prefix对应的节点

        // 只要节点存在即可，不需要是单词结尾
        return node != null;
    }

    // 辅助方法：查找前缀对应的节点
    // 如果前缀存在，返回最后一个字符对应的节点
    // 如果前缀不存在，返回null
    private Trie searchPrefix(String prefix){
        Trie node = this;  // 从根节点开始

        // 沿着前缀路径向下查找
        for(int i = 0; i < prefix.length(); i++){
            char ch = prefix.charAt(i);
            int index = ch - 'a';

            // 如果路径中断（某个字符不存在），返回null
            if(node.children[index] == null)
                return null;

            // 继续向下查找
            node = node.children[index];
        }

        // 返回前缀对应的最后一个节点
        return node;
    }

    public static void main(String[] args) {

        Trie trie1 = new Trie();
        // 插入单词"apple"
        trie1.insert("apple");
        // 搜索存在的完整单词
        System.out.println(trie1.search("apple"));
        // 搜索不存在的完整单词
        System.out.println(trie1.search("app"));
        // 前缀查找
        System.out.println(trie1.startsWith("app"));
    }
}