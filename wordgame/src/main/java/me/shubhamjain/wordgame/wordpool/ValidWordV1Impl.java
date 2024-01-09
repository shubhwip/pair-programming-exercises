package me.shubhamjain.wordgame.wordpool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class ValidWordV1Impl implements ValidWordsV1{
    /**
     * Review Comments
     *
     * <SJ>
     *     IMHO Vector wouldn't be a good choice for below reasons
     *     1.) Not Optimal choice for storing words here because searching for a word has complexity of upper bound of n i.e. O(n)
     *     	  Where n is size of vector
     *     2.) Vector class is synchronized but not completely thread-safe*
     *     3.) Performance Issues, Vector synchronizes for each operation i.e. we will be acquiring lock for each operation
     *     		losing performance
     *     4.) Vector is deprecated so don't expect support from java community :)
     * </SJ>
     *
     * <SJ>
     *     I have Some suggestions/options for data structure for storing words
     *     1.) HashSet of words can be suitable option here as it will provide us constant time complexity in searching a word
     *     but Space wise little efficient because we will be storing lots of string. There are ways to manage
     *     synchronization with hashset.
     *     <YL>
     *         Good points. Set is probably the best choice - since words should also be unique.
     *     </YL>
     *     2.) Trie can also be our potential candidate because it can help reduce space complexity for many of common prefixes
     *     in the words. we have to handle synchronization by ourselves here.
     * </SJ>
     *
     * <SJ>
     *     this.getClass().getResourceAsStream can lead to Null Pointer Exception
     *     For safety either add Object.requirenonnull at the beginning
     *     Or add a null check after getting resource
     * </SJ>
     *
     * <SJ>
     *     We should avoid hardcoding anything in the classes and There is hardcoding of wordlist.txt file so we should avoid it
     *     and inject it as dependency and or read it from commandline argument/properties file.
     * </SJ>
     *
     * <SJ>
     *     In order to build flexible design for future, We should add a parser interface which we can implement and
     *     support parsing of various type of supplied input like web url, csv file, txt file etc and support further future options.
     *     and our ValidWordImpl class can inject that parser through constructor.
     * </SJ>
     *
     *
     * <SJ>
     *     Instead of catching parent Exception class, we should catch specific exceptions like IOException, FileNotFoundException etc.
     * 	   <YL> Also Exception handling should be improved (log, re-throw) - there is no point in running the game with an invalid word list (fail fast). </YL>
     * </SJ>
     *
     * <SJ>
     *     There can be cases where input stream won't be closed because of some exceptional conditions which will lead to memory leaks
     *     and will be hard to detect.
     * 	   One option would be to use finally clause to close the resources as finally clause will be executed always.
     * 	   Other option would be to use try with resources clause added from Java7 onwards.
     * </SJ>
     *
     *
     */

    // Synchronized Class Vector
    // Raw Types should not be used
    Vector v = new Vector();

    public void ValidWordsV1Impl() {
        try {
            // the use of a String such as "UTF-8" which has the drawback of requiring the catch/throw of an UnsupportedEncodingException that will never actually happen
            // StandardCharsets.UTF_8 should be used
            InputStreamReader reader = new InputStreamReader(this.getClass().getResourceAsStream("/wordlist.txt"), "utf-8");
            BufferedReader in = new BufferedReader(reader);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                v.add(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean contains(String word) {
        return v.contains(word);
    }

    @Override
    public int size() {
        return v.size();
    }
}
