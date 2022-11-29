package com.equitasit.ms.dept.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.equitasit.ms.dept.controller.DeptController;
import com.equitasit.ms.dept.dto.DeptDTO;
import com.equitasit.ms.dept.exception.DeptException;
import com.equitasit.ms.dept.exception.DeptExceptionConstants;
import com.equitasit.ms.dept.service.DeptService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DeptController.class)
//@WebMvcTest(controllers = EmpController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
@ActiveProfiles("test")
@ContextConfiguration
class DeptControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DeptService deptService;

	private String baseUrl;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void init() {
		baseUrl = "/dept";

	}

	@Test
	void testSave() throws Exception {

		DeptDTO deptDTO = getDeptDTO();

		Mockito.when(deptService.save(Mockito.any(DeptDTO.class))).thenReturn(getDeptDTO());

		this.mockMvc
				.perform(post(baseUrl).content(mapper.writeValueAsString(deptDTO))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated()).andExpect(content().string(containsString("10")));

	}

	@Test
	void testUpdate() throws Exception {

		DeptDTO deptDTO = getDeptDTO();

		Mockito.when(deptService.update(Mockito.any(DeptDTO.class))).thenReturn(getDeptDTO());

		this.mockMvc
				.perform(
						put(baseUrl).content(mapper.writeValueAsBytes(deptDTO)).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("10")));
	}

	@Test
	void testRemove() throws Exception {

		Mockito.doNothing().when(deptService).remove(Mockito.anyInt());

		this.mockMvc.perform(delete(baseUrl + "/10").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNoContent());
	}

	@Test
	void testGetById() throws Exception {

		Mockito.when(deptService.get(Mockito.anyInt())).thenReturn(getDeptDTO());

		this.mockMvc.perform(get(baseUrl + "/10").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is2xxSuccessful()).andExpect(content().string(containsString("10")));

	}

	@Test
	void testGetByIdWhenIdNotFound() throws Exception {

		Mockito.doThrow(new DeptException(DeptExceptionConstants.DEPT_NOT_FOUND.getValue())).when(deptService)
				.get(Mockito.anyInt());

		this.mockMvc.perform(get(baseUrl + "/10").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is4xxClientError());

	}

	@Test
	void testGetAll() throws Exception {

		List<DeptDTO> list = new ArrayList<>();

		list.add(getDeptDTO());

		Mockito.when(deptService.getAll()).thenReturn(list);

		this.mockMvc.perform(get(baseUrl).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is2xxSuccessful()).andExpect(content().string(containsString("10")));
	}

	private DeptDTO getDeptDTO() {
		DeptDTO deptDTO = new DeptDTO();
		deptDTO.setDeptno(10);
		deptDTO.setDname("IT");
		return deptDTO;
	}

}
