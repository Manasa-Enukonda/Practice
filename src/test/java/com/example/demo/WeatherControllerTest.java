package com.example.demo;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private WeatherController subject;

    @Mock
    private WeatherService weatherService;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Mock
    private WeatherResponse mockResponse;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(subject).build();
    }

    @Test
    public void test_getCurrentWeather_success() throws Exception {

        when(weatherService.getCityWeather(stringArgumentCaptor.capture())).thenReturn(mockResponse);

        String url = "/api/v1/weather/{cityname}";

        MvcResult mvcResult = this.mockMvc.perform(get(url, "OMAHA")).andExpect(status().isOk()).andReturn();

        verify(weatherService, times(1)).getCityWeather(anyString());
        //assertEquals("OMAHA", stringArgumentCaptor.getValue());
        //assertEquals(HttpStatus.OK, mvcResult.getResponse().getStatus());
        assertSame(mockResponse, mvcResult.getResponse());

    }

    @Test
    public void test_getCurrentWeather_failure() throws Exception {

        when(weatherService.getCityWeather(stringArgumentCaptor.capture())).thenReturn(null);

        String url = "/api/v1/weather/{cityname}";

        MvcResult mvcResult = this.mockMvc.perform(get(url, "ABC").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();

        verify(weatherService, times(1)).getCityWeather(anyString());
       // assertEquals("ABC", stringArgumentCaptor.getValue());
        assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());

    }
}