package com.lsp.amazion.services.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsp.amazion.fixer.model.FixerApiResponse;
import com.lsp.amazion.services.api.ConvertionService;

@Service
public class ConvertionServiceImpl implements ConvertionService {

	private static final Logger LOGGER = Logger.getLogger(ConvertionServiceImpl.class.getName());

	@Value("${fixer.apikey}")
	private String fixerApiKey;

	private static String apiRequestUrl = "http://data.fixer.io/api/latest?access_key={0}";

	public double convert(double value, String from, String to) throws IOException {
		Map<String, Double> rates = fetchNewRates(from).getRates();
		Double usdRate = rates.get("USD");
		// 1 usd = x to
		Double rate = 1 / (usdRate / rates.get(to));
		return rate * value;
	}

	private FixerApiResponse fetchNewRates(String from) throws IOException {

		String fixerRequestUrl = MessageFormat.format(apiRequestUrl, fixerApiKey);

		URL url = new URL(fixerRequestUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(RequestMethod.GET.toString());
		// Get Response
		InputStream is = connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		String line;
		while ((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\n');
		}
		rd.close();

		ObjectMapper mapper = new ObjectMapper();

		// JSON from file to Object
		return mapper.readValue(response.toString(), FixerApiResponse.class);

	}
}
