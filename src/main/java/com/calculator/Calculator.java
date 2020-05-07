package com.calculator;

import java.util.Scanner;

public class Calculator {

	public static void main(String[] args){

		Scanner in = new Scanner(System.in);

		System.out.println("Calculator");

		while(true) {
			System.out.println("Select an option:");
			System.out.println("\t1 - Addition\n\t2 - Subtraction\n\t3 - Multiplication\n\t4 - Division\n\t0 - Exit");
			System.out.println("Select an option:");
			int option = 0;
			if(in.hasNext()) option = in.nextInt();

			if(option < 0 || option > 4){
				System.out.println("Not a valid selection: " + Integer.toString(option) + "\ntry again!!");
				continue;
			}

			if(option == 0) {
				break;
			}
			else{
				int num1 = 1, num2 = 1;
				System.out.println("Enter first number: ");
				if(in.hasNext()) num1 = in.nextInt();
				System.out.println("Enter second number: ");
				if(in.hasNext()) num2 = in.nextInt();

				switch (option) {
					case 1:
						System.out.println("Result: " + Integer.toString(Calculator.add(num1, num2)));
						break;
					case 2:
						System.out.println("Result: " + Integer.toString(Calculator.subtract(num1, num2)));
						break;
					case 3:
						System.out.println("Result: " + Long.toString(Calculator.multiply(num1, num2)));
						break;
					case 4:
						System.out.println("Result: " + Double.toString(Calculator.divide(num1, num2)));
						break;
					default:
						System.out.println("Not valid");
				}
			}
		}

	}

	public static int add(int a, int b) {
		return a + b;
	}

	public static int subtract(int a, int b) {
		return a - b;
	}

	public static long multiply(int a, int b) {
		return a * b;
	}
	
	public static double divide(int a, int b) {
		double result;
		if (b == 0) {
			throw new IllegalArgumentException("Divisor cannot divide by zero");
		} else {
			result = Double.valueOf(a)/Double.valueOf(b);
		}
		return result;
	}
}
