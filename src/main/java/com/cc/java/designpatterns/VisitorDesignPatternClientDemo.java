package com.cc.java.designpatterns;

/**
 * @author cclaudiu
 *
 */

// -- The entry point of any Visitor Design Pattern, defines an overloaded method foreach Type of Visitable
interface Visiter {
    double visit(Food food);

    double visit(Electronics electronics);
}

// -- The Visiter Impl defines the fully decoupled mechanism for operating each Type of Concrete Object
class VisiterImpl implements Visiter {

    @Override public double visit(Food food) {
        double finalPrice = food.getCost() * 1.17;
            System.out.println("Food without VAT=" + food.getCost() + ", Final Price=" + finalPrice);
        return finalPrice;
    }

    @Override public double visit(Electronics electronics) {
        double finalPrice = electronics.getCost() * 1.24;
            System.out.println("Electronics without VAT=" + electronics.getCost() + ", Final Price=" + finalPrice);
        return finalPrice;
    }
}

// -- the Visitable accepts a Visitor for which depending on the Runtime Instance Type the corresponding overloaded method is called
interface Visitable {
    void accept(Visiter visiter);
    double getCost();
}

// -- The "Food" is fully decoupled from how the taxes are calculated, it has only a price
class Food implements Visitable {
    private final double price;

    Food(double price) {
        this.price = price;
    }

    @Override public void accept(Visiter visiter) {
        visiter.visit(this);
    }

    @Override public double getCost() {
        return price;
    }
}

// -- The product "Electronics" is fully decoupled from how the taxes are calculated, it has only a price
class Electronics implements Visitable {
    private final double price;

    Electronics(double price) {
        this.price = price;
    }

    @Override public void accept(Visiter visiter) {
        visiter.visit(this);
    }

    @Override public double getCost() {
        return price;
    }
}

// -- Visitor Client, which performs the actual operation(could be the man from the cashier)
public class VisitorDesignPatternClientDemo {

    public static void main(String[] args) {
        Visiter scanner = new VisiterImpl();
        Food food = new Food(123.123d);
        Electronics electronics = new Electronics(443.45d);

        food.accept(scanner);
        electronics.accept(scanner);
    }
}