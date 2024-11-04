Feature: This is a sample features test

  Scenario: client makes call to GET /tasks
    When the client calls GET Tasks
    Then the api returns with status code 200
