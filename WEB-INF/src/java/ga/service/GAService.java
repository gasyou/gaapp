package ga.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Account;
import com.google.api.services.analytics.model.Accounts;

import common.service.AbstractService;
import common.service.ServiceConfig;

/**
 * A simple example of how to access the Google Analytics API using a service
 * account.
 */
public class GAService extends AbstractService {

	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

	private Analytics analytics = null;

	@Override
	protected void subInitialize(ServiceConfig serviceConfig) {
		try {
			initializeAnalytics();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private void initializeAnalytics() throws Exception {
		// Initializes an authorized analytics service object.

		// Construct a GoogleCredential object with the service account email
		// and p12 file downloaded from the developer console.
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
				.setJsonFactory(JSON_FACTORY)
				.setServiceAccountId(getServiceConfig().get(ServiceConfig.GA_SERVICE_ACCOUNT_EMAIL))
				.setServiceAccountPrivateKeyFromP12File(new File(getServiceConfig().get(ServiceConfig.GA_KEY_FILE_LOCATION)))
				.setServiceAccountScopes(AnalyticsScopes.all()).build();

		// Construct the Analytics service object.
		analytics = new Analytics.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(getServiceConfig().get(ServiceConfig.GA_APPLICATION_NAME)).build();
	}

	public List<Account> getAccounts() throws IOException {

		// Query for the list of all accounts associated with the service account.
		Accounts accounts = analytics.management().accounts().list().execute();
		return accounts.getItems();
	}

//	private static String getFirstProfileId(Analytics analytics) throws IOException {
//		// Get the first view (profile) ID for the authorized user.
//		String profileId = null;
//
//		// Query for the list of all accounts associated with the service account.
//		Accounts accounts = analytics.management().accounts().list().execute();
//
//		if (accounts.getItems().isEmpty()) {
//			System.err.println("No accounts found");
//		} else {
//			String firstAccountId = accounts.getItems().get(0).getId();
//
//			// Query for the list of properties associated with the first account.
//			Webproperties properties = analytics.management().webproperties().list(firstAccountId).execute();
//
//			if (properties.getItems().isEmpty()) {
//				System.err.println("No Webproperties found");
//			} else {
//				String firstWebpropertyId = properties.getItems().get(0).getId();
//
//				// Query for the list views (profiles) associated with the property.
//				Profiles profiles = analytics.management().profiles().list(firstAccountId, firstWebpropertyId)
//						.execute();
//
//				if (profiles.getItems().isEmpty()) {
//					System.err.println("No views (profiles) found");
//				} else {
//					// Return the first (view) profile associated with the property.
//					profileId = profiles.getItems().get(0).getId();
//				}
//			}
//		}
//		return profileId;
//	}

//	private static GaData getResults(Analytics analytics, String profileId) throws IOException {
//		// Query the Core Reporting API for the number of sessions
//		// in the past seven days.
//		return analytics.data().ga().get("ga:" + profileId, "7daysAgo", "today", "ga:sessions").execute();
//	}
//
//	private static void printResults(GaData results) {
//		// Parse the response from the Core Reporting API for
//		// the profile name and number of sessions.
//		if (results != null && !results.getRows().isEmpty()) {
//			System.out.println("View (Profile) Name: " + results.getProfileInfo().getProfileName());
//			System.out.println("Total Sessions: " + results.getRows().get(0).get(0));
//		} else {
//			System.out.println("No results found");
//		}
//	}
}