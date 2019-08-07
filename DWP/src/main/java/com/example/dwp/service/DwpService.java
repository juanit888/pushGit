package com.example.dwp.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dwp.model.User;

@Service
public class DwpService {
	public static final String url = "https://bpdts-test-app.herokuapp.com";
	public static final int DISTANCE_MILES = 50;
	public static final BigDecimal LONDON_LATTITUDE = new BigDecimal(51.507351);
	public static final BigDecimal LONDON_LONGITUDE = new BigDecimal(-0.127758);
	public static final BigDecimal DEGREE_LONDON_LATTITUDE_MILES = new BigDecimal(68.707);
	public static final BigDecimal DEGREE_LONDON_LONGITUDE_MILES = new BigDecimal(12.05);

	public List<User> usersByCityOrDistance(String city) {

		RestTemplate restTemplate = new RestTemplate();
		String url2 = url + "/city/" + city + "/users";

		ResponseEntity<List<User>> respUsersByCity = restTemplate.exchange(url2, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		List<User> usersByCity = respUsersByCity.getBody();

		String url3 = url + "/users";
		ResponseEntity<List<User>> respUsers = restTemplate.exchange(url3, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		List<User> listUsers = respUsers.getBody();

		List<User> listUsersInDistance = listUsers.stream().filter(user -> this.pythagoricDistance(user))
				.collect(Collectors.toList());

		// We join the 2 lists now, as there are users registered in city of London with
		// coordinates far away
		Stream<User> combinedList = Stream.concat(usersByCity.stream(), listUsersInDistance.stream());
		List<User> result = combinedList.distinct().collect(Collectors.toList());

		return result;
	}

	private boolean pythagoricDistance(User user) {
		BigDecimal sub1 = user.getLatitude().subtract(LONDON_LATTITUDE);
		BigDecimal distX = sub1.multiply(DEGREE_LONDON_LATTITUDE_MILES);
		BigDecimal sub2 = user.getLongitude().subtract(LONDON_LONGITUDE);
		BigDecimal distY = sub2.multiply(DEGREE_LONDON_LONGITUDE_MILES);
		BigDecimal dist2 = distX.pow(2).add(distY.pow(2));
		MathContext mc = new MathContext(1);
		BigDecimal dist = dist2.sqrt(mc);

		return dist.compareTo(new BigDecimal(DISTANCE_MILES)) < 0;
	}

}
