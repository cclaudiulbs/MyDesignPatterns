package com.cc.java.stuff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cclaudiu
 */

abstract class MyExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyExecutor.class);

    /**
     * and the most important method -- execute() which body should contain the actual code that needs to be executed
     */
    abstract void execute();

    /**
     * have one method which uses a callback abstract method -- execute() to define an implementation on subclass
     */
    void executeWithRetries() {
        boolean operationSucceed = false;
        while (!operationSucceed) {
            try {
                Thread.sleep(400);
                execute();
                operationSucceed = true;
            } catch (InterruptedException e) {
                LOGGER.debug("Thread Interrupted, exception stack is {}", e);
            }
        }
    }
}

public class CallbackAbstractionDemo {

    private static void aComplexOperationThatShouldAlignWithTheSRP() {
        System.out.println("complex method which logic should not contain the retrying mechanism in it but should be decoupled in other class -- using callbacks on abstract methods");
    }

    public static void main(String[] args) {
        new MyExecutor() {
            @Override public void execute() {
                aComplexOperationThatShouldAlignWithTheSRP();
            }
        }.executeWithRetries();
    }
}
