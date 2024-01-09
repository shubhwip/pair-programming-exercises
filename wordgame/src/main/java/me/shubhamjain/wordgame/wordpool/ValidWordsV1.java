package me.shubhamjain.wordgame.wordpool;

public interface ValidWordsV1 {


    /**
     * @param word the word to check against the valid words collection
     * @return true if the valid words collection contains the word
     */
    public boolean contains(String word);

    /**
     * The size of the valid words collection.
     *
     * @return the size of the collection
     */
    public int size();

}
