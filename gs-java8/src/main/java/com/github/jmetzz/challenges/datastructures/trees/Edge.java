package com.github.jmetzz.challenges.datastructures.trees;

import com.google.common.collect.ComparisonChain;

/**
 * Created by Jean Metz.
 */
public class Edge implements Comparable<Edge> {
    private final TreeNode t1;
    private final TreeNode t2;
    private boolean directional = false;

    public Edge(TreeNode t1, TreeNode t2, boolean d) {
        this(t1, t2);
        directional = d;
    }

    public Edge(TreeNode t1, TreeNode t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public TreeNode getT1() {
        return t1;
    }

    public TreeNode getT2() {
        return t2;
    }

    public boolean isDirectional() {
        return directional;
    }

    public void setDirectional(boolean directional) {
        this.directional = directional;
    }

    @Override
    public int compareTo(Edge o) {
        return ComparisonChain.start()
                .compare(t1, o.getT1())
                .compare(t2, o.getT2())
                .compareFalseFirst(this.directional, o.isDirectional())
                .result();
    }

    @Override
    public String toString() {
        return "[" + t1.getLabel() + " , " + t2.getLabel() + "]";
    }
}
