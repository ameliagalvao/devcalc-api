# DevCalc

## Descrição

O **DevCalc** é uma aplicação REST desenvolvida em Java, que disponibiliza operações matemáticas básicas (adição, subtração, multiplicação e divisão).  
Ela foi criada como exercício da disciplina de Pipelines de CI/CD e DevOps para ajudar no aprendizado de pipelines de CI/CD com o **GitHub Actions**, incluindo:

1. Verificação de qualidade de código (lint, formatação, análise estática)
2. Execução de testes automatizados
3. Build e empacotamento da aplicação
4. Simulação de deploy

## Tecnologias e Ferramentas

- **Linguagem:** Java (mínimo JDK 11)
- **Framework Web:** Javalin
- **Build Tool:** Maven
- **Controle de Versão:** Git + GitHub
- **CI/CD:** GitHub Actions

## Build Tool

O projeto utiliza **Maven** para:

- Gerenciar dependências
- Compilar o código
- Executar testes
- Empacotar a aplicação em um JAR executável

### Configuração principal

- Arquivo de build: `pom.xml`
- Plugins principais:
    - `maven-compiler-plugin` (compilação Java)
    - `maven-surefire-plugin` (execução de testes)
    - `maven-jar-plugin` (empacotamento do JAR)

## Como Executar Localmente

1. **Clonar o repositório**
   ```bash
   git clone https://github.com/ameliagalvao/devcalc-api.git
   cd devcalc-api
   ```

2. **Build & Empacotar**
   ```bash
   mvn clean package
   ```

3. **Executar a aplicação**
    - Via Maven:
      ```bash
      mvn exec:java -Dexec.mainClass="com.devcalc-api.DevCalcApp"
      ```
    - Ou com o JAR gerado:
      ```bash
      java -jar target/devcalc-api-1.0.0.jar
      ```

4. **Acessar a API**
    - Base URL: `http://localhost:7000`
    - Exemplos de chamadas (usando `curl`):
      ```bash
      # Adição
      curl "http://localhost:7000/calc/add?a=3&b=5"
      
      # Subtração
      curl "http://localhost:7000/calc/subtract?a=10&b=4"
      ```

## Endpoints da API

| Método | Rota                     | Descrição                    | Parâmetros             |
| ------ | ------------------------ | ---------------------------- | ---------------------- |
| GET    | `/calc/add`              | Soma dois números            | `a`, `b` (query params)|
| GET    | `/calc/subtract`         | Subtrai `b` de `a`           | `a`, `b`               |
| GET    | `/calc/multiply`         | Multiplica dois números      | `a`, `b`               |
| GET    | `/calc/divide`           | Divide `a` por `b`           | `a`, `b`               |

## Testes

- Os testes unitários estão em `src/test/java`.
- Para executá-los:
  ```bash
  mvn test
  ```

## Workflows do CI/CD

Todos os workflows do GitHub Actions estão no diretório:
```
.github/workflows/
```