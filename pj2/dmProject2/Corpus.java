package dmProject2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Corpus {
	/**
	 * An arraylist of all documents in the corpus.
	 */
	private ArrayList<Document> documents;
	
	/**
	 * The inverted index. 
	 * It will map a term to a set of documents that contain that term.
	 */
	private HashMap<String, Set<String>> invertedIndex;
	
	/**
	 * The constructor - it takes in an arraylist of documents.
	 * It will generate the inverted index based on the documents.
	 * @param documents the list of documents
	 */
	public Corpus(ArrayList<Document> documents) {
		this.documents = documents;
		invertedIndex = new HashMap<String, Set<String>>();		
		createInvertedIndex(documents);
	}
	
	/**
	 * This method will create an inverted index.
	 */
	private void createInvertedIndex(ArrayList<Document> documents) {
		System.out.println("Creating the inverted index");
		
		for (Document document : documents) {
			Set<String> terms = document.getTermList();
			
			for (String term : terms) {
				if (invertedIndex.containsKey(term)) {
					Set<String> list = invertedIndex.get(term);
					list.add(document.getFileName());
				} else {
					Set<String> list = new TreeSet<String>();
					list.add(document.getFileName());
					invertedIndex.put(term, list);
				}
			}
		}
	}
	
	/**
	 * This method returns the idf for a given term.
	 * @param term a term in a document
	 * @return the idf for the term
	 */
	public double getInverseDocumentFrequency(String term) {
		if (invertedIndex.containsKey(term)) {
			double size = documents.size();
			Set<String> list = invertedIndex.get(term);
			double documentFrequency = list.size();
			
			return Math.log10(size / documentFrequency);
		} else {
			return 0;
		}
	}

	/**
	 * @return the documents
	 */
	public ArrayList<Document> getDocuments() {
		return documents;
	}

	/**
	 * @return the invertedIndex
	 */
	public HashMap<String, Set<String>> getInvertedIndex() {
		return invertedIndex;
	}

}
