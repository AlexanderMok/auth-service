# auth-service
Java authentication and authorization service using Spring Security Jwt Oauth2.

This implementation supports password and refresh token ***Grant types*** according to Oauth2.

### Version
- Spring boot 2.1.3.RELEASE

- Java 1.8

- MySQL to store user info

### Configuration

- Related configurations are in application.yml
- Tables used are in db_dev.sql

### User
```
username: user
password: 123456
```
Password is encrypted by BCryptPasswordEncoder in Database.


### Client Id and Client Secret
```
client_id: app-client
client_secret: client-secret

client_id: trusted
client_secret: client-secret
```
client_secret is encrypted by BCryptPasswordEncoder in Database.

### Test

- set up basic authorization using ***Client Id and Client Secret***
```
encode 'app-client:client-secret' by Base64
get 'YXBwLWNsaWVudDpjbGllbnQtc2VjcmV0'

encode 'trusted:client-secret' by Base64
get 'dHJ1c3RlZDpjbGllbnQtc2VjcmV0'
```

#### request auth server

Use rest client or curl.

Set http headers first
```
Authorization: Basic dHJ1c3RlZDpjbGllbnQtc2VjcmV0
Content-Type: application/x-www-form-urlencoded
```
Set http body. Use form if you can or just plain text like the following.
```
username=user&password=123456&grant_type=password
```
Send post request to server endpoint `/oauth/token`

```
curl -X POST -H 'Content-Type: application/x-www-form-urlencoded' -H 'Authorization: Basic dHJ1c3RlZDpjbGllbnQtc2VjcmV0' 
-i http://localhost:9092/oauth/token 
--data 'username=user&password=123456&grant_type=password'
```

And you will get the following json if it successes.
```
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTA0ODAwMjcsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJVU0VSX0NSRUFURSIsIlVTRVJfVVBEQVRFIiwiVVNFUl9SRUFEIiwiVVNFUl9ERUxFVEUiXSwianRpIjoiYmEwYjFhMGUtMmE4NC00NGU1LWFiZGUtNzAyZWFiODRkNGUzIiwiY2xpZW50X2lkIjoidHJ1c3RlZCIsInNjb3BlIjpbIkFVVEgiXX0.9Ejy8qy1QYLO5QXC_xEY0eKtWzSNWiy0VZnsMKcQ2Hs",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsiQVVUSCJdLCJhdGkiOiJiYTBiMWEwZS0yYTg0LTQ0ZTUtYWJkZS03MDJlYWI4NGQ0ZTMiLCJleHAiOjE1NTA0ODcxMDcsImF1dGhvcml0aWVzIjpbIlVTRVJfQ1JFQVRFIiwiVVNFUl9VUERBVEUiLCJVU0VSX1JFQUQiLCJVU0VSX0RFTEVURSJdLCJqdGkiOiIzNWVhOWZiNy05NjAxLTRkN2YtOTA4Yi1iNWYwM2U1NjYwNWQiLCJjbGllbnRfaWQiOiJ0cnVzdGVkIn0.cDjH7M0gDcAGAJ5ApRzQYI1xt4koL5F_acjfaPN3hVE",
    "expires_in": 117,
    "scope": "AUTH",
    "jti": "ba0b1a0e-2a84-44e5-abde-702eab84d4e3"
}
```

#### Refresh token

Set up http headers Content-type and Authorization like previous step.

Set up http body with parameters `grant_type` and the `refresh_token` received previously.
```
grant_type=refresh_token&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsiQVVUSCJdLCJhdGkiOiJiYTBiMWEwZS0yYTg0LTQ0ZTUtYWJkZS03MDJlYWI4NGQ0ZTMiLCJleHAiOjE1NTA0ODcxMDcsImF1dGhvcml0aWVzIjpbIlVTRVJfQ1JFQVRFIiwiVVNFUl9VUERBVEUiLCJVU0VSX1JFQUQiLCJVU0VSX0RFTEVURSJdLCJqdGkiOiIzNWVhOWZiNy05NjAxLTRkN2YtOTA4Yi1iNWYwM2U1NjYwNWQiLCJjbGllbnRfaWQiOiJ0cnVzdGVkIn0.cDjH7M0gDcAGAJ5ApRzQYI1xt4koL5F_acjfaPN3hVE
```

Send post request to server endpoint `/oauth/token`
```
curl -X POST -H 'Content-Type: application/x-www-form-urlencoded' -H 'Authorization: Basic dHJ1c3RlZDpjbGllbnQtc2VjcmV0' -i http://localhost:9092/oauth/token --data 'grant_type=refresh_token&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsiQVVUSCJdLCJhdGkiOiJiYTBiMWEwZS0yYTg0LTQ0ZTUtYWJkZS03MDJlYWI4NGQ0ZTMiLCJleHAiOjE1NTA0ODcxMDcsImF1dGhvcml0aWVzIjpbIlVTRVJfQ1JFQVRFIiwiVVNFUl9VUERBVEUiLCJVU0VSX1JFQUQiLCJVU0VSX0RFTEVURSJdLCJqdGkiOiIzNWVhOWZiNy05NjAxLTRkN2YtOTA4Yi1iNWYwM2U1NjYwNWQiLCJjbGllbnRfaWQiOiJ0cnVzdGVkIn0.cDjH7M0gDcAGAJ5ApRzQYI1xt4koL5F_acjfaPN3hVE'
```

And you will get a new access_token and refresh_token.

#### check token

Check token is to decode access_token.

Set up http headers Content-type and Authorization like previous step.

Set up http body with parameter `token`

Send post request to server endpoint `/oauth/check_token`

```
curl -X POST -H 'Content-Type: application/x-www-form-urlencoded' -H 'Authorization: Basic dHJ1c3RlZDpjbGllbnQtc2VjcmV0' -i http://localhost:9092/oauth/check_token --data 'token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTA0ODI3MjMsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJVU0VSX0NSRUFURSIsIlVTRVJfVVBEQVRFIiwiVVNFUl9SRUFEIiwiVVNFUl9ERUxFVEUiXSwianRpIjoiZmM5YWE1ODMtMDg2NC00ZDkwLWEzODMtMWMxNTExNzA0NWUwIiwiY2xpZW50X2lkIjoidHJ1c3RlZCIsInNjb3BlIjpbIkFVVEgiXX0.wyWDFUensSh6ps0ho4jwP814UekyM1Y6s1ewXDCBnEE'
```

And you will get result like the following.
```
{
  "user_name": "user",
  "scope": ["AUTH"],
  "active": true,
  "exp": 1550482723,
  "authorities": ["USER_CREATE", "USER_UPDATE", "USER_READ", "USER_DELETE"],
  "jti": "fc9aa583-0864-4d90-a383-1c15117045e0",
  "client_id": "trusted"
}
```