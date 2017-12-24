package dmProject2;
import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.util.*;

public class LdaOutputCSV {
	private int count =0;
	private void line2CSV(String line, FileWriter fw) {
		 
		 //extract the label
		 String[] temp = line.split("\\s");
		 //System.out.print(temp[1]);
		 int index1 = temp[1].indexOf("++");
		 int index2 = temp[1].lastIndexOf(".txt");
		 String sLabel = temp[1].substring(index1+2, index2);

		 //add label and distributes to instance and return
		 if (count==0) {
			 List<String> list = new ArrayList<>();
	         list.add("Label");
	         for (int i=2; i<temp.length; i++) {
	        	 list.add("topic"+Integer.toString(i-1));
	         }
	         try {
				CSVUtils.writeLine(fw, list);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         count++;
		 }
         List<String> list = new ArrayList<>();
         list.add(sLabel);
         for (int i=2; i<temp.length; i++) {
        	 list.add(temp[i]);
         }
         try {
			CSVUtils.writeLine(fw, list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void readFileByLines(String fileName, String outfileName) {
       File file = new File(fileName);
       BufferedReader reader = null;
       try {
           //System.out.println("");
           reader = new BufferedReader(new FileReader(file));
           String tempString = null;
           int line = 1;
           FileWriter fw = new FileWriter(outfileName);
           while ((tempString = reader.readLine()) != null) {
               // show line number
               //System.out.println("line " + line + ": " + tempString);
               //deal with the line and output in csv format
               line2CSV(tempString,fw);
              //line ++ and go to another loop
               line++;
           }
           fw.flush();
           fw.close();
           reader.close();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (reader != null) {
               try {
                   reader.close();
               } catch (IOException e1) {
               }
           }
       }
   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       //LdaOutputCSV obj=new LdaOutputCSV();
       //obj.readFileByLines("C:\\Users\\linhai_ma1991\\Desktop\\pj2\\try.txt", "C:\\Users\\linhai_ma1991\\Desktop\\pj2\\try_new.csv");
	}
}
