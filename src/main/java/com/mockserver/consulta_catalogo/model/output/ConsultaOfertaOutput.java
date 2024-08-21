package com.mockserver.consulta_catalogo.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mockserver.consulta_catalogo.MonthlyPremiumAmount;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
public class ConsultaOfertaOutput {
    private String id;

    @JsonProperty("product_id")
    private String productId;

    private String name;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    private Boolean active;

    private Map<String, BigDecimal> coverages;

    private List<String> assistances;

    @JsonProperty("monthly_premium_amount")
    private MonthlyPremiumAmount monthlyPremiumAmount;
}
