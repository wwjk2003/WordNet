import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

/**
 * Created by wwjk2 on 2015/12/8.
 */
public class SAP {
    private Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v<0||w<0) throw new IndexOutOfBoundsException();
        BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(G, w);
        int ancestor = ancestor(v, w);
        return ancestor==-1?-1:V.distTo(ancestor) + W.distTo(ancestor);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v<0||w<0) throw new IndexOutOfBoundsException();
        BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(G, w);
        int dis = V.distTo(w);
        int ances = dis==Integer.MAX_VALUE?  -1:w;
        for (int vv = 0; vv < G.V(); vv++) {

            if (V.hasPathTo(vv)&&W.hasPathTo(vv)&&V.distTo(vv) + W.distTo(vv) < dis) {
                dis = V.distTo(vv) + W.distTo(vv);
                ances = vv;
            }
        }
        return ances;

    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v==null||w==null) throw new NullPointerException();
        BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(G, w);
        int ancestor = ancestor(v, w);
        return ancestor==-1?-1:V.distTo(ancestor)+W.distTo(ancestor);
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v==null||w==null) throw new NullPointerException();
        int dis = Integer.MAX_VALUE;
        int ances = -1;
        BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(G, w);
        for (int vv = 0; vv < G.V(); vv++) {

            if (V.hasPathTo(vv)&&W.hasPathTo(vv)&&V.distTo(vv) + W.distTo(vv) < dis) {
                dis = V.distTo(vv) + W.distTo(vv);
                ances = vv;
            }
        }
        return ances;
    }

    // do unit testing of this class
    public static void main(String[] args) {
//        In in = new In(args[0]);
//        Digraph G = new Digraph(in);
//        SAP sap = new SAP(G);
//        while (!StdIn.isEmpty()) {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
//
//
//            int length = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
//            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//
//        }
    }
}