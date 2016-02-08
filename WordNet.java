import java.util.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
/**
 * Created by wwjk2 on 2015/12/9.
 */
public class WordNet {
    private Digraph G;
    private Map< String,ArrayList<Integer>> hash = new HashMap<>();
    private Map<Integer,String> search = new HashMap<>();
    private SAP wordsap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets.isEmpty()||hypernyms.isEmpty()) throw new NullPointerException();
        In in = new In(synsets);
        while (in.hasNextLine()) {
            String Synsets = in.readLine();
            String[] syn = Synsets.split(",");
            search.put(Integer.parseInt(syn[0]),syn[1]);
            String[] nouns=syn[1].split(" ");
            for (String noun : nouns) {
                if (hash.containsKey(noun)) {
                    hash.get(noun).add(Integer.parseInt(syn[0]));
                }
                else{
                    hash.put(noun,new ArrayList<>());
                    hash.get(noun).add(Integer.parseInt(syn[0]));
                }
            }

        }
        G = new Digraph(hash.size());
        In in2 = new In(hypernyms);
        while (in2.hasNextLine()) {
            String Hypernyms = in2.readLine();
            String[] hyper = Hypernyms.split(",");
               for (int i=1;i<hyper.length;i++) {
                G.addEdge(Integer.parseInt(hyper[0]), Integer.parseInt(hyper[i]));
                }
        }
        int root=0;
        for (int i = 0; i < G.V(); i++) {
            if (G.outdegree(i)==0)
                root++;
        }
        if (root!=1) throw new IllegalArgumentException();
        DirectedCycle dc=new DirectedCycle(G);
        //if (dc.hasCycle()) throw new IllegalArgumentException();
        wordsap=new SAP(G);


    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {

        return hash.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word){
        if (word.isEmpty()) throw new NullPointerException();
        return hash.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
     public int distance(String nounA, String nounB){
         if (nounA.isEmpty()||nounB.isEmpty()) throw new NullPointerException();
         if (!hash.containsKey(nounA)||!hash.containsKey(nounB)) throw new IllegalArgumentException();
         return  wordsap.length(hash.get(nounA),hash.get(nounB));
     }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
      public String sap(String nounA, String nounB){
          if (nounA.isEmpty()||nounB.isEmpty()) throw new NullPointerException();
          if (!hash.containsKey(nounA)||!hash.containsKey(nounB)) throw new IllegalArgumentException();
          return  search.get(wordsap.ancestor(hash.get(nounA),hash.get(nounB)));
      }

    // do unit testing of this class
    public static void main(String[] args) {

        WordNet wn = new WordNet(args[0], args[1]);
        for (String s : wn.nouns()) {
            System.out.println(s);
        }

    }

}

