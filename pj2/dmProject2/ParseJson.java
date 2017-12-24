package dmProject2;
import java.io.*;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ParseJson {
	public ParseJson(String path, String name) {
		Json2Txts(path,name);
	}
	private void deleteFolder(String dir) {
		  File delfolder=new File(dir); 
		  File oldFile[] = delfolder.listFiles();
		  try { 
		     for (int i = 0; i < oldFile.length; i++){
		        /*if(oldFile[i].isDirectory()){
		           deleteFolder(dir+oldFile[i].getName()+"//"); //recursive cleaning the fold
		        }*/
		        oldFile[i].delete();
		     }
		  } 
		  catch (Exception e){ 
		    System.out.println("clean error!!"); 
		    e.printStackTrace(); 
		  }
		}
	private void Json2Txts(String p,String n) {
		// TODO Auto-generated method stub
	    ArrayList<JSONObject> json=new ArrayList<JSONObject>();
	    JSONObject obj;
	    // The name of the file to open.
	    //String fileName = "C:\\Users\\linhai_ma1991\\Desktop\\pj2\\try.json";

	    // This will reference one line at a time
	    String line = null;
	    int count=0;
	    String fileName = p+"\\"+n+".json";
	    deleteFolder(p+"\\corpus\\1");
	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(fileName);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        while((line = bufferedReader.readLine()) != null) {
	            obj = (JSONObject) new JSONParser().parse(line);
	            json.add(obj);
	           // System.out.println((String)obj.get("reviewerID")+":"+
	           //                    (String)obj.get("reviewText")+"---"+
	           //                    Double.toString((Double)obj.get("overall"))+"\n");
	            String ID=(String)obj.get("reviewerID");
	            String overRall=Double.toString((Double)obj.get("overall"));
	            String reviewText=(String)obj.get("reviewText");
	            //String summary=(String)obj.get("summary");
	            PrintWriter pw = new PrintWriter(p+"\\corpus\\1\\"+Integer.toString(count)+ID+"++"+overRall+".txt");
	            pw.write(reviewText);
	            //pw.write(summary);
	            pw.close();
	            System.out.println(Integer.toString(count)+"+"+ID+"output");
	            count++;
	        }
	        // Always close files.
	        bufferedReader.close();         
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file '" + fileName + "'");                
	    }
	    catch(IOException ex) {
	        System.out.println("Error reading file '" + fileName + "'");                  
	        // Or we could just do this: 
	        // ex.printStackTrace();
	    } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    System.out.println("The input "+n+".json "+"has been parsed!!" );
	    System.out.println("The result corpus is in "+p+"\\corpus\\1 !!");
	}

}
