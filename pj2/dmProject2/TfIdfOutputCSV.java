package dmProject2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class TfIdfOutputCSV {
	public TfIdfOutputCSV(String path, String name) throws IOException {
		tfIdf(path, name);
	}
	
	private void tfIdf(String p, String n) throws IOException {
		
		File folder = new File(p+"\\corpus\\1");
		StopWords sw = new StopWords(p);
		ArrayList<Document> documents = new ArrayList<Document>();
		File docs[] = folder.listFiles();
		
		
		for (int i = 0; i < docs.length; i++) {
			documents.add(new Document(p+"\\corpus\\1\\"+docs[i].getName(), sw.getSet()));
		}
		System.out.println("reading is OK");
		Corpus corpus = new Corpus(documents);
		System.out.println("corpus is OK");
		new VectorSpaceModel(corpus,p,n);//calculate tfidf and createCSV
		System.out.println("VSM is OK");


	}
	public static void main() throws IOException {
		new TfIdfOutputCSV("C:\\Users\\linhai_ma1991\\Desktop\\pj2","try");
	}
}
