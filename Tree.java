import java.util.Scanner;
import java.util.Stack;

public class Tree {

    //Declare the stack, of type TreeNode
    public Stack<TreeNode> treeStack = new Stack<TreeNode>();

    //Constructor, which parses expression and pushes nodes to stack. These are then converted into a tree
    public Tree(String expressionArg){

        //This TreeNodes are used when a parent node must be formed
        TreeNode a, b, tmpNode;

        //Declare scanner for parsing expression
        Scanner scanner = new Scanner(expressionArg);

        //While there is another term in the expression...
        while(scanner.hasNext()){

            //Pull in next term and make it a string. Then look at first char to determine if its an operator or operand
            String currentTerm = scanner.next();
            char currentChar = currentTerm.charAt(0);

            //If its an operand, convert the string to a double, push the node to the stack
            if(Character.isDigit(currentChar)){
                double currentDouble = Double.parseDouble(currentTerm);
                tmpNode = new TreeNode(currentDouble);
                treeStack.push(tmpNode);
            }
            //If it's an operator, convert the string to a char which is now a parent node of the preceding two nodes
            else if((currentChar == '/')||(currentChar == '*')||(currentChar == '-')||(currentChar == '+')) {
                a = treeStack.pop(); b = treeStack.pop();
                tmpNode = new TreeNode(currentChar, b, a);
                treeStack.push(tmpNode);
            }
            else{System.out.println("ERROR!! Exiting Program Now"); break;}
        }
    }

    //Method declarations and definitions

    //Returns the first node in the stack, used in main to initiate calls to recursive functions below
    public TreeNode getFirstNode(){return treeStack.peek();}

    //Recursive function that navigates tree and performs arithmetic on values
    public float evaluate(TreeNode thisNode){
        float result = 0; //Final result

        //If parent node, determine which operation is required and recursively call left and right nodes
        if(thisNode.isParent() == true){
            char tmpChar = thisNode.getThisCharVal();
            if(tmpChar == '*'){result = evaluate(thisNode.getLeftNode()) * evaluate(thisNode.getRightNode());}
            if(tmpChar == '/'){result = evaluate(thisNode.getLeftNode()) / evaluate(thisNode.getRightNode());}
            if(tmpChar == '+'){result = evaluate(thisNode.getLeftNode()) + evaluate(thisNode.getRightNode());}
            if(tmpChar == '-'){result = evaluate(thisNode.getLeftNode()) - evaluate(thisNode.getRightNode());}
        }
        //If not a parent node, send operand up the chain to be evaluated
        if(thisNode.isParent() == false){return (float)thisNode.getThisDoubleVal();}

        return result;

    } //END evaluate method

    //PreOrder traversal and print, uses recursion to print (Parent, Left, Right)
    public void preorder(TreeNode thisNode){
        //If parent node, print and then recursively call left and right nodes
        if(thisNode.isParent() == true){
            System.out.print(thisNode.getThisCharVal()+" ");
            preorder(thisNode.getLeftNode());
            preorder(thisNode.getRightNode());
        }
        //Not parent node, so print operand
        if(thisNode.isParent() == false){ System.out.print(thisNode.getThisDoubleVal()+" "); }
    } //END preorder method

    //PostOrder traversal and print, uses recursion to print (Left, Right, Parent)
    public void postorder(TreeNode thisNode){
        //If it's a parent node, recursively call left and right child nodes and then print parent
        if(thisNode.isParent() == true){
            postorder(thisNode.getLeftNode());
            postorder(thisNode.getRightNode());
            System.out.print(thisNode.getThisCharVal()+" ");
        }
        //If its not a parent node, print the operator
        if(thisNode.isParent() == false){ System.out.print(thisNode.getThisDoubleVal()+" "); }
    } //END postorder method

    //InOrder traversal and print, uses recursion to print (Left, Parent, Right) - Use parentheses to establish PEMDAS
    public void inorder(TreeNode thisNode){
        if(thisNode.isParent() == true){
            System.out.print("( "); inorder(thisNode.getLeftNode());        //Left side  "(_operand"
            System.out.print(" "+thisNode.getThisCharVal()+" ");            //Middle     "_operator_"
            inorder(thisNode.getRightNode()); System.out.print(" )");       //Right side "operand_)"
        }
        //If it's not a parent node, print the corresponding operand
        if(thisNode.isParent() == false){ System.out.print(thisNode.getThisDoubleVal()); }
    } //END inorder method

}
