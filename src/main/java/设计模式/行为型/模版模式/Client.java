package 设计模式.行为型.模版模式;

// 抽象类：饮料制作器
abstract class BeverageMaker {
    // 模板方法：定义制作饮料的算法骨架（固定步骤）
    public final void makeBeverage() {
        // 1. 烧水（公共步骤）
        boilWater();
        // 2. 冲泡（抽象方法，由子类实现）
        brew();
        // 3. 倒入杯中（公共步骤）
        pourInCup();
        // 4. 添加调料（钩子方法，子类可选择性实现）
        if (customerWantsCondiments()) {
            addCondiments();
        }
        // 5. 完成提示（公共步骤）
        finish();
    }

    // 公共具体方法：所有子类共享
    private void boilWater() {
        System.out.println("烧开水");
    }

    private void pourInCup() {
        System.out.println("将饮料倒入杯中");
    }

    private void finish() {
        System.out.println("您的饮料已制作完成，请享用！");
    }

    // 抽象方法：子类必须实现
    protected abstract void brew();

    // 抽象方法：子类必须实现（如果有调料的话）
    protected abstract void addCondiments();

    // 钩子方法：子类可重写以决定是否添加调料，默认返回true
    // 允许子类控制算法的某个部分（本例中决定是否加调料），默认实现返回true。
    protected boolean customerWantsCondiments() {
        return true; // 默认需要调料
    }
}

// 具体子类：咖啡制作器
class CoffeeMaker extends BeverageMaker {
    @Override
    protected void brew() {
        System.out.println("用热水冲泡咖啡粉");
    }

    @Override
    protected void addCondiments() {
        System.out.println("加入糖和牛奶");
    }

    // 可以选择重写钩子方法，例如不想加调料时返回false
    // 这里保持默认true
}

// 具体子类：茶制作器
class TeaMaker extends BeverageMaker {
    @Override
    protected void brew() {
        System.out.println("用热水浸泡茶叶");
    }

    @Override
    protected void addCondiments() {
        System.out.println("加入柠檬片");
    }

    // 重写钩子方法，表示不想加调料
    @Override
    protected boolean customerWantsCondiments() {
        return false; // 茶不加调料
    }
}

// 客户端使用
public class Client {
    public static void main(String[] args) {
        System.out.println("--- 制作咖啡 ---");
        BeverageMaker coffee = new CoffeeMaker();
        coffee.makeBeverage(); // 执行模板方法

        System.out.println("\n--- 制作茶 ---");
        BeverageMaker tea = new TeaMaker();
        tea.makeBeverage();// 执行模板方法
    }
}