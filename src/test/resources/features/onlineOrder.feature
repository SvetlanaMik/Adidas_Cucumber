Feature: Online Order
  Agile story:SHOP: https://www.demoblaze.com/index.html
  Customer navigation through product categories: Phones, Laptops and Monitors
  Navigate to "Laptop" → "Sony vaio i5" and click on "Add to cart". Accept pop up confirmation.
  Navigate to "Laptop" → "Dell i7 8gb" and click on "Add to cart". Accept pop up confirmation.
  Navigate to "Cart" → Delete "Dell i7 8gb" from cart.
  Click on "Place order".
  Fill in all web form fields.
  Click on "Purchase"
  Capture and log purchase Id and Amount.
  Assert purchase amount equals expected.
  Click on "Ok"

  @wip
  Scenario: Customer navigation through product categories: Phones, Laptops and Monitors
    Given User on the home page
    When User see the Categories options
    Then User should be able to click on each Option

  @wip @w
  Scenario Outline: Add products to the cart
    Given User on the home page
    When User navigates to Laptop page
    And add "<item>" to the cart
    And User accepts alert
    When User navigates to Cart
    Then User should see "<item>" in the cart

    Examples:
      |   item     |
      |Sony vaio i5|
      |Dell i7 8gb |

  @wip @w
  Scenario Outline: Delete products from the cart
    Given User navigates to Cart
    And delete "<item>" from the cart
    Then User should see that "<item>" is deleted from shopping cart
    Examples:
    |    item   |
    |Dell i7 8gb|

  @wip @w
  Scenario: Place order and verify price
    Given User navigates to Cart
    When cart is not empty
    And User clicks on Place Order
    When User fills in all place order form fields
    And clicks on Purchase
    Then Purchase amount must be equal expected

