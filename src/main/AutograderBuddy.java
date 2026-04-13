package main;

import browser.NgordnetQueryHandler;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordHistoryFile, String yearHistoryFile,
            String synsetFile, String hyponymFile) {

        NGramMap ngms = new NGramMap(wordHistoryFile, yearHistoryFile);
        return new HyponymsHandler(synsetFile, hyponymFile, ngms);
    }
}
