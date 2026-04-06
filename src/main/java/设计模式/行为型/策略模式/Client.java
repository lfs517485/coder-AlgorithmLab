package 设计模式.行为型.策略模式;

// 支付策略接口
interface PaymentStrategy {
    void pay(double amount);
}

// 支付宝支付策略
class AlipayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        // 模拟调用支付宝API
        System.out.println("使用支付宝支付：" + amount + "元");
        // 实际业务中会调用支付宝SDK
    }
}

// 微信支付策略
class WechatPayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        // 模拟调用微信支付API
        System.out.println("使用微信支付：" + amount + "元");
    }
}

// 银联支付策略
class UnionPayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        // 模拟调用银联支付API
        System.out.println("使用银联支付：" + amount + "元");
    }
}

// 支付上下文，用于持有策略并执行支付
class PaymentContext {
    private PaymentStrategy strategy;

    // 可以通过setter设置策略
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    // 执行支付，委托给当前策略
    public void executePayment(double amount) {
        if (strategy == null) {
            throw new IllegalStateException("支付策略未设置");
        }
        strategy.pay(amount);
    }
}

// 客户端使用示例
public class Client {
    public static void main(String[] args) {
        // 创建支付上下文，并设置初始策略为支付宝
        PaymentContext context = new PaymentContext();

        // 动态切换策略为支付宝支付
        context.setStrategy(new AlipayStrategy());
        context.executePayment(100.0); // 输出：使用支付宝支付：100.0元

        // 动态切换策略为微信支付
        context.setStrategy(new WechatPayStrategy());
        context.executePayment(200.0); // 输出：使用微信支付：200.0元

        // 再切换为银联支付
        context.setStrategy(new UnionPayStrategy());
        context.executePayment(300.0); // 输出：使用银联支付：300.0元
    }
}