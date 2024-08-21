# MockServer: Consulta catálogo de produtos e ofertas

Este projeto é uma aplicação mock de consulta de produtos e ofertas de seguros, desenvolvida em Java utilizando Spring Boot e Maven. \
Ele inclui serviços para consultar produtos e ofertas e é primordial para a execução local do serviço de cotação situado no 
repositório [acme-seguradora](https://github.com/martinsacs/acme-seguradora/tree/feature/cotacao-seguro).

## Arquitetura

### Ferramentas
Essa solução utiliza: 
- **Linguagem:** Java 17
- **Gerenciamento de depêndencias e build:** Maven
- **Framework(s):** Spring

### Arquitetura utilizada

A arquitetura utilizada neste projeto é a arquitetura de microsserviços. \
Cada serviço é responsável por uma funcionalidade específica e se comunica com outros serviços através de APIs REST.

### Justificativa

A escolha da arquitetura de microsserviços foi feita com base nos seguintes pontos:

- **Escalabilidade**: Permite escalar individualmente cada serviço conforme a demanda.
- **Manutenibilidade**: Facilita a manutenção e evolução do sistema, pois cada serviço pode ser desenvolvido, testado e implantado de forma independente.
- **Desempenho**: Melhora o desempenho ao permitir que serviços críticos sejam otimizados e escalados separadamente.
- **Flexibilidade**: Permite a utilização de diferentes tecnologias e frameworks para cada serviço, conforme a necessidade.


## Endpoints v1

### `GET /v1/produtos/{id_produto}`
Consulta informações de um produto específico.

- **Path Variable**: `id_produto` (ID do produto)
- **Response**: `ResponseEntity<ConsultaProdutoOutput>` com os detalhes do produto.

### `GET /v1/ofertas/{id_oferta}`
Consulta informações de uma oferta específica.

- **Path Variable**: `id_oferta` (ID da oferta)
- **Response**: `ResponseEntity<ConsultaOfertaOutput>` com os detalhes da oferta.

## Estrutura do projeto

### Pacotes principais

#### `com.mockserver-consulta-catalogo`
Pacote raiz do projeto que contém as classes principais e de configuração.

#### `com.mockserver-consulta-catalogo.model`
Contém as classes de modelo que representam os dados utilizados na aplicação.

#### `com.mockserver-consulta-catalogo.model.output`
Contém as classes de modelo que representam as respostas simuladas das consultas de produtos e ofertas.

#### `com.mockserver-consulta-catalogo.config`
Contém as classes de configuração da aplicação.

### Modelos

#### `ConsultaProdutoOutput`
Classe que representa a resposta da consulta de um produto. Inclui informações sobre o status (ativo ou inativo) do produto.

#### `ConsultaOfertaOutput`
Classe que representa a resposta da consulta de uma oferta. Inclui informações sobre o status da oferta, valores de prêmio mensal, coberturas e assistências.

#### `MonthlyPremiumAmount`
Classe que representa os valores de prêmio mensal mínimo, máximo e recomendado.


## Configuração e execução

### Pré-requisitos
- Java 11 ou superior
- Maven 3.6 ou superior

### Como executar

1. Clone o repositório:
```
git clone https://github.com/martinsacs/mockserver-consulta-catalogo
cd mockserver-consulta-catalogo
```

2. Abra o arquivo pom.xml como projeto no IDE de sua preferência (recomendamos IntelliJ)

3. Execute o comando abaixo para compilar o projeto:
```
maven clean install
```

4. Digite a instrução abaixo para executar os testes unitários:
```
mvn test
```

5. Digite a instrução abaixo para executar a aplicação:
```
mvn spring-boot:run -Dspring-boot.run.mainClass=com.mockserver-consulta-catalogo.ConsultaCatalogoApplication
```
