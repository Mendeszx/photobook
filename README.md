# Photobook

Este é o README para o projeto Photobook, um aplicativo web desenvolvido com Java (Spring Boot) no backend e Angular no frontend.

## Visão Geral

O projeto Photobook é uma aplicação web desenvolvida para demonstrar um sistema de feed de publicações de fotos e textos, com autenticação e autorização com Spring Security e JWT no backend, juntamente com um frontend Angular para interação com o usuário. Ele permite aos usuários se autenticarem, visualizarem um feed de notícias e interagirem com o sistema de forma segura.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Flyway
- JWT
- Spring Security
- Spring Cache
- Maven
- Angular
- Docker

## Funcionalidades Principais

- Autenticação de usuários com Spring Security
- Criptografia de senhas no banco de dados
- Geração e validação de tokens JWT para autorização de acesso aos endpoints
- Armazenamento de publicações e álbuns de fotos
- Comentários em publicações e álbuns de fotos
- Visualização de um feed de notícias

- ## Banco de Dados

O banco de dados utilizado neste projeto é o PostgreSQL. Ele é configurado para ser executado automaticamente usando o Docker Compose e é inicializado com um volume para armazenar os dados. Isso garante que os dados persistam entre as execuções do contêiner. As tabelas são automaticamentes criadas pelo Flyway

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:

- Postman
- Docker

## Executando o Projeto

1. Clone o repositório para o seu ambiente local:

```bash
git clone https://github.com/Mendeszx/photobook
```

2. Navegue até o diretório do projeto:

```bash
cd photobook
```

3. Execute o Docker Compose para iniciar o backend e o frontend:

```bash
docker-compose up
```

- Isso iniciará o backend (Spring Boot) em [http://localhost:8080](http://localhost:8080)
- e o frontend (Angular) em [http://localhost:4200](http://localhost:4200) (Em Desenvolvimento)

## Estrutura do Projeto

- **backend-photobook**: Contém o código fonte do backend desenvolvido em Java (Spring Boot) e Maven e o Dockerfile.
- **frontend-photobook**: Contém o código fonte do frontend desenvolvido em Angular e o Dockerfile.
- **docker-compose.yml**: Arquivo de configuração do Docker Compose para iniciar os contêineres do backend e frontend.

### Endpoints

#### Álbuns de Fotos

- **Criar Novo Álbum**
  ```
  POST http://localhost:8080/api/v1/album/novo-album
  Content-Type: multipart/form-data
  ```

- **Listar Álbuns do Usuário**
  ```
  GET http://localhost:8080/api/v1/album/listar-albuns/{{usuarioId}}
  Content-Type: application/json
  ```

#### Autenticação e Cadastro de Usuário

- **Login**
  ```
  POST http://localhost:8080/api/v1/auth/login
  Content-Type: application/json
  ```

- **Cadastro de Usuário**
  ```
  POST http://localhost:8080/api/v1/cadastro/usuario
  Content-Type: application/json
  ```

#### Comentários

- **Adicionar Comentário**
  ```
  POST http://localhost:8080/api/v1/comentario/novo-comentario
  Content-Type: application/json
  ```

- **Listar Comentários**
  ```
  GET http://localhost:8080/api/v1/comentario/listar-comentarios
  Content-Type: application/json
  ```

- **Deletar Comentário**
  ```
  DELETE http://localhost:8080/api/v1/comentario/deletar-comentario
  Content-Type: application/json
  ```

#### Saúde da Aplicação

- **Verificar Saúde da Aplicação**
  ```
  GET http://localhost:8080/api/v1/health/check
  Content-Type: application/json
  ```

#### Publicações

- **Criar Nova Publicação**
  ```
  POST http://localhost:8080/api/v1/publicacao/nova-publicacao
  Content-Type: multipart/form-data
  ```

- **Listar Publicações**
  ```
  GET http://localhost:8080/api/v1/publicacao/listar-publicacoes
  Content-Type: application/json
  ```

- **Buscar Publicação por ID**
  ```
  GET http://localhost:8080/api/v1/publicacao/{{publicacaoId}}
  Content-Type: application/json
  ```

- **Deletar Publicação**
  ```
  DELETE http://localhost:8080/api/v1/publicacao/deletar-publicacao
  Content-Type: application/json
  ```
