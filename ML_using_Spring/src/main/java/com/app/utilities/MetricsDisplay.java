package com.app.utilities;

public class MetricsDisplay {

	int numberOfTrainingRows;
	int numberOfTestingRows;
	int eachSubsetSize;
	HeuristicsMetrics olderHeuristics;
	HeuristicsMetrics newerHeuristics;

	public int getNumberOfTrainingRows() {
		return numberOfTrainingRows;
	}

	public void setNumberOfTrainingRows(int numberOfTrainingRows) {
		this.numberOfTrainingRows = numberOfTrainingRows;
	}

	public int getNumberOfTestingRows() {
		return numberOfTestingRows;
	}

	public void setNumberOfTestingRows(int numberOfTestingRows) {
		this.numberOfTestingRows = numberOfTestingRows;
	}

	public int getEachSubsetSize() {
		return eachSubsetSize;
	}

	public void setEachSubsetSize(int eachSubsetSize) {
		this.eachSubsetSize = eachSubsetSize;
	}

	public HeuristicsMetrics getOlderHeuristics() {
		return olderHeuristics;
	}

	public void setOlderHeuristics(HeuristicsMetrics olderHeuristics) {
		this.olderHeuristics = olderHeuristics;
	}

	public HeuristicsMetrics getNewerHeuristics() {
		return newerHeuristics;
	}

	public void setNewerHeuristics(HeuristicsMetrics newerHeuristics) {
		this.newerHeuristics = newerHeuristics;
	}

	@Override
	public String toString() {
		return "MetricsDisplay [numberOfTrainingRows=" + numberOfTrainingRows + ", numberOfTestingRows="
				+ numberOfTestingRows + ", eachSubsetSize=" + eachSubsetSize + ", olderHeuristics=" + olderHeuristics
				+ ", newerHeuristics=" + newerHeuristics + "]";
	}

	
	
}
