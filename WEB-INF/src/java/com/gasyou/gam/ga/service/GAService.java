package com.gasyou.gam.ga.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.gasyou.gam.account.search.AccountIndexer;
import com.gasyou.gam.common.search.Indexer;
import com.gasyou.gam.common.service.AbstractService;
import com.gasyou.gam.common.service.ServiceConfig;
import com.gasyou.gam.profile.search.ProfileIndexer;
import com.gasyou.gam.webproperty.search.WebpropertyIndexer;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Account;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.Profile;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;
import com.google.api.services.analytics.model.Webproperty;

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
		List<Account> items = accounts.getItems();

		Indexer<Account> indexer = new AccountIndexer();
		for (Account acct: items) {
			indexer.doReindex(acct);
		}

		return items;
	}

	public List<Webproperty> getWebproperties(String accountId) throws IOException {

		Webproperties webProperties = analytics.management().webproperties().list(accountId).execute();
		List<Webproperty> items = webProperties.getItems();

		Indexer<Webproperty> indexer = new WebpropertyIndexer();
		for (Webproperty webProps: items) {
			indexer.doReindex(webProps);
		}

		return items;
	}

	public List<Profile> getProfiles(String accountId, String webPropertyId) throws IOException {

		Profiles profiles = analytics.management().profiles().list(accountId, webPropertyId).execute();
		List<Profile> items = profiles.getItems();

		Indexer<Profile> indexer = new ProfileIndexer();
		for (Profile profile: items) {
			indexer.doReindex(profile);
		}

		return items;
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