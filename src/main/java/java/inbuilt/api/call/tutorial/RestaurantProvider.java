package java.inbuilt.api.call.tutorial;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author naveen.chauhan on 30/07/22
 */
public class RestaurantProvider {

	/**
	 * Description of Problem -
	 * It calls an api to get All the restaurant in the city using 2 param - City, page
	 * The Api gives the response in the pagenated form. We have to get all the restaurant given in the pages
	 * We call the using two param - city and page
	 * Once we get the information, get all the restaurant in a list form if estimated_cost of that restaurant for 2 person is less than our budget given as 'maxcost'
	 * <p>
	 * How -
	 * We used inbuilt java method to get the response in the string format
	 * <p>
	 * We have json-simple library to get the list of restaurant which was given as nested json format
	 * <p>
	 */

	private static void addAllValidRestaurant(JSONArray jsonArray, int maxCost, List<String> restaurants) {
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject explrObject = (JSONObject) jsonArray.get(i);

			long estimatedCost = (long) explrObject.get("estimated_cost");

			if (maxCost >= estimatedCost) {
				restaurants.add((String) explrObject.get("name"));
			}
		}
	}

	public static String callApi(String url) throws RuntimeException {
		ByteArrayOutputStream responseBody = null;
		Scanner httpResponseBodyScanner = null;
		String response = "Empty";

		try {

			URL callingUrl = new URL(url);
			HttpURLConnection urlConnection = (HttpURLConnection) callingUrl.openConnection();

			httpResponseBodyScanner = new Scanner(urlConnection.getInputStream());

			// Use a ByteArrayOutputStream to store the contents of the HTTP response body
			responseBody = new ByteArrayOutputStream();
			while (httpResponseBodyScanner.hasNextLine()) {
				responseBody.write(httpResponseBodyScanner.nextLine().getBytes());
			}

			responseBody.close();
			httpResponseBodyScanner.close();
			response = responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (responseBody != null) {
				try {
					responseBody.close();
				} catch (IOException ioe) {
					System.out.println("Error while closing response body stream");
				}
			}
			if (httpResponseBodyScanner != null) {
				httpResponseBodyScanner.close();
			}
		}
		return response;
	}


	private static void getData(String city, int maxCost, List<String> restaurants) throws ParseException {
		String response = callApi("https://jsonmock.hackerrank.com/api/food_outlets?city=" + city + "&page=" + 1);

		JSONParser parser = new JSONParser();
		JSONObject json = null;
		json = (JSONObject) parser.parse(response);
		Long totalPages = (Long) json.get("total_pages");
		JSONArray jsonArray = (JSONArray) json.get("data");

		int pageCounter = 2; //start from page 2
		addAllValidRestaurant(jsonArray, maxCost, restaurants);

		while (pageCounter <= totalPages) {
			response = callApi("https://jsonmock.hackerrank.com/api/food_outlets?city=" + city + "&page=" + pageCounter);

			json = (JSONObject) parser.parse(response);
			jsonArray = (JSONArray) json.get("data");

			addAllValidRestaurant(jsonArray, maxCost, restaurants);
			pageCounter++;
		}
	}

	private static List<String> getAllRelevantRestaurant(String city, int maxCost) throws ParseException {
		List<String> restaurants = new ArrayList<>();
		getData(city, maxCost, restaurants);
		return restaurants;
	}

	public static void main(String[] args) throws IOException, ParseException {
		String city = "Denver";
		int maxCost = 120;

		List<String> retaurants = getAllRelevantRestaurant(city, maxCost);
		for (String restaurant : retaurants) {
			System.out.println(restaurant);
		}
	}
}
