# stock-api-web


# QuickStart
Esse projeto ilustra a a simulação de um estoque inteligente utilizando JDK8 com Spring Boot e Maven.


# Começando

Para executar o projeto, será necessário instalar os seguintes programas:

JDK 8 OU Superior
Maven 3.6 ou superior

# Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretorio de sua preferencia"
git clone https://github.com/santannaf/stock-api-web.git
```

# Construção

Para construir o projeto com o Maven, executar os comando abaixo:

```shell
mvn clean install
```

# Deploy e Publicação

O deploy do projeto está configurado com o CI/CD de pipeline do heroku, automáticamente quando for realizado um push da branch master, o build de produção será inicializado.
Sua publicão também é de forma automática atraves do pipeline do heroku.

# Features

O projeto do estoque inteligente ele realiza um monitoramento do estoque que é automáticamente gerado no momento da compilação/execução do código.
No projeto você pode inserir um novo produto e simular um carregamento de estoque inicial. Os números de consumo do estoque são ficticios e usados para a implementação do projeto. 

O carregamento inicial do estoque foi pensado no número de estados brasileiros com o distrito federal, portanto no momento de execução, automaticamente o sistema faz um carregamento de 27 polos.

O projeto tambem conta com uma atualização de estoque e em tempo real aplica a classificação do estoque para avaliar se as quantidades de produtos são suficientes ou nao de acordo com a regra de negócio estipulada para cada estoque.

# Configuracão

Para configuração voce pode utilizar qualquer IDE de sua preferencia, porém indico o Inteliji da JetBrains, para que o mesmo já identifique de forma automática todas as dependências do projeto que estão sendo utilizadas e instale-as.

# Teste

Para rodar os testes, utilize o comando abaixo:

```
mvn test
```

# Links

O projeto pode ser encontrado aqui:
https://stock-web-challenge.web.app/home

E o link do backend hospedado no heroku é:
https://challenge-api-web.herokuapp.com

# Licença

Não se aplica
