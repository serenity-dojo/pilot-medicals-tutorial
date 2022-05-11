Feature: FAA pilot medical exams
  In order to stay compliant with air regulations and continue to fly legally
  As a pilot
  I want to know when my next medical is due

#  Acceptance Criteria:
#    - A First Class FAA medical certificate is valid for 6 months.
#    - A Second Class FAA medical certificate is valid for 1 year.
#    - A Third Class FAA medical certificate is valid for 2 years; or 5 years if you are under 40 years old at the time of the medical.
#    - All of the FAA validities are measured from the last day of the month.
#    - Registered pilots should be notified that their next medical is due 1 month in advance if they have not already booked it

  Rule: First class pilots need a medical exam every 12 months, or every 6 months if they are 40 or older
  For ATP (Airline Transport Pilots)
  A Class 1 medical certificate is valid for 12 months, unless you are 40, in which case it is 6 months.

    Scenario Outline: First class medical dates
      Given Frank is a 1st class pilot
      And Frank is <age> years old
      When Frank's last medical was <last medical>
      Then his next medical should be <next medical>
      Examples:
        | age | last medical | next medical | rule        |
        | 35  | 8 months ago | in 4 months  | Under 40    |
        | 40  | 4 months ago | in 2 months  | 40 or older |
        | 45  | 2 months ago | in 4 months  | 40 or older |

  Rule: All of the FAA validities are measured from the last day of the month

    Scenario Outline: Medical certificate validity for first class pilots
      Given Frank is a 1st class pilot
      And Frank is <age> years old
      When Frank's last medical was on the <date of last medical>
      Then his next medical should be no later than <date limit>
      Examples: Under 40 years
        | age | date of last medical | date limit       | notes                                       |
        | 35  | April 15 2023        | April 30 2024    | License is valid until the end of the month |
        | 25  | February 28 2023     | February 29 2024 | Leap Year                                   |
      Examples: 40 or over
        | age | date of last medical | date limit        | notes |
        | 45  | March 15 2023        | September 30 2023 |       |
        | 45  | September 20 2023    | March 31 2024     |       |



#  Rule: Second class pilots need a medical every 12 months regardless of age
#  For commercial pilots and ATC operators
#    Scenario Outline: Second class medical dates
#      Given Serena is a 2nd class pilot
#      And Serena is <age> years old
#      When Serena's last medical was <last medical>
#      Then her next medical should be <next medical>
#      Examples:
#        | age | last medical | next medical | rule        |
#        | 35  | 8 months ago | in 4 months  | Under 40    |
#        | 45  | 2 months ago | in 10 months  | 40 or older |
#
#  Rule: Third class pilots need a medical every 5 years if under 40, and every 2 years if they are 40 or older
#  For private, recreational, student and sports pilots, and for flight instructors
#    Scenario Outline: Third class medical dates
#      Given Theo is a 3rd class pilot
#      And Theo is <age> years old
#      When Theo's last medical was <last medical>
#      Then his next medical should be <next medical>
#      Examples:
#        | age | last medical | next medical | rule        |
#        | 35  | 8 months ago | in 4 months  | Under 40    |
#        | 45  | 2 months ago | in 10 months  | 40 or older |
#
#
#  Rule: If the medical date is not respected the pilot loses their First Class status
#
#    Scenario Outline: A pilot loses first class status when they miss their medical
#      The date of the next medical is based on the requirements of the pilot's new class
#
#      Given Frank is a 1st class pilot
#      And Frank is <age> years old
#      When Frank's last medical was <last medical>
#      Then he should now be a <new class> pilot
#      And his next medical should be <next medical>
#
#      Examples:
#        | age | last medical  | new class | next medical |
#        | 35  | 18 months ago | 3rd class | in 42 months |
#        | 40  | 7 months ago  | 2nd class | in 5 months  |
#        | 45  | 9 months ago  | 2nd class | in 9 months  |
