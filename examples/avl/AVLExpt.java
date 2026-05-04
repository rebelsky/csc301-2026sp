import java.io.PrintWriter;

public class AVLExpt {
  public static void main(String[] args) {
    BST<String,String> tree = new AVL<String,String>();
    PrintWriter pen = new PrintWriter(System.out, true);
    BSTExpt.runExperiments(tree, args, pen);
  } // main(String[])
} // class AVLExpt
