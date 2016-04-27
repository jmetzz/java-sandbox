package com.github.jmetzz.genericClass.container;

import java.util.ArrayList;
import java.util.List;

public class BoxDemo {

    public static void main(String[] args) {

        IntegerBox<Integer> integerBox = new IntegerBox<>(10, 20, 25, "Wood");
        integerBox.put(10);

        integerBox.instpect(4.7); // --- Double is a Number, so it is ok :)
        // --- compilation error, "some text" is not a number as expected by the method formal parameter
        //integerBox.inspect("some text");

        ArrayList<Container<Integer>> listOfIntegerBoxes = new ArrayList<>();

        BoxDemo.addIntegerBox(10, listOfIntegerBoxes);
        BoxDemo.addIntegerBox(20, listOfIntegerBoxes);
        BoxDemo.addIntegerBox(30, listOfIntegerBoxes);
        BoxDemo.outputBoxes(listOfIntegerBoxes);

        // --- compilation error, since 4.5 is not a Integer object as expected by the method formal parameter
        // observe that addIntegerBox(U u, List<AbstractBox<U>> boxes), therefore the first argument must have the same
        // type as the underling elements in the given box
        // BoxDemo.addIntegerBox(4.5, listOfIntegerBoxes);

    }

    public static <U extends Integer> void addIntegerBox(U u, List<Container<U>> boxes) {
        IntegerBox<U> box = new IntegerBox<>(1, 1, 1, "Default");
        box.put(u);
        boxes.add(box);
    }

    public static <U> void outputBoxes(List<Container<U>> boxes) {
        int counter = 0;

        for (Container<U> box : boxes) {
//            System.out.println(box.getClass().getSimpleName() + "#" + counter + " contains [" + box.get().toString() + "]");
            System.out.println(box.getClass().getSimpleName() + "#" + counter + " is " + box + " with volume equals to " + box.volume());
            counter++;
        }
    }

}
