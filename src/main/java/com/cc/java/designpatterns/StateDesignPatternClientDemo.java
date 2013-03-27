package com.cc.java.designpatterns;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cclaudiu
 * 
 *         Allows for Objects to behave differently depending on their internal State, when for eg. we want our class to
 *         behave differently and perform different computations depending on the arguments passed via Constructor
 *
 * Known as a "Behavioral" Design pattern along with the "Strategy" to quote from GoF:
 * "allows an Object to alter its behavior when its internal state changes"
 * State Design Pattern UML is very similar to the Strategy Design Pattern in that:
 * -- we have a "Context" ----> Interface "State" |
 *                                                +----> Concrete State 1
 *                                                +----> Concrete State 2
 *
 * "State" is similar to "Strategy", except that changes happen at Runtime, rather than letting the client decide which
 * strategy to call; States save us from lots of conditional statements;
 *
 * We should use this pattern when the behavior of an Object is influenced by its state
 * In short: the Context HAS-A composition to the "State" Interface, which defines a method which acccepts as argument
 * a "Context"; Now each concrete implementor of the "State" is calling on the "Context" the "State" Implementer
 */

interface State {
    void doProcess(Context context);
}

final class StateImpl implements State {

    @Override public void doProcess(Context context) {
        context.setState(new StateImpl2());
    }

    @Override public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

final class StateImpl2 implements State {

    @Override public void doProcess(Context context) {
        context.setState(new StateImpl());
    }

    @Override public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

// -- The Context has a reference-composition of the State Interface
final class Context {
    private State state;

    public Context(State state) {
        this.state = state;

        System.out.println("Setting initial state to=" + state);
    }

    // -- allows changing the internal state of the Object when State changes
    public void setState(State newState) {
        this.state = newState;

        System.out.println("State Changed on runtime; new state=" + state);
    }
}

public class StateDesignPatternClientDemo {
    public static void main(String[] args) {
        final State stateOne = new StateImpl();
        final State stateTwo = new StateImpl2();

        final Context context = new Context(stateOne);

        stateOne.doProcess(context);
        stateTwo.doProcess(context);
    }
}
