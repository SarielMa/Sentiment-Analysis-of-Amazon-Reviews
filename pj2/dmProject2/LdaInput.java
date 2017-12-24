package dmProject2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class LdaInput {
	public LdaInput(int it, int num, String path, String name){
		createBat(it, num, path, name);
		callCommand(path);
	}
	private void createBat(int it, int num, String p, String n) {
		//call \mallet-2.0.8\bin\mallet import-dir --input \Users\linhai_ma1991\Desktop\pj2\corpus\* --output \Users\linhai_ma1991\Desktop\pj2\try.mallet --keep-sequence --remove-stopwords
		//call \mallet-2.0.8\bin\mallet train-topics --input \Users\linhai_ma1991\Desktop\pj2\try.mallet --num-topics 5 --output-doc-topics \Users\linhai_ma1991\Desktop\pj2\try.txt
		//call exit
		String corpusPath = p+"\\corpus\\*";
		int opItv=10;
		File malletFile = new File(p+"\\"+n+".mallet");
		if(malletFile.exists()) {
			malletFile.delete();
		}
		File ldaTxtFile = new File(p+"\\"+n+".txt");
		if(ldaTxtFile.exists()) {
			ldaTxtFile.delete();
		}
		try {
			FileWriter fw = new FileWriter(p+"\\my_command.bat");
			fw.write("call \\mallet-2.0.8\\bin\\mallet import-dir --input "+corpusPath+" --output "+p+"\\"+n+".mallet"+" --keep-sequence --remove-stopwords\n");
			fw.write("call \\mallet-2.0.8\\bin\\mallet train-topics --input "+p+"\\"+n+".mallet"+" --num-topics "+Integer.toString(num)+" --num-iterations "+Integer.toString(it)+" --optimize-interval "+Integer.toString(opItv)+" --output-doc-topics "+p+"\\"+n+".txt"+"\n");
			//fw.write("call pause\n");
			fw.write("call exit\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void callCommand(String p) {
		Process ps=null;
        String strcmd="cmd /c start "+p+"\\my_command.bat";
        try {
        	 ps = Runtime.getRuntime().exec(strcmd);   
            System.out.println("we are waiting for the LDA processing..");
			InputStream in = ps.getInputStream();
			int c;
			while ((c = in.read()) != -1) {
				//System.out.print(c);
			}
			//in.close();
            ps.waitFor();
            System.out.println("lda process has done and txt is creeated!");
        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        }
        
        int i = ps.exitValue();  
        if (i == 0) {
            System.out.println("succeed");
        } else {
            System.out.println("fail");
        }
        ps.destroy(); 
        ps = null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //new LdaInput();
		new LdaInput(50,5,"C:\\Users\\linhai_ma1991\\Desktop\\pj2","try");
	}

}
