package com.requestanalysis.tool;

public class RestWSRequest {

	String restURI;
	long totalTime;
	long callCount;
	Float averageTime;

	public String getRestURI() {
		return restURI;
	}

	public void setRestURI(String restURI) {
		this.restURI = restURI;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public long getCallCount() {
		return callCount;
	}

	public void setCallCount(long callCount) {
		this.callCount = callCount;
	}

	public Float getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(Float averageTime) {
		this.averageTime = averageTime;
	}


}
