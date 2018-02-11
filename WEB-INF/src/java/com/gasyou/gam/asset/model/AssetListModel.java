package com.gasyou.gam.asset.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.gasyou.gam.asset.entity.Asset;
import com.gasyou.gam.asset.service.AssetService;
import com.gasyou.gam.common.model.AbstractModel;
import com.gasyou.gam.common.service.ServiceFactory;

public class AssetListModel extends AbstractModel {

	@Override
	public String action() throws Exception {

		String keyword = getParameter("keyword", StringUtils.EMPTY);

		AssetService service = ServiceFactory.getService(AssetService.class);
		List<Asset> assets = service.search(keyword);

		req.setAttribute("keyword", keyword);
		req.setAttribute("assets", assets);

		return "success";
	}
}
