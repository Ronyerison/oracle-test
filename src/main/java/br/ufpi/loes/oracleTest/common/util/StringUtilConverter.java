package br.ufpi.loes.oracleTest.common.util;

import java.util.Arrays;

public class StringUtilConverter {
	
	public static double[][] stringToArray(String str) {
	    int row = 0;
	    int col = 0;
	    for (int i = 0; i < str.length(); i++) {
	        if (str.charAt(i) == '[') {
	            row++;
	        }
	    }
	    row--;
	    for (int i = 0;; i++) {
	        if (str.charAt(i) == ',') {
	            col++;
	        }
	        if (str.charAt(i) == ']') {
	            break;
	        }
	    }
	    col++;

	    double[][] out = new double[row][col];

	    str = str.replaceAll("\\[", "").replaceAll("\\]", "");

	    String[] s1 = str.split(", ");

	    int j = -1;
	    for (int i = 0; i < s1.length; i++) {
	        if (i % col == 0) {
	            j++;
	        }
	        out[j][i % col] = Double.parseDouble(s1[i]);
	        //System.out.println(s1[i] + "\t" + j + "\t" + i % col);
	    }
	    return out;
	}
	
	public static String arrayToString(double[][] matrix) {
		return Arrays.deepToString(matrix);
	}
}
