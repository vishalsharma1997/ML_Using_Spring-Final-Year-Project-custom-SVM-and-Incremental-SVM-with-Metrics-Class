package com.app.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileOutputReader {

	public static MetricsDisplay SVMOutputReader(File outputFile) {
		String outputFileString = "";
		MetricsDisplay metricsDisplay = new MetricsDisplay();
		HeuristicsMetrics olderHeuristics = new HeuristicsMetrics();
		HeuristicsMetrics newerHeuristics = new HeuristicsMetrics();

		metricsDisplay.setOlderHeuristics(olderHeuristics);
		metricsDisplay.setNewerHeuristics(newerHeuristics);

		String heuristics = "";

		try {

			Scanner scanner = new Scanner(outputFile);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				outputFileString = outputFileString + line + "\n";

				if (line.equalsIgnoreCase(
						"====================== RESULTS OBTAINTED WITH OLDER HEURISTICS : ===================")
						|| line.equalsIgnoreCase(
								"========================== RESULTS OBTAINTED WITH BUILT-IN SVM : ==========================")) {
					heuristics = "older";
				} else if (line.equalsIgnoreCase(
						"================= RESULTS OBTAINTED WITH NEW PROPOSED HEURISTICS : =================")
						|| line.equalsIgnoreCase(
								"========================== RESULTS OBTAINTED WITH CUSTOM SVM : ==========================")) {
					heuristics = "newer";
				} else {

				}

				try {

					String str[] = line.split(":");
					for (int i = 0; i < str.length; i++) {
						str[i] = str[i].trim();
					}

					if (str[0].equalsIgnoreCase("NO. OF TRAINING ROWS IS")) {
						int numberOfTrainingRows = Integer.parseInt(str[1]);
						metricsDisplay.setNumberOfTrainingRows(numberOfTrainingRows);
					} else if (str[0].equalsIgnoreCase("NO. OF TESTING ROWS IS")) {
						int numberOfTestingRows = Integer.parseInt(str[1]);
						metricsDisplay.setNumberOfTestingRows(numberOfTestingRows);
					} else if (str[0].equalsIgnoreCase("EACH SUBSET SIZE IS")) {
						int eachSubsetSize = Integer.parseInt(str[1]);
						metricsDisplay.setEachSubsetSize(eachSubsetSize);
					} else if (str[0].equalsIgnoreCase("Confusion Matrix")) {

						if (heuristics.equals("older")) {
							int confusionMatrix[][] = new int[2][2];
							String cm_line1[] = scanner.nextLine().replaceAll("[^a-zA-Z0-9 ]", "").trim()
									.replaceAll(" +", " ").split(" ");
							String cm_line2[] = scanner.nextLine().replaceAll("[^a-zA-Z0-9 ]", "").trim()
									.replaceAll(" +", " ").split(" ");
							confusionMatrix[0][0] = Integer.parseInt(cm_line1[0]);
							confusionMatrix[0][1] = Integer.parseInt(cm_line1[1]);
							confusionMatrix[1][0] = Integer.parseInt(cm_line2[0]);
							confusionMatrix[1][1] = Integer.parseInt(cm_line2[1]);
							metricsDisplay.olderHeuristics.setConfusionMatrix(confusionMatrix);
						}
						if (heuristics.equals("newer")) {
							int confusionMatrix[][] = new int[2][2];
							String cm_line1[] = scanner.nextLine().replaceAll("[^a-zA-Z0-9 ]", "").trim()
									.replaceAll(" +", " ").split(" ");
							String cm_line2[] = scanner.nextLine().replaceAll("[^a-zA-Z0-9 ]", "").trim()
									.replaceAll(" +", " ").split(" ");
							confusionMatrix[0][0] = Integer.parseInt(cm_line1[0]);
							confusionMatrix[0][1] = Integer.parseInt(cm_line1[1]);
							confusionMatrix[1][0] = Integer.parseInt(cm_line2[0]);
							confusionMatrix[1][1] = Integer.parseInt(cm_line2[1]);
							metricsDisplay.newerHeuristics.setConfusionMatrix(confusionMatrix);
						}
					} else if (str[0].equalsIgnoreCase("Accuracy Score")) {
						if (heuristics.equals("older")) {
							double accuracyScore = Double.parseDouble(str[1]);
							metricsDisplay.olderHeuristics.setAccuracyScore(accuracyScore);
						}
						if (heuristics.equals("newer")) {
							double accuracyScore = Double.parseDouble(str[1]);
							metricsDisplay.newerHeuristics.setAccuracyScore(accuracyScore);
						}
					} else if (str[0].equalsIgnoreCase("Precision Score")) {
						if (heuristics.equals("older")) {
							double precisionScore = Double.parseDouble(str[1]);
							metricsDisplay.olderHeuristics.setPrecisionScore(precisionScore);
						}
						if (heuristics.equals("newer")) {
							double precisionScore = Double.parseDouble(str[1]);
							metricsDisplay.newerHeuristics.setPrecisionScore(precisionScore);
						}
					} else if (str[0].equalsIgnoreCase("Recall Score")) {
						if (heuristics.equals("older")) {
							double recallScore = Double.parseDouble(str[1]);
							metricsDisplay.olderHeuristics.setRecallScore(recallScore);
						}
						if (heuristics.equals("newer")) {
							double recallScore = Double.parseDouble(str[1]);
							metricsDisplay.newerHeuristics.setRecallScore(recallScore);
						}
					} else if (str[0].equalsIgnoreCase("F1 - Score")) {
						if (heuristics.equals("older")) {
							double f1_score = Double.parseDouble(str[1]);
							metricsDisplay.olderHeuristics.setF1_score(f1_score);
						}
						if (heuristics.equals("newer")) {
							double f1_score = Double.parseDouble(str[1]);
							metricsDisplay.newerHeuristics.setF1_score(f1_score);
						}
					} else if (str[0].equalsIgnoreCase("For Positive (+ve) class")) {
						if (heuristics.equals("older")) {
							int numberOfPositiveClassSupportVectors = Integer.parseInt(str[1]);
							metricsDisplay.olderHeuristics
									.setNumberOfPositiveClassSupportVectors(numberOfPositiveClassSupportVectors);
						}
						if (heuristics.equals("newer")) {
							int numberOfPositiveClassSupportVectors = Integer.parseInt(str[1]);
							metricsDisplay.newerHeuristics
									.setNumberOfPositiveClassSupportVectors(numberOfPositiveClassSupportVectors);
						}
					} else if (str[0].equalsIgnoreCase("For Negative (-ve) class")) {
						if (heuristics.equals("older")) {
							int numberOfNegativeClassSupportVectors = Integer.parseInt(str[1]);
							metricsDisplay.olderHeuristics
									.setNumberOfNegativeClassSupportVectors(numberOfNegativeClassSupportVectors);
							;
						}
						if (heuristics.equals("newer")) {
							int numberOfNegativeClassSupportVectors = Integer.parseInt(str[1]);
							metricsDisplay.newerHeuristics
									.setNumberOfNegativeClassSupportVectors(numberOfNegativeClassSupportVectors);
							;
						}
					} else {

					}

				} catch (Exception e) {
					// System.out.print("\':\' missing... ");
				}

			}

			// System.out.println(outputFileString);
			System.out.println(metricsDisplay);
			metricsDisplay.olderHeuristics.displayConfusionmatrix();
			if (heuristics.equalsIgnoreCase("newer")) {
				metricsDisplay.newerHeuristics.displayConfusionmatrix();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Output File is Missing !!!");
		}
		return metricsDisplay;
	}

}
