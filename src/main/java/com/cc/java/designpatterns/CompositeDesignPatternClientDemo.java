package com.cc.java.designpatterns;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cclaudiu Known as a "Structural" Design Pattern - for grouping large objects structures <br>
 *         GoF definition: <br>
 *         Allow you to compose objects into tree structures to represent part-whole hierarchies. Composite lets clients
 *         treat individual objects and compositions of objects uniformly.
 * 
 *         Any Object could be itself a composite in its own whole
 * 
 *         It's structure consist of a: Component Interface(or abstract class with some default behavior), where basic
 *         Operations are defined, a Leaf Concrete Class which represents a particular single-Object, and a "Composite"
 *         which consits of a collection of concrete/leafs Objects; each leaf/concrete Object can be replaced for a
 *         part-whole Composite Collection
 *
 *         The Client of the Pattern, usually uses only the "Component" Interface for structuring Objects
 *
 *         Usage: Hierarchical DataStructures,
 *         Allows us to treat nodes and leafs in the same manner
 */

// -- Component is the Entry point for the Composite DesignPattern
interface CompositeComponent {
    void push(CompositeComponent file);
    void pop(CompositeComponent file);
    void listIt();
    CompositeComponent get(int idx);
}

// -- Leaf Concrete Object, which might implement an Abstract Component for a default behavior defined
final class CompositeFile implements CompositeComponent {
    @Override
    public void listIt() {
        System.out.println("Listing Composite File..." + this);
    }

    @Override public void push(CompositeComponent file) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override public void pop(CompositeComponent file) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override public CompositeComponent get(int idx) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

// -- Node of the Structural Composite Tree, is interpreted as a part-whole, just like any Leaf Object, which consists
class CompositeDirectory implements CompositeComponent {

    private final List<CompositeComponent> composites = new ArrayList<>();

    @Override public void push(CompositeComponent file) {
        composites.add(file);
    }

    @Override public void pop(CompositeComponent file) {
        composites.remove(file);
    }

    @Override public void listIt() {
        for(CompositeComponent eachComponent: composites) {
            eachComponent.listIt();
        }
    }

    @Override public CompositeComponent get(int idx) {
        return composites.get(idx);
    }
}

public class CompositeDesignPatternClientDemo {

    public static void main(String[] args) {
        CompositeComponent compositeFile1 = new CompositeFile();
        CompositeComponent compositeFile2 = new CompositeFile();
        CompositeComponent compositeFile3 = new CompositeFile();

        CompositeComponent compositeDir1 = new CompositeDirectory();
        compositeDir1.push(compositeFile1);
        compositeDir1.push(compositeFile2);
        compositeDir1.push(compositeFile3);

        CompositeComponent compositeDir2 = new CompositeDirectory();
        compositeDir2.push(compositeFile3);

        CompositeComponent parentNode = new CompositeDirectory();
        parentNode.push(compositeFile1);
        parentNode.push(compositeDir1);
        parentNode.push(compositeDir2);

        parentNode.listIt();
    }
}
