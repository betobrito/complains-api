# language: en
Feature: Delete Complaint

    Background:
        Given that there is a registered complaint

    Scenario: 01 - delete for existing complaint
        Given that the complaint to be deleted has id "1"
        Then should return status code ok and no objects found with id "1"

    Scenario: 02 - delete for non existent complaint
        Given that the complaint to be deleted has id "2"
        Then should return an error with status not found
        And should return a error message "No complaints found with this id."
