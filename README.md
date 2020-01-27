# Cast Spring Project
Projeto utilizando Spring para comparar dados criptografados em base 64 com três endpoints.


## Como utilizar  
Pode-se utilizar o Postman com os seguintes endpoints e json: 

- Para comparar Left e Right

http://localhost:8080/v1/diff/1

{
  "data": "ZXNxdWVyZGE="
}

- Para salvar em Left

http://localhost:8080/v1/diff/1/left

{
  "data": "ZXNxdWVyZGE="
}

- Para salvar em Right

http://localhost:8080/v1/diff/1/Right

{
  "data": "ZXNxdWVyZGE="
}
