# Autoavaliação

Os 10 critérios que serão avaliados na revisão do código, descritos em `Aula 10-11/AVALIACAO.md`, no repositório da disciplina. Para cada um, a marca do grupo e onde está a evidência.

- ✅ atende
- ◐ atende em parte (diga o que falta)
- ❌ não atende

| Critério | Marca | Evidência |
|---|---|---|
| P1 A API sobe seguindo o README |✅| `README.md`, seção "Como executar" |
| P2 Cada classe no pacote da sua camada |✅| `src/main/java/api/` dividido em `controller`, `service`, `repository`, `model`, `dto`, `mapper` e `config` |
| P3 Migrations e `@Entity` de todas as tabelas, e a API sobe |✅| Migrations `V1`, `V2`, `V3` em `src/main/resources/db/migration/` e entidades `Equipamento`, `OrdemProducao`, `InspecaoQualidade` em `api.model` |
| P4 DTOs de entrada e de saída de todas as entidades |✅| Records `RequestDTO` e `ResponseDTO` para todas as entidades em `api.dto` com Bean Validation |
| P5 Repository, Mapper, Service e Controller de todas, com o comentário no topo, e o mapa da estrutura |✅| Todas as classes criadas em suas camadas com comentários estruturais no topo e tabela em `README.md`, seção "Arquitetura" |
| P6 API simples: `POST`, `GET` e `GET /{id}`, com `400` e `404` |✅| Implementadas e funcionais em `EquipamentoController`, `OrdemProducaoController` e `InspecaoQualidadeController` |
| P7 No mínimo 3 regras de negócio implementadas, com `400` quando violadas |✅| **R1** em `OrdemProducaoService`, **R2** e **R3** em `InspecaoQualidadeService` lançando `RegraDeNegocioException` (400) |
| P8 As regras atendem ao problema e ao tema |✅| `README.md`, seção "O problema" |
| P9 Código igual ao desenho |✅| `docs/modelo-de-dominio.md` e `docs/contrato-da-api.md` |
| P10 README completo e bem escrito |✅| `README.md`, conferido no GitHub |

**Pontos que o grupo espera no projeto:** 5,0

A apresentação vale os outros 5,0 pontos: são 3 perguntas, feitas na Aula 11.
