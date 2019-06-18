package com.interview.adv.designpattern;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/18 09:40
 * @Description:
 */
public class FactoryPattern {
    public static void main(String[] args) {
        // 抽象工厂模式使用demo
        //Provider provider = new SendMailFactory();
        //Sender mailSender = provider.produce();
        //mailSender.send();
        //provider = new SendSmsFactory();
        //Sender smsSender = provider.produce();
        //smsSender.send();

        // 建造者模式使用demo
        //Builder builder = new Builder();
        //builder.produceMailSender(10);

        // 类适配器模式demo
        Targetable adapter = new Adapter();
        adapter.method1();
        adapter.method2();


        // 对象适配器模式demo
        Targetable target = new Wrapper(new Source());
        target.method1();
        target.method2();

        // 装饰器模式 demo
        Sourceable obj = new Decorator(new Source2());
        obj.method();

        // 策略模式demo
        String exp = "2+8";
        ICalculator cal = new Plus();
        int result = cal.calculate(exp);
        System.out.println(result);

        // 观察者模式
        Subject sub = new MySubject();
        sub.add(new Observer1());
        sub.add(new Observer2());
        sub.operation();
    }

}


/**
 * 普通工厂模式
 */
class SendFactoryA {
    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }
}

/**
 * 多个工厂方法模式
 */
class SendFactoryB {
    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }
}

/**
 * 静态工厂方法模式
 */
class SendFactoryC {
    public static Sender produceMail() {
        return new MailSender();
    }

    public static Sender produceSms() {
        return new SmsSender();
    }
}

/*------------------抽象工厂模式-------------------------------------*/
class SendSmsFactory implements Provider {

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}

class SendMailFactory implements Provider {

    @Override
    public Sender produce() {
        return new MailSender();
    }
}

/*----------------------------------------------------------------*/

/**
 * 建造者模式
 */
class Builder {
    private List<Sender> list = new ArrayList<>();

    public void produceMailSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());
        }
    }
}

/*------------------适配器模式------------------------------------*/
/* 1.类的适配器模式 ****************/
class Source {
    public void method1() {
        System.out.println("this is original method!");
    }
}

interface Targetable {
    // 与原类中的方法相同
    void method1();

    // 新类的方法
    void method2();
}

class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}

/**********************************/

/* 2.类的适配器模式 ****************/
class Wrapper implements Targetable {
    private Source source;

    public Wrapper(Source source) {
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();

    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}

/*---------------------------------------------------------------*/


/*--------------------装饰器模式-----------------------------------*/
interface Sourceable {
    void method();
}

class Source2 implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}

class Decorator implements Sourceable {
    private Sourceable source;

    public Decorator(Sourceable source) {
        this.source = source;
    }

    @Override
    public void method() {
        System.out.println("before decorator!");
        source.method();
        System.out.println("after decorator!");
    }
}

/*---------------------------------------------------------*/

/*-----------------------策略模式-----------------------------*/
interface ICalculator {
    int calculate(String exp);
}

class Minus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "-");
        return arrayInt[0] - arrayInt[1];
    }
}

class Plus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "\\+");
        return arrayInt[0] + arrayInt[1];
    }
}

class AbstractCalculator {
    public int[] split(String exp, String opt) {
        String[] array = exp.split(opt);
        int[] arrayInt = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}
/*---------------------------------------------------------*/

/*-----------------------观察者模式---------------------------*/
interface Observer{
    void update();
}

class Observer1 implements Observer{

    @Override
    public void update() {
        System.out.println("observer1 has received!");
    }
}

class Observer2 implements Observer{

    @Override
    public void update() {
        System.out.println("observer2 has received!");
    }
}

interface Subject{
    // 增加观察者
    void add(Observer observer);
    // 删除观察者
    void del(Observer observer);
    // 通知所有观察者
    void notifyObservers();
    // 自身的操作
    void operation();

}

abstract class AbstractSubject implements Subject{
    private Vector<Observer> vector = new Vector<>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();
        while(enumo.hasMoreElements()){
            enumo.nextElement().update();
        }
    }
}

class MySubject extends AbstractSubject{

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }
}

/*---------------------------------------------------------*/


class MailSender implements Sender {

    @Override
    public void send() {
        System.out.println("this is mail sender!");
    }
}

class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("this is sms sender!");
    }
}

interface Provider {
    public Sender produce();
}

interface Sender {
    void send();
}
