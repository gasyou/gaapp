package com.gasyou.gam.common.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import com.gasyou.gam.common.annotation.AnnotationResolver;
import com.gasyou.gam.common.servlet.ModelServlet;

public class ModelConfigFactory {

	private static final Logger LOG = LogManager.getLogger(ModelConfigFactory.class);

	private String modelConfigPath = null;

	public ModelConfigFactory(String modelConfigPath) {
		this.modelConfigPath = modelConfigPath;
	}

	public ModelConfig create() {

		ModelConfig modelConfig = new ModelConfig();

		InputStream is = null;
		try {
			is = ModelServlet.class.getResourceAsStream("/" + modelConfigPath);

			// SAXを扱う時のオマジナイ
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser parser = saxParserFactory.newSAXParser();

			// XML 処理
			ModelConfigSAXHandler handler = new ModelConfigSAXHandler();
			parser.parse(is, handler);
			modelConfig = handler.getModelConfig();
			is.close();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LOG.error("Failed to read model-config.xml", e);
		}

		// アノテーションから設定を補完する
		List<Class<WebModel>> models = new AnnotationResolver<WebModel>().resolve(WebModel.class);

		for (Class<WebModel> c : models) {
			WebModel model = (WebModel) c.getDeclaredAnnotation(WebModel.class);

			ModelInfo modelInfo = new ModelInfo();
			String modelClassName = c.getName();
			modelInfo.setClassName(modelClassName);
			String modelName = model.name();
			modelConfig.addModel(modelName, modelInfo);

			WebModelForward[] forwards = model.forwards();
			for (WebModelForward forward : forwards) {
				modelInfo.addForward(forward.name(), new ForwardInfo(forward.path(), false));
			}
		}

		return modelConfig;
	}
}
