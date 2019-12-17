# language: en
Feature: Edit Complaint

    Background:
        Given that there is a registered complaint

    Scenario: 01 - edit complaint
        Given Since you should edited a complaint with the following information: id "1" title "Parking", locale "Maceió", company "Maceió Shopping"
        Then should return a complaint with title "Parking", locale "Maceió", company "Maceió Shopping"

    Scenario: 02 - complaint edited without title
        Given Since you should edited a complaint with the following information: id "1" title "", locale "Arapiraca", company "Shopping"
        Then should return an error with status bad request
        And should return a error message list "must not be blank"

    Scenario: 03 - complaint edited without locale
        Given Since you should edited a complaint with the following information: id "1" title "Parking", locale "", company "Shopping"
        Then should return an error with status bad request
        And should return a error message list "must not be blank"

    Scenario: 04 - complaint edited without company
        Given Since you should edited a complaint with the following information: id "1" title "Parking", locale "Arapiraca", company ""
        Then should return an error with status bad request
        And should return a error message list "must not be blank"
