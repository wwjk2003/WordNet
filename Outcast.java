/**
 * Created by wwjk2 on 2015/12/12.
 */
public class Outcast {
    private  WordNet wordnet;
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet=wordnet;
    }     // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns)   {
        int max=0;
        String oc="";
        for (String noun : nouns) {
            int dis=0;
            for (String n : nouns) {
                dis+=wordnet.distance(noun,n);
            }
            if (dis>max){
                max=dis;
                oc=noun;
            }
        }
        return oc;
    }
    public static void main(String[] args)  {
//        WordNet wordnet = new WordNet(args[0], args[1]);
//        Outcast outcast = new Outcast(wordnet);
//        for (int t = 2; t < args.length; t++) {
//            In in = new In(args[t]);
//            String[] nouns = in.readAllStrings();
//            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
//        }
    }
}
