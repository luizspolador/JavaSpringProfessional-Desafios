package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<List<SaleMinDTO>> getReport(
			@RequestParam(value = "minDate", required = false) String minDate,
			@RequestParam(value = "maxDate", required = false) String maxDate,
			@RequestParam(value = "name", defaultValue = "") String name) {

		LocalDate startDate = minDate != null ? LocalDate.parse(minDate) : LocalDate.now().minusYears(1);
		LocalDate endDate = maxDate != null ? LocalDate.parse(maxDate) : LocalDate.now();

		List<SaleMinDTO> result = service.getSalesReport(startDate, endDate, name);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<Object[]>> getSummary(
			@RequestParam(value = "minDate", required = false) String minDate,
			@RequestParam(value = "maxDate", required = false) String maxDate) {

		LocalDate startDate = minDate != null ? LocalDate.parse(minDate) : LocalDate.now().minusYears(1);
		LocalDate endDate = maxDate != null ? LocalDate.parse(maxDate) : LocalDate.now();

		List<Object[]> result = service.getSalesSummary(startDate, endDate);
		return ResponseEntity.ok(result);
	}
}
