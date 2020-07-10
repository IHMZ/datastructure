package com.datastructure.linkList;


import com.sun.deploy.panel.NodeBorder;

import javax.management.NotificationEmitter;
import java.util.Stack;

public class SingleLinkListDemo {

    public static void main(String[] args) {
        SingleLinkList singleLinkList =  new SingleLinkList();
        SingleLinkList newSingleLinkList =  new SingleLinkList();
        Node node1 = new Node(1,"张三","北京");
        Node node2 = new Node(2,"李四","上海");
        Node node3 = new Node(3,"王五","广州");
        Node node4 = new Node(4,"赵六","深圳");
        Node node5 = new Node(5,"新张三","北京");
        Node node6 = new Node(6,"新李四","上海");
        Node node7 = new Node(7,"新王五","广州");
        Node node8 = new Node(8,"新赵六","深圳");

        singleLinkList.add(node1);
        singleLinkList.add(node3);
        singleLinkList.add(node5);
        singleLinkList.add(node7);
        newSingleLinkList.add(node2);
        newSingleLinkList.add(node4);
        newSingleLinkList.add(node6);
        newSingleLinkList.add(node8);


//        singleLinkList.orderAdd(node4);
//        singleLinkList.orderAdd(node3);
//        singleLinkList.orderAdd(node1);
//        singleLinkList.orderAdd(node2);

//        singleLinkList.update(new Node(2,"小李","魔都"));

//        singleLinkList.delete(1);
//        singleLinkList.delete(4);
//        singleLinkList.delete(3);
//        singleLinkList.delete(2);
//        reverseLinkList(singleLinkList.getHead());
//        reversePrint(singleLinkList.getHead());

        SingleLinkList sort2LinkList = sort2LinkList(singleLinkList.getHead(), newSingleLinkList.getHead());
//        singleLinkList.list();
        sort2LinkList.list();
    }

//    反转链表
    public static void reverseLinkList(Node headNode){
//        如果为空或者只有一个数据则不需要反转
        if (headNode.next == null || headNode.next.next == null){
            return;
        }
//        定义辅助信息
        Node cur = headNode.next; //当前节点
        Node next = null; //下一个节点
        Node reverseHead = new Node(0,null, null); //反转链表头节点
        while (cur != null){
//            记录当前节点下一个节点，将当前节点的next指向反转节点头节点的next，（断开原链表的连接，移动到反转节点的头），
//            再将反转链表的头，指向当前节点，形成新的链表，当前节点后移
//            先右边连上，再左边连上，每次都从头节点断开
            next = cur.next; //记录下一个节点
            cur.next = reverseHead.next; // 当前节点的下一个节点指向 反转节点 【头】节点 的下一个节点
            reverseHead.next = cur; // 反转节点【头】节点指向当前节点
            cur = next; //后移
        }
        headNode.next = reverseHead.next;
    }
//    逆序打印
    public static void reversePrint(Node headNode){
        if (headNode.next == null){
            System.out.println("空链表");
            return;
        }
        Stack<Node> nodeStack = new Stack<>();
        Node temp = headNode.next;
        while (temp != null){
            nodeStack.push(temp);
            temp = temp.next;
        }
        while (!nodeStack.empty()){
            System.out.println(nodeStack.pop());
        }
    }

    /**
     * 双链表排序
     * @param node1 第一个链表头节点 有序链表
     * @param node2 第二个链表头节点 有序链表
     * @return 新链表
     */
    public static SingleLinkList sort2LinkList(Node node1,Node node2){
        SingleLinkList newSingleList = new SingleLinkList();
        //此处不考虑链表为空的情况,不考虑重复情况
        Node temp1 = node1.next;
        Node temp2 = node2.next;
        Node next = null;
        Node cur = newSingleList.getHead();  //当前节点位置
//        都不为空遍历比较进行连接
        while (temp1 != null || temp2 != null){
            while (temp1 != null && temp2 != null){
                if (temp1.no < temp2.no){
                    next = temp1.next;
                    cur.next = temp1;
                    temp1 = temp1.next;
                }else {
                    next = temp2.next;
                    cur.next = temp2;
                    temp2 = temp2.next;
                }
                cur = cur.next;

            }
//            存在一个为空时，全部连接在最后即可
            if (temp1 == null){
                cur.next = temp2;
                break;
            }
            if (temp2 == null){
                cur.next = temp1;
                break;
            }
        }
        return newSingleList;
    }

}

/**
 * 单向链表类
 */
class SingleLinkList{
//    链表头，不存放信息，只是指向下一个节点
    private Node head = new Node(0,null, null);

    public Node getHead() {
        return head;
    }

    //    添加数据
    public void add(Node node){
//        临时变量，遍历链表
        Node temp = head;
        while (true){
//            为空则进行赋值添加，不为空则至下一个节点
            if (temp.next == null){
                temp.next = node;
                break;
            }
            temp = temp.next;
        }
    }
//    数据添加按照一定的顺序
    public void orderAdd(Node node){
//        判断是否相等
        boolean flag = false;
//        定义临时变量
        Node temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > node.no){
                break;
            }if (temp.next.no == node.no){
                flag = true;
                break;
            }
//            上述条件都不满足的情况，temp指向下一个
            temp = temp.next;
        }
        if (flag){
            System.out.println("节点已存在");
        }else {
//            添加节点
            node.next = temp.next;
            temp.next = node;
        }
    }
//    修改数据
    public void update(Node node){
        if (head.next == null){
            System.out.println("链表为空，无法修改");
            return;
        }
        Node temp = head.next;
        boolean flag = false;
        while (true){
            if (temp.no == node.no){
                flag = true;
                break;
            }
            if (temp == null){
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = node.name;
            temp.nickName = node.nickName;
        }else {
            System.out.println("未找到该数据");
        }
    }
//    删除节点
    public void delete(int no){
        Node temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("未找到该数据");
        }
    }

//    遍历输出链表
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
        }else {
            Node temp = head;
            while (true){
                if (temp.next == null){
                    break;
                }
                System.out.println(temp.next);
                temp = temp.next;
            }
        }
    }



}

/**
 * 节点类
 */
class Node{
    public int no;
    public String name;
    public String nickName;
    public Node next;

    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

}
