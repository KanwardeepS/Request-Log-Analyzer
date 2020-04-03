package com.requestanalysis.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Line {

	public static void main( String[] argh ) {
		
		Scanner s = new Scanner( System.in );
		int n = s.nextInt();
		
		checkConsecutiveUnos(n);
		/*int count = 0, maxCount = 0, a;
		String x = "";
		boolean lastBitUno = false;
		while ( n > 0 ) {
			a = n % 2;
			if ( a == 1 ) {
				count++;
				lastBitUno = true;
				if(lastBitUno) {
					maxCount = count;
				}
			}
			else {
				lastBitUno = false;
				count = 0;
			}
			x = x + "" + a;
			n = n / 2;
		}
		System.out.println( "No. of 1s:" + maxCount );*/
	}

	private static void checkConsecutiveUnos( int p_n ) {
		String binaryString = Integer.toBinaryString( p_n );
		System.out.println( binaryString );
		char [] bits = binaryString.toCharArray();
		
		int count =0 , maxCount =0;
		boolean lastBitUno = false;
		for(int index = 0 ; index <= bits.length-1;index++) {
			if ( bits[index] == '1' ) {
				count++;
				lastBitUno = true;
				if(lastBitUno) {
					if(maxCount<count) {
						maxCount = count;
					}
				}
			}
			else {
				lastBitUno = false;
				count = 0;
			}
		}
		
		System.out.println( maxCount );
		
	}

}
