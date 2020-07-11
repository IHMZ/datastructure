package com.datastructure.tree.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {

    }
}

class AVLTree{
    private Node root;

    /**
     * 添加
     * @param node
     */
    public void add(Node node){
        if (root == null){
            root = node;
        }else{
            root.add(node);
        }
    }
}

/**
 * 节点类
 */
class Node{
    int value;
    Node left;
    Node right;

    /**
     * 添加节点，并且保证为AVL
     * @param node
     */
    public void add(Node node){
        // 空节点-无需添加
        if (node == null){
            return;
        }
        //节点值小于根节点值，添加在左节点上
        if (node.value < this.value){
            //空-则直接添加
            if (this.left == null){
                this.left = node;
            }
            // 不为空，则递归调用至空，添加
            else {
                this.left.add(node);
            }
        }
        //节点值大约等于当前节点
        else {
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
        //添加完成后判断是否需要旋转，构成AVL二叉树
        //右边大于左边，则进行左旋
        if (rightHeight() - leftHeight() > 1){
            if (right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }
            else {
                leftRotate();
            }
            return;
        }
        if (leftHeight() - rightHeight() > 1){
            if (left != null && left.rightHeight() > left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }else {
                rightRotate();
            }
        }
    }

    /**
     * 右旋转 类似左旋转
     */
    private void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     * 左旋转
     */
    private void leftRotate(){
        //以当前节点的值，创建新的节点
        Node newNode = new Node(value);
        //新节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //新节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //当前节点的值替换成右子节点的值
        value = right.value;
        //当前节点的右子树设置成右子树的右子树
        right = right.right;
        //当前节点的左子树设置成新节点
        left = newNode;
    }

    /**
     * 返回右子树的高度
     * @return
     */
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }

    /**
     * 返回左子树的高度
     * @return
     */
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    /**
     * 返回已该节点为根节点的树的高度
     * @return
     */
    private int height() {
        return Math.max(left == null? 0:left.height(),right == null ? 0:right.height()) + 1;
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}