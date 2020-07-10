package com.datastructure.tree;

import java.util.TooManyListenersException;

public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {

    }

}


/**
 * 二叉树
 */
class TBinaryTree{
    private TNode root;
    private TNode pre = null; //前一个节点

    /**
     * 遍历线索化二叉树
     */
    public void TList(){
        TNode node = root;
        while (node != null){
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node); //找到类型为1的左节点进行打印
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }

    }

    /**
     * 将节点线索化  中序遍历
     */
    public void threadedNodes(TNode tNode){
        if (tNode == null){
            return;
        }

        //线索化左边
        if (tNode.getLeft() != null){
            threadedNodes(tNode.getLeft());
        }
        //线索化中间
        if (tNode.getLeft() == null){ //无左节点，则设置先驱节点
            tNode.setLeft(pre);
            tNode.setLeftType(1);
        }
        //前节点不为空，且前前节点的右节点为空，设置前节点的后继节点，第一次会置空，第二次再将下一个节点设置为前一个节点的后继节点
        if (pre != null && pre.getRight() == null){
            pre.setRight(tNode);
            pre.setRightType(1);
        }
        pre = tNode; //前节点，为上一次遍历的节点
        //线索化右边
        if (tNode.getRight() != null){
            threadedNodes(tNode.getRight());
        }

    }




    public TNode getPre() {
        return pre;
    }

    public void setPre(TNode pre) {
        this.pre = pre;
    }

    public TBinaryTree(TNode root) {
        this.root = root;
    }

    public TBinaryTree() {
    }

    public TNode getRoot() {
        return root;
    }

    public void setRoot(TNode root) {
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

    public TNode preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }
        return null;
    }

    public TNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }
        return null;
    }

    public TNode postOrderSearch(int no){
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
class TNode{
    private int no;
    private String name;
    private TNode left;
    private TNode right;
    private int leftType; // 0表示子树，1表示前驱节点
    private int rightType; // 0表示字数，1表示后继节点

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public TNode(int no, String name) {
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

    public TNode getLeft() {
        return left;
    }

    public void setLeft(TNode left) {
        this.left = left;
    }

    public TNode getRight() {
        return right;
    }

    public void setRight(TNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TNode{" +
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

    public TNode preOrderSearch(int no){
        if (this.no == no){
            return this;
        }
        TNode resultNode = null;
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

    public TNode infixOrderSearch(int no){
        TNode resultNode = null;
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

    public TNode postOrderSearch(int no){
        TNode resultNode = null;
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