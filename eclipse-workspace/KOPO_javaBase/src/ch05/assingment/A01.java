package ch05.assingment;

import java.util.Scanner;

public class A01 {

	public static void main(String[] args) {
		A01 a01 = new A01();
		
		int[] inputNum = a01.input();
		
		a01.printPermutation(inputNum);
	}

	/* input integer n, r and check validation
	 * 
	 * return : an array of input value n, r
	 */
	public int[] input() {
		Scanner sc = new Scanner(System.in);
		
		int[] input = new int[2];
		while (true) {
			System.out.print("input n : ");
			input[0] = sc.nextInt();
			System.out.print("input r : ");
			input[1] = sc.nextInt();
			
			if (input[0] > input[1])
				break;
			else
				System.out.println("wrong input - n must be bigger than r");
		}
		return input;
	}

	/* calculate Permutation using factorial 
	 * AND print total result as given format
	 * 
	 * parameter : an array of input value n, r 
	 */
	public void printPermutation(int[] inputNum) {
		A01 a01 = new A01();
		
		System.out.print("P(" + inputNum[0] + ", " + inputNum[1] + ")");
		System.out.print(" = " + inputNum[0] + "! / (" + inputNum[0] + " - " + inputNum[1] + ")! = ");
		a01.printFactorialExp(inputNum[0]);
		System.out.print(" / ");
		a01.printFactorialExp(inputNum[0] - inputNum[1]);
		System.out.print(" = ");
		System.out.print(a01.factorialVal(inputNum[0]));
		System.out.print(" / ");
		System.out.print(a01.factorialVal(inputNum[0] - inputNum[1]));
		System.out.print(" = ");
		System.out.print(a01.factorialVal(inputNum[0]) / a01.factorialVal(inputNum[0] - inputNum[1]));
	}

	/* recursive function to calculate factorial
	 * 
	 * parameter : a multiplication factor of n-factorial , n is the input value
	 * return : a value of num-factorial
	 */
	public int factorialVal(int num) {
		if (num == 1)
			return 1;
		else
			return num * factorialVal(--num);
	}
	
	/* print formula about factorial of num
	 * 
	 * parameter : a number to print formula about factorial of num
	 */
	private void printFactorialExp(int num) {
		System.out.print("(");
		
		for (int i = num ; i > 0 ; i--) {
			System.out.print(i);
			if (i != 1)
				System.out.print(" X ");
		}
		
		System.out.print(")");
	}
	
}
