package 设计模式.结构型.组合模式;

import java.util.ArrayList;
import java.util.List;

// 抽象组件（Component）
abstract class FileSystemNode {
    protected String name;

    public FileSystemNode(String name) {
        this.name = name;
    }

    // 显示节点信息（公共接口）
    public abstract void display(String prefix);

    // 组合节点需要的方法，叶子节点可以不实现或抛出异常
    public void add(FileSystemNode node) {
        throw new UnsupportedOperationException("不支持添加操作");
    }
}

// 叶子节点：文件
class File extends FileSystemNode {
    private long size; // 文件大小

    public File(String name, long size) {
        super(name);
        this.size = size;
    }

    @Override
    public void display(String prefix) {
        System.out.println(prefix + "文件：" + name + " (" + size + "字节)");
    }
}

// 组合节点：文件夹
class Folder extends FileSystemNode {
    private List<FileSystemNode> children = new ArrayList<>(); // 子节点集合

    public Folder(String name) {
        super(name);
    }

    @Override
    public void display(String prefix) {
        System.out.println(prefix + "文件夹：" + name);
        // 递归显示所有子节点
        for (FileSystemNode node : children) {
            node.display(prefix + "  "); // 增加缩进
        }
    }

    // 重写添加方法
    @Override
    public void add(FileSystemNode node) {
        children.add(node);
    }
}

// 客户端使用
public class Client {
    public static void main(String[] args) {
        // 创建文件节点
        File file1 = new File("document.txt", 1);
        File file2 = new File("image.jpg", 2);
        File file3 = new File("video.mp4", 3);

        // 创建文件夹节点
        Folder root = new Folder("root");
        Folder subFolder = new Folder("subFolder");

        // 组装树形结构
        root.add(file1);
        root.add(subFolder);
        subFolder.add(file2);
        subFolder.add(file3);

        // 客户端统一调用
        System.out.println("文件系统结构：");
        root.display(""); // 输出树形结构
    }
}