Feature: posts deletion

  Background: 
    Given uri of rest api in "QA" environment
    And define HTTP request

  @smoketest
  Scenario: delete an existing post
    When submit request for id 100 via DELETE method
    Then status code is 204

  @realtest
  Scenario: delete posts
    When submit request via DELETE to service by taking data from text file
    Then validate response as per data in text file
