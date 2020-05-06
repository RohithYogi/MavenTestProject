package com.calculator;

import java.util.Scanner;

public class Calculator {

	public static void main(String[] args){

		Scanner in = new Scanner(System.in);

		System.out.println("Welcome! This is Calculator v1.0");

		while(true) {
			System.out.println("Choose what to do :");
			System.out.println("1 - Addition\n2 - Subtraction\n0 - Exit");
			System.out.println("Please choose option [0 - 2]");
			int option = in.nextInt();

			if(option < 0 || option > 2){
				System.out.println("Invalid selection: " + Integer.toString(option) + "\nPlease try again!");
				continue;
			}

			if(option == 0) {
				break;
			}
			else if (option == 1) {
				int num1, num2;
				System.out.println("Please enter first number: ");
				num1 = in.nextInt();
				System.out.println("Please enter second number: ");
				num2 = in.nextInt();
				System.out.println("Result: " + Integer.toString(Calculator.add(num1, num2)));
			}
			else {
				int num1, num2;
				System.out.println("Please enter first number: ");
				num1 = in.nextInt();
				System.out.println("Please enter second number: ");
				num2 = in.nextInt();
				System.out.println("Result: " + Integer.toString(Calculator.subtract(num1, num2)));
			}
		}

	}

	public static int add(int a, int b) {
		return a + b;
	}

	public static int subtract(int a, int b) {
		return a - b;
	}

	// public static long multiply(int a, int b) {
	// 	return a * b;
	// }
	//
	// public static double divide(int a, int b) {
	// 	double result;
	// 	if (b == 0) {
	// 		throw new IllegalArgumentException("Divisor cannot divide by zero");
	// 	} else {
	// 		result = Double.valueOf(a)/Double.valueOf(b);
	// 	}
	// 	return result;
	// }
}
