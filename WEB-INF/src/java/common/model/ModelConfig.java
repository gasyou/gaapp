package common.model;

import java.util.HashMap;
import java.util.Map;

public class ModelConfig {

	/** Model の設定 */
	private Map<String, ModelInfo> models = new HashMap<String, ModelInfo>();

	/**
	 * Model を取得する.
	 * @param modelName Model 名
	 * @return Model
	 */
	public ModelInfo getModelInfo(String modelName) {
		return models.get(modelName);
	}

	/**
	 * Model を追加する.
	 * @param modelName Model 名
	 * @param ModelInfo
	 */
	ModelInfo addModel(String modelName, ModelInfo model) {
		return models.put(modelName, model);
	}
}
