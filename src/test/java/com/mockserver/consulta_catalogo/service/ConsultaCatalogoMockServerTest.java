package com.mockserver.consulta_catalogo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockserver.consulta_catalogo.ConsultaCatalogoApplication;
import com.mockserver.consulta_catalogo.MonthlyPremiumAmount;
import com.mockserver.consulta_catalogo.config.ConsultaCatalogoMockServerConfig;
import com.mockserver.consulta_catalogo.model.output.ConsultaOfertaOutput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mockserver.consulta_catalogo.config.ConsultaCatalogoMockServerConfig.generateMockObjetoConsultaProduto;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {ConsultaCatalogoApplication.class, ConsultaCatalogoMockServerConfig.class})
public class ConsultaCatalogoMockServerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testMockServerConsultaProdutoResponse() throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/v1/produtos/123", String.class);
        assertThat(response.getBody()).isEqualTo(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(generateMockObjetoConsultaProduto()));
    }

    @Test
    public void testMockServerConsultaOfertaResponse() throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/v1/ofertas/123", String.class);
        assertThat(response.getBody()).isEqualTo(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(generateMockObjetoConsultaOferta()));
    }

    public static ConsultaOfertaOutput generateMockObjetoConsultaOferta() {
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

    private static List<String> generateAssistances() {
        return new ArrayList<>(List.of("Encanador", "Eletricista", "Chaveiro 24h", "Assistência Funerária"));
    }

    private static MonthlyPremiumAmount generateMonthlyPremiumAmount() {
        return new MonthlyPremiumAmount() {{
            setMaxAmount(BigDecimal.valueOf(100.74));
            setMinAmount(BigDecimal.valueOf(50.00));
            setSuggestedAmount(BigDecimal.valueOf(60.25));
        }};
    }

    public static Map<String, BigDecimal> generateCoverages() {
        Map<String, BigDecimal> coverages = new HashMap<>();
        coverages.put("Incêndio", BigDecimal.valueOf(500_000.00));
        coverages.put("Desastres naturais", BigDecimal.valueOf(600_000.00));
        coverages.put("Responsabilidade civil", BigDecimal.valueOf(80_000.00));
        coverages.put("Roubo", BigDecimal.valueOf(100_000.00));
        return coverages;
    }
}
