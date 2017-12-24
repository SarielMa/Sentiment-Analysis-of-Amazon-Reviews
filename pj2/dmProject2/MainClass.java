package dmProject2;

import java.io.IOException;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		//input Json file 
		String inputJson="YOUR_PATH\\pj2\\Musical_Instruments_5.json";
		//Choose a model, 0 for LDA and 1 for TfIdf;
		int flag =0;
		//LDA configure
		int numOfTopics=650;
		int numOfIteration=1000;
		
		
		//You can only configure parameters above, following code can not be modified.
		String inputName=inputJson.substring(inputJson.lastIndexOf("\\")+1,inputJson.lastIndexOf(".json"));
		String inputPath=inputJson.substring(0,inputJson.lastIndexOf("\\"));
		new ParseJson(inputPath, inputName);
		//use lda or vsm to get the feature vector of each document, output a CSV document
		
		if (flag == 0) {
			new LdaInput(numOfIteration,numOfTopics,inputPath,inputName);
			new LdaOutputCSV().readFileByLines(inputPath+"\\"+inputName+".txt", inputPath+"\\"+inputName+"_new.csv");
		}else {
			try {
				new TfIdfOutputCSV(inputPath, inputName);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		System.out.println("The CSV file has been created: "+inputPath+"\\"+inputName+".csv");
	    System.out.println("weka preprocessing begins...");
		try {
			new wekaClassification(inputPath, inputName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("total time cost: "+Long.toString(end-start));
	}

}
