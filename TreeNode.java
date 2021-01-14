public class TreeNode {

    //Member Variables
    private boolean isParent;
    private char thisCharVal;
    private double thisDoubleVal;
    private TreeNode leftNode, rightNode;

    //Constructors
    //First constructor takes a char value, meaning it is a parent with left and right child nodes
    public TreeNode(char operator, TreeNode LNode, TreeNode RNode){
        isParent = true;
        thisCharVal = operator;
        leftNode = LNode; rightNode = RNode;
    }
    //Second constructor takes a double, meaning it has no child nodes
    public TreeNode(double operand){
        isParent = false;
        thisDoubleVal = operand;
    }

    //Methods
    public double getThisDoubleVal(){return thisDoubleVal;}
    public char getThisCharVal(){return thisCharVal;}
    public TreeNode getLeftNode(){return leftNode;}
    public TreeNode getRightNode(){return rightNode;}
    public boolean isParent(){return isParent;}

}
