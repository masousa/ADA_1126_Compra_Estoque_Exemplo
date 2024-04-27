# Serviço de compra e estoque

## Passos para funcionar

Antes de executar o projeto é necessário executar o arquivo ``docker-compose.yml``. Este arquivo contém o recurso necessário para iniciar o banco de dados, como também o RabbitMQ.

Caso não seja possível iniciar o docker é necessário instalar tanto o postgres quanto o RabbitMQ separadamente.

Para isto:

  - [Postgres](https://www.postgresql.org/download/)
  - [RabbitMQ](https://www.rabbitmq.com/docs/download)

>> Para o postgres, sem o uso do docker, ainda é necessário criar o banco de dados bem como o usuário da aplicação.

```sql
CREATE ROLE estoque LOGIN PASSWORD 'estoque';
CREATE ROLE compra LOGIN PASSWORD 'compra';

CREATE DATABASE estoque;
CREATE DATABASE compra;

ALTER DATABASE estoque OWNER TO estoque;
ALTER DATABASE compra OWNER TO compra;

GRANT ALL ON DATABASE estoque TO "estoque";
GRANT ALL ON DATABASE compra TO "compra";

```

## Iniciando os serviços

### RabbitMQ

Com o RabbitMQ funcionado localmente (http://localhost:15672/), caso as filas ainda não tenham sido criadas:

  - remover-itens-estoque
  - retorno-remover-itens-estoque

### Microserviços

Os serviços estão disponíveis

  - Compra: http://localhost:9080/swagger-ui/index.html
  - Estoque: http://localhost:9081/swagger-ui/index.html

Ao rodar o sistema de estoque pela primeira vez será possível notar que ele insere uma série de itens no estoque. Isto é possível através da classe ``ItemRepositoryLoader``

## Conhecendo os serviços
Listando aqui os métodos mais utilizados entre os serviços

### VendaService

#### Adicionar ao carrinho

Vai adicionar os itens a um carrinho. Caso o id do carrinho não seja informado, o sistema vai gerar um automaticamente. Para adicionar mais itens ao carrinho já existente é necessário informar o seu identificador.

Como retorno este método retorna o identificador do carrinho, bem como os itens relacionados a ele.

Exemplo:

Requisição:

```json
{
  "skuId": "Z002",
  "valorUnitario": 200,
  "quantidade": 5

}
```

Resposta:

```json
{
  "id": "1e3b3a9f-f3aa-46e5-8877-d57213735b9b",
  "itens": [
    {
      "skuId": "Z0002",
      "valorUnitario": 200,
      "quantidade": 5
    }
  ],
  "status": "INICIAL"
}
```

#### Realizar venda

Vai salvar a venda no banco de dados do sistema, deixando-a com o status de pendente, uma vez que a compra vai ser analisada pelo estoque.
Ela recebe um parametro booleano para definir se a comunicação com o serviço de estoque vai ser por meio de REST ou AMQP

Requisição
```json
{
  "cliente": {
    "id": "cb7961ec-7de5-11ee-b962-0242ac120002"
  },
  "carrinho-id": "1e3b3a9f-f3aa-46e5-8877-d57213735b9b"
}
```

Resposta

```json
{
  "id": "1e3b3a9f-f3aa-46e5-8877-d57213735b9b",
  "itens": [
    {
      "skuId": "Z001",
      "valorUnitario": 200,
      "quantidade": 5
    }
  ],
  "status": "PENDENTE"
}
```

Cenários de erro:

 - Enviar um item que não existe no estoque
 - Enviar uma quantidade que não existe no estoque

### Estoque service

#### Remover itens

Vai remover os itens de acordo com a quantidade e o sku dos itens informados. Os itens só são removidos se nenhum dos itens contiver erros
Requisição:
```json
[
  {
    "skuId": "string",
    "quantidade": 0,
    "compraId": "string"
  }
]
```
Resposta:

```json
{
  "idRemocao": "string",
  "itemsRemovidos": [
    "string"
  ]
}
```

Cenários de erro:

 - Enviar um item que não existe no estoque
 - Enviar uma quantidade que não existe no estoque

 #### Listar itens
 Método get que lista os itens e as quantidades disponíveis existentes no estoque

 ```json
[
  {
    "skuId": "string",
    "quantidade": 0,
    "compraId": "string"
  }
]
```