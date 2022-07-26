package medicals.stepdefinitions;

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
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class MedicalsStepDefinitions {

    Pilot pilot;
    MedicalsService medicalsService = new MedicalsService();

    @ParameterType(".*")
    public PilotClass pilotClass(String label) {
        return PilotClass.withLabel(label);
    }

    @Given("{} is a {pilotClass} pilot born on {readableDate}")
    public void pilot_a_1st_class_pilot(String pilotName, PilotClass pilotClass, LocalDate dateOfBirth) {
        pilot = Pilot.builder().pilotName(pilotName).pilotClass(pilotClass).birthDate(dateOfBirth).build();
    }

    @Given("{} is {int} years old")
    public void pilot_is_years_old(String pilotName, int pilotAge) {
        pilot.setAge(pilotAge);
    }

    @Given("{} was born on {readableDate}")
    public void pilot_was_born_on(String pilotName, LocalDate birthDate) {
        pilot.setBirthDate(birthDate);
    }

    @When("{}'s last medical was {int} months ago")
    public void last_medical_was_months_ago(String pilotName, Integer numberOfMonths) {
        LocalDate dateOfLastMedical = LocalDate.now().minusMonths(numberOfMonths);
        medicalsService.recordDateOfMedical(pilot, dateOfLastMedical);
    }

    @When("today is the {readableDate}")
    public void todayIs(LocalDate today) {
        medicalsService.checkLicenseValidity(pilot, today);
    }

    @Then("he/she should now be a {pilotClass} pilot")
    public void heShouldNowBeANewClassPilot(PilotClass newClass) {
        assertThat(pilot.getPilotClass()).isEqualTo(newClass);
    }

    private final static DateTimeFormatter MONTH_DAY_YEAR_FORMAT = DateTimeFormatter.ofPattern("MMMM d yyyy");

    @ParameterType(".*")
    public LocalDate readableDate(String value) {
        LocalDate monthDay = LocalDate.parse(value, MONTH_DAY_YEAR_FORMAT);
        return monthDay;
    }

    @When("his/her last medical was on the {readableDate}")
    public void last_medical_was_months_ago(LocalDate dateOfLastMedical) {
        medicalsService.recordDateOfMedical(pilot, dateOfLastMedical);
    }

    @Then("his/her next medical should be no later than {readableDate}")
    public void next_medical_should_be_on_the(LocalDate expectedNextMedical) {
        LocalDate nextMedical = medicalsService.findDateLimitForNextMedical(pilot);
        assertThat(nextMedical).isEqualTo(expectedNextMedical);
    }

}
