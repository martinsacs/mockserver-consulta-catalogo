package com.mockserver.consulta_catalogo.model.output;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ConsultaProdutoOutput {
    private String id;
    private String name;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    private Boolean active;

    private List<String> offers;

}
