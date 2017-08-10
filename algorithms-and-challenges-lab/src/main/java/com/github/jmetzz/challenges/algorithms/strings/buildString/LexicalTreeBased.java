package com.github.jmetzz.challenges.algorithms.strings.buildString;


import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jean Metz.
 */
public class LexicalTreeBased implements StringGenerator {

    @Override
    public int costToGenerate(String tape, int costAppendChar, int costAppendString) {

        LexicalTree lexicalTree = new LexicalTree(tape.length());
        int cost = 0;

        char[] inputSeq = tape.toCharArray();

        int start = 0;
        int index = 0;
        while (index < inputSeq.length) {
            Node current = lexicalTree.walk(inputSeq[index]);
            if (current != null) {
                // keep walking
                index++;
            } else {
                // add the only character
                lexicalTree.add(inputSeq, start, index);
                cost += start == index ? costAppendChar : costAppendString;
                lexicalTree.resetWalk();
                index++;
                start = index;
            }
        }
        return cost;
    }

    private class LexicalTree {

        private final char[] buffer;
        private Node root;
        private int filled;
        private Node pointer;

        public LexicalTree(int bufferSize) {
            root = new Node(Character.MIN_VALUE);
            pointer = root;
            buffer = new char[bufferSize];
            filled = 0;
        }

        public Node walk(final char c) {
            pointer = pointer.children.get(c);
            return pointer;
        }

        public void resetWalk() {
            pointer = root;
        }

        public void clear() {
            root = new Node(Character.MIN_VALUE);
            filled = 0;
            pointer = root;
        }

        public void add(String sequence) {
            char[] chars = sequence.toCharArray();

            for (int index = chars.length - 1; index >= 0; index--) {
                add(chars, index, chars.length - 1);
            }
        }

        private void add(final char[] window, final int start, final int end) {
            checkNotNull(window);
            checkArgument(end - start < window.length);

            Node current = root;
            Node father = null;
            int index = start;
            while (current != null && index <= end) {
                father = current;
                current = current.children.get(window[index++]);
            }
            if (current == null) {
                addSubTree(father, window, index - 1, end);
            } else if (index < window.length) {
                addSubTree(current, window, index - 1, end);
            }
        }

        private void addSubTree(final Node root, final char[] window, final int start, final int end) {
            Node current = root;
            for (int i = start; i <= end; i++) {
                Node child = new Node(window[i]);
                current.children.put(child.value, child);
                current = child;
            }
        }

    }

    private class Node {
        final Character value;
        final Map<Character, Node> children;

        public Node(Character value) {
            this.value = value;
            children = new HashMap<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equal(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }
    }
}
