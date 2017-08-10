package com.github.jmetzz.challenges.graphTheory.weekendAway;

import com.github.jmetzz.challenges.algorithms.graphSearch.DijkstraSimple;
import com.github.jmetzz.challenges.datastructures.graph.Edge;
import com.github.jmetzz.challenges.datastructures.graph.Graph;
import com.github.jmetzz.challenges.datastructures.graph.Vertex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jean Metz.
 */
public class DijkstraSimpleTest {

    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
    public void testExcute1() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 4; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }


        addLane("Edge_0", 0, 1, 2);
        addLane("Edge_0", 1, 0, 2);

        addLane("Edge_1", 0, 2, 4);
        addLane("Edge_1", 2, 0, 4);

        addLane("Edge_2", 0, 3, 8);
        addLane("Edge_2", 3, 0, 8);

        addLane("Edge_3", 1, 2, 3);
        addLane("Edge_3", 2, 1, 3);

        addLane("Edge_4", 1, 3, 3);
        addLane("Edge_4", 3, 1, 3);

        addLane("Edge_5", 2, 3, 1);
        addLane("Edge_5", 3, 2, 1);


        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraSimple dijkstra = new DijkstraSimple(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(3));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        int dist = 0;
        for (int i = 1; i < path.size(); i++) {
            dist += getLane(graph, path.get(i - 1), path.get(i)).getWeight();
        }

        System.out.println(dist);
    }


    @Test
    public void testExcute2() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }

        addLane("Edge_0", 0, 1, 85);
        addLane("Edge_1", 0, 2, 217);
        addLane("Edge_2", 0, 4, 173);
        addLane("Edge_3", 2, 6, 186);
        addLane("Edge_4", 2, 7, 103);
        addLane("Edge_5", 3, 7, 183);
        addLane("Edge_6", 5, 8, 250);
        addLane("Edge_7", 8, 9, 84);
        addLane("Edge_8", 7, 9, 167);
        addLane("Edge_9", 4, 9, 502);
        addLane("Edge_10", 9, 10, 40);
        addLane("Edge_11", 1, 10, 600);

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraSimple dijkstra = new DijkstraSimple(graph);
        dijkstra.execute(nodes.get(1));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }

        int dist = 0;
        for (int i = 1; i < path.size(); i++) {
            dist += getLane(graph, path.get(i - 1), path.get(i)).getWeight();
        }

        System.out.println(dist);
    }

    private Edge getLane(Graph graph, Vertex o, Vertex d) {
        for (Edge e : graph.getEdges()) {
            if (e.getSource().equals(o) && e.getDestination().equals(d))
                return e;
        }
        return null;
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }


}