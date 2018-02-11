package com.gasyou.gam.common.search;

import java.io.IOException;

public interface Indexer<T> {

	public void doReindex(T object) throws IOException;
}
