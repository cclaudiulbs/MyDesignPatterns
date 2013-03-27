package com.cc.java.designpatterns;

/**
 * @author cclaudiu
 * Client ---> Context-----> Strategy
 *                               |---------> Concrete Strategy 1
 *                               |---------> Concrete Strategy 2
 *
 * The EntryPoint is the Strategy Interface which defines a generic method
 * There are concrete Strategy implementations for each particular behavior
 */

interface MathOperationStrategy {
    int doOperation(Numbers numbers);
}

class AddOperationStrategyImpl implements MathOperationStrategy {

    @Override
    public int doOperation(Numbers numbers) {
        int result = numbers.leftHandOperand + numbers.rightHandOperand;
        System.out.println("Result of add: " + numbers.leftHandOperand + " + " + numbers.rightHandOperand + "=" + result);
        return result;
    }
}

class MinusOperationStrategyImpl implements MathOperationStrategy {

    @Override
    public int doOperation(Numbers numbers) {
        int result = numbers.leftHandOperand - numbers.rightHandOperand;
        System.out.println("Result of minus: " + numbers.leftHandOperand + " - " + numbers.rightHandOperand + "=" + result);
        return result;
    }
}

// --------- Wrapper class(No Strategy Pattern specific) ----------//
class Numbers {
    final int leftHandOperand;
    final int rightHandOperand;

    Numbers(int leftHandOperand, int rightHandOperand) {
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
    }
}

/*
 * The Strategy Context decides which implementation to be called on runtime
 * Composition of a Strategy Interface
 */
class StrategyContext {
    private final MathOperationStrategy mathStrategy;

    StrategyContext(MathOperationStrategy mathStrategy) {
        this.mathStrategy = mathStrategy;
    }

    int executeStrategy(Numbers numbers) {
        System.out.println("Invoked by the Client: Client---> Context---> request Strategy");
        return mathStrategy.doOperation(numbers);
    }
}

/*
 * The Client does NOT invoke directly the Strategy! but through the Context, which last calls the coresponding
 * Runtime Strategy based on the instance of the Strategy
 */
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Numbers numbers = new Numbers(10, 5);
        StrategyContext addContext = new StrategyContext(new AddOperationStrategyImpl());
        StrategyContext minusContext = new StrategyContext(new MinusOperationStrategyImpl());

        addContext.executeStrategy(numbers);
        minusContext.executeStrategy(numbers);
    }
}