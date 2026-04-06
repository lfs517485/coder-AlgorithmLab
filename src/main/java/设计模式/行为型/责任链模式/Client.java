package 设计模式.行为型.责任链模式;

/**
 * 抽象处理者（Handler）
 * 定义了处理请求的接口，并持有下一个处理者的引用
 */
abstract class Approver {
    // 下一个处理者（后继者），形成链的关键
    protected Approver nextApprover;

    /**
     * 设置下一个处理者
     * @param next 下一个处理者对象
     */
    public void setNext(Approver next) {
        this.nextApprover = next;
    }

    /**
     * 处理采购请求的抽象方法
     * 具体子类实现自己的处理逻辑
     * @param request 采购请求对象
     */
    public abstract void processRequest(PurchaseRequest request);
}

/**
 * 具体处理者：经理
 * 审批额度：<= 1000
 * 若超出权限，则将请求转发给下一个处理者
 */
class Manager extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        // 判断当前处理者是否有权处理
        if (request.getAmount() <= 1000) {
            System.out.println("经理审批通过，金额：" + request.getAmount());
        } else {
            // 无权处理，检查是否存在下一个处理者，存在则转发
            if (nextApprover != null) {
                nextApprover.processRequest(request);  // 请求沿链传递
            }
            // 若没有下一个处理者，可在此处理默认逻辑（如抛异常）
        }
    }
}

/**
 * 具体处理者：总监
 * 审批额度：<= 5000
 * 超出权限则转发
 */
class Director extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() <= 5000) {
            System.out.println("总监审批通过，金额：" + request.getAmount());
        } else {
            if (nextApprover != null) {
                nextApprover.processRequest(request);  // 转发给上级（CEO）
            }
        }
    }
}

/**
 * 具体处理者：CEO
 * 审批额度：无限制，链的终点
 */
class CEO extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        // CEO拥有最高权限，直接处理所有请求，不再转发
        System.out.println("CEO审批通过，金额：" + request.getAmount());
    }
}

/**
 * 请求类（PurchaseRequest）
 * 封装了请求的具体数据（如采购金额）
 */
class PurchaseRequest {
    private double amount;  // 采购金额

    public PurchaseRequest(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

/**
 * 客户端
 * 负责组装责任链，并提交请求
 */
public class Client {
    public static void main(String[] args) {
        // 1. 创建处理者对象
        Approver manager = new Manager();   // 经理
        Approver director = new Director(); // 总监
        Approver ceo = new CEO();           // CEO

        // 2. 组装责任链：经理 -> 总监 -> CEO
        // 设置每个处理者的下一个处理者，形成链式结构
        manager.setNext(director);   // 经理的上司是总监
        director.setNext(ceo);       // 总监的上司是CEO

        // 3. 创建请求并提交
        // 请求将从链的第一个节点（manager）开始处理
        PurchaseRequest request1 = new PurchaseRequest(2000); // 金额2000
        System.out.println("--- 处理金额2000的请求 ---");
        manager.processRequest(request1);  // 应由总监处理

        PurchaseRequest request2 = new PurchaseRequest(8000); // 金额8000
        System.out.println("--- 处理金额8000的请求 ---");
        manager.processRequest(request2);  // 应由CEO处理
    }
}