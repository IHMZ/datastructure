package com.datastructure.sparsearray;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
//        创建初始二维数组
        int[][] originalArr = new int[11][11];
//        查看原始二维数组
        for (int[] ints : originalArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
        System.out.println("放入数据后：");
//        放入数据
        originalArr[2][2] = 1;
        originalArr[3][3] = 2;
        originalArr[5][6] = 1;

        for (int[] ints : originalArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
//      转换为转换为稀疏数组
        int sum = 0; //记录总数据量
        for (int[] ints : originalArr) {
            for (int anInt : ints) {
                if (anInt != 0){
                    sum++;
                }
            }
        }
        System.out.println();
        System.out.println("一共存放的数据数量为：" + sum);
        int[][] sparseArr = new int[sum + 1][3];
//        初始化第一行数据
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int count = 0; //记录行数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (originalArr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = originalArr[i][j];
                }

            }
        }
//       遍历稀疏数组
        System.out.println();
        System.out.println("稀疏数组为：");
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
//        将稀疏数组恢复
        int[][] backArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            backArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
//        恢复后的数组
        System.out.println();
        System.out.println("恢复后的数组：");
        for (int[] ints : backArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }

    }
}
