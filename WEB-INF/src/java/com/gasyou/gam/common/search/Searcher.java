package com.gasyou.gam.common.search;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Searcher {

//	public Document getDocument(SearchableAsset sa) throws IOException, ParseException {
//
//		Analyzer analyzer = new StandardAnalyzer();
//
//		Path path = Paths.get("/work/lucene/sampleapp");
//	    Directory directory = FSDirectory.open(path);
//
//		// Now search the index:
//	    DirectoryReader ireader = DirectoryReader.open(directory);
//	    IndexSearcher isearcher = new IndexSearcher(ireader);
//
//	    // Parse a simple query that searches for "text":
//	    QueryParser parser = new QueryParser("fulltext", analyzer);
//	    Query query = parser.parse(fulltext);
//
//	    ScoreDoc[] hits = isearcher.search(query, 100, new Sort()).scoreDocs;
//	    // Iterate through the results:
//	    List<Document> results = new ArrayList<Document>();
//	    for (int i = 0; i < hits.length; i++) {
//	      Document hitDoc = isearcher.doc(hits[i].doc);
//	      results.add(hitDoc);
//	    }
//	    ireader.close();
//	    directory.close();
//
//	    return results;
//	}

//	public Document searchAsset(SearchableAsset asset) {
//		Analyzer analyzer = new StandardAnalyzer();
//
//		Path path = Paths.get("/work/lucene/sampleapp");
//		Directory directory = FSDirectory.open(path);
//
//		// Now search the index:
//		DirectoryReader ireader = DirectoryReader.open(directory);
//		IndexSearcher isearcher = new IndexSearcher(ireader);
//
//		// Parse a simple query that searches for "text":
//		QueryParser parser = new QueryParser("fulltext", analyzer);
//		Query query = parser.parse(fulltext);
//
//		ScoreDoc[] hits = isearcher.search(query, 100, new Sort()).scoreDocs;
//		// Iterate through the results:
//		List<Document> results = new ArrayList<Document>();
//		for (int i = 0; i < hits.length; i++) {
//			Document hitDoc = isearcher.doc(hits[i].doc);
//			results.add(hitDoc);
//		}
//		ireader.close();
//		directory.close();
//
//		return results;
//	}

	public List<Document> search(String fulltext) throws IOException, ParseException {

		Analyzer analyzer = new StandardAnalyzer();

		Path path = Paths.get("/work/lucene/sampleapp");
		Directory directory = FSDirectory.open(path);

		// Now search the index:
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

		// Parse a simple query that searches for "text":
		QueryParser parser = new QueryParser(Field.TITLE.getFiledName(), analyzer);
		Query query = parser.parse(fulltext);

		ScoreDoc[] hits = isearcher.search(query, 100, new Sort()).scoreDocs;
		// Iterate through the results:
		List<Document> results = new ArrayList<Document>();
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = isearcher.doc(hits[i].doc);
			results.add(hitDoc);
		}
		ireader.close();
		directory.close();

		return results;
	}

	public static void main(String[] args) throws IOException, ParseException {
		List<Document> results = new Searcher().search("ウェブサイト");
		System.out.println(results);
	}
}
