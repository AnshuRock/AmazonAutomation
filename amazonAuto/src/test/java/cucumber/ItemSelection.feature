@tag
Feature: Searched Item selected in the cart and validate total Amount
  

  @dev_Windows @aut_Anshu
  Scenario: Validate the number of cart items
    Given I landed on amazon site
    When searched the samung phone in all category
    And selected some price range and clicked on go button
    Then added the available items to the cart 
    And validated the total number of items in the cart
  
 Scenario: Validate the total amount of cart items
 
    Given I landed on amazon site
    When searched the samung phone in all category
    And selected some price range and clicked on go button
    Then added the available items to the cart 
    And validated the total payable amount

    Scenario: Extract the phone text from the cart page
 
    Given I landed on amazon site
    When searched the samung phone in all category
    And selected some price range and clicked on go button
    Then added the available items to the cart 
    And extract the all phone texts