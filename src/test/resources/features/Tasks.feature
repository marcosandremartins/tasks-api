Feature: This is a sample features test

  Scenario: client makes call to GET /tasks
    When the client calls GET Tasks
    Then the api returns with status code 200

  Scenario: implementing a new POST Method
    Given the client makes a POST request with title "appointment" and description "just a new appointment"
    When the client makes a GET request
    Then the api returns with status code 200
