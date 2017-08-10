package com.github.jmetzz.challenges.algorithms.compression;


import com.google.common.base.MoreObjects;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class Huffman {

    private int R = 256; //alphabet size

    public static void main(String[] args) {
        Huffman h = new Huffman();
        String input = "aaabbbcdaeee";

        int[] frequency = h.buildFrequencyTable(input);
        input.chars().distinct()
                .sorted()
                .forEach(c -> System.out.println(frequency[(int) c]));
        System.out.println("==================");
        Node root = h.code(input);
        System.out.println(root);

    }

    public Node code(String input) {
        int[] frequencyTable = buildFrequencyTable(input);

        PriorityQueue<Node> queue = new PriorityQueue<>();

        input.chars().distinct().forEach(e -> queue.add(new Node(frequencyTable[e], new Character((char) e))));

        while (queue.size() > 1) {
            Node l = queue.poll();
            Node r = queue.poll();

            Node n = new Node(l.w + r.w);
            n.elements.addAll(l.elements);
            n.elements.addAll(r.elements);

            n.left = l;
            n.right = r;
            queue.add(n);
        }
        return queue.poll();
    }

    public int[] buildFrequencyTable(String input) {
        int[] frequency = new int[R];

        char[] v = input.toCharArray();
        Arrays.sort(v);

        for (int i = 0, count = 0; i < v.length; i += count) {
            count = 0;
            char c = v[i];
            while (i + count < v.length && v[i + count] == c) {
                count++;
            }
            frequency[c] = count;
        }
        return frequency;
    }


    public String uncompress(boolean[] bits) {
        return null;
    }

    class Node implements Comparable<Node> {
        Set<Character> elements;
        Integer w;
        Node left;
        Node right;

        public Node(Integer w, Character... elements) {
            this.elements = new HashSet<>();
            this.w = w;
            this.elements.addAll(Arrays.asList(elements));
        }

        public Node(Integer w) {
            this(w, new Character[]{});
        }


        @Override
        public int compareTo(Node o) {
            return w.compareTo(o.w);
        }

        @Override
        public String toString(){
//            String s =  (left != null ? left : "") + "#" + elements.toString() + "#" + (right != null ? right : "") ;
            String s =  elements.toString();
            return  s.replaceAll("\\[", "").replaceAll("\\]", "");
        }

    }

}
