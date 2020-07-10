package com.datastructure.recursion;

import sun.misc.resources.Messages_zh_CN;

import java.util.Map;

public class Maze {
    public static void main(String[] args) {
//        创建二维数组，并初始化迷宫,1表示墙
        int[][] maze = new int[8][7];
        for (int i = 0;i < 7;i++){
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for (int i = 0;i < 6;i++){
            maze[i][0] = 1;
            maze[i][6] = 1;
        }
        maze[3][1] = 1;
        maze[3][2] = 1;
//        查看初始化结果
        System.out.println("迷宫初始化结果：");
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        setWay(maze,1,1);
        System.out.println("走过的路径");
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 当道道map左下角位置时，表示成功
     * 2表示可以走，3表示走不通，初始为0表示未走过
     * 策略按照 下 右 上 左
     * @param map 二维迷宫
     * @param i 初始行
     * @param j 初始列
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5] == 2){//本例使用上述所示map，故将结果写定
            return true;
        }else {
            if (map[i][j] == 0){ //未走过，按照策略进行尝试
                map[i][j] = 2;
                if (setWay(map,i+1,j)){ //下
                    return true;
                }else if (setWay(map,i,j+1)){ //右
                    return true;
                }else if (setWay(map,i-1,j)){ //上
                    return true;
                }else if (setWay(map,i,j-1)){ //左
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }

    }

}
