package com.app.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



class Test implements Runnable {
	public void run() {
		System.out.print("Training...");
		for (int i = 1; TrainTestUtil.isProcessCompleted != true; i++) {
			if(i % 1 == 0) {
//				System.out.println("i = " + i);
			System.out.print(".");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}


class MyProcessBuilder implements Runnable{
	public void run() {
		
		TrainTestUtil.isProcessCompleted = false;
		File commandsInputs = null;
		File commandsOutputs = null;
		File commandsErrors = null;
		
		ProcessBuilder processBuilder = new ProcessBuilder("cmd");
		
		if(TrainTestUtil.typeOfSVM.equals("Custom SVM")) {
			commandsInputs = new File("E:\\ProcessBuilder_Test\\Custom_SVM_Commands_Inputs.txt");

			commandsOutputs = new File("E:\\ProcessBuilder_Test\\Custom_SVM_Commands_Output.txt");

			commandsErrors = new File("E:\\ProcessBuilder_Test\\Custom_SVM_Commands_Errors_Log.txt");

		}
		else if(TrainTestUtil.typeOfSVM.equals("Incremental SVM")) {
			commandsInputs = new File("E:\\ProcessBuilder_Test\\Incremental_SVM_Commands_Inputs.txt");

			commandsOutputs = new File("E:\\ProcessBuilder_Test\\Incremental_SVM_Commands_Output.txt");

			commandsErrors = new File("E:\\ProcessBuilder_Test\\Incremental_SVM_Commands_Errors_Log.txt");

		}
		

		processBuilder.redirectError(commandsErrors);
		processBuilder.redirectInput(commandsInputs);
		processBuilder.redirectOutput(commandsOutputs);
		
		processBuilder.directory(new File("E:\\ProcessBuilder_Test"));
		try {
			Process command_process = processBuilder.start();
			command_process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		TrainTestUtil.isProcessCompleted = true;
		
	}
}


public class TrainTestUtil {
	
	static boolean isProcessCompleted = true;
	static String typeOfSVM = null;
	
	
	public MetricsDisplay displayOutput(String typeOfSVM) {
		File outputFile = null;
		if(typeOfSVM.equals("Custom SVM")) {
			outputFile = new File("E:\\ProcessBuilder_Test\\python_Custom_SVM_Outputs.txt");
		}
		else if(typeOfSVM.equals("Incremental SVM")) {
			outputFile = new File("E:\\ProcessBuilder_Test\\python_Incremental_SVM_Outputs.txt");
		}
		MetricsDisplay outputFileDisplay = FileOutputReader.SVMOutputReader(outputFile);
		return outputFileDisplay;
	}
	
	
	public void trainSVM(String SVM_type) {
		
		typeOfSVM = SVM_type;
		
		MyProcessBuilder myProcessBuilder = new MyProcessBuilder();
		
		Thread commandLineThread = new Thread(myProcessBuilder);
		
		Test obj = new Test();
		Thread t1 = new Thread(obj);
		
		commandLineThread.start();
		t1.start();
		try {
			commandLineThread.join();
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Thread.sleep(10000);

		System.out.println("\nTraining Done...");
	}
	
	public void deleteTestTrainDataCache() {
		ProcessBuilder processBuilder = new ProcessBuilder("cmd");
		processBuilder.directory(new File("E:\\ProcessBuilder_Test"));
		File commandsInputs = new File("E:\\ProcessBuilder_Test\\delete_train_test_data_cache.txt");
		processBuilder.redirectInput(commandsInputs);
		try {
			processBuilder.start();
			System.out.println("Cache Cleared...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in Clearing Cache...");
		}
	}
	
	public boolean setTrainTestRatioForCustomSVM(String trainTestRatio) {
		try {
		FileWriter svmTrainTestInputFile = new FileWriter("E:\\ProcessBuilder_Test\\python_Custom_SVM_Inputs.txt");
		svmTrainTestInputFile.write(trainTestRatio);
		svmTrainTestInputFile.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Oops !!! Custom SVM Inputs are not written !!!");
			return false;
		}
		System.out.println("Custom SVM Inputs written Successfully !!!");
		return true;
	}
	
	public boolean setTrainTestRatioForIncrementalVM(String trainTestRatio, String numberOfSubsets) {
		try {
		FileWriter svmTrainTestInputFile = new FileWriter("E:\\ProcessBuilder_Test\\python_Incremental_SVM_Inputs.txt");
		svmTrainTestInputFile.write(trainTestRatio + "\n" + numberOfSubsets);
		svmTrainTestInputFile.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Oops !!! Incremental SVM Inputs are not written !!!");
			return false;
		}
		System.out.println("Incremental SVM Inputs written Successfully !!!");
		return true;
	}
	
	
}
