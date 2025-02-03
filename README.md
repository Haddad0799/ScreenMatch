# **ScreenMatch API - API de Filmes e Séries**  

## ✏️ **Introdução**  
A **ScreenMatch API** é uma API desenvolvida para filtrar dados sobre filmes e séries renomadas e fornecer esses dados traduzidos em português. Este projeto foi criado para fins de aprendizado e fixação de conceitos de **APIs REST**, **integração com APIs externas** e para praticar o uso de **Streams** e **funções Lambda** no **Java**, visando um código mais curto, legível e eficiente.  

---

## ⚙️ **Instalação e Configuração**  
Esta aplicação utiliza um **banco de dados relacional**. Abaixo estão as instruções para configuração:  

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

### Exemplos de Variáveis de Ambiente:

```plaintext
OMDB_APIKEY=sua_api_key_aqui
MYSQL_DB_HOST=seu_db_host_aqui
MYSQL_DB_USERNAME=seu_db_username_aqui
MYSQL_DB_PASSWORD=seu_db_password_aqui
````

#### **OMDB_APIKEY**
- **path:** com.project.screenmatch.util
```plaintext
 public String construirUrl(String tituloPesquisado) {

            return ENDERECO_BASE + tituloPesquisado.replace(" ", "+").toLowerCase()
                    + API_KEY_ENDPOINT + System.getenv("SUA_APIKEY_OMDB_AQUI");
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

 


