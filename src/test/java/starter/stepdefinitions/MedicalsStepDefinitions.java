package starter.stepdefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import medicals.domain.MedicalsService;
import medicals.domain.Pilot;
import medicals.domain.PilotClass;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MedicalsStepDefinitions {

    Pilot pilot;
    MedicalsService medicalsService = new MedicalsService();

    @ParameterType(".*")
    public PilotClass pilotClass(String label) {
        return PilotClass.withLabel(label);
    }

    @Given("{} is a {pilotClass} pilot")
    public void pilot_a_1st_class_pilot(String pilotName, PilotClass pilotClass) {
        pilot = Pilot.builder().pilotName(pilotName).pilotClass(pilotClass).build();
    }

    @Given("{} is {int} years old")
    public void pilot_is_years_old(String pilotName, int pilotAge) {
        pilot.setAge(pilotAge);
    }

    @When("{}'s last medical was {int} months ago")
    public void last_medical_was_months_ago(String pilotName, Integer numberOfMonths) {
        LocalDate dateOfLastMedical = LocalDate.now().minusMonths(numberOfMonths);
        medicalsService.recordDateOfMedical(pilot, dateOfLastMedical);
    }

    /*
            | 35  | April 15             | April 30         | License is valid until the end of the month |
        | 25  | February 28 2023     | February 29 2024 | Leap Year                                   |
     */

    private final static DateTimeFormatter MONTH_DAY_YEAR_FORMAT = DateTimeFormatter.ofPattern("MMMM d yyyy");

    @ParameterType(".*")
    public LocalDate dateOfMedical(String value) {
        LocalDate monthDay = LocalDate.parse(value, MONTH_DAY_YEAR_FORMAT);
        return monthDay;
    }

    @When("{}'s last medical was on the {dateOfMedical}")
    public void last_medical_was_months_ago(String pilotName, LocalDate dateOfLastMedical) {
        medicalsService.recordDateOfMedical(pilot, dateOfLastMedical);
    }

    @Then("his/her next medical should be in {int} months")
    public void next_medical_should_be_in_months(Integer numberOfMonthsTillNextMedical) {
        LocalDate nextMedical = medicalsService.findDateLimitForNextMedical(pilot);
        LocalDate expectedNextMedical = LocalDate.now().plusMonths(numberOfMonthsTillNextMedical);
        Assertions.assertThat(nextMedical.getMonth()).isEqualTo(expectedNextMedical.getMonth());
    }

    @Then("his next medical should be no later than {dateOfMedical}")
    public void next_medical_should_be_on_the(LocalDate expectedNextMedical) {
        LocalDate nextMedical = medicalsService.findDateLimitForNextMedical(pilot);
        Assertions.assertThat(nextMedical).isEqualTo(expectedNextMedical);
    }

    @Then("he should now be a {pilotClass} pilot")
    public void heShouldNowBeANewClassPilot(PilotClass newClass) {
    }
}
