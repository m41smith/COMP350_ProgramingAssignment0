// Imports required Libraries
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

// Runs Main program
public class Main {
    public static void main(String[] args)throws IOException {
        // gets and reads text file
        var fileName = "alice(1).txt";
        var fileRetriever = Paths.get(fileName);
        var wordArray = new ArrayList<String>();
        Map<String, ArrayList<String>> wordMap = new HashMap<>();
        Map<String, ArrayList<Integer>> lineNumMap = new HashMap<>();
        var reader = Files.readAllLines(fileRetriever);
        var wordStorage = new ArrayList<String>();

        for (var line : reader) {
            int i = 0;
            var splitLines = line.split("/n");
//            if (!splitLines[i].isEmpty()) {
//                wordArray.add(splitLines[i]);
//            }
            wordArray.add(splitLines[i]);
            i++;
        }

        int lineNum = 1;
        for (var line : wordArray) {
            var splitWords = line.split(" ");
            String word;
            for (int i=0; i<splitWords.length; i++) {
                word = splitWords[i];
                boolean matchFound = Pattern.matches("[a-zA-Z0-9-]+", word);
                if (!matchFound) {
                    word = word.replaceAll("[-+.^:,?'()]","");
                }
                if (wordMap.get(word) == null) {
                    wordMap.put(word, new ArrayList<>());
                    wordMap.get(word).add(word);
                }
                else {
                    wordMap.get(word).add(word);
                }
                if (lineNumMap.get(word) == null) {
                    lineNumMap.put(word, new ArrayList<>());
                    lineNumMap.get(word).add(lineNum);
                }
                else if (!lineNumMap.get(word).contains(lineNum)) {
                    lineNumMap.get(word).add(lineNum);
                }
                else {
                    continue;
                }
                if (!wordStorage.contains(word)) {
                    wordStorage.add(word);
                }
            }
            lineNum++;
        }

        wordStorage.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Word \t Count \t Line Number");
        for (int i=0; i<wordStorage.size(); i++) {
            var temp = wordStorage.get(i);
            System.out.println(temp + " \t" + wordMap.get(temp).size() + " \t" + lineNumMap.get(temp));
        }
    }
}