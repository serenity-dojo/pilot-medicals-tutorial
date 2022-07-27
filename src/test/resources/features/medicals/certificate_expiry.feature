Feature: Certificate expiry
  In order to encourage pilots to renew their medical examinations on time in a fair manner
  As an airline regulator
  I want to pilots to be downgraded to the next lowest status if they don't renew their medicals

  Rule: If the medical date is not respected the pilot license may expire or be downgraded

    Scenario Outline: When medical certificates expire
      Given Frank is a <Class> pilot born on <Birth Date>
      When his last medical was on the <Last Medical>
      And today is the <Current Date>
      Then he should now be a <New Class> pilot
      And his next medical should be no later than <Next Medical>
      Examples: Expiry rules for first class pilots
        | Class     | Birth Date      | Last Medical    | Current Date  | New Class | Next Medical    | Comments          |
        | 1st class | January 01 2000 | January 20 2020 | March 1 2021  | 3rd class | January 31 2025 | Frank is under 40 |
        | 1st class | January 01 1970 | January 20 2020 | August 1 2021 | 2nd class | January 31 2021 | Frank is over 40  |

      Examples: Expiry rules for second class pilots
        | Class     | Birth Date      | Last Medical    | Current Date    | New Class | Next Medical    | Comments           |
        | 2nd class | January 01 2000 | January 20 2020 | February 1 2021 | 3rd class | January 31 2025 | Frank is under 40 |
        | 2nd class | January 01 1970 | January 20 2020 | February 1 2021 | 3rd class | January 31 2022 | Frank is over 40  |

    Scenario Outline: When a 3rd class pilot misses their medical their license expires
      Given Theo is a 3rd class pilot born on <Birth Date>
      When his last medical was on the <Last Medical>
      And today is the <Current Date>
      Then his license should now be <New Class>
      Examples:
        | Birth Date      | Last Medical    | Current Date    | New Class | Comments           |
        | January 01 2000 | January 20 2016 | February 1 2021 | Expired   |Theo is under 40 |
        | January 01 1970 | January 20 2019 | February 1 2021 | Expired   |Theo is over 40 |

