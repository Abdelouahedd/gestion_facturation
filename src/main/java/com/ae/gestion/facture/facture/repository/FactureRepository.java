package com.ae.gestion.facture.facture.repository;

import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.service.dto.FactureMonth;
import com.ae.gestion.facture.facture.service.dto.FactureComplete;
import com.ae.gestion.facture.facture.service.dto.TotalPerMonth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long>, JpaSpecificationExecutor<Facture> {

  Optional<Facture> findByTotal(Double total);

  Page<Facture> findAll(Specification specification, Pageable pageable);

  @Query(value = "select distinct FUNCTION('to_char',f.createdDate,'YYYY') as year from Facture f order by year asc")
  List<String> getDateFactures();

  @Query(value = "select count(id) as number,to_char(created_date  ,'TMMonth') as month,to_char(created_date  ,'MM') as order_month from facture " +
    "where to_char(created_date,'YYYY') = :year and state <> 'DELETED' group by month,order_month order by order_month", nativeQuery = true)
  List<FactureMonth> getNumberFactureByMonth(@Param("year") String year);

  @Query("SELECT SUM(f.total) from Facture f")
  BigDecimal getTotalPriceOfAllBills();


  @Query("select new com.ae.gestion.facture.facture.service.dto.FactureComplete(COUNT(distinct F.id),(CASE WHEN (F.complete=true) THEN 'complete' else 'Non Complete' end)) " +
    "from Facture F group by F.complete")
  List<FactureComplete> getNumberFactureByStatus();


  @Query("select sum(f.total) as total,FUNCTION('to_char',f.createdDate ,'TMMonth') as month,FUNCTION('to_char',f.createdDate ,'MM') as order_month\n" +
    "from Facture f\n" +
    "group by month,order_month\n" +
    "order by order_month")
  List<TotalPerMonth>getTotalFacturePerMonth();

}
