/* Bryan Bennett
CSC201 Fall 2020
Programming Assignment 2
September 28, 2020
 */

/* Main Description: This program uses exprs.txt as an input, which contains postfix arithmetic expressions.
For each expression, it reads in all operators and operands and creates an expression tree object using the tree class.
The tree class uses a stack to create a tree of tree nodes, which are either operators or operands.
The loop in this main navigates each expression and sends each to the processExpr method where a tree is created print statements are called.
* */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static void processExpr(String expression){

        //Create an expression tree. Get first Node for making calls to recursion-based functions below
        Tree exprsTree = new Tree(expression);
        TreeNode firstNode = exprsTree.getFirstNode();

        //Print preorder (prefix) expression
        System.out.print("PreOrder (prefix): ");
        exprsTree.preorder(firstNode);
        System.out.println();

        //Print postorder (postfix) expression
        System.out.print("PostOrder (postfix): ");
        exprsTree.postorder(firstNode);
        System.out.println();

        //Print inorder (infix) expression AND result
        System.out.print("InOrder (infix): ");
        exprsTree.inorder(firstNode);

        //Evaluate the expression by traversing the expression tree and executing the operations. Result truncated to 5 decimal places
        System.out.printf(" = %.5f\n\n", exprsTree.evaluate(firstNode));
    }

    public static void main(String args[]) throws FileNotFoundException {

        //Open file and create input file stream (Relative path does not work, full path used)
        FileInputStream expressions = new FileInputStream("/Users/bryanbennett/IdeaProjects/Bennett_Bryan_Project2/src/exprs.txt");

        //Create scanner to read in text file
        Scanner scanner = new Scanner(expressions);

        //run loop until there are no more expressions in the txt file
        while (scanner.hasNextLine()) {

            //Create string that stores current line
            String currentLine = scanner.nextLine();

            //Call processExpr method, send expression as argument
            processExpr(currentLine);
        }
    }
}
