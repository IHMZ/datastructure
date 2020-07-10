package com.datastructure.huffmancode;

import java.util.ArrayList;
import java.util.Collections;

public class CodeDemo {
    public static void main(String[] args) {

    }


}


//class HuffmanTreeDemo {
//    public static void main(String[] args) {
//        int array[] = {32,34,13};
//        HNode huffmanTree = createHuffmanTree(array);
//        preOrder(huffmanTree);
//
//    }
//
//    public static void preOrder(HNode root){
//        if (root != null){
//            root.preOrder();
//        }else {
//            System.out.println("空树");
//        }
//    }
//
//    public static HNode createHuffmanTree(int[] arr){
//        //将数组存入集合中
//        ArrayList<HNode> hNodes = new ArrayList<>();
//        for (int i : arr) {
//            hNodes.add(new HNode(i));
//        }
//        //依次取出，放入数
//        while (hNodes.size() > 1){
//            Collections.sort(hNodes); //集合进行排序
//            HNode leftNode = hNodes.get(0); //取出最小的两个
//            HNode rightNode = hNodes.get(1);
//            //根据取出的创建新节点
//            HNode parent = new HNode(leftNode.weight + rightNode.weight);
//            parent.left = leftNode;
//            parent.right = rightNode;
//            //集合中删除处理过的二叉树
//            hNodes.remove(leftNode);
//            hNodes.remove(rightNode);
//            //加入parent
//            hNodes.add(parent);
//        }
//        //遍历结束后返回跟节点
//        return hNodes.get(0);
//    }
//}

/**
 * 节点类
 */
class HNode implements Comparable<HNode>{

    Byte data;
    int weight;
    HNode left;
    HNode right;

    public HNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "HNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(HNode o) {
        return this.weight - o.weight;
    }
}