# city_info_telegram_bot
This project is a Telegram Bot that gives information (some tourist facts) about entered city.
## Usage:
1. Change your credential for connection to data base in *application.properties* file.
2. Build project with maven to *jar* file.
3. Start MySQL8 data base (use start script *create_db.sql* that you can find in this project's properties)
3. Start jar file in your server with comand *"java -jar city_info_telegram_bot-0.0.1-SNAPSHOT.jar"*
## REST Controle
You can add new cities and fact about them in DB using REST API:

1. Get all cities in bot's DB
```HTTP
GET /cities
```
2. Get city by id

```HTTP
GET /cities/{id}
```
3. Add new city
```HTTP
POST /cities
{
  "name": "city_name"
}
```
4. Update city
```HTTP
PUT /cities/{id}
{
  "name": "new_city_name"
}
```
5. Delete city by id
```HTTP
DELETE /cities/{id}
```
____
Also yor can get, add, update, delete different facts about cities

1. Add new fact about city
```HTTP
POST /cities/{id}/facts
{
  "fact": "fact_text"
}
```
2. Get facts about city with id = {id}
```HTTP
GET /cities/{id}/facts
```
3. Get fact with id = {fact_id} about city with id = {city_id}
```HTTP
GET /cities/{city_id}/facts/{fact_id}
```
4. Update fact
```HTTP
PUT /cities/{city_id}/facts/{fact_id}
{
  "fact": "fact_text"
}
```
5. Delete fact
```HTTP
DELETE /citie/{city_id}/facts/{fact_id}
```
