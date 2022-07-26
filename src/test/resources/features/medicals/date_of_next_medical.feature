Feature: Date of next medical
  In order to stay compliant with air regulations and continue to fly legally
  As a pilot
  I want to know when my next medical is due

  Rule: First class pilots need a medical exam every 12 months, or every 6 months if they are 40 or older
  For commercial airline pilots

    Scenario Outline: First class medical dates
      Given Frank is a 1st class pilot born on <Birth Date>
      When his last medical was on the <Last Medical>
      Then his next medical should be no later than <Next Medical>
      Examples:
        | Birth Date      | Last Medical    | Next Medical    | Comments          |
        | January 01 2000 | January 20 2020 | January 31 2021 | Frank is under 40 |
        | January 15 1970 | January 20 2020 | July 31 2020    | Frank is over 40  |

  Rule: Second class pilots need a medical every 12 months regardless of age
  For other commercial pilots
    Scenario Outline: Second class medical dates
      Given Serena is a 2nd class pilot born on <Birth Date>
      When her last medical was on the <Last Medical>
      Then her next medical should be no later than <Next Medical>
      Examples:
        | Birth Date      | Last Medical    | Next Medical    | Comments           |
        | January 15 2000 | January 20 2020 | January 31 2021 | Serena is under 40 |
        | January 01 1970 | January 20 2020 | January 31 2021 | Serena is over 40  |

  Rule: Third class pilots need a medical every 5 years if under 40, and every 2 years if they are 40 or older
  For private, recreational, student and sports pilots, and for flight instructors
    Scenario Outline: Third class medical dates
      Given Theo is a 3rd class pilot born on <Birth Date>
      When his last medical was on the <Last Medical>
      Then his next medical should be no later than <Next Medical>
      Examples:
        | Birth Date      | Last Medical    | Next Medical    | Comments           |
        | January 15 2000 | January 20 2020 | January 31 2025 | Theo is under 40 |
        | January 01 1970 | January 20 2020 | January 31 2022 | Theo is over 40  |
