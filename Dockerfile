# # Use uma imagem Ubuntu como base
# FROM ubuntu

# # Instale as dependências necessárias para o Java e o projeto Spring
# RUN apt-get update && \
#     apt-get install -y default-jdk && \
#     apt-get clean

# # Copie o código-fonte do projeto para o contêiner
# COPY . /src

# # Defina um diretório de trabalho dentro do contêiner
# WORKDIR /src

# # Comando de entrada para executar o projeto Spring Boot (ajuste conforme seu projeto)
# CMD ["./mvnw", "spring-boot:run"]
