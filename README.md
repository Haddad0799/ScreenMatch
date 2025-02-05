# 🎬 **ScreenMatch API - API de Filmes e Séries**  

## ✏️ **Introdução**  
A **ScreenMatch API** é uma API desenvolvida para filtrar dados sobre filmes e séries renomadas e fornecer esses dados traduzidos em português. Este projeto foi criado para fins de aprendizado e fixação de conceitos de **APIs REST**, **integração com APIs externas** e para praticar o uso de **Streams** e **funções Lambda** no **Java**, visando um código mais curto, legível e eficiente.

---

## 🛠️ **Tecnologias Usadas**
- Java Versão 17+
- Maven
- Spring Boot 3
- Spring Data JPA
- MySQL
- Documentação com Swagger API

## ⚙️ **Instalação e Configuração**  
### **Pré-requisitos**  
A aplicação requer a **JVM** para funcionar corretamente. 

1. **Baixe e instale o JDK 17:**  
   - [Baixar JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  

2. **Clone o Repositório:**  
   Execute o comando abaixo no terminal:  

   ```sh
   git clone https://github.com/Haddad0799/screenmatch.git
   ```  

### **1️⃣ Escolha uma IDE**  
Para rodar a aplicação, recomenda-se utilizar a **IntelliJ IDEA** ou **Eclipse**:  
- **IntelliJ IDEA (Recomendado):** [Baixar IntelliJ IDEA](https://www.jetbrains.com/idea/download/)  
- **Eclipse:** [Baixar Eclipse](https://www.eclipse.org/downloads/)  

### **2️⃣ Configuração do Banco de Dados**  
A aplicação requer um banco de dados **MySQL**.  

#### **Instale o MySQL conforme seu sistema operacional:**  
- **Windows:** [Baixar MySQL](https://dev.mysql.com/downloads/installer/)  
- **Linux:** [Instalar MySQL no Linux](https://dev.mysql.com/doc/refman/8.0/en/linux-installation.html)  
- **Mac:** [Baixar MySQL para Mac](https://dev.mysql.com/downloads/mysql/)  

#### **Criação do Banco de Dados**  
Após a instalação do MySQL, execute o seguinte comando no terminal ou no cliente SQL:  

```sql
CREATE DATABASE screenmatch;
```

### **3️⃣ Configuração das Variáveis de Ambiente**  
Recomenda-se utilizar a mesma nomenclatura para as seguintes variáveis de ambiente:

- **OMDB_APIKEY:** Chave da API externa OMDB para buscar dados de filmes e séries.  
  - [Obtenha sua chave aqui](https://www.omdbapi.com/apikey.aspx)  
- **MYSQL_DB_HOST:** Endereço do host do banco de dados.  
- **MYSQL_DB_USERNAME:** Usuário do banco de dados.  
- **MYSQL_DB_PASSWORD:** Senha do banco de dados.  
- **OPENAI_APIKEY:** Chave da API do ChatGPT para obter traduções.  
  - [Obtenha sua chave aqui](https://gipiti.chat/get-chatgpt-api-key)  

#### **Configuração da Chave OMDB**  
Adicione sua chave OMDB nos métodos da classe `UrlConstrutor.class`:  

- **Path:** `com.project.screenmatch.util`

```java
public String construirUrl(String tituloPesquisado) {
    return ENDERECO_BASE + tituloPesquisado.replace(" ", "+").toLowerCase()
            + API_KEY_ENDPOINT + System.getenv("OMDB_APIKEY");
}
```

```java
public String construirUrl(String tituloPesquisado, Integer temporada) {
    return ENDERECO_BASE + tituloPesquisado.replace(" ", "+").toLowerCase()
            + TEMPORADA_ENDPOINT + temporada + API_KEY_ENDPOINT + System.getenv("OMDB_APIKEY");
}
```

#### **Configuração da Chave OpenAI**  
Adicione sua chave OpenAI no método `obterTraducao` da classe `TradutorChatGptService.class`:

- **Path:** `com.project.screenmatch.service`

```java
public static String obterTraducao(String texto) {
    OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));

    CompletionRequest requisicao = CompletionRequest.builder()
            .model("gpt-4")
            .prompt("Please translate the following text into Portuguese: \n\n" + texto)
            .maxTokens(1000)
            .temperature(0.5)
            .build();

    var resposta = service.createCompletion(requisicao);
    if (resposta.getChoices() != null && !resposta.getChoices().isEmpty()) {
        return resposta.getChoices().get(0).getText().trim();
    } else {
        return "Falha na tradução!";
    }
}
```

🔹 **Observação:** A versão gratuita da API OpenAI possui um limite de requisições. Caso deseje utilizar a funcionalidade de tradução, descomente o método no código:  
```java
//TradutorChatGptService.obterTraducao(filmeOmdb.getSinopse());
```

#### **Configuração do Banco de Dados**  
Adicione as seguintes variáveis no `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://${MYSQL_DB_HOST}/screenmatch
spring.datasource.username=${MYSQL_DB_USERNAME}
spring.datasource.password=${MYSQL_DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### **4️⃣ Compilar e Executar a Aplicação**  

Execute o seguinte comando no terminal dentro do diretório do projeto:

```sh
mvn spring-boot:run
```

A aplicação estará rodando em: [http://localhost:8080](http://localhost:8080)

## 🌐 **Endpoints Disponíveis**  
Acesse a documentação Swagger da API para explorar os endpoints disponíveis:  

📌 **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🔥 **Diferenciais do projeto** 
- Arquitetura MVC e SOLID: A API segue o padrão de arquitetura MVC e aplica os princípios SOLID para garantir um código desacoplado, organizado e de fácil manutenção.
- Tratamento de Erros Robusto: Implementação de exceptions personalizadas para fornecer mensagens claras e detalhadas sobre os erros, garantindo melhor experiência ao usuário e facilitando a depuração.
- Uso de Streams e Funções Lambda: Código otimizado e mais legível com a utilização de Java Streams e expressões Lambda para manipulação de coleções e processamento de dados.
- Banco de Dados Relacional: Persistência utilizando MySQL e Spring Data JPA, com mapeamento eficiente das entidades e consultas otimizadas.
- Documentação Completa: API documentada com Swagger/OpenAPI, facilitando testes e integração com outras aplicações.
- Integração com API Externa: Caso um título não seja encontrado no banco de dados, a API busca automaticamente na OMDb API e armazena os dados localmente, reduzindo chamadas externas futuras e otimizando a performance.


 


