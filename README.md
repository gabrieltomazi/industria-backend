# Industria Backend

Sistema para o chão de fábrica controlar a operação de equipamentos, o andamento de ordens de produção e os testes de inspeção de qualidade.

Projeto Final da disciplina de Backend (Engenharia de Software, SATC). **Etapa 1: arquitetura do backend.**

## Índice

- [Integrantes](#integrantes)
- [O problema](#o-problema)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Entidades](#entidades)
- [Rotas](#rotas)
- [Como executar](#como-executar)
- [Situação do projeto](#situação-do-projeto)
- [Documentação](#documentação)

## Integrantes

| Nome | GitHub |
|---|---|
| Rafael Silvano | https://github.com/RafaelSilvano |
| Gabriel Tomazi | https://github.com/gabrieltomazi |
| Tiago Portella | https://github.com/TiagoPortella |

## O problema

**Tema sorteado:** 🏭 Indústria e Manufatura

No dia a dia de uma fábrica, é comum começarem a fabricar peças em máquinas que estão com defeito ou precisando de conserto, porque a equipe não foi avisada a tempo. Outro grande problema é que, quando as peças começam a sair com defeito, a máquina continua ligada gerando desperdício de material. O sistema resolve isso: ele só deixa iniciar a produção se a máquina estiver funcionando normalmente e suspende a ordem de produção na hora se o teste de qualidade reprovar muitas peças.

**Quem usa o sistema:**

| Quem | O que faz no sistema |
|---|---|
| Supervisor de linha de produção | Cadastra equipamentos e acompanha ordens de produção na fábrica |
| Operador de máquinas | Aponta a produção e registra inspeções de qualidade nas ordens |
| Técnico de manutenção industrial | Monitora o estado dos equipamentos e atua em máquinas com manutenção pendente |

**Regras de negócio:**

| # | Regra | Onde está no código | Rota que usa |
|---|---|---|---|
| R1 | Uma ordem de produção só pode ser iniciada se o equipamento associado estiver no estado ATIVO. Se estiver em manutenção ou inativo, o sistema devolve HTTP 400 Bad Request. | `OrdemProducaoService` | `POST /ordens-producao` |
| R2 | Não é permitido registrar uma inspeção de qualidade com quantidade de peças inspecionadas superior à quantidade total produzida na respectiva ordem de produção (devolve HTTP 400 Bad Request). | `InspecaoQualidadeService` | `POST /inspecoes-qualidade` |
| R3 | Se mais de 10% das peças inspecionadas forem reprovadas num teste de qualidade, o equipamento associado deve ter o seu estado automaticamente alterado para MANUTENCAO_PENDENTE e a ordem de produção é suspensa. | `InspecaoQualidadeService` | `POST /inspecoes-qualidade` |

## Tecnologias

| O quê | Qual |
|---|---|
| Linguagem | Java 25 |
| Framework | Spring Boot 3.5 (Web, Validation, Data JPA) |
| Banco de dados | PostgreSQL 16, em Docker |
| Migrations | Flyway |
| Documentação da API | springdoc-openapi (Swagger UI) |

## Arquitetura

A API é organizada em camadas. Uma requisição entra pelo Controller, passa pelo Service, que aplica as regras de negócio, e chega ao banco pelo Repository; a resposta faz o caminho de volta. O Mapper converte DTO em entidade e entidade em DTO.

**Mapa da estrutura.** Uma linha por entidade, todas as do modelo. Em cada célula, a classe e a situação: **pronta**, **implementada** (já tem lógica), **em parte** (só uma parte das rotas) ou **montada** (criada, ligada às outras, ainda sem lógica).

| Entidade | Migration | Model | Repository | DTOs | Mapper | Service | Controller |
|---|---|---|---|---|---|---|---|
| `Equipamento` | `V1__criar_tabela_equipamentos.sql`, pronta | `Equipamento`, a fazer | `EquipamentoRepository`, a fazer | `EquipamentoRequestDTO`, `EquipamentoResponseDTO`, a fazer | `EquipamentoMapper`, a fazer | `EquipamentoService`, a fazer | `EquipamentoController`, a fazer |
| `OrdemProducao` | `V2__criar_tabela_ordens_producao.sql`, pronta | `OrdemProducao`, a fazer | `OrdemProducaoRepository`, a fazer | `OrdemProducaoRequestDTO`, `OrdemProducaoResponseDTO`, a fazer | `OrdemProducaoMapper`, a fazer | `OrdemProducaoService`, a fazer | `OrdemProducaoController`, a fazer |
| `InspecaoQualidade` | `V3__criar_tabela_inspecoes_qualidade.sql`, pronta | `InspecaoQualidade`, a fazer | `InspecaoQualidadeRepository`, a fazer | `InspecaoQualidadeRequestDTO`, `InspecaoQualidadeResponseDTO`, a fazer | `InspecaoQualidadeMapper`, a fazer | `InspecaoQualidadeService`, a fazer | `InspecaoQualidadeController`, a fazer |

## Entidades

| Entidade | O que representa | Como se relaciona |
|---|---|---|
| `Equipamento` | Máquina ou equipamento fabril | Possui várias ordens de produção |
| `OrdemProducao` | Ordem de fabricação executada em um equipamento | Vinculada a um equipamento e recebe inspeções de qualidade |
| `InspecaoQualidade` | Checagem de amostragem de peças produzidas | Vinculada a uma ordem de produção |

Atributos, tipos e o diagrama das tabelas estão em [docs/modelo-de-dominio.md](docs/modelo-de-dominio.md).

## Rotas

### As que já funcionam

| Verbo | Caminho | O que faz | Regra | Sucesso | Erros |
|---|---|---|---|---|---|
| ... | ... | ... | ... | ... | ... |

Exemplo de requisição:

```http
POST /equipamentos
Content-Type: application/json

{
  "nome": "Torno Mecânico CNC",
  "codigo": "TRN-001",
  "setor": "Usinagem",
  "estado": "ATIVO"
}
```

Resposta, `201 Created`:

```json
{
  "id": 1,
  "nome": "Torno Mecânico CNC",
  "codigo": "TRN-001",
  "setor": "Usinagem",
  "estado": "ATIVO"
}
```

### Erros

Todo erro sai no mesmo formato. Este é o `400` de uma regra de negócio violada (R1):

```json
{
  "status": 400,
  "erro": "Bad Request",
  "mensagem": "R1: o equipamento associado deve estar no estado ATIVO para iniciar a ordem de produção",
  "caminho": "/ordens-producao",
  "timestamp": "2026-10-12T14:30:00",
  "campos": []
}
```

Um campo inválido também devolve `400`, com a lista `campos` dizendo qual campo corrigir. Um id que não existe devolve `404`.

### As planejadas

Estão no contrato, mas ainda não respondem nesta primeira etapa:

| Recurso | O que vai fazer |
|---|---|
| `PUT /equipamentos/{id}` | Atualiza dados cadastrais ou estado de um equipamento |
| `DELETE /equipamentos/{id}` | Remove um equipamento cadastrado |
| `PUT /ordens-producao/{id}` | Atualiza dados ou altera o estado de uma ordem de produção |
| `DELETE /ordens-producao/{id}` | Cancela e remove uma ordem de produção |
| `PUT /inspecoes-qualidade/{id}` | Corrige dados de uma inspeção de qualidade realizada |
| `DELETE /inspecoes-qualidade/{id}` | Remove o registro de uma inspeção de qualidade |

O contrato completo, com todas as rotas e os DTOs, está em [docs/contrato-da-api.md](docs/contrato-da-api.md).

## Como executar

Pré-requisitos: JDK 25, Docker Desktop aberto e Git.

```bash
git clone https://github.com/gabrieltomazi/industria-backend.git
cd industria-backend

docker compose up -d          # sobe o PostgreSQL na porta 5437
./mvnw spring-boot:run        # Windows: .\mvnw.cmd spring-boot:run
```

Com a API no ar:

| Endereço | O que é |
|---|---|
| <http://localhost:8080/swagger-ui.html> | Swagger UI: as rotas, com **Try it out** para enviar requisições |
| <http://localhost:8080/v3/api-docs> | O contrato OpenAPI em JSON |

Para testar as rotas, use o Swagger UI ou o **Postman**. No Postman, importe todas as rotas de uma vez: **Import** → **Link** → `http://localhost:8080/v3/api-docs`.

Para parar: `Ctrl+C` na API e `docker compose stop` no banco. Para apagar o banco e rodar as migrations do zero: `docker compose down -v` e suba de novo.

## Situação do projeto

| Parte | Situação |
|---|---|
| Desenho | Completo: problema e regras neste README, modelo e contrato em docs/ |
| Estrutura | Em andamento: migrations, entidades, repositories, DTOs, mappers, services e controllers das 3 entidades |
| API e regras | Em andamento: API simples de equipamentos e implementação de R1, R2 e R3 |

### Com mais tempo (Próximos passos)
- **Frontend simples (Dashboard operacional):** Uma interface web visual para o chão de fábrica, permitindo que operadores apontem a produção, registrem inspeções e o supervisor visualize em tempo real quais máquinas estão com `MANUTENCAO_PENDENTE` (sem depender do Swagger ou Postman).
- **CRUD completo:** Implementar as rotas de edição e exclusão (`PUT` e `DELETE`) planejadas no contrato.

## Documentação

| Documento | O que tem |
|---|---|
| [Modelo de domínio](docs/modelo-de-dominio.md) | Entidades, relacionamentos, diagrama das tabelas, migrations |
| [Contrato da API](docs/contrato-da-api.md) | Todas as rotas, os DTOs, exemplos de JSON e erros |
| [Autoavaliação](docs/autoavaliacao.md) | Os critérios da revisão do código, marcados pelo grupo |
