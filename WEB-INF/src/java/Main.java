import java.io.IOException;

import account.entity.Account;
import common.search.SearchIndexer;

public class Main {
	public static void main(String[] args) throws IOException {
		Account asset = new Account();
		asset.setId("1");
		asset.setName("Hoge Hogera");
		new SearchIndexer().saveIndex(asset);
	}
}
