# Hole

## Ferramentas:
- :pushpin: Java 11
- :pushpin: Maven
- :pushpin: MariaDB 10.7
## Proposta
API com o objetivo de registrar buracos nas avenidas e estradas das cidades


## :cd: Iniciar
[***Link para API no Heroku***](hole-register.herokuapp.com/)

Na linha de comando:
Use o ```mvnw``` para ambiente Linux e ```mvnw.cmd``` para Windows
```
./mvnw spring-boot:run
```

## Usuário:
- login: admin@example.com
- senha: H0L3_3sYR@Dª
- tipo: ADMIN


## Observação

- Para acessar recursos da API é necessário logar no sistema e inserir o token com o prefixo ***Bearer*** no header do HTTP:

```
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaXNzIjoiUHJvZ3JhbWFTVEFSVEVSIiwiZXhwIjoxNjM5Njc4MTc3fQ.x7EPUxp7tZWNiZYbilmZC73hDe551uPatXgvgkLSI54
```

Exemplo com o ***cURL***:
```
curl --location --request GET 'https://hole-register.herokuapp.com/v1/cidades' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaXNzIjoiUHJvZ3JhbWFTVEFSVEVSIiwiZXhwIjoxNjM5Njc2NjQwfQ.mSySanqS48_RIzBq1quDdE77MX2Iaz7cGxuCPCmKQr4'
```