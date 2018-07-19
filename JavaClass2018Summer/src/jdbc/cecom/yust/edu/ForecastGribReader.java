package jdbc.cecom.yust.edu;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ForecastGribReader  {

	private final static Logger logger = LoggerFactory.getLogger(ForecastGribReader.class);

	public static void main(String args[]){

		logger.info(" REST Service Reader Starterd...");

		try {
		
			String forecastApiBaseUri = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib";
			String serviceKey = "EtEoyjHe%2FaPwjjmhCbBNzeMubWevH8UwLy4GHGDcPVNHoqXZleBOmvLq%2FROQtYBDx3TO%2Bvj%2F0qHJmNnTY8j76Q%3D%3D";
			String type = "json";

			// String baseTime = "1600";

			URIBuilder builder = new URIBuilder(forecastApiBaseUri);
			builder.addParameter("ServiceKey", URLDecoder.decode(serviceKey, "UTF-8"));
			builder.addParameter("base_date", "20180720");
			builder.addParameter("base_time", "0000");
			builder.addParameter("nx", "58");
			builder.addParameter("ny", "125");
			builder.addParameter("_type", type);

			// Call RESTful API for forecast
			HttpClient client = HttpClients.createDefault();
			String listStubsUri = builder.build().toString();
			HttpGet getStubMethod = new HttpGet(listStubsUri);
			HttpResponse getStubResponse = client.execute(getStubMethod);

			int getStubStatusCode = getStubResponse.getStatusLine().getStatusCode();
			if (getStubStatusCode < 200 || getStubStatusCode >= 300) {
				// Handle non-2xx status code
				logger.error("53th Line Error!!!");
			}
			String responseBody = EntityUtils.toString(getStubResponse.getEntity());

			ObjectMapper mapper = new ObjectMapper();
			JsonNode response = mapper.readTree(responseBody).path("response");
			System.out.println(response.toString());
			if (response.path("header").path("resultMsg").asText().equals("OK")) {
				int totalCount = response.path("body").path("totalCount").intValue();
				if (totalCount == 0) {
					logger.error("Response with no Data.");
					throw new RuntimeException(" ===== ForecastGribReader 94 ===== No Result. =====");
				}
				JsonNode readItems = response.path("body").path("items").path("item");
				logger.info(" ===== RESPONSE ITEM: {} =====", readItems.toString());
				
				List<Item> dataList = mapper.readValue(readItems.traverse(),
						mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
				
				for (Item data : dataList) {
					System.out.println(data.getCategory() + ": " + data.getObsrValue());
				}

			} else {
				logger.debug(" ===== MSG: {} =====", response.path("header").path("resultMsg").asText());

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
