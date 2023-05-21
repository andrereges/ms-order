# Microserviço Processador de Pedidos - ms-order

Sistema criado para desafio Engenheiro de Software.

Este sistema consume dados de uma fila *message broker* em formato JSON e será gravado em um banco de dados. Exemplo da mensagem consumida:

```json
{
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "items": [
    {
      "produto": "lápis",
      "quantidade": 100,
      "preco": 1.10
    },
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 1.00
    }
  ]
}
```

É possível consultar os seguintes dados via API REST:

- Valor total do pedido
- Quantidade de pedidos por cliente
- Lista de pedidos realizados por cliente
