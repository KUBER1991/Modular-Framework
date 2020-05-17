Feature: Facebook Login Functionality

Background: 
Given initialise the chrome browser

Scenario Outline: Login Into facebook
When User enters "<userID>" and "<Password>"
And clicks on login button
Then User see the homepage  
Examples:
|userID|PAssword|



#API scenario
Scenario Outline: Get API
Given I hve endpoint BAse URI "baseAPI"
And Required url path for "<requestID>" "Endoint","<dyanmicvar>"
When user request with "<requestID>" and "Header"
And submits "Get" request for "<APIType>"
Then verify response status code
Examples:
|requestID|dyanmicvar|APIType|

Scenario Outline: Get post
Given I hve endpoint BAse URI "baseAPI"
And Required url path for "<requestID>" "Endoint","<dyanmicvar>"
When user request with "<requestID>" and "Header"
And submits "Get" request for "<APIType>"
Then verify response status code
Examples:
|requestID|dyanmicvar|APIType|

