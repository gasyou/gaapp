package common.search;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import account.entity.Account;

public class SearchIndexer {

	public void saveIndex(SearchableAsset asset) throws IOException {
		Analyzer analyzer = new StandardAnalyzer();

	    // To store an index on disk:
		Path path = Paths.get("/work/lucene/sampleapp");
	    Directory directory = FSDirectory.open(path);

	    IndexWriterConfig config = new IndexWriterConfig(analyzer);
	    IndexWriter iwriter = new IndexWriter(directory, config);

	    iwriter.addDocument(asset.getDocument());
	    iwriter.close();
	}

	public static void main(String[] args) throws IOException {
		Account ac = new Account();
		ac.setId("1");
		ac.setName("Masaaki Tsujimoto");
		new SearchIndexer().saveIndex(ac);

		ac = new Account();
		ac.setId("2");
		ac.setName("Hoge Hogera");
		new SearchIndexer().saveIndex(ac);
	}
}
