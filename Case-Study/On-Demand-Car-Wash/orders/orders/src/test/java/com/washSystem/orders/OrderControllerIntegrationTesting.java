package com.washSystem.orders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

import com.washSystem.orders.model.Address;
import com.washSystem.orders.model.CardDetail;
import com.washSystem.orders.model.Cars;
import com.washSystem.orders.model.Order;
import com.washSystem.orders.service.OrderService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTesting {
	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort
    private int port;
	
	@Autowired
    OrderService service;
	
	private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }
    
    @Test
    public void testGetAllOrders() {
    HttpHeaders headers = new HttpHeaders();
       HttpEntity<String> entity = new HttpEntity<String>(null, headers);
       ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/findAllOrder",
       HttpMethod.GET, entity, String.class);
       assertNotNull(response.getBody());
   }
    
    @Test
    public void testGetOrderById() {
        Order order = restTemplate.getForObject(getRootUrl() + "findOrder/1014", Order.class);
        System.out.println(order.getStatus());
        assertNotNull(order);
    }
    
    
    @Test
    public void testDeleteOrder(){
    	String message = restTemplate.getForObject(getRootUrl() + "/admin/deleteOrder/33", String.class);
    	assertThat(message).isEqualTo("order deleted with id : 33");
    }
    
   
    @Test
    public void testSaveOrder() {
    	HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/findAllOrder",
        HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }
    
    @Test
    public void testUpdateOrder() {
    	HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/findAllOrder",
        HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }
}
