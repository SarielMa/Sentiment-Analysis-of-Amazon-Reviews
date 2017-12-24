package dmProject2;

import java.io.File;
import java.io.IOException;
import java.util.Random;


import weka.classifiers.*;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.NumericToNominal;

public class wekaClassification {
	public wekaClassification(String filePath, String fileName) throws Exception {
		PreprocessAndClassification(filePath, fileName);
	}
	private void PreprocessAndClassification(String p,String n) throws Exception {
		//preprocess the data,get data
		String fileName = p+"\\"+n+"_new.csv";
		File inputFile = new File(fileName);
		CSVLoader csv = new CSVLoader();
		try {
			csv.setFile(inputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Instances instances0 = csv.getDataSet();
		//label 3 classes
		Discretize d1 = new Discretize();
		String op[]=new String[4];
		op[0]="-R";
		op[1]="1";
		op[2]="-B";
		op[3]="5";
		d1.setOptions(op);
		d1.setInputFormat(instances0);
		Instances instances=Filter.useFilter(instances0, d1);
		instances0.delete();
		System.out.println(instances.attribute(0));
		//n2n for label
		NumericToNominal filterlb=new NumericToNominal();
		String options[]=new String[2];
		options[0]="-R";
		options[1]="1";
		filterlb.setOptions(options);
		filterlb.setInputFormat(instances);
		Instances instances1=Filter.useFilter(instances, filterlb);
		instances.delete();
		System.out.println("label's trasfering to nominal ends ");
		//normalize
		Normalize nof = new Normalize();
		nof.setInputFormat(instances1);
		Instances instances2=Filter.useFilter(instances1, nof);
		instances1.delete();
		System.out.println("others' normalization ends ");
		//discretize
		Discretize disFilter = new Discretize();
		String options1[]=new String[4];
		options1[0]="-R";
		options1[1]="2-last";
		options1[2]="-B";
		options1[3]="20";
		disFilter.setOptions(options1);
		disFilter.setInputFormat(instances2);
		Instances instances3=Filter.useFilter(instances2, disFilter);
		instances2.delete();
		System.out.println("others'discretizing ends ");
		//n2n again for rest
		NumericToNominal f4=new NumericToNominal();
		String options2[]=new String[2];
		options2[0]="-R";
		options2[1]="2-last";
		f4.setOptions(options2);
		f4.setInputFormat(instances3);
		Instances newInstances = Filter.useFilter(instances3, f4); 
		instances3.delete();
		System.out.println("others' trasfering to nominal ends ");
		System.out.println("writing to final CSV file...");
		CSVSaver saver = new CSVSaver();  
		 saver.setInstances(newInstances);  
		 saver.setFile(new File(p+"\\"+n+"_final.csv"));  
		 //saver.setDestination(new File(p+"\\"+n+"_final.csv"));   // **not** necessary in 3.5.4 and later  
		 saver.writeBatch();  
		//System.out.println(newInstances.toSummaryString());
		//System.out.println(newInstances.attribute(1));
		//classify and evaluate
		 System.out.println("classification and crossvalidation start (for Tf-Idf it may take a long time, please wait patiently)...");
		Classifier lbsvm = new NaiveBayes();
		//Classifier lbsvm = new SMO();
		//Classifier lbsvm = new J48();
		newInstances.setClassIndex(0);
		lbsvm.buildClassifier(newInstances);
		
		Evaluation eval = null;
		eval = new Evaluation(newInstances);
		eval.crossValidateModel(lbsvm, newInstances, 10, new Random(1));// CV
		System.out.println("final result++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(eval.toSummaryString());
		System.out.println(eval.toClassDetailsString());
		System.out.println(eval.toMatrixString());
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
       new wekaClassification("C:\\Users\\linhai_ma1991\\Desktop\\pj2","try");
        
	}

}
