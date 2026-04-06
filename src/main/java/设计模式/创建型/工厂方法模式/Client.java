package 设计模式.创建型.工厂方法模式;

// ---------- 产品接口 ----------
interface Car {
    void drive(); // 所有汽车都能驾驶
}

// ---------- 具体产品：轿车 ----------
class Sedan implements Car {
    @Override
    public void drive() {
        System.out.println("驾驶轿车，平稳舒适");
    }
}

// ---------- 具体产品：SUV ----------
class SUV implements Car {
    @Override
    public void drive() {
        System.out.println("驾驶SUV，越野能力强");
    }
}

// ---------- 抽象工厂：汽车工厂 ----------
abstract class CarFactory {
    // 工厂方法——由子类实现具体产品的创建
    public abstract Car createCar();
}

// ---------- 具体工厂：轿车工厂 ----------
class SedanFactory extends CarFactory {
    @Override
    public Car createCar() {
        return new Sedan(); // 生产轿车
    }
}

// ---------- 具体工厂：SUV工厂 ----------
class SUVFactory extends CarFactory {
    @Override
    public Car createCar() {
        return new SUV(); // 生产SUV
    }
}

// ---------- 客户端 ----------
public class Client {
    public static void main(String[] args) {

        CarFactory factory;

        // 需要轿车
        factory= new SedanFactory();
        Car car = factory.createCar(); // 通过工厂方法获得轿车
        car.drive(); // 输出：驾驶轿车，平稳舒适

        // 需要SUV
        factory = new SUVFactory();
        car = factory.createCar(); // 通过工厂方法获得SUV
        car.drive(); // 输出：驾驶SUV，越野能力强
    }
}