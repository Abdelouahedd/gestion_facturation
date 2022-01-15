package com.ae.gestion.facture.facture.service.dto;


import java.math.BigDecimal;


public interface TotalPerMonth {
  BigDecimal getTotal();

  String getMonth();
}
