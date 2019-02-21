package us.mattgreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    private final static FileInput indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map = new HashMap<String, Integer>();
    private static List<Integer> wordCount = new ArrayList<Integer>();   
    private static Set<String> wordList = new HashSet<String>();

    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        String line;
        String[] words;
        int singleUseWords = 0;

        while ((line = indata.fileReadLine()) != null) {
            // Remove anything that's not a letter or space
            line = line.replaceAll("[^a-zA-Z ]","")
                    .toLowerCase().trim();
           
            // Don't process lines with no characters
            if (line.isEmpty()) {
                continue;
            }
            
            // Split string over one or more spaces
            words = line.split(" +");
            
            // Look for each word in the map
            for (String word : words) {
                // This word isn't yet a key, init count to 1
                if (!map.containsKey(word)) {
                    map.put(word, 1);
                }
                else {
                    // Increment count using word as key
                    map.put(word, map.get(word) + 1);
                }

            }

            // Loop over entries in the map, getting each key/value pair
            for (Map.Entry<String, Integer> entry : map.entrySet()) { 
                wordList.add(entry.getKey());
            }
        }       
   
        wordCount = new ArrayList<>(map.values()); 

        for (int i = 0; i < wordCount.size(); i++) {
            if (wordCount.get(i) == 1) {
                singleUseWords++;
            }
        }
        
        Collections.sort(wordCount, Collections.reverseOrder());
                 
        System.out.println(singleUseWords + " words appear only once in War of the Worlds.");
        System.out.println("The 15 most common words in War of the Worlds are:");
        for (int i = 0; i < 15; i++)
        {
            System.out.println((i + 1) + ":\t" + wordCount.get(i));
        }
    }
    
}