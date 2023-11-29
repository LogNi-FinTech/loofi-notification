/# LooFi-notification-service

Every tech company requires a notification component. LooFi Notification Service is a simple and robust generic notification system designed for any tech company that needs to send notifications (email, SMS, push) to its customers.


Technology: Java 17, Spring Boot 3.1.5, Postgres

# Components:
- Processor (process request to real notification body)
- Common (repositories, entities and common code)
- Dispatcher (consume message from MQ and send corresponding channel)
- MFA (OTP management service)

# Features
- Notification template management
- parse template to real notification from request variables
- send it to message queue 
- consume and send thru corresponding channel
- MFA (OTP) management

# Example API:
Create Template:\
Path: http://localhost:8070/api/v1/template \
Method: POST\
Request Body:
```
{
"templateName": "ACCOUNT_BALANCE",
"description": "User Balance",
"notificationChannel": "EMAIL",
"subject": "Account Balance",
"locale": "en",
"template": "Hello ${userName},<br/> Your account balance is ${balance}. <br/> Thanks ",
"isHtml": true
}
```

Send Email Notification:\
Path: http://localhost:8070/api/v1/send \
Method: POST\
Request Body:
```
{
"templateName":"ACCOUNT_BALANCE",
"notificationChannel":"EMAIL",
"locale":"en",
"to":"loofitest001@gmail.com",
"variables":{
  "userName":"LooFi Test Account",
  "balance":"5000.00"
  }
}
```


# Notification Type:
- Email
- SMS
- Push Notification


# Contribution guideline
- clone the repo
- create a branch
- create pull request

Contribution Notes:
- Priority notification management
- Dispatcher service (consume notification from MQ and send corresponding channel[e.g Email])
- MFA service (OTP/MFA management) and MFA authenticator 
- Improve code and add any feature
- Cache notification template


# Contact:
- LinkedIn :
    - https://www.linkedin.com/company/90690030
- Email: lognifintech@gmail.com



