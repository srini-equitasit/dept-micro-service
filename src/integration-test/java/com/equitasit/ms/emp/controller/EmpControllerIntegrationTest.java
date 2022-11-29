package com.equitasit.ms.emp.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.equitasit.ms.dept.dto.DeptDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("integration_test")
class DeptControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private String baseUrl;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void init() {
		baseUrl = "http://localhost:" + port + "/dept";
	}

	@Test
	@Order(1)
	void testSave() throws Exception {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		DeptDTO deptDTO = getDeptDTO();

		HttpEntity<String> request = new HttpEntity<String>(mapper.writeValueAsString(deptDTO), headers);

		ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity(baseUrl, request, String.class);

		Assertions.assertNotNull(responseEntity);

		Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());

		Assertions.assertNotNull(responseEntity.getBody());

		JsonNode tree = mapper.readTree(responseEntity.getBody());

		Assertions.assertEquals(10, tree.at("/deptno").asInt());

	}

	@Test
	@Order(2)
	void testUpdate() throws Exception {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		DeptDTO deptDTO = getDeptDTO();
		deptDTO.setDname("HR");

		HttpEntity<String> request = new HttpEntity<String>(mapper.writeValueAsString(deptDTO), headers);

		ResponseEntity<String> responseEntity = this.testRestTemplate.exchange(baseUrl, HttpMethod.PUT, request,
				String.class);

		Assertions.assertNotNull(responseEntity);

		Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

		Assertions.assertNotNull(responseEntity.getBody());

		JsonNode tree = mapper.readTree(responseEntity.getBody());

		Assertions.assertEquals("HR", tree.at("/dname").asText());

	}

	@Test
	@Order(3)
	void testGetById() throws Exception {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<String> responseEntity = this.testRestTemplate.getForEntity(baseUrl + "/1234", String.class);

		Assertions.assertNotNull(responseEntity);

		Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

		Assertions.assertNotNull(responseEntity.getBody());

		JsonNode tree = mapper.readTree(responseEntity.getBody());

		Assertions.assertEquals(10, tree.at("/deptno").asInt());

	}

	@Test
	@Order(4)
	void testGetByIdWhenIdNotFound() throws Exception {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<String> responseEntity = this.testRestTemplate.getForEntity(baseUrl + "/1235", String.class);

		Assertions.assertNotNull(responseEntity);

		Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());

	}

	@Test
	@Order(5)
	void testGetAll() throws Exception {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<String> responseEntity = this.testRestTemplate.getForEntity(baseUrl, String.class);

		Assertions.assertNotNull(responseEntity);

		Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

		Assertions.assertNotNull(responseEntity.getBody());

		JsonNode tree = mapper.readTree(responseEntity.getBody());

		Assertions.assertEquals(1234, tree.at("/0/deptno").asInt());
	}

	@Test
	@Order(6)
	void testRemove() throws Exception {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<String> responseEntity = this.testRestTemplate.exchange(baseUrl + "/10", HttpMethod.DELETE,
				request, String.class);

		Assertions.assertNotNull(responseEntity);

		Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), responseEntity.getStatusCodeValue());

	}

	private DeptDTO getDeptDTO() {
		DeptDTO deptDTO = new DeptDTO();
		deptDTO.setDeptno(10);
		deptDTO.setDname("IT");
		return deptDTO;
	}

}
