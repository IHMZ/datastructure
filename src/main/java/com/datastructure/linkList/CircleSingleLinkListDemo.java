package com.datastructure.linkList;

import java.net.HttpRetryException;
import java.net.SocketTimeoutException;

public class CircleSingleLinkListDemo {

    public static void main(String[] args) {
        SingleCircleLinkList singleCircleLinkList = new SingleCircleLinkList();
        singleCircleLinkList.add(7);
        singleCircleLinkList.show();
        singleCircleLinkList.nodeOut(1,2,7);
    }

}

/**
 * 单向循环链表
 */
class SingleCircleLinkList{

    private SingleCircleNode first;

    /**
     * 创建指定大小的单向循环链表
     * @param num
     */
    public void add(int num){
//        判断num是否大于1
        if (num < 1){
            System.out.println("num异常");
            return;
        }
//        创建辅助指针
        SingleCircleNode currentNode = null;
        for (int i = 0;i < num; i++){
            SingleCircleNode node = new SingleCircleNode(i + 1);
            if (i == 0){ //第一个节点
                first = node;
                first.setNext(first);
                currentNode = first;
            }else {
                currentNode.setNext(node);
                node.setNext(first);
                currentNode = node;
            }
        }

    }

    /**
     * 遍历链表
     */
    public void show(){
        if (first == null){
            System.out.println("列表为空");
            return;
        }
        SingleCircleNode currentNode = first;
        while (true){
            System.out.println(currentNode);
            if (currentNode.getNext() == first){
                break;
            }
            currentNode = currentNode.getNext();
        }
    }

    /**
     * 约瑟夫环问题
     * @param startNo 开始位置
     * @param countNum 间隔个数
     * @param nums 初始大小
     */
    public void nodeOut(int startNo,int countNum,int nums){
        if (first == null || startNo < 1 ||startNo >nums){
            System.out.println("参数有误");
            return;
        }
//        辅助指针指向first前一个节点
        SingleCircleNode helper = first;
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

//       将first移动至初始位置前两个位置，因first本身位置为1
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true){
            if (helper == first){
                break; //只剩一个节点
            }
//            移动指针，first指向要出去的节点 first本身也需要算1次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println(first.getNo());

    }
}

/**
 * 单项节点类
 */
class SingleCircleNode{

    private int no;
    private SingleCircleNode next;

    public int getNo() {
        return no;
    }

    @Override
    public String toString() {
        return "SingleCircleNode{" +
                "no=" + no +
                '}';
    }

    public void setNo(int no) {
        this.no = no;
    }

    public SingleCircleNode(int no) {
        this.no = no;
    }

    public SingleCircleNode getNext() {
        return next;
    }

    public void setNext(SingleCircleNode next) {
        this.next = next;
    }
}
