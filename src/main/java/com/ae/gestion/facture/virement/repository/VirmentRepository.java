package com.ae.gestion.facture.virement.repository;

import com.ae.gestion.facture.facture.service.dto.TotalPerMonth;
import com.ae.gestion.facture.virement.domaine.Virment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VirmentRepository extends JpaRepository<Virment, Long>, JpaSpecificationExecutor<Virment> {

    Optional<Virment> findByFacture(Long idFacture);

  @Query("select sum(prix) as total,FUNCTION('to_char',createdDate ,'TMMonth') as month,FUNCTION('to_char',createdDate ,'MM') as order_month " +
    "from Virment " +
    "group by month,order_month " +
    "order by order_month")
  List<TotalPerMonth> getTotalVirmentPerMonth();
}
