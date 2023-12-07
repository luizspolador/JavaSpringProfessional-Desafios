package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleMinDTO> getSalesReport(LocalDate minDate, LocalDate maxDate, String name) {
		return repository.findSalesReport(minDate, maxDate, "%" + name + "%");
	}

	public List<Object[]> getSalesSummary(LocalDate minDate, LocalDate maxDate) {
		return repository.findSalesSummary(minDate, maxDate);
	}

}
