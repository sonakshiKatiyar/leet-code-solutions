package org.com.sonakshi.graph;

import java.util.*;

public class EvaluateDivision {
    public static void main(String[] args) {
        List<List<String>> eq = List.of(
                List.of("a", "b"), List.of("b", "c"));
        double[] val = {2.0, 3.0};
        List<List<String>> q  = List.of(
                List.of("a","c"), List.of("b","a"), List.of("a","e"),
                List.of("a","a"), List.of("x","x"));

        System.out.println(Arrays.toString(
                new EvaluateDivision().calcEquation(eq, val, q)));
    }
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            Map<String, Map<String, Double>> graph = buildGraph(equations, values);
            double[] res = new double[queries.size()];

            for (int i = 0; i < queries.size(); i++) {
                System.out.print("i: "+i+ " start:"+ queries.get(i).get(0)+" end:"+queries.get(i).get(1));
                res[i] = calculateValue(queries.get(i).get(0), queries.get(i).get(1), graph, new HashSet<>());
                System.out.println(", weight: "+ res[i]);
            }
            return res;
        }

        private double calculateValue(String start, String end, Map<String, Map<String, Double>> graph,
                                      Set<String> visited) {
            if (!graph.containsKey(start) || !graph.containsKey(end))
                return -1.0;
            if(Objects.equals(start, end))
                return 1.0;
            if (graph.get(start).containsKey(end))
                return graph.get(start).get(end);
            visited.add(start);
            for (Map.Entry<String, Double> neighbour : graph.get(start).entrySet()) {
                if (!visited.contains(neighbour.getKey())) {
                    double value = calculateValue(neighbour.getKey(), end, graph, visited);
                    if (value != -1.0)
                        return neighbour.getValue() * value;
                }
            }
            return -1.0;

        /*
        A--2.0-->B--3.0-->C
                 |
                 2.0
                 |
                 D
        calcValue(a,c,graph)

        */

        }

        private Map<String, Map<String, Double>> buildGraph(List<List<String>> eq, double[] values) {
            Map<String, Map<String, Double>> graph = new HashMap<>();
            for (int i = 0; i < eq.size(); i++) {
                String edge1 = eq.get(i).get(0);
                String edge2 = eq.get(i).get(1);
                graph.putIfAbsent(edge1, new HashMap<>());
                graph.putIfAbsent(edge2, new HashMap<>());
                graph.get(edge1).put(edge2, values[i]);
                graph.get(edge2).put(edge1, 1 / values[i]);
            }
            return graph;
        }
//build graph where Map< edge1 Map< edge2, value>>
}
