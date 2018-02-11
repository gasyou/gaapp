package com.gasyou.gam.common.search;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public abstract class BaseIndexer<T> implements Indexer<T> {

	public void doReindex(T object) throws IOException {
		Document baseDoc = new Document();

		final String className = object.getClass().getName();
		baseDoc.add(new StringField(Field.CLASS.getFiledName(), className, Store.YES));

		final String primaryKey = getPrimaryKey(object);
		baseDoc.add(new StringField(Field.PK.getFiledName(), primaryKey, Store.YES));

		baseDoc.add(new StringField(Field.UUID.getFiledName(), getUUID(className, primaryKey), Store.YES));

		populateDocument(baseDoc, object);

		updateIndex(baseDoc);
	}

	protected abstract void populateDocument(Document baseDoc, T object);

	protected abstract String getPrimaryKey(T object);

	private void updateIndex(Document document) throws IOException {
		Analyzer analyzer = new StandardAnalyzer();

	    // To store an index on disk:
		Path path = Paths.get("/work/lucene/sampleapp");
	    Directory directory = FSDirectory.open(path);

	    // Search existing document
	    String uuid = document.getField(Field.UUID.getFiledName()).stringValue();
	    Term uuidTerm = new Term(Field.UUID.getFiledName(), uuid);
//	    Query termQuery = new TermQuery(uuidTerm);
//
//	    DirectoryReader ireader = DirectoryReader.open(directory);
//		IndexSearcher isearcher = new IndexSearcher(ireader);
//		ScoreDoc[] hits = isearcher.search(termQuery, 1).scoreDocs;

		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter iwriter = new IndexWriter(directory, config);

//		for (ScoreDoc doc: hits) {
//			Document hitDoc = isearcher.doc(doc.doc);
//			iwriter.deleteDocuments(termQuery)
//		}

		iwriter.updateDocument(uuidTerm, document);
	    iwriter.close();
	}

	private String getUUID(String className, String primaryKey) {
		return UUID.nameUUIDFromBytes((className + primaryKey).getBytes()).toString();
	}
}
