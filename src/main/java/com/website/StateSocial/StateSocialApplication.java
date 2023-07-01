package com.website.StateSocial;

import com.website.StateSocial.entity.State;
import com.website.StateSocial.repository.StateRepository;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import com.website.StateSocial.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class StateSocialApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StateSocialApplication.class, args);
	}

//	@Autowired
//	CharacterEncodingFilter characterEncodingFilter;
//
//	@Bean
//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	CharacterEncodingFilter characterEncodingFilter() {
//		CharacterEncodingFilter filter = new CharacterEncodingFilter();
//		filter.setEncoding("UTF-8");
//		filter.setForceEncoding(true);
//		return filter;
//	}

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		String sql = "SELECT * FROM state";


		List<State> s = jdbcTemplate.query(sql,
				(rs, rowNum) -> new State(
							rs.getString("state_name"),
							rs.getString("state_abbr")
					));

		System.out.println(s);

		if (s.size() < 50) {
			String[] names = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
					"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
					"Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
					"Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
					"New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
					"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
					"West Virginia", "Wisconsin", "Wyoming"};

			String[] abbrs = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
					"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
					"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
					"VA", "WA", "WV", "WI", "WY"};

			for(int i = 0; i< 50; i++) {
				State states = new State(names[i], abbrs[i]);
				stateRepository.save(states);
			}
		} else {
			System.out.println("No need to seed");
		}


//		State state = new State("Tennessee", "TN");
//			stateRepository.save(state);
	}

}
