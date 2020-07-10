package com.datastructure.tree;


public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node root = new Node(0, "root");
        Node 张三 = new Node(1, "张三"); //居然直接提示变量名为中文，试试看
        Node 李四 = new Node(2, "李四");
        Node 王五 = new Node(3, "王五");
        Node 赵六 = new Node(4, "赵六");
        binaryTree.setRoot(root);
        root.setLeft(张三);
        root.setRight(李四);
        张三.setLeft(王五);
        张三.setRight(赵六);
        binaryTree.preOrder();
//        binaryTree.infixOrder();
//        binaryTree.postOrder();
//        System.out.println(binaryTree.postOrderSearch(7));
//        System.out.println(binaryTree.preOrderSearch(7));
//        System.out.println(binaryTree.infixOrderSearch(7));
        System.out.println("==================");
        binaryTree.deleteNode(1);
        binaryTree.preOrder();
    }

}

/**
 * 二叉树
 */
class BinaryTree{
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public BinaryTree() {
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("空树");
        }
    }

    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("空树");
        }
    }

    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("空树");
        }
    }

    public Node preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }
        return null;
    }

    public Node infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }
        return null;
    }

    public Node postOrderSearch(int no){
        if (root != null){
            return root.postOrderSearch(no);
        }
        return null;
    }

    public void deleteNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            }else {
                root.deleteNode(no);
            }
        }else {
            System.out.println("空树");
        }
    }
}

/**
 * 节点类
 */
class Node{
    private int no;
    private String name;
    private Node left;
    private Node right;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this); //父节点
        if (this.left != null){ //左节点
            this.left.preOrder();
        }
        if (this.right != null){ //右节点
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this.left != null){ //左节点
            this.left.infixOrder();
        }
        System.out.println(this); //父节点
        if (this.right != null){ //右节点
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public Node preOrderSearch(int no){
        if (this.no == no){
            return this;
        }
        Node resultNode = null;
        if (this.left != null){
            resultNode = this.left.preOrderSearch(no);
        }
        if (resultNode != null){
            return resultNode;
        }
        if (this.right != null){
            resultNode = this.right.preOrderSearch(no);
        }
        return resultNode;
    }

    public Node infixOrderSearch(int no){
        Node resultNode = null;
        if (this.left != null){
            resultNode = this.left.infixOrderSearch(no);
        }
        if (resultNode != null){
            return resultNode;
        }
        if (this.no == no){
            return this;
        }
        if (this.right != null){
            resultNode = this.right.infixOrderSearch(no);
        }
        return resultNode;
    }

    public Node postOrderSearch(int no){
        Node resultNode = null;
        if (this.left != null){
            resultNode = this.left.postOrderSearch(no);
        }
        if (resultNode != null){
            return resultNode;
        }
        if (this.right != null){
            resultNode = this.right.postOrderSearch(no);
        }
        if (resultNode != null){
            return resultNode;
        }
        if (this.no == no){
            return this;
        }
        return resultNode;
    }

    public void deleteNode(int no){
        if (this.left != null && this.left.no == no){
            if (this.left.left == null && this.left.right == null){
                this.left = null;
            }else {
                if (this.left.left != null){
                    this.left = this.left.left;
                }else {
                    this.left = this.left.right;
                }
            }
            return;
        }
        if (this.right != null && this.right.no == no){
            if (this.right.left == null && this.right.right == null){
                this.right = null;
            }else {
                if (this.right.left != null){
                    this.right = this.right.left;
                }else {
                    this.right = this.right.right;
                }

            }
            return;
        }
        if (this.left != null){
            this.left.deleteNode(no);
        }
        if (this.right != null){
            this.right.deleteNode(no);
        }
    }
}

