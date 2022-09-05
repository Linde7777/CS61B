import java.util.ArrayList;
import java.util.List;

public class Boggle {

    // File path of dictionary file
    static String dictPath = "words.txt";

    /**
     * Solves a Boggle puzzle.
     *
     * @param k             The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     * The Strings are sorted in descending order of length.
     * If multiple words have the same length,
     * have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        // YOUR CODE HERE
        /*
        List<String> result = new ArrayList<>();
        Trie trie = new Trie();
        In board = new In(boardFilePath);
        In dict = new In(dictPath);
        trie.put(board.readAll());
        while (dict.hasNextLine()) {
            String key = dict.readLine();
            if (trie.findKey(key)) {
                result.add(key);
            }
        }
        return result;
         */

        List<String> result=new ArrayList<>();
        In boardStream=new In(boardFilePath);
        In dictStream=new In(dictPath);
        Trie trieOfDict=new Trie();
        trieOfDict.put(dictStream.readAll());

        boardStream.close();
        dictStream.close();
        return result;
    }

    public char[][] createBoardArray(In boardStream){
        int width=boardStream.readLine().length();
        int height=1;
        while(boardStream.hasNextLine()){
            height+=1;
            boardStream.readLine();
        }
        char[][] board=new char[width][height];
        return board;
    }

}
