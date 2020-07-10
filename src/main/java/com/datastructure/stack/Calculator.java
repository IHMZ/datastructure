package com.datastructure.stack;

public class Calculator {

	public static void main(String[] args) {
		//运算表达式
		String expression = "7*2*2-5+1-5+3-4";
		//创建 符号栈 与数字栈
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		//定义辅助变量
		int index = 0;//运算表达式中的位置
		int num1 = 0; //第一个取出的数字 栈中弹出的数字
		int num2 = 0;//第二个取出的数字 栈中弹出的数字
		int oper = 0; //运算符
		int res = 0; //结果
		char ch = ' '; //取出的字符若
		String keepNum = "";
		while(true) {
			//取出一个字符
			ch = expression.substring(index, index+1).charAt(0);
			//判断是否为运算符
			if(operStack.isOper(ch)) {
				if(!operStack.isEmpty()) { // 不为空，则进行判断及运算
					if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						numStack.push(res);
						operStack.push(ch);
					} else {
//						运算符栈为空，则直接入栈
						operStack.push(ch);
					}
				}else {
					operStack.push(ch); // 1 + 3
				}
			} else {
//				不是运算符直接进行字符拼接，直到碰到运算符
				keepNum += ch;
//				判断是否到达运算的尾部
				if (index == expression.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
				}else{
//					查看下一位是否为运算符
					if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
						numStack.push(Integer.parseInt(keepNum));
						keepNum = "";
					}
				}
			}
			index++;
			if (index >= expression.length()) {
				break;
			}
		}

		while(true) {
			if(operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);
		}
		int res2 = numStack.pop();
		System.out.printf(" %s = %d", expression, res2);
	}

}


//数组实现栈
class ArrayStack2 {
	private int maxSize;
	private int[] stack;
	private int top = -1;// top 标识栈顶 初始为-1
	

	public ArrayStack2(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	
	//查看栈顶数据
	public int peek() {
		return stack[top];
	}
	
	//判断是否满
	public boolean isFull() {
		return top == maxSize - 1;
	}
	//判断是否空
	public boolean isEmpty() {
		return top == -1;
	}
	//放入数据
	public void push(int value) {
		if(isFull()) {
			System.out.println("已满");
			return;
		}
		top++;
		stack[top] = value;
	}
	//取出数据
	public int pop() {
		//判断是否为空
		if(isEmpty()) {
			throw new RuntimeException("空栈");
		}
		int value = stack[top];
		top--;
		return value;
	}
	//遍历
	public void list() {
		if(isEmpty()) {
			System.out.println("空栈");
			return;
		}
		for(int i = top; i >= 0 ; i--) {
			System.out.printf("stack[%d]=%d\n", i, stack[i]);
		}
	}
	//定义运算符的优先级
	public int priority(int oper) {
		if(oper == '*' || oper == '/'){
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1;
		}
	}

//	判断是否为操作符
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}
	//计算
	public int cal(int num1, int num2, int oper) {
		int res = 0;
		switch (oper) {
		case '+':
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1;
			break;
		case '*':
			res = num1 * num2;
			break;
		case '/':
			res = num2 / num1;
			break;
		default:
			break;
		}
		return res;
	}
	
}
