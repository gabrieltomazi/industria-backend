# Contrato da API

> O contrato é o que o cliente da API enxerga: rotas, JSON e status. Desenhe as rotas de **todas** as entidades. Nesta etapa precisam funcionar as três da API simples e as que as regras de negócio usam.

## Convenções

| Item | Valor |
|---|---|
| Endereço base | `http://localhost:8080` |
| Formato | JSON, na requisição e na resposta |
| Datas | `yyyy-MM-dd` |
| Erros | Sempre no formato `ErroDTO` (ver [Erros](#erros)) |
| Documentação viva | `http://localhost:8080/swagger-ui.html` |

## Rotas

Coluna **Funciona**: `sim` para as rotas que já respondem nesta etapa: as da API simples (`/equipamentos`) e as que as regras de negócio usam.

### Equipamentos

| Verbo | Caminho | O que faz | Entrada | Saída | Sucesso | Erros | Funciona |
|---|---|---|---|---|---|---|---|
| `GET` | `/equipamentos` | Lista os equipamentos | Nenhuma | lista de `EquipamentoResponseDTO` | `200` | Nenhum | sim |
| `GET` | `/equipamentos/{id}` | Busca um equipamento | Nenhuma | `EquipamentoResponseDTO` | `200` | `404` | sim |
| `POST` | `/equipamentos` | Cadastra um equipamento | `EquipamentoRequestDTO` | `EquipamentoResponseDTO` | `201` | `400` | sim |
| `PUT` | `/equipamentos/{id}` | Atualiza um equipamento | `EquipamentoRequestDTO` | `EquipamentoResponseDTO` | `200` | `400`, `404` | não |
| `DELETE` | `/equipamentos/{id}` | Remove um equipamento | Nenhuma | Nenhuma (sem corpo) | `204` | `404` | não |

### Ordens de Produção

| Verbo | Caminho | O que faz | Entrada | Saída | Sucesso | Erros | Funciona |
|---|---|---|---|---|---|---|---|
| `GET` | `/ordens-producao` | Lista as ordens de produção | Nenhuma | lista de `OrdemProducaoResponseDTO` | `200` | Nenhum | não |
| `GET` | `/ordens-producao/{id}` | Busca uma ordem pelo ID | Nenhuma | `OrdemProducaoResponseDTO` | `200` | `404` | não |
| `POST` | `/ordens-producao` | Registra e inicia uma ordem de produção | `OrdemProducaoRequestDTO` | `OrdemProducaoResponseDTO` | `201` | `400`, `404` | sim |
| `PUT` | `/ordens-producao/{id}` | Atualiza uma ordem | `OrdemProducaoRequestDTO` | `OrdemProducaoResponseDTO` | `200` | `400`, `404` | não |
| `DELETE` | `/ordens-producao/{id}` | Remove uma ordem | Nenhuma | Nenhuma (sem corpo) | `204` | `404` | não |

### Inspeções de Qualidade

| Verbo | Caminho | O que faz | Entrada | Saída | Sucesso | Erros | Funciona |
|---|---|---|---|---|---|---|---|
| `GET` | `/inspecoes-qualidade` | Lista as inspeções de qualidade | Nenhuma | lista de `InspecaoQualidadeResponseDTO` | `200` | Nenhum | não |
| `GET` | `/inspecoes-qualidade/{id}` | Busca uma inspeção pelo ID | Nenhuma | `InspecaoQualidadeResponseDTO` | `200` | `404` | não |
| `POST` | `/inspecoes-qualidade` | Registra uma inspeção de qualidade | `InspecaoQualidadeRequestDTO` | `InspecaoQualidadeResponseDTO` | `201` | `400`, `404` | sim |
| `PUT` | `/inspecoes-qualidade/{id}` | Atualiza uma inspeção | `InspecaoQualidadeRequestDTO` | `InspecaoQualidadeResponseDTO` | `200` | `400`, `404` | não |
| `DELETE` | `/inspecoes-qualidade/{id}` | Remove uma inspeção | Nenhuma | Nenhuma (sem corpo) | `204` | `404` | não |

### Rotas que executam as Regras de Negócio

| Verbo | Caminho | O que faz | Regra | Sucesso | Erros |
|---|---|---|---|---|---|
| `POST` | `/ordens-producao` | Inicia uma ordem garantindo que o equipamento esteja ATIVO | R1 | `201` | `400`, `404` |
| `POST` | `/inspecoes-qualidade` | Valida que a quantidade inspecionada não supera a quantidade produzida | R2 | `201` | `400`, `404` |
| `POST` | `/inspecoes-qualidade` | Se reprovação > 10%, suspende a ordem e altera equipamento para MANUTENCAO_PENDENTE | R3 | `201` | `400`, `404` |

## DTOs

O que entra não é igual ao que sai: o cliente não envia `id` nem campos que a API decide.

| DTO | Direção | Campos | Validações |
|---|---|---|---|
| `EquipamentoRequestDTO` | entrada | `nome`, `codigo`, `setor`, `estado` | `nome`, `codigo`, `setor` obrigatórios (`@NotBlank`); `estado` opcional (default `ATIVO`) |
| `EquipamentoResponseDTO` | saída | `id`, `nome`, `codigo`, `setor`, `estado` | Nenhuma |
| `OrdemProducaoRequestDTO` | entrada | `codigo`, `produto`, `quantidadePlanejada`, `quantidadeProduzida`, `equipamentoId` | `@NotBlank` para strings, `@NotNull` e `@Positive` para quantidades, `@NotNull` para `equipamentoId` |
| `OrdemProducaoResponseDTO` | saída | `id`, `codigo`, `produto`, `quantidadePlanejada`, `quantidadeProduzida`, `estado`, `equipamentoId` | Nenhuma |
| `InspecaoQualidadeRequestDTO`| entrada | `ordemProducaoId`, `quantidadeInspecionada`, `quantidadeReprovada`, `observacoes` | `@NotNull` para id e quantidades, `@Positive` para `quantidadeInspecionada`, `@Min(0)` para `quantidadeReprovada` |
| `InspecaoQualidadeResponseDTO`| saída | `id`, `ordemProducaoId`, `quantidadeInspecionada`, `quantidadeReprovada`, `data`, `observacoes` | Nenhuma |

## Exemplos

### `POST /equipamentos`

Requisição:

```json
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

### `POST /ordens-producao`

Requisição:

```json
{
  "codigo": "OP-2026-100",
  "produto": "Eixo de Transmissão",
  "quantidadePlanejada": 500,
  "quantidadeProduzida": 100,
  "equipamentoId": 1
}
```

Resposta, `201 Created`:

```json
{
  "id": 1,
  "codigo": "OP-2026-100",
  "produto": "Eixo de Transmissão",
  "quantidadePlanejada": 500,
  "quantidadeProduzida": 100,
  "estado": "EM_ANDAMENTO",
  "equipamentoId": 1
}
```

### `POST /inspecoes-qualidade`

Requisição:

```json
{
  "ordemProducaoId": 1,
  "quantidadeInspecionada": 50,
  "quantidadeReprovada": 2,
  "observacoes": "Amostragem do primeiro lote de 100 peças"
}
```

Resposta, `201 Created`:

```json
{
  "id": 1,
  "ordemProducaoId": 1,
  "quantidadeInspecionada": 50,
  "quantidadeReprovada": 2,
  "data": "2026-10-12",
  "observacoes": "Amostragem do primeiro lote de 100 peças"
}
```

## Erros

Todo erro sai no mesmo formato. Este é o `404` de `GET /equipamentos/999`:

```json
{
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Equipamento não encontrado: 999",
  "caminho": "/equipamentos/999",
  "timestamp": "2026-10-12T14:30:00",
  "campos": []
}
```

No `400` de validação, a lista `campos` traz um item por campo inválido: `{"campo": "nome", "mensagem": "Nome é obrigatório"}`.

Exemplo de `400` por violação da regra de negócio **R1**:

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

| Status | Quando acontece neste projeto |
|---|---|
| `400` | JSON ilegível ou campo inválido no DTO de entrada |
| `404` | Id que não existe |
| `400` | Regra de negócio violada: a `mensagem` diz qual regra barrou o pedido |
