package com.auto.parking.autoparking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AutoParkingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void calculateFee() throws Exception {
		this.mockMvc.perform(get("/calculate/M1?from=2018-12-22T21:34:55&to=2018-12-23T21:34:55")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("{\"zone\":\"M1\",\"from\":\"2018-12-22T21:34:55\",\"to\":\"2018-12-23T21:34:55\",\"fee\":1440.0}"));
	}

	@Test
	public void calculateFeeExceedUserRate() throws Exception {
		this.mockMvc.perform(get("/calculate/M1?from=2018-12-22T21:34:55&to=2018-12-23T21:34:55").header("Client-Id", "testClient")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("{\"zone\":\"M1\",\"from\":\"2018-12-22T21:34:55\",\"to\":\"2018-12-23T21:34:55\",\"fee\":1440.0}"));

		this.mockMvc.perform(get("/calculate/M1?from=2018-12-22T21:34:55&to=2018-12-23T21:34:55").header("Client-Id", "testClient")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("{\"zone\":\"M1\",\"from\":\"2018-12-22T21:34:55\",\"to\":\"2018-12-23T21:34:55\",\"fee\":1440.0}"));

		this.mockMvc.perform(get("/calculate/M1?from=2018-12-22T21:34:55&to=2018-12-23T21:34:55").header("Client-Id", "testClient")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("{\"zone\":\"M1\",\"from\":\"2018-12-22T21:34:55\",\"to\":\"2018-12-23T21:34:55\",\"fee\":1440.0}"));

		this.mockMvc.perform(get("/calculate/M1?from=2018-12-22T21:34:55&to=2018-12-23T21:34:55").header("Client-Id", "testClient")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("{\"zone\":\"M1\",\"from\":\"2018-12-22T21:34:55\",\"to\":\"2018-12-23T21:34:55\",\"fee\":1440.0}"));

		this.mockMvc.perform(get("/calculate/M1?from=2018-12-22T21:34:55&to=2018-12-23T21:34:55").header("Client-Id", "testClient")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("{\"zone\":\"M1\",\"from\":\"2018-12-22T21:34:55\",\"to\":\"2018-12-23T21:34:55\",\"fee\":1440.0}"));

		this.mockMvc.perform(get("/calculate/M1?from=2018-12-22T21:34:55&to=2018-12-23T21:34:55").header("Client-Id", "testClient")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("{\"zone\":\"M1\",\"from\":\"2018-12-22T21:34:55\",\"to\":\"2018-12-23T21:34:55\",\"fee\":1440.0}"));

		this.mockMvc.perform(get("/calculate/M1?from=2018-12-22T21:34:55&to=2018-12-23T21:34:55").header("Client-Id", "testClient")).andDo(print()).andExpect(status().isTooManyRequests());
	}
}

