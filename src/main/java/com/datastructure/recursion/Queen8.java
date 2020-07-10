package com.datastructure.recursion;

public class Queen8 {
    
    int max = 8; //8皇后
    int[] array = new int[max];
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.put(0);

    }

    /**
     * 放置皇后
     * @param n 第n个皇后
     */
    private void put(int n){
        if (n == max){
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)){
                put(n+1);
            }
        }
    }


    /**
     *  判断是否冲突
     * @param n 第n个皇后
     * @return
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[i] -array[n])){
                return false;
            }
        }
        return true;
    }

    /**
     * 打印结果
     */
    private void print(){
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
