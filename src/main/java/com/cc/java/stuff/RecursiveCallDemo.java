package com.cc.java.stuff;

/**
 * @author cclaudiu
 * 
 */
public class RecursiveCallDemo {

	// if number == 3 ---> 3 * 2 * 1
	static int getFactorial(int number) {
		if(number == 1) {
			return number;
		} else {
			return number * getFactorial(number - 1);
		}
	}

	// finalNumber = 3 + 2 + 1
	static int sumUp(int bigNumber) {
		if(bigNumber == 0) {
			return bigNumber;
		}

		return bigNumber + sumUp(bigNumber - 1);
	}

	public static void main(String[] args) {
		System.out.println(getFactorial(3));

		System.out.println(sumUp(3));
	}
}
