package com.app.utilities;
import java.util.Arrays;

public class HeuristicsMetrics {

	int confusionMatrix[][];
	double accuracyScore;
	double precisionScore;
	double recallScore;
	double f1_score;
	int numberOfPositiveClassSupportVectors;
	int numberOfNegativeClassSupportVectors;

	
	public void displayConfusionmatrix() {
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				System.out.print(this.confusionMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public int[][] getConfusionMatrix() {
		return confusionMatrix;
	}

	public void setConfusionMatrix(int[][] confusionMatrix) {
		this.confusionMatrix = confusionMatrix;
	}

	public double getAccuracyScore() {
		return accuracyScore;
	}

	public void setAccuracyScore(double accuracyScore) {
		this.accuracyScore = accuracyScore;
	}

	public double getPrecisionScore() {
		return precisionScore;
	}

	public void setPrecisionScore(double precisionScore) {
		this.precisionScore = precisionScore;
	}

	public double getRecallScore() {
		return recallScore;
	}

	public void setRecallScore(double recallScore) {
		this.recallScore = recallScore;
	}

	public double getF1_score() {
		return f1_score;
	}

	public void setF1_score(double f1_score) {
		this.f1_score = f1_score;
	}

	public int getNumberOfPositiveClassSupportVectors() {
		return numberOfPositiveClassSupportVectors;
	}

	public void setNumberOfPositiveClassSupportVectors(int numberOfPositiveClassSupportVectors) {
		this.numberOfPositiveClassSupportVectors = numberOfPositiveClassSupportVectors;
	}

	public int getNumberOfNegativeClassSupportVectors() {
		return numberOfNegativeClassSupportVectors;
	}

	public void setNumberOfNegativeClassSupportVectors(int numberOfNegativeClassSupportVectors) {
		this.numberOfNegativeClassSupportVectors = numberOfNegativeClassSupportVectors;
	}

	@Override
	public String toString() {
		return "HeuristicsMetrics [confusionMatrix=" + Arrays.toString(confusionMatrix) + ", accuracyScore="
				+ accuracyScore + ", precisionScore=" + precisionScore + ", recallScore=" + recallScore + ", f1_score="
				+ f1_score + ", numberOfPositiveClassSupportVectors=" + numberOfPositiveClassSupportVectors
				+ ", numberOfNegativeClassSupportVectors=" + numberOfNegativeClassSupportVectors + "]";
	}
	
	

}
