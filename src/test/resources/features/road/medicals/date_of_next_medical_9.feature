@Road
Feature: Road Medicals Date of next medical 9
  In order to stay compliant with air regulations and continue to fly legally
  As a pilot
  I want to know when my next medical is due

  Rule: The date of the next examination depends on the class of license and the age of the pilot

    Scenario Outline: Road Travel Examination date rules

      Given Frank is a <Class> pilot born on <Birth Date>
      When his last medical was on the <Last Medical>
      Then his next medical should be no later than <Next Medical>

      Examples: Road Travel First class pilots
      First class pilot examinations are every 6 months for 40 or over, and every 12 months for under 40s
        | Class     | Birth Date      | Last Medical    | Next Medical    | Comments          |
        | 1st class | January 01 2000 | January 20 2020 | January 31 2021 | Frank is under 40 |
        | 1st class | January 15 1970 | January 20 2020 | July 31 2020    | Frank is over 40  |

      Examples: Road Travel Second class pilots
      Second class pilots need a medical every 12 months regardless of age
        | Class     | Birth Date      | Last Medical    | Next Medical    | Comments           |
        | 2nd class | January 15 2000 | January 20 2020 | January 31 2021 | Serena is under 40 |
        | 2nd class | January 01 1970 | January 20 2020 | January 31 2021 | Serena is over 40  |

      Examples: Road Travel Third class pilots
      Third class pilots need a medical every 5 years if under 40, and every 2 years if they are 40 or older
        | Class     | Birth Date      | Last Medical    | Next Medical    | Comments         |
        | 3rd class | January 15 2000 | January 20 2020 | January 31 2025 | Theo is under 40 |
        | 3rd class | January 01 1970 | January 20 2020 | January 31 2022 | Theo is over 40  |
