package com.gasyou.gam.common.annotation;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gasyou.gam.common.model.WebModel;

/**
 * Annocation が指定されたクラスを検索する.
 * @param <A> Class of Annotation
 */
public class AnnotationResolver<A extends Annotation> {

	private static final Logger logger = LogManager.getLogger(AnnotationResolver.class);

	/**
	 *
	 * @param annotation Class of Annocation
	 * @return Annocation が指定されたクラス
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Class<A>> resolve(Class<A> annotation) {
		String classFileName = AnnotationResolver.class.getName().replaceAll("\\.", "/") + ".class";

		File file = new File(Thread.currentThread().getContextClassLoader().getResource(classFileName).getPath().replaceAll("classes.+", "classes"));

		List<Class<A>> ret = new ArrayList<Class<A>>();

		try {
			readClassFile(ret, file, annotation);
		} catch (ClassNotFoundException e) {
			logger.error("Failed to read class file.", e);
		}

		return ret;
	}


	@SuppressWarnings("unchecked")
	private void readClassFile(List<Class<A>> ret, File file, Class<A> annocation) throws ClassNotFoundException {

		if (file.isDirectory()) {
			for (File childFile : file.listFiles()) {
				readClassFile(ret, childFile, annocation);
			}
		} else {
			// ファイルがクラスファイルの場合
			if (file.getName().endsWith(".class")) {


				Class<?> claz = Class.forName(file.getPath().replaceAll(".+\\\\classes\\\\", "").replaceAll("\\.class", "").replaceAll("\\\\", "\\."));
				A annotation = claz.getAnnotation(annocation);

				if (annotation != null) {
					ret.add((Class<A>) claz);
				}
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		List<Class<WebModel>> ret = new AnnotationResolver().resolve(WebModel.class);
		System.out.println(ret);
	}
}
