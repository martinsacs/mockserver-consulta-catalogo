package com.mockserver.consulta_catalogo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mockserver.consulta_catalogo.model.output.ConsultaOfertaOutput;
import com.mockserver.consulta_catalogo.model.output.ConsultaProdutoOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsultaCatalogoServiceTest {

    private ConsultaCatalogoService consultaCatalogoService;

    @BeforeEach
    public void setUp() {
        consultaCatalogoService = new ConsultaCatalogoService();
    }

    @Test
    public void testGetProduto() throws JsonProcessingException {
        ConsultaProdutoOutput produto = consultaCatalogoService.getProduto("1b2da7cc-b367-4196-8a78-9cfeec21f587");
        assertThat(produto).isNotNull();
        assertThat(produto.getId()).isEqualTo("1b2da7cc-b367-4196-8a78-9cfeec21f587");
        assertThat(produto.getName()).isEqualTo("Seguro de Vida");
        assertTrue(produto.getActive());
        assertThat(produto.getOffers()).containsExactly("adc56d77-348c-4bf0-908f-22d402ee715c", "bdc56d77-348c-4bf0-908f-22d402ee715c", "cdc56d77-348c-4bf0-908f-22d402ee715c");
    }

    @Test
    public void testGetOferta() throws JsonProcessingException {
        ConsultaOfertaOutput oferta = consultaCatalogoService.getOferta("adc56d77-348c-4bf0-908f-22d402ee715c");
        assertThat(oferta).isNotNull();
        assertThat(oferta.getId()).isEqualTo("adc56d77-348c-4bf0-908f-22d402ee715c");
        assertThat(oferta.getProductId()).isEqualTo("1b2da7cc-b367-4196-8a78-9cfeec21f587");
        assertThat(oferta.getName()).isEqualTo("Seguro de Vida Familiar");
        assertTrue(oferta.getActive());
        assertThat(oferta.getCoverages()).containsEntry("Incêndio", BigDecimal.valueOf(500_000.00));
        assertThat(oferta.getAssistances()).containsExactly("Encanador", "Eletricista", "Chaveiro 24h", "Assistência Funerária");
        assertThat(oferta.getMonthlyPremiumAmount().getMaxAmount()).isEqualTo(BigDecimal.valueOf(100.74));
    }
}