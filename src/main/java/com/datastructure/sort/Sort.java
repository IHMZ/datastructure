package com.datastructure.sort;


import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] array = {3,6,19,1,18,2,11,33,-1};
        int[] arr = {3,3412,12341,542,1324,45,3413251,12531,134};
//        bubbleSort(array);
//        selectSort(array);
//        insertSort(array);
//        shellSort(array);
//        shellSortTwo(array);
//        quickSort(array,0, array.length - 1);
//        int[] temp = new int[array.length];
//        mergeSort(array,0,array.length - 1,temp);
//        radixSort(arr);
//        System.out.println(Arrays.toString(array));
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 堆排序
     * @param array
     */
    public static void heapSort(int array[]){
        int temp = 0;
        for (int i = array.length / 2 - 1;i >= 0;i--){ //i初始值为左叶子节点
            adjustHeap(array,i,array.length);
        }
        for (int j = array.length - 1;j>0;j--){ //堆顶跟堆底进行交换
            temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            adjustHeap(array,0, j); //交换完成后，除去最后一节点，重新构建大顶堆
        }
    }

    /**
     * 构建大顶堆
     * @param array
     * @param i
     * @param length
     */
    public static void adjustHeap(int array[],int i,int length){
        int temp = array[i];
        for (int k = i * 2 + 1;k < length;k = k * 2 + 1){ // i * 2 + 1 i节点的左子节点
            if (k + 1 < length && array[k] < array[k + 1]){ //判断左子节点与右子节点的大小，选出大节点
                k++;
            }
            if (array[k] > temp){ //子节点与父节点进行比较，选出大的
                array[i] = array[k];
                i = k;
            }else {
                break;
            }
        }
        array[i] = temp;
    }

    /**
     * 基数排序
     * @param array
     */
    public static void radixSort(int[] array){
        int max = array[0];
        //获取数组中最大数的位数
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }
        }
        int maxLength = (max + "").length();
        //定义二维数组，十进制，十个数，每个都是一个一维数组，用来存放放入桶中的数据
        int[][] bucket = new int[10][array.length];
        //定义一个一维数组，记录每个桶中存放的数据个数，索引代表桶的索引，值为数据的个数
        int[] bucketElementCounts = new int[10]; //10个桶，初始存放个数都为0
        //循环遍历排序
        for (int i = 0,n = 1; i < maxLength; i++,n*=10) {//根据最大位数，判断遍历几次
            for (int j = 0; j < array.length; j++) { //遍历数组中的数据
                int digitOfElement = array[j] / n % 10;  // 取出个 十 百 千...位的值
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j]; //将数据放入指定桶的，对位位置
                bucketElementCounts[digitOfElement]++; //对应桶的位置进行后移
            }
            //遍历每次放入桶中的数据，再次存入数组中
            int index = 0;
            for (int k = 0; k < 10; k++) { //遍历十个桶
                if (bucketElementCounts[k] != 0){ //判断桶中是否存入数据
                    for (int l = 0; l < bucketElementCounts[k]; l++) { //存在数据则取出，放入数组中
                        array[index++] = bucket[k][l];
                    }
                    //数据取出后，记录数置零
                    bucketElementCounts[k] = 0;
                }
            }
        }
    }

    /**
     * 拆分 + 合并
     * @param array
     * @param left
     * @param right
     */
    public static void mergeSort(int[] array,int left,int right,int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            mergeSort(array,left, mid,temp); //左边拆分
            mergeSort(array,mid+1,right,temp); //右边拆分
            merge(array,left,mid,right,temp); //合并
        }
    }

    /**
     * 合并
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] array,int left, int mid,int right,int[] temp){
        int l = left; //左侧有序序列索引
        int r = mid + 1; //右侧有序序列初始索引
        int t = 0; //temp数组的当前索引
        while (l <= mid && r <= right){ //遍历两边的数组，直到一边遍历完
            if (array[l] <= array[r]){ //左边小，将左边放入temp
                temp[t] = array[l++];
            }else { //右边小，将右边放入数组中
                temp[t] = array[r++];
            }
            t++;
        }
        //一边遍历完后，将另一边全部放入temp
        while (l <= mid){ //左
            temp[t++] = array[l++];
        }
        while (r <= right){ //右
            temp[t++] = array[r++];
        }
        //temp拷贝至原数组
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            array[tempLeft++] = temp[t++];
        }
    }

    /**
     * 快速排序
     * @param array
     * @param left
     * @param right
     */
    public static void quickSort(int[] array,int left,int right){
        int l = left; //左
        int r = right; //右
        int pivot = array[(left + right) / 2]; //中轴值
        int temp = 0; //临时变量，交换使用
        while ( l < r){
            //在左边找比中轴值大（等于）的数，l即为该值index
            while (array[l] < pivot){
                l += 1;
            }
            //在右边找比中轴值小（等于）的数，r即为该值的index
            while (array[r] > pivot){
                r -= 1;
            }
            //表示pivot左边全部小于，右边全部大于
            if (l >= r){
                break;
            }
            //交换
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;
            //交换完后，若左边值为中轴值，表示，右边找到的为中轴值，需左移
            if (array[l] == pivot){
                r -= 1;
            }
            //同上
            if(array[r] == pivot){
                l += 1;
            }
        }
        //此处可以退出，防止栈溢出
        if (l == r){
            l += 1;
            r -= 1;
        }
        //左边递归
        if (left < r){
            quickSort(array,left, r);
        }
        //右边递归
        if (right > l){
            quickSort(array,l,right);
        }

    }

    /**
     * 希尔排序 待优化
     * @param array
     */
    public static void shellSort(int[] array){
        int temp;
        for (int gap = array.length / 2;gap > 0; gap /= 2) { //依次进行分组,gap为步长
            for (int i = gap; i < array.length; i++) { //遍历每一组
                for (int j = i -gap;j >= 0;j -= gap){  //对每一组内部进行排序
                    if (array[j] >array[j+gap]){
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序 优化后
     * @param array
     */
    public static void shellSortTwo(int[] array){
        for (int gap = array.length / 2; gap > 0; gap /= 2) { //确定步长
            for (int i = gap; i < array.length; i++) { //类似插入排序
                int j = i;
                int temp = array[j];
                if (array[j] < array[j-gap]){
                    while (j- gap >= 0 && temp < array[j-gap]){
                        array[j] = array[j-gap];
                        j -= gap;
                    }
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * @param array
     */
    public static void insertSort(int[] array){
        int insertValue; //要插入的数据
        int insertIndex; //插入数据的位置的前一位，比较大小，找到第一个比自己小的，查到该位置的前一个
        for (int i = 1; i < array.length; i++) { //从第二个数据开始，往前进行比较插入，
            insertValue = array[i];  //获取当前值
            insertIndex = i - 1; //记录原本index
            while (insertIndex >= 0 && insertValue < array[insertIndex]){
                array[insertIndex + 1] = array[insertIndex]; //将当前值赋值为前一个值，即数组右移一位
                insertIndex--; //小于0，则插入最前，否则
            }
            //判断是否需要更换位置进行插入
            if (insertIndex + 1 != i){
                array[insertIndex + 1] = insertValue;
            }
        }
    }

    /**
     * 选择排序
     * @param array
     */
    public static void selectSort(int[] array){
        for (int i = 0; i < array.length - 1 ; i++) {
            //最小值假定为第i个
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length ; j++) {
                if (min > array[j]){ //比较出最小的并进行记录
                    min = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                //交换
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
    }

    /**
     * 冒泡排序
     * @param array
     */
    public static void bubbleSort(int[] array){
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                //从小到大,前面的大于后面的则进行交换
                if (array[j] > array[j+1]){
                    flag = true;
                    temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
            if (flag){
                flag = false;
            }else {
                break;
            }
        }
    }
}
