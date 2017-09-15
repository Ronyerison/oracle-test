package br.ufpi.loes.oracleTest.core;

import java.util.Random;

public class TesteRandom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int visibleElements = 233;
		
		Random rand = new Random();
		
		for (int i = 0; i < 10; i++) {
			int newNum = rand.nextInt(60) - 30;
			if(newNum >= 0 && newNum < 10) {
				newNum += 10;
			}
			if(newNum < 0 && newNum > -10) {
				newNum -= 10;
			}
			System.out.println(newNum);
		}
	}

}
