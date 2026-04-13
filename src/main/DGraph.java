package main;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DGraph {
    private Map<Integer, List<Integer>> hypoEdge;    //Stores hyponyms for traversal

    public DGraph() {
        hypoEdge = new HashMap<>();
    }

    // Checks both nodes to first confirm existence,
    // puts each of them in a separate, new list if they
    // don't exist, and then makes the connection between them,
    // signifying the "is-a" relationship
    public void createConnection(int first, int next) {
        if (!hypoEdge.containsKey(first)) {
            hypoEdge.put(first, new ArrayList<>());
        }
        if (!hypoEdge.containsKey(next)) {
            hypoEdge.put(next, new ArrayList<>());
        }
        hypoEdge.get(first).add(next);
    }

    // If the ID is not a part of an existing connection,
    // create a new synset (node) so that a connection
    // can be made later
    public void newSynset(int id) {
        if (!hypoEdge.containsKey(id)) {
            hypoEdge.put(id, new ArrayList<>());
        }
    }

    // Returns a list of the IDs which are direct
    // hyponyms of the given ID
    public List<Integer> getDirectHypoymns(int id) {
        if (hypoEdge.containsKey(id)) {
            return hypoEdge.get(id);
        } else { //It's a leaf with no direct hyponyms
            return new ArrayList<>();
        }
    }

}

