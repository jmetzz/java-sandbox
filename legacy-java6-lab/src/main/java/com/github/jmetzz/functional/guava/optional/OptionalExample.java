package com.github.jmetzz.functional.guava.optional;

import com.github.jmetzz.functional.guava.pojos.InventoryOrder;
import com.google.common.base.Optional;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exi853 on 06/04/2016.
 */
public class OptionalExample {


    public static void main(String[] args) {
        List<InventoryOrder> list = new ArrayList<InventoryOrder>();

        list.add(null);

        String str = "Yes";
        System.out.println(Optional.fromNullable(str).or("Nope"));

        Card c = new Card("1111111", null);
        System.out.println(" ---- ");
        System.out.println(Optional.fromNullable(c.getNumber()).or("Null number"));
        System.out.println(Optional.fromNullable(c.getType()).or("Null type"));

    }
    private static class Card {
        private String number;
        private String type;

        public Card(String number, String type) {
            this.number = number;
            this.type = type;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
