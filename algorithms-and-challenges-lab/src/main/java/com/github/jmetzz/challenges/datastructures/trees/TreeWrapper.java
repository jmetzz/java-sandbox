package com.github.jmetzz.challenges.datastructures.trees;

import com.github.jmetzz.challenges.util.FileHelper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jean Metz.
 */
public class TreeWrapper {

    private static Predicate<TreeNode> isRoot = new Predicate<TreeNode>() {
        @Override
        public boolean apply(TreeNode treeNode) {
            return treeNode.hasFather();
        }
    };
    private Logger logger = Logger.getLogger(TreeWrapper.class.getName());
    private Set<Edge> edges = new HashSet<>();
    private Map<String, TreeNode> treeMap = new TreeMap<>();
    private TreeNode root;
    private int numOriginalNodes;
    private int numOriginalEdges;

    public static void main(String[] args) {
        TreeWrapper tw = new TreeWrapper();
        try {
            TreeNode originalTree = tw.buildTree(args[0]);
            List<TreeNode> forest = tw.decompose();
            int oEdges = tw.getNumOriginalEdges();
            int curEdges = tw.getNumEdges();
            System.out.println(oEdges - curEdges);
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong tree structure. Verify your input file.");
        }
    }

    private TreeNode buildTree(String srcFile) {
        List<String> content = retrieveRawTextTree(srcFile);
        extractMetaInfo(content);

        for (String line : content) {
            List<String> labels = parseLabels(line);
            String childLabel = labels.get(0);
            String fatherLabel = labels.get(1);

            TreeNode fatherTreeNode = retriveTreeFromMap(fatherLabel);
            TreeNode childTreeNode = retriveTreeFromMap(childLabel);
            connectNodes(fatherTreeNode, childTreeNode);
        }

        checkArgument(numOriginalNodes == treeMap.size());
        checkArgument(numOriginalEdges == edges.size());
        checkArgument(!isForest());
        root = FluentIterable.from(treeMap.values()).filter(isRoot).first().get();

        logger.info("Root node: " + root);
        return root;
    }

    private List<TreeNode> decompose() {
        checkNotNull(root);
        checkArgument(isEven(root));
        checkArgument(!isForest());

        Set<Edge> remove = new HashSet<>();

        for (Edge edge : edges) {
            if (isEven(edge.getT1()) && isEven(edge.getT2()))
                remove.add(edge);
        }
        logger.info("Edges to remove: " + remove);


        Iterator<Edge> it = remove.iterator();
        while (it.hasNext()) {
            Edge edge = it.next();

            TreeNode t1 = edge.getT1(); // remember t1 is the father
            TreeNode t2 = edge.getT2(); // remember t2 is child
            t1.getChildren().remove(t2);
            t2.setFather(null);
        }

        edges.removeAll(remove);
        logger.info("Remaining Edges: " + edges);

        return getForest().toList();
    }

    public FluentIterable<TreeNode> getForest() {
        checkNotNull(treeMap);
        return FluentIterable.from(treeMap.values()).filter(isRoot);
    }

    public boolean isForest() {
        return getForest().size() > 1;
    }

    private void connectNodes(TreeNode fatherTreeNode, TreeNode childTreeNode) {
        fatherTreeNode.addChild(childTreeNode);
        childTreeNode.setFather(fatherTreeNode);
        edges.add(new Edge(fatherTreeNode, childTreeNode, false));
    }

    private void extractMetaInfo(List<String> content) {
        String[] metaInfo = content.get(0).split(" ");
        checkArgument(metaInfo.length == 2);
        numOriginalNodes = Integer.parseInt(metaInfo[0]);
        numOriginalEdges = Integer.parseInt(metaInfo[1]);
        logger.info("#num orig nodes: " + numOriginalNodes);
        logger.info("#num orig edges: " + numOriginalEdges);

        content.remove(0);
    }

    public int getNumOriginalNodes() {
        return numOriginalNodes;
    }

    public int getNumOriginalEdges() {
        return numOriginalEdges;
    }

    public int getNumNodes() {
        return treeMap.size();
    }

    public int getNumEdges() {
        return edges.size();
    }

    public int getNumTrees() {
        return getForest().size();
    }

    private List<String> retrieveRawTextTree(String srcFile) {
        List<String> content = null;
        try {
            content = FileHelper.loadFileAsStringList(srcFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private TreeNode retriveTreeFromMap(String fatherLabel) {
        if (!treeMap.containsKey(fatherLabel)) {
            treeMap.put(fatherLabel, new TreeNode(fatherLabel));
        }
        return treeMap.get(fatherLabel);
    }

    private List<String> parseLabels(String line) {
        String[] labels = line.split(" ");
        checkArgument(labels.length == 2);
        return Arrays.asList(labels);
    }

    public int getTreeSize(TreeNode node) {
        if (node.getChildren().size() == 0)
            return 1;
        int n = 0;
        for (TreeNode child : node.getChildren()) {
            n += getTreeSize(child);
        }
        return n + 1;
    }

    public boolean isEven(TreeNode node) {
        return (getTreeSize(node) % 2) == 0;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("numOriginalNodes", numOriginalNodes)
                .add("edges", edges)
                .add("treeMap", treeMap)
                .toString();
    }


}
