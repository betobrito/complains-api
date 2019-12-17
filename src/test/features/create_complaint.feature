# language: en
Feature: Create Complaint

    Scenario: 01 - create complaint
        Given Since you should register a complaint with the following information: title "Defective Parking", locale "Maceió", company "Shopping"
        Then should return a complaint with title "Defective Parking", locale "Maceió", company "Shopping"

    Scenario: 02 - untitled complaint created
        Given Since you should register a complaint with the following information: title "", locale "Maceió", company "Shopping"
        Then should return an error with status bad request
        And should return a error message list "must not be blank"

    Scenario: 03 - complaint created without locale
        Given Since you should register a complaint with the following information: title "Defective Parking", locale "", company "Shopping"
        Then should return an error with status bad request
        And should return a error message list "must not be blank"

    Scenario: 04 - complaint created without company
        Given Since you should register a complaint with the following information: title "Defective Parking", locale "Maceió", company ""
        Then should return an error with status bad request
        And should return a error message list "must not be blank"
