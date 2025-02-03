# **ScreenMatch API - API de Filmes e Séries**  

## ✏️ **Introdução**  
A **ScreenMatch API** é uma API desenvolvida para filtrar dados sobre filmes e séries renomadas e fornecer esses dados traduzidos em português. Este projeto foi criado para fins de aprendizado e fixação de conceitos de **APIs REST**, **integração com APIs externas** e para praticar o uso de **Streams** e **funções Lambda** no **Java**, visando um código mais curto, legível e eficiente.

---

## 🛠️ **Tecnologias Usadas**
- Java Versão 17+
- Maven
- Spring Versão 3
- Flyway
- MySQL
- Documentação com Swagger API

## ⚙️ **Instalação e Configuração**  
**Esta aplicação necessita de uma JVM para funcionar.**
- Acesse esse link para baixar o JDK do java na versão 17: [Baixar JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

### **1️⃣ IDE Requerida**  
Para rodar a aplicação, recomenda-se utilizar a **IntelliJ IDEA** ou **Eclipse**:  
- **IntelliJ IDEA (Recomendado)**: [Baixar IntelliJ IDEA](https://www.jetbrains.com/idea/download/)  
- **Eclipse**: [Baixar Eclipse](https://www.eclipse.org/downloads/)  

### **2️⃣ Banco de Dados**  
A aplicação requer um banco de dados **MySQL**.  

#### **MySQL**  
- **Windows:** [Baixar MySQL](https://dev.mysql.com/downloads/installer/)  
- **Linux:** [Instalar MySQL no Linux](https://dev.mysql.com/doc/refman/8.0/en/linux-installation.html)  
- **Mac:** [Baixar MySQL para Mac](https://dev.mysql.com/downloads/mysql/)

#### Após seguir os passos de instalação do MySQL execute o seguinte script:
```plaintext
CREATE DATABASE screenmatch
````

### **3️⃣ Variáveis de ambiente**

Recomendado a utilização da mesma nomenclatura na criação das seguintes variáveis:

- **OMDB_APIKEY**: Chave da API externa de filmes e séries para as requisições [Link para obter a chave](https://www.omdbapi.com/apikey.aspx)
- **MYSQL_DB_HOST**: Variável que representa o host do banco de dados.
- **MYSQL_DB_USERNAME**: Variável com o nome do usuário do banco de dados.
- **MYSQL_DB_PASSWORD**: Variável com a senha do usuário do banco de dados.

#### **OMDB_APIKEY**
##### **Adicione sua chave OMDB nesses dois métodos da classe UrlConstrutor.class** 
- **path:** com.project.screenmatch.util
```plaintext
 public String construirUrl(String tituloPesquisado) {

            return ENDERECO_BASE + tituloPesquisado.replace(" ", "+").toLowerCase()
                    + API_KEY_ENDPOINT + System.getenv("SUA_OMDB_APIKEY_AQUI");
        }
````
```plaintext
public String construirUrl(String tituloPesquisado, Integer temporada) {

            return ENDERECO_BASE + tituloPesquisado.replace(" ", "+").toLowerCase()
                    + TEMPORADA_ENDPOINT + temporada +  API_KEY_ENDPOINT + System.getenv("SUA_OMDB_APIKEY_AQUI");
        } 
````


#### **Variáveis database:**
- **Path:** application.properties
```plaintext
spring.datasource.url=jdbc:mysql://${SEU_MYSQL_DB_HOST_AQUI}/screenmatch
spring.datasource.username=${SEU_MYSQL_DB_USERNAME_AQUI}
spring.datasource.password=${SUA_MYSQL_DB_PASSWORD_AQUI}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
hibernate.dialect=org.hibernate.dialect.MySQLDialect
````
### **4️⃣ Compile e execute a aplicação**
```plaintext
mvn spring-boot:run
````
#### A aplicação estará rodando em :(http://localhost:8080) 

##  🌐 **Endpoints Disponíveis** 
- Com a aplicação rodando acesse o endpoint da documentação do swagger: http://localhost:8080/swagger-ui.html

## 📂 **Arquitetura do Projeto**
### A arquitetura do projeto foi baseada no padrão MVC utilizando clean archtecture, visando o desacoplamento das regras de negócios dos demais componentes que envolvem a aplicação, tornando a aplicação escalável, de fácil manutensão e expansão.

- Estrutura dos pacotes:

```plaintext
/src
│── com.project.screenmatch
|   ├── config           # Possui as Configurações do projeto
|   ├── controllers      # Contém os endpoints da API
│   ├── dtos             # Contém os objetos de transferência de dados
│   ├── infra            # Contém classes relacionadas a infraestrutura e exceptions
│   ├── integration      # Contém classes de integração com serviços externos
│   ├── model            # Contém as entidades do banco de dados
│   ├── repositorys      # Contém as interfaces de acesso ao banco de dados
│   ├── service          # Contém as regras de negócio
│   └── util             # Contém classes auxiliares para formatação e validação
````




 


