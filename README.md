# INNSO TEST TECHNIQUE BACKEND

## Swagger
Une documentation via swagger est disponible à cette addresse : http://localhost:8080/innso/swagger-ui.html

## Base de données
Une base H2 est incorporée au projet. La console H2 est disponible à cette adresse : http://localhost:8080/innso/h2-console

## Utilisation
Appeler le service de création de message  
POST /api/messages, avec le JSON suivant dans le corps de la requête :  
`{
  "channel": "MAIL",
  "content": "Bonjour, j'ai un problème avec mon nouveau téléphone",
  "name": "Jérémie Durand"
}` 

 Appeler ensuite le service de création de dossier client, en y associant le premier message créé  
 POST /api/customers, avec le JSON suivant dans le corps de la requête :  
` {
 	"name": "Jérémie Durand",
   "idMessages": [
     1
   ]
 }`

Pour créer un nouveau message, lié au dossier client créé juste avant, il faut appeler le service  
POST /api/customers/{idCustomer}/messages, avec idCustomer = l'id du dossier client créé avant et en body de la requête le JSON  
`{
  "channel": "MAIL",
  "content": "Je suis Sonia, et je vais mettre tout en œuvre pour vous aider. Quel est le modèle de votre téléphone ?",
  "name": "Sonia Valentin"
}`  

Pour modifier le dossier client, appeler le service de modification via  
PUT /api/customers, avec le JSON suivant dans le corps de la requête :  
`{
  "idCustomer": 1,
  "name": "Jérémie Durand",
  "reference": "KA-18B6"
}`  

Pour récupérer les dossiers de la base de données, appeler le service  
GET /api/customers
