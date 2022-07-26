Feature: Certificate expiry
  In order to encourage pilots to renew their medical examinations on time in a fair manner
  As an airline regulator
  I want to pilots to be downgraded to the next lowest status if they don't renew their medicals

  Rule: If the medical date is not respected the pilot loses their First Class status

    Scenario Outline: First class medical expiry
      Given Frank is a 1st class pilot born on  <Birth Date>
      When his last medical was on the <Last Medical>
      And today is the <Current Date>
      Then he should now be a <New Class> pilot
      And his next medical should be no later than <Next Medical>
      Examples:
        | Birth Date      | Last Medical    | Current Date  | New Class | Next Medical    | Comments          |
        | January 01 2000 | January 20 2020 | March 1 2021  | 3rd class | January 31 2025 | Frank is under 40 |
        | January 01 1970 | January 20 2020 | August 1 2021 | 2nd class | January 31 2021 | Frank is over 40  |

    Scenario Outline: Second class medical expiry
      Given Serena is a 2nd class pilot born on <Birth Date>
      When her last medical was on the <Last Medical>
      And today is the <Current Date>
      Then she should now be a <New Class> pilot
      And her next medical should be no later than <Next Medical>
      Examples:
        | Birth Date      | Last Medical    | Current Date    | New Class | Next Medical    | Comments           |
        | January 01 2000 | January 20 2020 | February 1 2021 | 3rd class | January 31 2025 | Serena is under 40 |
        | January 01 1970 | January 20 2020 | February 1 2021 | 3rd class | January 31 2022 | Serena is over 40  |
