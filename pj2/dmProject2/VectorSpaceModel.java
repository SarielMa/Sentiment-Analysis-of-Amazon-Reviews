package dmProject2;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class VectorSpaceModel {
	/**
	 * The corpus of documents.
	 */
	private Corpus corpus;
    private String p;
    private String n;

	public VectorSpaceModel(Corpus corpus, String path, String name) {
		this.p=path;
		this.n=name;
		this.corpus = corpus;	
		try {
			createTfIdfWeights();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createTfIdfWeights() throws IOException {
		System.out.println("Creating the tf-idf weight vectors");
		//Set<String> terms = corpus.getInvertedIndex().keySet();
		int count=0;
		FileWriter fw = new FileWriter(p+"\\"+n+"_new.csv");
		List<String> headLine = getHeadLine();
		CSVUtils.writeLine(fw,headLine);
		headLine.remove(0);
		for (Document document : corpus.getDocuments()) {
			HashMap<String, Double> weights = new HashMap<String, Double>();
			
			for (String term : headLine) {
				double tf = document.getTermFrequency(term);
				double idf = corpus.getInverseDocumentFrequency(term);			
				double weight = tf * idf;			
				weights.put(term, weight);
			}
			List<String> rt = new ArrayList<>();
			rt.add(document.getFileName().substring(document.getFileName().indexOf("++")+2, document.getFileName().lastIndexOf(".txt")));
			for(String s: headLine ) {
				rt.add(Double.toString(weights.get(s)));
			}
			CSVUtils.writeLine(fw, rt);
			count++;
			System.out.println(Integer.toString(count)+"th tf-idf is created");
			//document=null;
		}
		fw.close();
	}
	
	private List<String> getHeadLine(){
		List<String> rt = new ArrayList<>();
		rt.add("Label");
		//System.out.print("Label, ");
		for (String at : corpus.getInvertedIndex().keySet()) {
			rt.add(at);
		//	System.out.print(at+" ");
		}
		//System.out.println(" ");
		return rt;
	}

}
