Feature: posts updation

  Background: 
    Given uri of rest api in "QA" environment
    And define HTTP request

  @smoketest
  Scenario: update an existing post
    When submit request for id 100 via PUT method
      | data           |
      | hi, h r u?, 10 |
    Then status code is 200 and content type is "application/json"

  @realtest
  Scenario: update posts
    When submit request via PUT to service by taking data from excel file
    Then validate response as per data in excel file
