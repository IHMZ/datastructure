package com.datastructure.hashtable;

import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.text.AbstractDocument;

public class HashTableDemo {


}

/**
 * 哈希表
 */
class HashTab{
    private NodeLinkList[] nodeLinkListArray;
    private int size;

    public HashTab(int size) {
        this.size = size;
        nodeLinkListArray = new NodeLinkList[size];
        for (int i = 0; i < size; i++) {
            nodeLinkListArray[i] = new NodeLinkList();
        }
    }

    public int hashFun(int id){
        return id % size;
    }

    public void add(Node node){
        int nodeLinkListNo = hashFun(node.id);
        nodeLinkListArray[nodeLinkListNo].add(node);
    }

    public void list(){
        for (int i = 0; i < size; i++) {
            nodeLinkListArray[i].list(i);
        }
    }

    public void findById(int id){
        int nodeLinkListNo = hashFun(id);
        Node node = nodeLinkListArray[nodeLinkListNo].findById(id);
        if (node != null){
            System.out.println(nodeLinkListNo + "中找到该节点");
        }else {
            System.out.println("未找到该雇员");
        }
    }

}


/**
 * 链表
 */
class NodeLinkList{
    private Node head;

    public void add(Node node){
        if (head == null){
            head = node;
            return;
        }
        Node currentNode = head;
        while (true){
            if (currentNode.next == null){
                break;
            }
            currentNode = currentNode.next;
        }
        currentNode.next = node;
    }

    public void list(int i){
        if (head == null){
            System.out.println("空链表");
            return;
        }
        System.out.println("第" + i + "条链表信息为");
        Node currentNode = head;
        while (true){
            System.out.print(currentNode + "\t");
            if (currentNode.next == null){
                break;
            }
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public Node findById(int id){
        if (head == null){
            System.out.println("空表");
            return null;
        }
        Node currentNode = head;
        while (true){
            if (currentNode.id == id){
                break;
            }
            if (currentNode.next == null){
                currentNode = null;
                break;
            }
            currentNode = currentNode.next;
        }
        return currentNode;
    }

}

/**
 * 节点表
 */
class Node{
    public int id;
    public int name;
    public Node next;
    public Node(int id, int name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}


