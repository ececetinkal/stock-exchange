# Stock Exchange
## An example Stock Market REST API Application 
## Integrated with Swagger, Securing REST API with JSON Web Token (JWT)


## Main Technology Stack
 * Spring Boot 
 * Spring Data JPA
 * Spring Security
 * JSON Web Token
 * H2 Database Engine
 * Swagger UI


## To run the application
You can execute this maven command: 'mvn spring-boot:run'. 

## To test the application

 ### Information below is needed for authentication 

 * client: testjwtclientid
 * secret: XY7kmzoNzl100
 * Standard user crendentials: ececetinkal | jwtpass
 * Admin user: admin | jwtpass
 * Services open to standard users: Stock Operations (Buy/Sell), Listing Stocks
 * Services open to admin users: All CRUD Operations of Users and Stocks.

 1. Generate an access token

   Use the following generic command to generate an access token:
   `$ curl client:secret@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=pwd`

   For this specific application, to generate an access token for the admin user 'admin', run:
   `$ curl testjwtclientid:XY7kmzoNzl100@localhost:8080/oauth/token -d grant_type=password -d username=admin -d password=jwtpass`
    You will receive a response similar to below

    `
    {
      "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoiYWRtaW4uYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNDk0NDU0MjgyLCJhdXRob3JpdGllcyI6WyJTVEFOREFSRF9VU0VSIiwiQURNSU5fVVNFUiJdLCJqdGkiOiIwYmQ4ZTQ1MC03ZjVjLTQ5ZjMtOTFmMC01Nzc1YjdiY2MwMGYiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQifQ.rvEAa4dIz8hT8uxzfjkEJKG982Ree5PdUW17KtFyeec",
      "token_type": "bearer",
      "expires_in": 43199,
      "scope": "read write",
      "jti": "0bd8e450-7f5c-49f3-91f0-5775b7bcc00f"
    }`

 2. Use the token to access resources through your RESTful API

    * Access content available to all authenticated users

        1. Use the generated token  as the value of the Bearer in the Authorization header as follows:
        `curl  http://localhost:8080/api/stocks -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoiZWNlY2V0aW5rYWwiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTQzMzQ2MDcwLCJhdXRob3JpdGllcyI6WyJTVEFOREFSRF9VU0VSIl0sImp0aSI6IjdmMDUwY2M2LWU3Y2UtNGIwMy05ZTNlLTg3ODFjOTY3YTNkYiIsImNsaWVudF9pZCI6InRlc3Rqd3RjbGllbnRpZCJ9.d8mGwVmYg3oarBFHEcXj4PluS3cjy8cR21bE657dxAs" `

        The response will be:
        `
        [
            {
                "code": "THY",
                "name": "THY Stock",
                "price": 0.06943922323891871
            },
            {
                "code": "ABC",
                "name": "Some description here",
                "price": 0.6629058060063366
            }
        ]
        `
        
        2. You can use Swagger UI:
        `http://localhost:8080/swagger-ui.html`

