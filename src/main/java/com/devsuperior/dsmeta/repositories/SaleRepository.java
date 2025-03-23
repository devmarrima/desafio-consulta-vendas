package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

        @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.date, obj.amount, obj.seller.name) obj FROM Sale obj WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))"
                        + "AND obj.date BETWEEN :dataInicial AND :dataFinal")
        Page<SaleMinDTO> searchByNameDate(String name, Pageable pageable, LocalDate dataFinal, LocalDate dataInicial);

        @Query("SELECT new com.devsuperior.dsmeta.dto.SellerMinDTO(obj.seller.name, SUM(obj.amount)) " +
                        "FROM Sale obj " +
                        "WHERE obj.date BETWEEN :dataInicial AND :dataFinal " +
                        "GROUP BY obj.seller.name")
        List<SellerMinDTO> searchByDate(LocalDate dataInicial, LocalDate dataFinal);

}
