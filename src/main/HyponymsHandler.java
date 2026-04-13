package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {

    private TraverseWordNet traverse;
    private NGramMap nGram;


    public HyponymsHandler(String synsets, String hyponyms, NGramMap nGrams) {
        traverse = new TraverseWordNet(synsets, hyponyms);
        nGram = nGrams;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> query = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int givenK = q.k();

        Set<String> result = traverse.hyponyms(query);

        if (givenK == 0) {
            return result.toString();
        }

        TreeMap<String, Double> count = new TreeMap<>();
        for (String word : result) {
            TreeMap<Integer, Double> tracked = nGram.countHistory(word,  startYear, endYear);
            double total = 0;
            for (double values:  tracked.values()) {
                total += values;
            }
            if (total > 0) {
                count.put(word, total);
            }

        }

        TreeSet<String> orderedResult = new TreeSet<>();
        for (int i = 0; i < givenK; i++) {
            if (count.isEmpty()) {
                break;
            }
            String topWord = null;
            double counter = -1;            // This is to zeroes were filtered out. Actual counts must be positive.
            for (String words :  count.keySet()) {
                if (count.get(words) > counter) {
                    counter = count.get(words);
                    topWord = words;
                }
            }
            orderedResult.add(topWord);
            count.remove(topWord);
        }


        return orderedResult.toString();
    }
}
