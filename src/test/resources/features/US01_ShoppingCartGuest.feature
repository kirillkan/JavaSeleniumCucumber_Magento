@us01 @regression
Feature: User adds products to the cart and checks out

  @ui
  Scenario Outline: User adds product with various quantities to the cart and performs checkout
    Given user is on magento shop's landing page
    And user searches for "<category>"
    And user opens product page
    And user sets "<quantity>" and clicks add to cart
    And user clicks shopping cart link
    And user clicks proceed to checkout in shopping cart page
    And user enters valid user information and selects "<shipping method>"

    Then user performs checkout successfully and able to see "Thank you for your purchase!"
    Then user can see proper value in order subtotal
    Then quantity chosen on product page matches with shipping page
    Then quantity chosen on product page matches with shopping cart page
    Then title is "Success Page"

    Examples:
      | category | quantity | shipping method |

      | tees     | 1        | Flat Rate       |
      | tees     | 3        | Best Way        |

      | shirt    | 1        | Best Way        |
      | shirt    | 5        | Flat Rate       |

      | hoodie   | 1        | Flat Rate       |
      | hoodie   | 15       | Best Way        |




