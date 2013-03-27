package com.cc.java.designpatterns;

import java.util.Arrays;
import java.util.List;

/**
 * @author cclaudiu
 * <br/></br>
 * Implementation for the Visitor Design Pattern
 * A real-life example is taken in consideration
 * The visitor DP, is having an entry-point Interface, where there
 * are overloaded methods, for each concrete Element ToBeVisited;
 * The Visitor Implementation, hence is a flat class implementing the
 * operations needed to be processed by the Visitor;
 * From GoF:
 * Provide a mechanism for a set of operations to be applied on one or more
 * elements/items, while decoupling the Operation to be applied from the Element;
 *
 */

interface TaxiVisitor {
    double visit(EuropeanPerson europeanPerson);
    double visit(AsianPerson europeanPerson);
}

class TaxiVisitorImpl implements TaxiVisitor {
    private static final double EUROPE_PERCENT = 13;
    private static final double ASIAN_PERCENT = 23;

    @Override
    public double visit(EuropeanPerson europeanPerson) {
        return europeanPerson.getPayment() + (EUROPE_PERCENT / 100 * europeanPerson.getPayment());
    }

    @Override
    public double visit(AsianPerson asianPerson) {
        return asianPerson.getPayment() + (ASIAN_PERCENT / 100 * asianPerson.getPayment());
    }
    // ................ and so on .............. //
}

interface Person {
    void accept(TaxiVisitor taxiVisitor);
}

class EuropeanPerson implements Person {
    private double payment;

    public EuropeanPerson(double payment) {
        this.payment = payment;
    }

    @Override
    public void accept(TaxiVisitor taxiVisitor) {
        taxiVisitor.visit(this);
    }

    public double getPayment() {
        return payment;
    }
}

class AsianPerson implements Person {
    private final double payment;

    public AsianPerson(double payment) {
        this.payment = payment;
    }

    @Override
    public void accept(TaxiVisitor taxiVisitor) {
        taxiVisitor.visit(this);
    }

    public double getPayment() {
        return payment;
    }
}

public class VisitorPatternDemo {
    static final List<EuropeanPerson> EUROPEAN_PERSONS = Arrays.asList(new EuropeanPerson(139d), new EuropeanPerson(111d));
    static final List<AsianPerson> ASIAN_PERSONS = Arrays.asList(new AsianPerson(33d), new AsianPerson(55d));
    static final TaxiVisitor VISITOR = new TaxiVisitorImpl();

    // ----- Handy Methods for calculating for applying different taxes on a a set of persons -------//
    static void applyEuropeTaxes(TaxiVisitor visitor) {
        for(EuropeanPerson european: EUROPEAN_PERSONS) {
            double percent = visitor.visit(european);

            System.out.println("Taxi Europe Payment=" + percent);

        }
    }

    static void applyAsiaTaxes(TaxiVisitor visitor) {
        for(AsianPerson asian: ASIAN_PERSONS) {
            double percent = visitor.visit(asian);

            System.out.println("Taxi Asia Payment=" + percent);
        }
    }

    public static void main(String[] args) {
        applyEuropeTaxes(VISITOR);
        applyAsiaTaxes(VISITOR);
    }
}