package com.mockserver.consulta_catalogo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mockserver.consulta_catalogo.model.output.ConsultaOfertaOutput;
import com.mockserver.consulta_catalogo.model.output.ConsultaProdutoOutput;
import com.mockserver.consulta_catalogo.service.ConsultaCatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class ConsultaCatalogoController {

    @Autowired
    private ConsultaCatalogoService consultaCatalogoService;

    private static final List<String> VALID_PRODUCT_IDS = Arrays.asList(
            "1b2da7cc-b367-4196-8a78-9cfeec21f587",
            "2b2da7cc-b367-4196-8a78-9cfeec21f588"
    );

    private static final List<String> VALID_OFFER_IDS = Arrays.asList(
            "adc56d77-348c-4bf0-908f-22d402ee715c",
            "bdc56d77-348c-4bf0-908f-22d402ee715d"
    );
    @GetMapping("/produtos/{idProduto}")
    public ResponseEntity<?> getProduto(@PathVariable String idProduto) throws JsonProcessingException {
        if (!VALID_PRODUCT_IDS.contains(idProduto)) {
            return ResponseEntity.badRequest().body("Produto inexistente.");
        }
        ConsultaProdutoOutput produto = consultaCatalogoService.getProduto(idProduto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/ofertas/{idOferta}")
    public ResponseEntity<?> getOferta(@PathVariable String idOferta) throws JsonProcessingException {
        if (!VALID_OFFER_IDS.contains(idOferta)) {
            return ResponseEntity.badRequest().body("Oferta inexistente.");
        }
        ConsultaOfertaOutput oferta = consultaCatalogoService.getOferta(idOferta);
        return ResponseEntity.ok(oferta);
    }
}