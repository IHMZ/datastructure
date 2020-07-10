package com.datastructure.tree;

public class ArrayBinaryDemo {
    public static void main(String[] args) {

    }
}

class ArrayBinaryTree{
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    public void preOrder(){
        preOrder(0);
    }

    /**
     * 前序遍历
     * @param index
     */
    public void preOrder(int index){
        if (array == null || array.length == 0){
            System.out.println("空");
        }
        System.out.println(array[index]);
        if ((index * 2 + 1) < array.length){
            preOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < array.length){
            preOrder(index * 2 + 2);
        }
    }
}
