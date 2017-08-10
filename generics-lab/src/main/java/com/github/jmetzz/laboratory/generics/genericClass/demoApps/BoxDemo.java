package com.github.jmetzz.laboratory.generics.genericClass.demoApps;

import com.github.jmetzz.laboratory.generics.genericClass.container.*;

import java.util.ArrayList;
import java.util.List;

public class BoxDemo {

    public static void main(String[] args) {


        // Unbounded but constrained to Number elements
        Container<Number> genericBox = new GenericBox<>(10, 20, 25, "Wood");
        genericBox.put(10);
        genericBox.put(15.0); // --- Double is-a Number relationship holds :)

        /* Compile time errors: */
        // --- Even though genericBox is unbounded, type check happens here.
        //     the object declaration states that this is a container of Number, not String
        // genericBox.put("Some string");


        /* Caveat !!! */
        // --- unbounded formal type parameter, results Object by erasure
        //     here the "public <U extends T> void inspect(U u)" method declaration is
        //     modified after erasure, resulting in U -> Object and T -> Object.
        ((Inspectable) genericBox).inspect(4.7);
        ((Inspectable) genericBox).inspect("some string"); // --- same as previous
        ((GenericBox) genericBox).inspect("some string");  // --- same as previous

        List<Container<Number>> listOfGenericBoxes = new ArrayList<>();
        BoxDemo.addBox(10, listOfGenericBoxes); // --- Integer is-a Number relationship holds :)
        BoxDemo.addBox(20.0, listOfGenericBoxes); // --- Double is-a Number relationship holds :)
        BoxDemo.addBox(30L, listOfGenericBoxes); // --- Long is-a Number relationship holds :)

        /* Compile time errors: */
        // --- Generic method with formal type parameters check:
        //      String is-a Number relationship does not hold :(
        //         BoxDemo.addBox("some string", listOfGenericBoxes);
        BoxDemo.outputBoxes(listOfGenericBoxes);

        System.out.println("---------------------------------------------------");

        // -----------------------------------------------------------
        // Check behaviour with bounded implementation
        // -----------------------------------------------------------
        Container<Number> numericBox = new NumericBox<>(10, 20, 25, "Iron");
        numericBox.put(10);
        numericBox.put(15.0); // --- Double is-a Number relationship holds :)

        ((Inspectable) numericBox).inspect(4.7);
        ((GenericBox) numericBox).inspect(4.7);
        ((NumericBox) numericBox).inspect(4.7);
        ((NumericBox) numericBox).inspect(2);

        /* Compile time errors: */
        // NumericBox formal type parameter is upper bounded by Number class
        //          ((NumericBox)numericBox).inspect("some string");


        /* Caveat !!! */
        /* Run time errors: */
        // java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Number
        // Inspectable interface is unbounded, thus inspect method replaces the formal argument type
        // by Object, which doesn't prevent the usage with non-numeric arguments.
        //          ((Inspectable)numericBox).inspect("some text"); // --- "some text" is not a Integer, so it is not ok :(

        List<Container<Number>> listOfGenericBoxes2 = new ArrayList<>();
        BoxDemo.addBox(10, listOfGenericBoxes2); // --- Integer is-a Number relationship holds :)
        BoxDemo.addBox(20.0, listOfGenericBoxes2); // --- Double is-a Number relationship holds :)
        BoxDemo.addBox(30L, listOfGenericBoxes2); // --- Long is-a Number relationship holds :)

        /* Compile time errors: */
        //         BoxDemo.addBox("some string", listOfGenericBoxes2); // --- String is-a Number relationship does not hold :(

        BoxDemo.outputBoxes(listOfGenericBoxes2); // --- Integer is-a Number relationship holds :)
        System.out.println("---------------------------------------------------");


        // -----------------------------------------------------------
        // Check behaviour with another bounded implementation
        // -----------------------------------------------------------
        Container<Integer> integerBox = new IntegerBox<>(10, 20, 25, "Steel");
        integerBox.put(10); // Ok, 10 is auto-boxed to Integer

         /* Compile time errors: */
        // --- Double is-a Number relationship holds, however, Double is-a Integer doesn't
        //     integerBox.put(15.0);

        ((Inspectable) integerBox).inspect(2);
        ((GenericBox) integerBox).inspect(2);
        ((IntegerBox) integerBox).inspect(new Integer(2));

        // Careful, IntegerBox is-a NumericBox relationship doesn't hold
        // java.lang.ClassCastException: IntegerBox cannot be cast to NumericBox
        //      ((NumericBox) integerBox).inspect(new Integer(2)); // ??

        /* Caveat !!! */
        /* Run time errors: */
        // java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Number
        // Inspectable interface is unbounded, thus inspect method replaces the formal argument type
        // by Object, which doesn't prevent the usage with non-numeric arguments.
        //      ((Inspectable)integerBox).inspect("some text");

        // java.lang.ClassCastException: java.lang.Double cannot be cast to java.lang.Integer
        //      ((Inspectable) integerBox).inspect(4.7); // --- Double is not a Integer, so it is not ok :(

        // java.lang.ClassCastException: java.lang.Short cannot be cast to java.lang.Integer
        //      ((Inspectable) integerBox).inspect(new Short((short) 4)); // --- Short is not a Integer, so it is not ok :(


        ArrayList<Container<Integer>> listOfIntegerBoxes = new ArrayList<>();

        BoxDemo.addBox(10, listOfIntegerBoxes);
        BoxDemo.addBox(20, listOfIntegerBoxes);
        BoxDemo.addBox(30, listOfIntegerBoxes);
        BoxDemo.outputBoxes(listOfIntegerBoxes);

        /* Compile time errors: */
        // --- compilation error,  incompatible bounds since 4.5 is not a Integer object as expected by the method formal parameter
        // observe that addBox(U u, List<Container<U>> boxes), therefore the first argument must have hold
        // the is-a property in relation to the underling Container
        // BoxDemo.addBox(4.5, listOfIntegerBoxes);

    }

    public static <U> void addBox(U u, List<Container<U>> boxes) {
        GenericBox<U> box = new GenericBox<>(1, 1, 1, "Default");
        box.put(u);
        boxes.add(box);
    }

    public static <U> void outputBoxes(List<Container<U>> boxes) {
        int counter = 0;

        for (Container<U> box : boxes) {
            System.out.println(box.getClass().getSimpleName() + "#" + counter + " is " + box + " with volume equals to " + box.volume());
            counter++;
        }
    }

}
