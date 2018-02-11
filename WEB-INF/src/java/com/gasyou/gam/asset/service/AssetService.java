package com.gasyou.gam.asset.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;

import com.gasyou.gam.asset.entity.Asset;
import com.gasyou.gam.common.search.Searcher;
import com.gasyou.gam.common.service.AbstractService;

public class AssetService extends AbstractService {

//	public List<AccountWrapper> search() {
//		List<AccountWrapper> l = new ArrayList<AccountWrapper>();
//
//		// start Dummy code.
//		l.add(create("sfdsa1", "dfdsafsda1"));
//		l.add(create("sfdsa2", "dfdsafsda2"));
//		l.add(create("sfdsa3", "dfdsafsda3"));
//		return l;
//	}

	public List<Asset> search(String keyword) {

		List<Asset> ret = new ArrayList<Asset>();

		if (StringUtils.isNotBlank(keyword)) {
			Searcher searcher = new Searcher();
			List<Document> items = null;
			try {
				items = searcher.search(keyword);
			} catch (IOException | ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			for (Document doc : items) {
				ret.add(new Asset().populate(doc));
			}
		}

		return ret;
	}
}
