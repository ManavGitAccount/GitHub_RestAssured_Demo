Feature: Validating GitHub API

  @AddRepo
Scenario Outline: Verify if user can add a repo using GitHub API
  Given Add repo payload with "<name>"
  When User calls "Add Repo API" with "POST" Http request
  Then the API call is success with the status code 201
  And "visibility" in the response body is "public"


  Examples:
    | name |
    | Caro Can Chess Repo|


  @PatchRepo
    Scenario Outline: Verify is user can rename the repo using GitHub API

    Given Patch Payload with "<name>"
    When User calls "Patch Repo API" with "PATCH" Http request
    Then the API call is success with the status code 200
    Examples:
      | name |
      | Caroooo     |



  @DeleteRepo
  Scenario: Verify if user can delete the repo using GitHub API

  Given Delete Repo Payload
  When User calls "Delete Repo API" with "DELETE" Http request
  Then the API call is success with the status code 204
