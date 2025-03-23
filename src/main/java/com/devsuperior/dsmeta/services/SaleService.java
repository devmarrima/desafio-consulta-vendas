package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> report(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate dataFinal;

		if (maxDate != null && !maxDate.isEmpty()) {
			dataFinal = LocalDate.parse(maxDate);
		} else {
			dataFinal = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		LocalDate dataInicial;
		if (minDate != null && !minDate.isEmpty()) {
			dataInicial = LocalDate.parse(minDate);
		} else {
			dataInicial = dataFinal.minusYears(1L);
		}
		Page<SaleMinDTO> result = repository.searchByNameDate(name, pageable, dataFinal, dataInicial);

		return result;
	}

	public List<SellerMinDTO> summary(String minDate, String maxDate) {
		LocalDate dataFinal;

		if (maxDate != null && !maxDate.isEmpty()) {
			dataFinal = LocalDate.parse(maxDate);
		} else {
			dataFinal = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		LocalDate dataInicial;
		if (minDate != null && !minDate.isEmpty()) {
			dataInicial = LocalDate.parse(minDate);
		} else {
			dataInicial = dataFinal.minusYears(1L);
		}
		List<SellerMinDTO> result = repository.searchByDate(dataInicial, dataFinal);
		return result;
	}

}
