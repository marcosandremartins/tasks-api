Feature: This is a sample features test

  Scenario: client makes call to GET /tasks
    When the client calls GET Tasks
    Then the api returns with status code 200

  Scenario: Successfully create a new appointment
    
    Given I have a valid payload to create an appointment
    When I send a POST request to "/appointments"
    Then the response status code should be 201
    And the response should contain the appointment details

