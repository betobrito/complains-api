# language: en
Feature: Find Specific Complaint

    Background:
        Given that there is a registered complaint

    Scenario: 01 - search for existing complaint
        Given that the complaint entered has id "1"
        Then should return a complaint title "Teste" and locale "Maceió"

    Scenario: 02 - search for non existent complaint
        Given that the complaint entered has id "2"
        Then should return an error with status not found
        And should return a error message "No complaints found with this id."
