package common.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ModelConfigSAXHandler extends DefaultHandler {

	/** model-config */
	private ModelConfig modelConfig = new ModelConfig();

	/** 読み込み中の Model */
	private ModelInfo readingModelInfo = null;

	public ModelConfig getModelConfig() {
		return modelConfig;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("model")) {
			// Model 設定を登録
			readingModelInfo = new ModelInfo();
			String modelName = attributes.getValue("name");
			modelConfig.addModel(modelName, readingModelInfo);
		} else if (qName.equals("model-class")) {
			// 実行クラス名を取得
			String className = attributes.getValue("value");
			readingModelInfo.setClassName(className);
		} else if (qName.equals("forward")) {
			// 遷移先を取得
			String forwardName = attributes.getValue("name");
			String forwardPath = attributes.getValue("path");
			boolean redirect = String.valueOf(Boolean.TRUE).equals(attributes.getValue("redirect"));

			readingModelInfo.addForward(forwardName, new ForwardInfo(forwardPath, redirect));
		} 
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("model")) {
			readingModelInfo = null;
		}
	}
}
