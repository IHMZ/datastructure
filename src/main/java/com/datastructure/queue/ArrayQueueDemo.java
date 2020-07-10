package com.datastructure.queue;

/**
 * 数组模拟队列
 */
public class ArrayQueueDemo {


}

/**
 * 循环数组模拟队列
 */
class CircleArrayQueue{

    private int rear; //0 指向最后一个元素后一个位置
    private int front; //0 指向第一个元素
    private int maxSize;
    private int[] array;

    public CircleArrayQueue(int max) {
        maxSize = max;
        array = new int[maxSize]; //会预留一个位置
    }
    //    判断队列是否满
    public boolean isFull(){
        return (rear+1) % maxSize == front;
    }
    //    判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //    添加数据到队列中
    public void add(int num){
        if (isFull()){
            throw  new RuntimeException("队列已满");
        }
        array[rear] = num;
        rear = (rear + 1) % maxSize;

    }
    //    获取数据
    public int get(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int frontNum = array[front];
        front = (front + 1) % maxSize;
        return frontNum;
    }

//    获取队列中元素个数
    public int size(){
        return (rear + maxSize - front)% maxSize;
    }

    //    显示数据
    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        for (int i = front; i < front + size();i ++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,array[i%maxSize]);
        }
    }
    //    显示队列头数据
    public int getHead(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return array[front];
    }
}

/**
 * 数组模拟队列，但是不能复用
 */
class ArrayQueue{

    private int rear;
    private int front;
    private int maxSize;
    private int[] array;

    public ArrayQueue(int max) {
        rear = -1;
        front = -1;
        maxSize = max;
        array = new int[maxSize];
    }
//    判断队列是否满
    public boolean isFull(){
        return rear == maxSize -1;
    }
//    判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
//    添加数据到队列中
    public void add(int num){
        if (isFull()){
            throw  new RuntimeException("队列已满");
        }
        rear++;
        array[rear] = num;
    }
//    获取数据
    public int get(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return array[front];
    }
//    显示数据
    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        for (int i : array) {
            System.out.printf("%d\t",i);
        }
    }
//    显示队列头数据
    public int getHead(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return array[front+1];
    }
}


