package com.datastructure.stack;

import javax.management.NotificationEmitter;

public class ArrayStackDemo {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        LinkListStack linkListStack = new LinkListStack();
        System.out.println("初始栈");
        linkListStack.list();

        linkListStack.push(node1);
        linkListStack.push(node2);
        linkListStack.push(node3);
        System.out.println("3个节点");
        linkListStack.list();
        System.out.println("出栈");
        System.out.println(linkListStack.pop());
        System.out.println(linkListStack.pop());
        System.out.println(linkListStack.pop());

        linkListStack.push(node4);
        linkListStack.push(node5);
        linkListStack.push(node6);
        System.out.println("最后3个");
        linkListStack.list();

    }

}

/**
 * 数组实现栈
 */
class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1; //栈顶，初始为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

//    判断是否满
    public boolean isFull(){
        return maxSize == top + 1;
    }
//    栈空
    public boolean isEmpty(){
        return top == -1;
    }
//    出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("空栈");
        }
        int value = stack[top--];
        return value;
    }
//    入栈
    public void push(int num){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = num;
    }

//    遍历
    public void list(){
        if (isEmpty()){
            System.out.println("空栈");
            return;
        }
//        从栈顶开始出栈
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}

/**
 * 链表实现栈
 */
class LinkListStack{
    private Node headNode; //链表头
    private Node topNode; //栈顶

    public LinkListStack() {
        this.headNode = new Node();
        this.topNode = headNode;
    }

//    空栈
    public boolean isEmpty(){
        return headNode == topNode;
    }
//    入栈
    public void push (Node node){
        topNode.setNext(node);
        node.setPrev(topNode);
        topNode = node;
    }
//    出栈
    public Node pop(){
        if (isEmpty()){
            throw new RuntimeException("空");
        }
        Node popValue = topNode;
        topNode.getPrev().setNext(null);
        topNode = topNode.getPrev();
        return popValue;
    }

//    遍历
    public void list(){
        if (isEmpty()){
            System.out.println("空栈");
            return;
        }
        Node currentNode = topNode;
        while (currentNode != headNode){
            System.out.println(currentNode);
            currentNode = currentNode.getPrev();
        }
    }
}

/**
 * 链表节点
 */
class Node{

    private int no;
    private Node next;
    private Node prev;

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node() {
    }

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}
