package com.requestanalysis.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TestClass {

	public static void main( String[] args ) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		PrintWriter wr = new PrintWriter( System.out );
		int T = Integer.parseInt( br.readLine().trim() );
		for ( int t_i = 0; t_i < T; t_i++ ) {
			int N = Integer.parseInt( br.readLine().trim() );
			String out_ = solve( N );
			wr.println( out_ );
		}

		wr.close();
		br.close();
	}

	static String solve( int N ) {
		String returnVal = null;
		if ( N <= 2 ) {
			returnVal = "No";
		}
		else if ( N > 2 ) {
			returnVal =  computeDistribution( N );
		}
		return returnVal;
	}

	static String computeDistribution( int N ) {
		String returnVal = null;
		if ( N / 2 < 2 ) {
			returnVal = "No";
		}
		if ( N / 2 == 2 ) {
			returnVal = "Yes";
		}
		for ( int index = 3; index <= N; index++ ) {
			if ( N / index >= 2 ) {
				returnVal = "Yes";
				break;
			}
		}
		return returnVal;
	}
}