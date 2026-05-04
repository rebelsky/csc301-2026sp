import java.io.PrintWriter;

public class BSTExpt {
  public static void main(String[] args) {
    BST<String,String> tree = new BST<String,String>();
    PrintWriter pen = new PrintWriter(System.out, true);
    runExperiments(tree, args, pen);
  } // main(String[])

  public static void runExperiments(BST<String,String> tree,
       String[] strings, PrintWriter pen) {
    // Insert everything
    for (String s : strings) {
      String key = s.substring(0,1);
      pen.println("-------------------------");
      pen.println("INSERTING " + key + ":" + s);
      pen.println();
      tree.insert(key, s);
      tree.dump(pen);
    } // for

    // Remove everything
    for (String s : strings) {
      String key = s.substring(0,1);
      pen.println("-------------------------");
      pen.println("REMOVING " + key);
      pen.println();
      tree.remove(key);
      tree.dump(pen);
    } // for

    // And that's it.
    pen.println("-------------------------");
  } // runExperiments(BST<String,String>, String[], PrintWriter)
} // class BSTExpt
