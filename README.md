# amazion

How to run
--------------------
gradlew bootrun

Properties file
----------------
src\main\resources\application.yml

Tasks:

- [issue 1] init project
- [issue 2] add converter service
- [issue 3] add rest endpoints
- [issue 4] presist packages in memory
- [issue 5] UI

Examples:

Post
--------------
POST /package HTTP/1.1
Host: localhost:8080
Authorization: Basic dG9tOmFiYzEyMw==
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: a35aacaa-4148-4ca0-4b41-ba56569b10c5

{
	"id":"pckg1",
	"name":"pckg1",
	"description":"A package",
	"products": [ 
	 {  "id" : "VqKb4tyj9V6i",
		"name" : "Shield",
		"usdPrice" : 1149
	 }, 
	 {
		"id" : "DXSQpv6XVeJm",
		"name" : "Helmet",
		"usdPrice" : 999
	 }, 
	 {
		"id" : "7dgX6XzU3Wds",
		"name" : "Sword",
		"usdPrice" : 899
	 }
	]

}



Get
-----------
GET /package?currency=EUR HTTP/1.1
Host: localhost:8080
Authorization: Basic dG9tOmFiYzEyMw==
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: c68be2f1-637e-25cd-c4d3-f0cb1bb6b72f



Update
--------------------
PUT /package/pckg1 HTTP/1.1
Host: localhost:8080
Authorization: Basic dG9tOmFiYzEyMw==
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: bcf511e3-7cc3-af0a-456e-b4bb1c75c07a

{
	"id":"pckg1",
	"name":"pckg1",
	"description":"A package",
	"products": [ 
	 {  "id" : "VqKb4tyj9V6i",
		"name" : "Shield",
		"usdPrice" : 1149
	 }, 
	 {
		"id" : "DXSQpv6XVeJm",
		"name" : "Helmet",
		"usdPrice" : 99999
	 }, 
	 {
		"id" : "7dgX6XzU3Wds",
		"name" : "Sword",
		"usdPrice" : 899
	 }
	]

}


Delete
----------------
DELETE /package/pckg1 HTTP/1.1
Host: localhost:8080
Authorization: Basic dG9tOmFiYzEyMw==
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 18768587-f983-5a98-945a-870f9f673654


