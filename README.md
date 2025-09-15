
# Sistema de Gestão Inteligente de Estoque Dasa (GIED)

## 📖 Sobre o Projeto

O **GIED** é uma solução de software desktop desenvolvida como parte do Challenge de Domain Driven Design da FIAP. O projeto visa resolver o desafio da baixa visibilidade e da falta de precisão no apontamento de consumo de materiais em unidades de diagnóstico da Dasa.

Através de uma interface gráfica intuitiva, o sistema substitui processos manuais e demorados, permitindo que os colaboradores registrem entradas e saídas de estoque em tempo real. Isso garante uma visão precisa do inventário, reduzindo discrepâncias, evitando a falta de materiais essenciais e otimizando a eficiência operacional.

## ✨ Funcionalidades Principais

  * **Controle de Acesso Seguro**: Autenticação de usuários por login e senha, com criptografia BCrypt.
  * **Níveis de Permissão**: Diferenciação entre usuários `ADMIN` (com acesso total) e `DEFAULT` (com acesso operacional).
  * **Gerenciamento de Usuários**: Administradores podem criar, consultar e remover contas de usuário.
  * **Movimentação de Estoque**:
      * **Registro de Entrada**: Cadastra novos lotes de produtos com quantidade e data de validade.
      * **Registro de Saída**: Realiza a baixa de itens do estoque, seguindo a lógica de consumir primeiro os lotes com validade mais próxima (FIFO).
  * **Consulta de Estoque**:
      * Visualização completa de todos os itens.
      * Alerta para itens com estoque abaixo do nível mínimo definido.
      * Busca detalhada de todos os lotes de um item específico.
  * **Relatórios**: Geração de um histórico completo de todas as movimentações de entrada e saída registradas no sistema.
  * **Validações de Negócio**: O sistema impede o registro de entradas com datas de validade retroativas e saídas de estoque maiores que a quantidade disponível.

## 🛠️ Tecnologias Utilizadas

  * **Linguagem**: Java 21
  * **Interface Gráfica**: Java Swing
  * **Banco de Dados**: Oracle Database
  * **Gerenciador de Dependências**: Apache Maven
  * **Testes**: JUnit 5 e Mockito
  * **Segurança**: Spring Security Crypto (para hashing de senhas)

## ⚙️ Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em seu ambiente:

  * [JDK 21](https://www.google.com/search?q=https://www.oracle.com/java/technologies/downloads/%23jdk21-windows) ou superior
  * [Apache Maven](https://maven.apache.org/download.cgi)
  * Um servidor de Banco de Dados Oracle com o schema do projeto já criado.

## 🚀 Configuração e Execução

1.  **Clone o repositório:**

    ```bash
    git clone https://[URL-DO-SEU-REPOSITORIO]/SistemaGIED.git
    cd SistemaGIED
    ```

2.  **Configure a Conexão com o Banco de Dados:**

      * Na pasta `src/main/resources/`, crie um arquivo chamado `application.properties`.
      * Adicione as seguintes linhas ao arquivo, substituindo os valores pelos dados da sua conexão Oracle:
        ```properties
        db.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
        db.user=seu_usuario
        db.password=sua_senha
        ```

3.  **Compile o projeto com o Maven:**

    ```bash
    mvn clean install
    ```

4.  **Execute a Aplicação:**
    A forma mais fácil de executar é através da sua IDE (IntelliJ, Eclipse, etc.), localizando a classe `Application.java` e executando o método `main`.

## ✅ Executando os Testes

Para rodar os testes unitários e garantir que a lógica de negócio está funcionando corretamente, execute o seguinte comando na raiz do projeto:

```bash
mvn test
```

## 📂 Estrutura do Projeto

O projeto segue uma arquitetura em camadas para separar as responsabilidades:

 ```

SistemaGIED/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/dasa/gied/
│   │   │       ├── config/
│   │   │       ├── dao/
│   │   │       │   └── jdbc/
│   │   │       ├── domain/
│   │   │       │   ├── enums/
│   │   │       │   └── model/
│   │   │       ├── service/
│   │   │       │   └── dto/
│   │   │       └── view/
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/dasa/gied/
│               └── service/
├── .gitignore
└── pom.xml

```


## 👥 Autores

Este projeto foi desenvolvido por:

  * Giulia Rocha Barbizan Alves
  * Felipe Marques de Oliveira
  * Gustavo Viega Martins Lopes
  * Kaio Drago Lima Souza
  * Gabriel Barros Cisoto
