package dmProject2;

import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.IOException;

import org.json.simple.*;
public class LdaOutput {
	
	private JSONObject line2Json(String line) {
		 JSONObject obj = new JSONObject();
		 //extract the label
		 String[] temp = line.split("\\s");
		 /*for(String s: temp) {
			 System.out.print(s);
		 }*/
		 //System.out.print(temp[1]);
		 int index1 = temp[1].indexOf("++");
		 int index2 = temp[1].lastIndexOf(".txt");
		 String sLabel = temp[1].substring(index1+2, index2);
		 Double dLabel = Double.parseDouble(sLabel);
		 obj.put("Overall", dLabel);
		 
		 //extract all the distribute
		 for (int i =2; i<temp.length; i++) {
			 obj.put("topic "+Integer.toString(i-1), Double.parseDouble(temp[i]));
		 }
		 //add label and distributes to json and output
		 //System.out.println(obj);
		 return obj;
	}
	public void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            //System.out.println("");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\linhai_ma1991\\Desktop\\pj2\\try_new.json"));
            while ((tempString = reader.readLine()) != null) {
                // show line number
                System.out.println("line " + line + ": " + tempString);
                //deal with the line and output in json format
                bw.write(line2Json(tempString).toString());
                bw.newLine();
               //line ++ and go to another loop
                line++;
            }
            bw.close();
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
        LdaOutput obj=new LdaOutput();
        obj.readFileByLines("C:\\Users\\linhai_ma1991\\Desktop\\pj2\\try.txt");
	}

}
