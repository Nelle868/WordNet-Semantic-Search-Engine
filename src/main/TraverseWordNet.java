package main;

import java.util.*;

public class TraverseWordNet {
    private ReadWordNet data;

    public TraverseWordNet(String synsets, String hyponyms) {
        data = new ReadWordNet(synsets, hyponyms);
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> marker =  new HashSet<>();             // Marks synset IDs that have been processed
        TreeSet<String> orderedResult = new TreeSet<>();    // Alphabetizes
        Queue<Integer> pendingSynIDs = new LinkedList<>();  // Fringe

        if (!data.getWordKey().containsKey(word)) {
            return orderedResult;
        }
        for (int synID: data.getWordKey().get(word)) {
            if (!marker.contains(synID)) {
                marker.add(synID);
                pendingSynIDs.add(synID);
            }
        }
        // BFS
        while (!pendingSynIDs.isEmpty()) {
            int currSynID = pendingSynIDs.remove();
            for (String wordInSynset: data.getId().get(currSynID)) {
                orderedResult.add(wordInSynset);
            }
            // Finds direct hyponyms and adds unmarked words to pendingSynIDs
            for (int directHypoID: data.getGraph().getDirectHypoymns(currSynID)) {
                if (!marker.contains(directHypoID)) {
                    marker.add(directHypoID);
                    pendingSynIDs.add(directHypoID);
                }
            }
        }
        return orderedResult;
    }

    // Overload hyponym
    public Set<String> hyponyms(List<String> words) {
        Set<String> inCommon = hyponyms(words.get(0));

        // Keep the intersection of both sets
        for (int i = 1; i < words.size(); i++) {
            Set<String> currentHypo = hyponyms(words.get(i));
            Set<String> intersection = new TreeSet<>();
            for (String word: inCommon) {
                if (currentHypo.contains(word)) {
                    intersection.add(word);
                }
            }
            inCommon = intersection;
        }
        return inCommon;
    }


}
