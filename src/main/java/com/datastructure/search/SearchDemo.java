package com.datastructure.search;

import jdk.internal.org.objectweb.asm.tree.LocalVariableAnnotationNode;

import javax.swing.*;
import java.lang.reflect.Array;
import java.security.Key;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchDemo {
    public static void main(String[] args) {
        int[] array = {1,2,3,5,5,7,8,8,8,9,10,11,11,15};
//        List<Integer> integers = binarySearch(array, 0, array.length - 1, 4);
//        List<Integer> list = insertSearch(array, 0, array.length - 1, 4);
//        System.out.println(list);
//        System.out.println(integers);
        System.out.println(fibSearch(array, 15));
    }

    public static int fibSearch(int[] array,int value){
        int low = 0;
        int high = array.length - 1;
        int k = 0; //指定斐波那契数列的下标
        int mid = 0;
        int f[] = fib(20); //获取一个斐波那契数列

        //找出当前数组的长度满足一个斐波那契数组中的值，并将数组扩容至该长度
        while (high > f[k] - 1){
            k++;
        }
        int[] temp = Arrays.copyOf(array,f[k]); //不足部分0填充
        for (int i = high + 1; i < temp.length; i++) { //考虑查找的数列实际为有序书列，最后填充应填充最后值
            temp[i] = array[high];
        }
        //遍历查找
        while (low <= high){
            mid = low + f[k-1] - 1; //mid选取斐波那契数列数列中的数字进行分割，每个数的与前一个数的比值都是黄金比例
            if (value < temp[mid]){ //若小于，则位于左边，向左移动
                high = mid -1;
                k--; //找到当前比例中的黄金分割点
            }else if (value > temp[mid]){ //若大于，则在右侧，需向右侧移动
                low = mid + 1;
                k -= 2;  // 因f[i] = f[i - 1] + f[i - 2]，故后半部分为f[i - 2]
            }else {
                //判断是否在原数组内找到
                if (mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }

    /**
     * 创建指定长度的斐波那契数组
     * @param fibSize
     * @return
     */
    public static int[] fib(int fibSize){
        int[] f = new int[fibSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < fibSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 插入查找
     * @param array
     * @param left
     * @param right
     * @param find
     * @return
     */
    public static List<Integer> insertSearch(int[] array,int left,int right,int find){
        if (left > right || find < array[left] || find > array[right]){ //终止条件
            return null;
        }
        int mid = left + (right -left) * (find - array[left]) / (array[right] - array[left]); //定义中间位置
        int midVal = array[mid]; //定义中间值
        if (find > midVal){  //比中间值大，则在右边，向右进行查找
            return insertSearch(array,mid + 1,right,find);
        }
        else if (find < midVal){  //比中间小，则在左边，在左边进行查找
            return insertSearch(array,left, mid -1, find);
        }
        else {  //既不大于，也不小于，则找到该值
            List<Integer> results = new ArrayList<>(); //创建返回数组
            results.add(mid); //放入找到的
            int temp = mid - 1; //判断左边是否有相同值
            while (true){
                if (temp < 0 || array[temp] != find){
                    break;
                }
                results.add(temp--);
            }
            temp = mid + 1; //判断右边是否有相同值
            while (true){
                if (temp > array.length - 1|| array[temp] != find){
                    break;
                }
                results.add(temp++);
            }
            return results; //返回结果
        }
    }

    /**
     *  二分查找
     * @param array
     * @param left
     * @param right
     * @param find
     * @return
     */
    public static List<Integer> binarySearch(int[] array,int left,int right,int find){
        if (left > right){ //终止条件
            return null;
        }
        int mid = (left + right) / 2; //定义中间位置
        int midVal = array[mid]; //定义中间值
        if (find > midVal){  //比中间值大，则在右边，向右进行查找
            return binarySearch(array,mid + 1,right,find);
        }
        else if (find < midVal){  //比中间小，则在左边，在左边进行查找
            return binarySearch(array,left, mid -1, find);
        }
        else {  //既不大于，也不小于，则找到该值
            List<Integer> results = new ArrayList<>(); //创建返回数组
            results.add(mid); //放入找到的
            int temp = mid - 1; //判断左边是否有相同值
            while (true){
                if (temp < 0 || array[temp] != find){
                    break;
                }
                results.add(temp--);
            }
            temp = mid + 1; //判断右边是否有相同值
            while (true){
                if (temp > array.length - 1|| array[temp] != find){
                    break;
                }
                results.add(temp++);
            }
            return results; //返回结果
        }
    }




}
