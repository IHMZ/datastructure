package com.datastructure.tree.binarysorttree;



public class BinarySortTreeDemo {
    public static void main(String[] args) {

    }
}

class BinarySortTree{
    private Node root;

    public void delNode(int value){
        if (root == null){
            return; //空树，无需删除
        }
        else {
            Node delNode = search(value); //查找要删除的节点
            if (delNode == null){
                return; //未找到则无需删除
            }
            if (root.left == null && root.right == null){
                root = null;
                return;
            }
            Node parent = searchParent(value); //要删除节点的父节点
            if (delNode.left == null && delNode.right == null){ //删除节点为叶子节点
                if (parent.left != null && parent.left.value == value){ //左节点
                    parent.left = null;
                }else if (parent.right != null && parent.right.value == value){//右节点
                    parent.right = null;
                }
            }else if (delNode.left != null && delNode.right != null){ //删除节点有两个子节点
                int minValue = delRightTreeMin(delNode.right);
                delNode.value = minValue;
            }else { //删除只有一个子树的节点
                if (delNode.left != null){ //删除节点有一左节点
                    if (parent != null){
                        if (parent.left.value == value){ //在父节点的左子节点上
                            parent.left = delNode.left;
                        }else { //在父节点的右子节点上
                            parent.right = delNode.left;
                        }
                    }else {
                        root = delNode.left;
                    }

                }else { //删除节点有一右节点
                    if ( parent != null){
                        if (parent.left.value == value){
                            parent.left = delNode.right;
                        }else {
                            parent.right = delNode.right;
                        }
                    }else {
                        root = delNode.right;
                    }

                }
            }
        }
    }

    public int delRightTreeMin(Node node){
        Node delNode = node;
        while (delNode.left != null){
            delNode = delNode.left;
        }
        delNode(delNode.value);
        return delNode.value;
    }


    public Node searchParent(int value){
        if (root == null){
            return  null;
        }else {
            return root.searchParent(value);
        }
    }

    public Node search(int value){
        if (root == null){
            return null;
        }else {
            return root.search(value);
        }
    }

    public void add(Node node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }else {
            System.out.println("空树");
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

/**
 * 节点类
 */
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 查找节点的父节点
     * @param value
     * @return
     */
    public Node searchParent(int value){
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        }else {
            if (value < this.value && this.left != null){ //小于则往左侧查找
                return this.left.searchParent(value);
            }else if (value >= this.value && this.right != null){
                return this.right.searchParent(value);
            }else {return null;}

        }
    }

    /**
     * 查找节点
     * @param value
     * @return
     */
    public Node search(int value){
        if (value == this.value){ //当前接待你
            return this;
        }
        else if (value < this.value){ //小于，则左侧查找
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        }
        else {
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 按照顺序添加 中序
     * @param node
     */
    public void add(Node node){
        if (node == null){
            return;
        }
        if (node.value < this.value){ //小于当前节点，放在左侧
            if (this.left == null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }
        else {
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
