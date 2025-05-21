# Diferença entre Workflows e Actions

No GitHub Actions, **Workflows** e **Actions** são componentes complementares, mas com responsabilidades distintas:

- **Workflow** é responsável por orquestrar um conjunto de jobs, os quais são executados em resposta a eventos (push, pull_request, schedule, etc.). Ele é definido num arquivo YAML no diretório `.github/workflows/`, como, por exemplo, `ci.yml`.
- **Action** é um conjunto de instruções reutilizáveis, as quais são responsáveis por executar uma tarefa específica, seja como configurar o ambiente, linters, testes ou deploy. Ela pode ser hospedada no GitHub Marketplace ou no próprio repositório (`.github/actions/`).

## Estrutura interna de uma Action

1. **Código-fonte**: scripts (JavaScript, TypeScript), contêineres Docker ou ações compostas que unem várias chamadas de outras actions.
2. **Arquivo `action.yml`**: metadados que definem:
    - **`inputs`**: parâmetros de entrada que o usuário pode fornecer.
    - **`outputs`**: valores que a action pode retornar para o workflow.
    - **`runs`**: como a action é executada (usando Docker, Node.js ou como uma action composta).

## Chamando uma Action no Workflow

No workflow (`.github/workflows/ci.yml`), usamos o campo **`uses`** para invocar uma action, indicando:

- **Repositório** e **versão**: `<owner>/<repo>@<tag|sha>`
- **`with`**: bloco onde passamos os valores das entradas definidas em `action.yml`.

### Exemplo real: GitHub Super Linter

No projeto DevCalc, configuramos o job de lint assim:

```yaml
jobs:
  lint:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v3
      - name: Setup Java 11
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '11'
      - name: Lint Java code with Super Linter
        uses: github/super-linter@v4
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        with:
          DEFAULT_BRANCH: main
          VALIDATE_JAVA: true
```

1. **`uses: github/super-linter@v4`**: aponta para a action hospedada no GitHub Marketplace.
2. **`with`**:
    - `DEFAULT_BRANCH: main` → sobrescreve o valor padrão definido em `action.yml`.
    - `VALIDATE_JAVA: true` → habilita especificamente o lint para Java.
3. **`env`**: variáveis de ambiente (como `GITHUB_TOKEN`) necessárias para autenticação interna.

Dessa forma, o workflow injeta parâmetros na action, que lê essas variáveis conforme definido no seu `action.yml`, executa o linting dentro de um contêiner Docker e retorna o resultado para o pipeline.

---
