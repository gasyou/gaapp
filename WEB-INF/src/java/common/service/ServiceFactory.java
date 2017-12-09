package common.service;

public class ServiceFactory {

	/**
	 * Service インスタンスを取得する.
	 * @param serviceClass 取得する Service クラス
	 * @return　Service インスタンス
	 */
	public static <T> T getService(Class<T> serviceClass) {

		AbstractService serviceInstance = null;
		try {
			serviceInstance = (AbstractService) serviceClass.newInstance();
			serviceInstance.initialize(ServiceConfig.getConfig());
			return (T) serviceInstance;
		} catch (Exception e) {
			throw new ServiceException("Service can't be created.");
		}
	}
}
