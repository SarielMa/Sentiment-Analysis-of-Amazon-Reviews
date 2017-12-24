package dmProject2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
//import java.util.Set;

public class StopWords {

	private HashSet<String> sw = new HashSet<String>();
	
 	public StopWords(String path) throws IOException {
 		BufferedReader br = new BufferedReader(new FileReader(path+"\\stop_words.txt"));
 		String s="";
 		String s1;
 		while((s1=br.readLine())!=null)
 		{
 		s=s+s1;
 		}
 		String [] data=s.split(",");
 		for (int i =0; i <data.length; i++) {
 			sw.add(data[i].replaceAll("[^A-Za-z0-9]", ""));
 		}
	}
 	public HashSet<String> getSet() {
 		return sw;
 	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        StopWords ob = new StopWords("C:\\Users\\linhai_ma1991\\Desktop\\pj2");
        for(String s: ob.getSet()) {
        	System.out.print(s);
        }
	}

}
