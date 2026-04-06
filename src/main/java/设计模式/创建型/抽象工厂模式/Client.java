package 设计模式.创建型.抽象工厂模式;

// ---------- 抽象产品：CPU ----------
interface CPU {
    void process(); // CPU处理数据
}

// ---------- 抽象产品：内存 ----------
interface Memory {
    void store(); // 内存存储数据
}

// ---------- 具体产品：Windows CPU ----------
class WindowsCPU implements CPU {
    @Override
    public void process() {
        System.out.println("Windows CPU 正在处理任务");
    }
}

// ---------- 具体产品：Windows 内存 ----------
class WindowsMemory implements Memory {
    @Override
    public void store() {
        System.out.println("Windows 内存存储数据");
    }
}

// ---------- 具体产品：Mac CPU ----------
class MacCPU implements CPU {
    @Override
    public void process() {
        System.out.println("Mac CPU 正在处理任务");
    }
}

// ---------- 具体产品：Mac 内存 ----------
class MacMemory implements Memory {
    @Override
    public void store() {
        System.out.println("Mac 内存存储数据");
    }
}

// ---------- 抽象工厂：电脑组件工厂 ----------
interface ComputerFactory {
    CPU createCPU();      // 创建CPU
    Memory createMemory(); // 创建内存
}

// ---------- 具体工厂：Windows 组件工厂 ----------
class WindowsFactory implements ComputerFactory {
    @Override
    public CPU createCPU() {
        return new WindowsCPU(); // 生产 Windows CPU
    }
    @Override
    public Memory createMemory() {
        return new WindowsMemory(); // 生产 Windows 内存
    }
}

// ---------- 具体工厂：Mac 组件工厂 ----------
class MacFactory implements ComputerFactory {
    @Override
    public CPU createCPU() {
        return new MacCPU(); // 生产 Mac CPU
    }
    @Override
    public Memory createMemory() {
        return new MacMemory(); // 生产 Mac 内存
    }
}

// ---------- 客户端 ----------
public class Client {
    public static void main(String[] args) {

        ComputerFactory factory;
        CPU cpu;
        Memory memory;

        // 假设需要组装一台 Windows 电脑
        factory= new WindowsFactory();
        // 使用工厂创建一系列相关产品（CPU 和内存）
        cpu = factory.createCPU();
        memory= factory.createMemory();

        // 使用产品
        cpu.process();
        memory.store();

        System.out.println("--- 切换产品族 ---");

        // 如果需要 Mac 电脑，只需更换具体工厂
        factory = new MacFactory();
        // 使用工厂创建一系列相关产品（CPU 和内存）
        cpu = factory.createCPU();
        memory = factory.createMemory();

        // 使用产品
        cpu.process();
        memory.store();
    }
}