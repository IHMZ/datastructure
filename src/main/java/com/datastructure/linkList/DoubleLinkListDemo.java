package com.datastructure.linkList;

public class DoubleLinkListDemo {
    public static void main(String[] args) {
        DoubleNode node1 = new DoubleNode(1,"张三","北京");
        DoubleNode node2 = new DoubleNode(2,"李四","上海");
        DoubleNode node3 = new DoubleNode(3,"王五","广州");
        DoubleNode node4 = new DoubleNode(4,"赵六","深圳");

        DoubleLinkList doubleLinkList = new DoubleLinkList();
        doubleLinkList.add(node1);
        doubleLinkList.add(node2);
        doubleLinkList.add(node3);
        doubleLinkList.add(node4);

        doubleLinkList.list();

        DoubleNode node5 = new DoubleNode(2,"老李四","新上海");

        doubleLinkList.update(node5);
        System.out.println("修改后");
        doubleLinkList.list();

        System.out.println("删除后");
        doubleLinkList.delete(4);
        doubleLinkList.list();

        DoubleLinkList doubleLinkListOrder = new DoubleLinkList();
        doubleLinkListOrder.orderAdd(node3);
        doubleLinkListOrder.orderAdd(node2);
        doubleLinkListOrder.orderAdd(node1);
        doubleLinkListOrder.orderAdd(node4);
        System.out.println("按照顺序添加：");
        doubleLinkListOrder.list();


    }



}

/**
 * 双向链表类
 */
class DoubleLinkList{
    //    链表头，不存放信息，只是指向下一个节点
    private DoubleNode head = new DoubleNode(0,null, null);

    public DoubleNode getHead() {
        return head;
    }

    /**
     * 添加数据默认添加至末尾
     * @param node
     */
    public void add(DoubleNode node){
//        临时变量，遍历链表
        DoubleNode temp = head;
        while (true){
//            为空则进行赋值添加，不为空则至下一个节点
            if (temp.next == null){
                temp.next = node;
//                添加node的前一个节点
                node.prev = temp;
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * 数据添加按照一定的顺序
     * @param node
     */
    public void orderAdd(DoubleNode node){
//        判断是否相等的标识
        boolean flag = false;
//        定义临时变量
        DoubleNode temp = head;
        while (true){
            if (temp.next == null){ //添加至末尾
                break;
            }
            if (temp.next.no > node.no){ //添加至中间
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
            if (temp.next != null){
//                双向链表每两个节点之间有两个连接，若添加至中间，则要注意
//                断开顺序，应先将新节点连上，再从找到的最前面断开，最后断开找到的节点
//                本程序，添加位置为temp.next的前一个,及添加位置为temp后，
//                故最后断开temp.next,避免链表断开，出现空指针
                node.next = temp.next;  //添加的下一个即为temp的下一个
                node.prev = temp; //添加的上一个即为temp
                temp.next.prev = node; //temp下一个的上一个即为添加
                temp.next = node;  //temp的下一个连上node
            }else {
                temp.next = node;
                node.prev = temp;
            }

        }
    }

    /**
     * 修改数据 根据DoubleNode中的no属性
     * @param node
     */
    public void update(DoubleNode node){
        DoubleNode temp = head;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == node.no){
                flag = true;
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

    /**
     * 根据no删除节点
     * @param no
     */
    public void delete(int no){
        DoubleNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }if (temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.prev.next  = temp.next;
//            判断是否为最后一个
            if (temp.next != null){
                temp.next.prev = temp.prev;
            }
        }else {
            System.out.println("未找到该数据");
        }
    }

    /**
     * 遍历链表
     */
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
        }else {
            DoubleNode temp = head;
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
class DoubleNode{
    public int no;
    public String name;
    public String nickName;
    public DoubleNode next;
    public DoubleNode prev;

    public DoubleNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
