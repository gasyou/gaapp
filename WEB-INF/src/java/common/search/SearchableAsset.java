package common.search;

import org.apache.lucene.document.Document;

public interface SearchableAsset {

	public Document getDocument();

//	public SearchableAsset setDocument(Document doc);
}
