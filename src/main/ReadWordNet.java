package main;

import edu.princeton.cs.algs4.In;

import java.util.*;

// Reads the data from the files and organizes them

public class ReadWordNet {
    private DGraph graph;
    private Map<Integer, List<String>> id;
    private Map<String, List<Integer>> wordKey;

    public ReadWordNet(String synsets, String hyponyms) {
        wordKey = new HashMap<>();
        id = new HashMap<>();
        graph = new DGraph();
        parseSyns(synsets);
        parseHypos(hyponyms);
    }

    // Getters
    public DGraph getGraph() {
        return graph;
    }

    public Map<Integer, List<String>> getId() {
        return id;
    }

    public Map<String, List<Integer>> getWordKey() {
        return wordKey;
    }

    // Parsers
    private void parseHypos(String hyponyms) {
        In in = new In(hyponyms);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] words = line.split(",");
            int hypernymID =  Integer.parseInt(words[0]);
            for (int i = 1; i < words.length; i++) {
                int hyponymID = Integer.parseInt(words[i]);
                graph.createConnection(hypernymID, hyponymID);
            }
        }
    }

    private void parseSyns(String synsets) {
        In in = new In(synsets);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] parts = line.split(",");
            int synID = Integer.parseInt(parts[0]);
            String[] eachWord = parts[1].split(" ");
            List<String> words = new ArrayList<>(Arrays.asList(eachWord));

            id.put(synID, words);
            graph.newSynset(synID);

            for (String word : words) {
                if  (!wordKey.containsKey(word)) {
                    wordKey.put(word, new ArrayList<>());
                }
                wordKey.get(word).add(synID);
            }
        }
    }


}
