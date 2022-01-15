package com.ae.gestion.facture.facture.repository;

import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.service.dto.FactureMonth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long>, JpaSpecificationExecutor<Facture> {

  Optional<Facture> findByTotal(Double total);

  Page<Facture> findAll(Specification specification, Pageable pageable);

  @Query(value = "select distinct FUNCTION('to_char',f.createdDate,'YYYY') as year from Facture f order by year asc")
  List<String> getDateFactures();

  @Query(value = "select count(id) as number,to_char(created_date  ,'TMMonth') as month,to_char(created_date  ,'MM') as order_month from facture " +
                 "where to_char(created_date,'YYYY') = :year group by month,order_month order by order_month", nativeQuery = true)
  List<FactureMonth> getNumberFactureByMonth(@Param("year") String year);
}
