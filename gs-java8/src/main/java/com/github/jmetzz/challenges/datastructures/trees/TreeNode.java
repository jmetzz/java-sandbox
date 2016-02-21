package com.github.jmetzz.challenges.datastructures.trees;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jean Metz.
 */
public class TreeNode implements Comparable<TreeNode> {

    private final String label;

    private List<TreeNode> children;

    private TreeNode father;

    public TreeNode(String label) {
        checkNotNull(label);
        this.label = label;
        children = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public void addChild(TreeNode child) {
        checkNotNull(child);
        this.children.add(child);
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public TreeNode getFather() {
        return father;
    }

    public void setFather(TreeNode father) {
        this.father = father;
    }


    public int compareTo(TreeNode o) {
        return ComparisonChain.start()
                .compare(this.label, o.getLabel())
                .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode other = (TreeNode) o;
        return Objects.equals(label, other.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("label", label)
//                .add("children", children)
                .toString();
    }

    public boolean hasFather() {
        return father == null;
    }
}
