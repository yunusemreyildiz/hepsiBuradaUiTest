Feature: Basket Test

  Scenario: Add an item to your basket, go to basket and check the item

    Given Navigate to website
    When Check home page is loaded
    When Get cursor to the selected category
    And Choose Apple option from filter menu
    When Click on tablet size
    And Click on highest price element
    And Get product's price and click add to cart button
    And Click on go to the basket button and get price
    Then Compare prices and check the basket

