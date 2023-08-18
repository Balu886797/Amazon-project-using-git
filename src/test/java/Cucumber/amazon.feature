
@tag
Feature: To verify the Functionalities Amazon Application

Background:
Given User will Lucch the Amazon application

@TC_001
  Scenario: To verify the access of Amazon application
    Then user will successfully lunch the Amazon application
    And user will close the application
    
@TC002  
   Scenario Outline: To verify the functionalities of Amazon apllication Sign In page with valid Credentials
    Given User logged in with Email <email> and password <pass> 
    When  User will Successfully Sign In  Amazon Application with valid credentials 
    And   user will SignOut from the application
    And user will close the application
    
     Examples: 
      | email               | pass      | product           |
      | bbbbk1508@gmail.com | bbbbk1508 | think like a monk | 
    
  @TC_003
  Scenario: To verify the functionalities of Amazon apllication Sign In page without entering Email
    When User will try to logged without entering Email
    Then user will get Alert message
    And user will close the application
    
@TC_004
    Scenario: To verify the functionalities of Amazon apllication Sign In page without entering password
    When User will try to logged without entering Password
    Then user will get password Alert message
    And user will close the application
    
@TC_005
    Scenario: To verify the functionalities of Forget password link in Sign In page 
    When User will try to logged with email and click on forget Password link
    Then user will on Password assistance page
    And user will close the application  
 
 @TC_006
    Scenario: To verify the functionalities of Add to cart button 
    Given User logged in with Email <email> and password <pass>
    When  user will search the Product <product>
    Then  User will add the product into the Cart
    And   user will succussfully add produt into the cart
    And   user will SignOut from the application
    And user will close the application 
     
    Examples: 
      | email               | pass      | product           |
      | bbbbk1508@gmail.com | bbbbk1508 | think like a monk |

@TC_007
    Scenario: To verify the functionality of Shopping cart button
    Given User logged in with Email <email> and password <pass>
    When  User will click on Shopping cart button
    Then  User will navigate Shopping cart page
    And user will close the application 
   
   Examples: 
      | email               | pass      | product           |
      | bbbbk1508@gmail.com | bbbbk1508 | think like a monk |
      
@TC_008
        Scenario: To verify the functionality of Shopping cart button
    Given User logged in with Email <email> and password <pass>
    When  User will click on Shopping cart button
    And  User will click on Proced to buy button
    Then User will Navigate Chuckout page
    And   user will SignOut from the application
    And user will close the application 
    
Examples: 
      | email               | pass      | product           |
      | bbbbk1508@gmail.com | bbbbk1508 | think like a monk |
      
@TC_009
     Scenario: To verify the functionalities of Chuckout page
     Given User logged in with Email <email> and password <pass>
    When  User will click on Shopping cart button
    And  User will click on Proced to buy button
    Then User will Navigate Chuckout page
    And user will select the address
    Then User will verify the Order Summary Details
    And user will close the application
    
     Examples: 
      | email               | pass      | product           |
      | bbbbk1508@gmail.com | bbbbk1508 | think like a monk |

    #@tag1
  #Scenario Outline: To verify the Functionalities Amazon Application
    #Given User logged in with Email <email> and password <pass>
    #When  user will search the Product <product>
    #And User will add the product into the Cart
    #And user will select the address
    #Then User will verify the Order Summary Details
    #And user will close the application
    #
     #Examples: 
      #| email               | pass      | product           |
      #| bbbbk1508@gmail.com | bbbbk1508 | think like a monk |
    