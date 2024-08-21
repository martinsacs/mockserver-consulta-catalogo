package com.mockserver.consulta_catalogo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.mockserver.consulta_catalogo.MonthlyPremiumAmount;
import com.mockserver.consulta_catalogo.model.output.ConsultaOfertaOutput;
import com.mockserver.consulta_catalogo.model.output.ConsultaProdutoOutput;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

@Configuration
public class ConsultaCatalogoMockServerConfig {

    private WireMockServer wireMockServer;

    @PostConstruct
    public void startServer() throws JsonProcessingException {
        wireMockServer = new WireMockServer(8081);
        wireMockServer.start();

        WireMock.configureFor("localhost", 8081);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        WireMock.stubFor(WireMock.get(urlPathMatching("/v1/produtos/[a-zA-Z0-9-]+"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(generateMockObjetoConsultaProduto()))));

        WireMock.stubFor(WireMock.get(urlPathMatching("/v1/ofertas/[a-zA-Z0-9-]+"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(generateMockObjetoConsultaOferta()))));
    }

    @PreDestroy
    public void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    public static ResponseEntity<ConsultaProdutoOutput> mockResponseEntity(ConsultaProdutoOutput body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    public static ConsultaProdutoOutput generateMockObjetoConsultaProduto() {
        return new ConsultaProdutoOutput() {{
            setId("1b2da7cc-b367-4196-8a78-9cfeec21f587");
            setName("Seguro de Vida");
            setActive(true);
            setCreatedAt(Timestamp.from(OffsetDateTime.parse("2021-07-01T00:00:00Z").toInstant()));
            setOffers(generateOffers());
        }};
    }

    private static List<String> generateOffers() {
        return new ArrayList<>(List.of(
                "adc56d77-348c-4bf0-908f-22d402ee715c",
                "bdc56d77-348c-4bf0-908f-22d402ee715c",
                "cdc56d77-348c-4bf0-908f-22d402ee715c"));

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