package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(s.id, s.amount, s.date) FROM Sale s "
            + "WHERE s.date BETWEEN :minDate AND :maxDate AND (:name = '' OR s.seller.name LIKE :name)")
    List<SaleMinDTO> findSalesReport(@Param("minDate") LocalDate minDate,
                                     @Param("maxDate") LocalDate maxDate,
                                     @Param("name") String name);

    @Query("SELECT s.seller.name, SUM(s.amount) FROM Sale s "
            + "WHERE s.date BETWEEN :minDate AND :maxDate GROUP BY s.seller.name")
    List<Object[]> findSalesSummary(@Param("minDate") LocalDate minDate,
                                    @Param("maxDate") LocalDate maxDate);


}
