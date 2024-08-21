package com.mockserver.consulta_catalogo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mockserver.consulta_catalogo.MonthlyPremiumAmount;
import com.mockserver.consulta_catalogo.model.output.ConsultaOfertaOutput;
import com.mockserver.consulta_catalogo.model.output.ConsultaProdutoOutput;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsultaCatalogoService {

    public ConsultaProdutoOutput getProduto(String idProduto) throws JsonProcessingException {
        return generateMockObjetoConsultaProduto();
    }

    public ConsultaOfertaOutput getOferta(String idOferta) throws JsonProcessingException {
        return generateMockObjetoConsultaOferta();
    }

    private ConsultaProdutoOutput generateMockObjetoConsultaProduto() {
        return new ConsultaProdutoOutput() {{
            setId("1b2da7cc-b367-4196-8a78-9cfeec21f587");
            setName("Seguro de Vida");
            setActive(true);
            setCreatedAt(Timestamp.from(OffsetDateTime.parse("2021-07-01T00:00:00Z").toInstant()));
            setOffers(generateOffers());
        }};
    }

    private List<String> generateOffers() {
        return new ArrayList<>(List.of(
                "adc56d77-348c-4bf0-908f-22d402ee715c",
                "bdc56d77-348c-4bf0-908f-22d402ee715c",
                "cdc56d77-348c-4bf0-908f-22d402ee715c"));
    }

    private ConsultaOfertaOutput generateMockObjetoConsultaOferta() {
        ConsultaOfertaOutput consultaOfertaOutput = new ConsultaOfertaOutput();
        consultaOfertaOutput.setId("adc56d77-348c-4bf0-908f-22d402ee715c");
        consultaOfertaOutput.setProductId("1b2da7cc-b367-4196-8a78-9cfeec21f587");
        consultaOfertaOutput.setName("Seguro de Vida Familiar");
        consultaOfertaOutput.setCreatedAt(Timestamp.from(OffsetDateTime.parse("2021-07-01T00:00:00Z").toInstant()));
        consultaOfertaOutput.setActive(true);
        consultaOfertaOutput.setCoverages(generateCoverages());
        consultaOfertaOutput.setAssistances(generateAssistances());
        consultaOfertaOutput.setMonthlyPremiumAmount(generateMonthlyPremiumAmount());
        return consultaOfertaOutput;
    }

    private List<String> generateAssistances() {
        return new ArrayList<>(List.of("Encanador", "Eletricista", "Chaveiro 24h", "Assistência Funerária"));
    }

    private MonthlyPremiumAmount generateMonthlyPremiumAmount() {
        return new MonthlyPremiumAmount() {{
            setMaxAmount(BigDecimal.valueOf(100.74));
            setMinAmount(BigDecimal.valueOf(50.00));
            setSuggestedAmount(BigDecimal.valueOf(60.25));
        }};
    }

    private Map<String, BigDecimal> generateCoverages() {
        Map<String, BigDecimal> coverages = new HashMap<>();
        coverages.put("Incêndio", BigDecimal.valueOf(500_000.00));
        coverages.put("Desastres naturais", BigDecimal.valueOf(600_000.00));
        coverages.put("Responsabilidade civil", BigDecimal.valueOf(80_000.00));
        coverages.put("Roubo", BigDecimal.valueOf(100_000.00));
        return coverages;
    }
}