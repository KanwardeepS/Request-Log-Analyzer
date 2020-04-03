package com.requestanalysis.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TomcatLogAnalyzer {

	public static void main(String[] args) {

		analyzeRestCallTimes();

	}

	private static void analyzeRestCallTimes() {
		try {
			List<RestWSRequest> listRequestURI = new ArrayList<RestWSRequest>();
			BufferedReader reader = new BufferedReader(
					new FileReader(
							"d:\\Profiles\\ksingh\\Desktop\\PERF\\22022019\\localhost_access_log.2019-02-22.txt"));
			String l_line = reader.readLine();
			Map<String, RestWSRequest> restCallDatamap = new ConcurrentHashMap<String, RestWSRequest>();

			while (l_line != null) {
				RestWSRequest l_restCall = new RestWSRequest();
				String l_content[] = l_line.split(" ");
				if (restCallDatamap.containsKey(l_content[6])) {
					Integer callCount = 0;
					l_restCall = restCallDatamap.get(l_content[6]);
					l_restCall.setRestURI(l_content[6]);
					l_restCall.setCallCount(l_restCall.getCallCount() + 1);
					String time = l_content[l_content.length - 1];
					l_restCall.setTotalTime(l_restCall.getTotalTime()
							+ Integer.parseInt(time));
					l_restCall.setAverageTime((float) (l_restCall
							.getTotalTime() / l_restCall.getCallCount()));

				} else {
					l_restCall.setCallCount(0);
					l_restCall.setRestURI(l_content[6]);
					l_restCall.setCallCount(l_restCall.getCallCount() + 1);
					String time = l_content[l_content.length - 1];
					l_restCall.setTotalTime(l_restCall.getTotalTime()
							+ Integer.parseInt(time));
					l_restCall
							.setAverageTime((float) l_restCall.getTotalTime());
					listRequestURI.add(l_restCall);
				}
				restCallDatamap.put(l_content[6], l_restCall);

				/*
				 * System.out.println(l_content[6] + "  request count:" +
				 * l_restCall.getCallCount() +
				 * " with Average time per request : " +
				 * l_restCall.getAverageTime());
				 */
				l_line = reader.readLine();
			}
			// System.out.println(restCallDatamap.entrySet().size());
			sortAndGenerateReport(listRequestURI);
			
			sendemail();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void sendemail() {
		
		
	}

	private static void sortAndGenerateReport(List<RestWSRequest> listRequestURI) {

		final Comparator<RestWSRequest> l_comparator = new Comparator<RestWSRequest>() {
			public int compare(RestWSRequest o1, RestWSRequest o2) {
				if (o1.getCallCount() < o2.getCallCount()) {
					return 1;
				}
				if (o1.getCallCount() > o2.getCallCount()) {
					return -1;
				}
				return 0;
			}
		};

		Collections.sort(listRequestURI, l_comparator);
		Integer count = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<table BORDER=10 BORDERCOLOR=#FFE4B5>");
		sb.append("<th> Request URI </th>");
		sb.append("<th> Request count </th>");
		sb.append("<th> Average time per Request </th>");

		for (RestWSRequest obj : listRequestURI) {
			// System.out.println("Called "+obj.getCallCount()
			// +" with Average Time "+obj.getRestURI() + " is :"+
			// obj.getAverageTime() );
			sb.append("<tr>");
			sb.append("<td> " + obj.getRestURI() + " </td>");
			sb.append("<td> " + obj.getCallCount() + " </td>");
			sb.append("<td> " + obj.getAverageTime() + " </td>");
			/*
			 * count++; if (count == 50) { break; }
			 */

		}

		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</body>");
		sb.append("</html>");
		//System.out.println(sb.toString());
		
		File l_newFile = new File("d:\\Profiles\\ksingh\\Desktop\\PERF\\22022019\\LogAnalysis.html");
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(l_newFile));
			bw.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
