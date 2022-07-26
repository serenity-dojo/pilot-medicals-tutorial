package medicals.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class MedicalsService {

    private final Map<String, LocalDate> lastMedical = new HashMap<>();
    private final Map<String, LocalDate> extendedMedical = new HashMap<>();

    public void recordDateOfMedical(Pilot pilot, LocalDate dateOfLastMedical) {
        lastMedical.put(pilot.getPilotName(), dateOfLastMedical);
    }
    public LocalDate dateOfMedicalFor(Pilot pilot) {
        return lastMedical.get(pilot.getPilotName());
    }

    public void recordDateOfExtendedMedical(Pilot pilot, LocalDate dateOfLastMedical) {
        extendedMedical.put(pilot.getPilotName(), dateOfLastMedical);
    }

    public LocalDate findDateLimitForNextMedical(Pilot pilot) {
        LocalDate dateOfLastMedical = dateOfMedicalFor(pilot);

        long pilotAge = ChronoUnit.YEARS.between( pilot.getBirthDate() , dateOfLastMedical );

        LocalDate anniveraryOfLastMedical;
        switch (pilot.getPilotClass()) {
            case FIRST_CLASS:
                anniveraryOfLastMedical = (pilotAge < 40) ? dateOfLastMedical.plusMonths(12) : dateOfLastMedical.plusMonths(6);
                break;
            case SECOND_CLASS:
                anniveraryOfLastMedical = dateOfLastMedical.plusMonths(12);
                break;
            case THIRD_CLASS:
                anniveraryOfLastMedical = (pilotAge < 40) ? dateOfLastMedical.plusMonths(60) : dateOfLastMedical.plusMonths(24);
                break;
            default:
                throw new IllegalArgumentException("Unsupported pilot class " + pilot.getPilotClass());
        }
        LocalDate roundedDueDate = anniveraryOfLastMedical.withDayOfMonth(anniveraryOfLastMedical.lengthOfMonth());

        if (extendedDueDateFor(pilot) != null && extendedDueDateFor(pilot).isAfter(roundedDueDate)) {
            return extendedDueDateFor(pilot);
        } else {
            return roundedDueDate;
        }

    }

    private LocalDate extendedDueDateFor(Pilot pilot) {
        return extendedMedical.get(pilot.getPilotName());
    }

    public void checkLicenseValidity(Pilot pilot, LocalDate currentDate) {
        LocalDate dueDate = findDateLimitForNextMedical(pilot);
        long pilotAge = ChronoUnit.YEARS.between( pilot.getBirthDate(),currentDate);

        if (currentDate.isAfter(dueDate)) {
            switch (pilot.getPilotClass()) {
                case FIRST_CLASS:
                    if (pilotAge < 40) {
                        pilot.setPilotClass(PilotClass.THIRD_CLASS);
                        recordDateOfExtendedMedical(pilot, dueDate.plusMonths(48));
                    } else {
                        pilot.setPilotClass(PilotClass.SECOND_CLASS);
                        recordDateOfExtendedMedical(pilot, dueDate.plusMonths(6));
                    }
                    break;
                case SECOND_CLASS:
                    if (pilotAge < 40) {
                        pilot.setPilotClass(PilotClass.THIRD_CLASS);
                        recordDateOfExtendedMedical(pilot, dueDate.plusMonths(48));
                    } else {
                        pilot.setPilotClass(PilotClass.THIRD_CLASS);
                        recordDateOfExtendedMedical(pilot, dueDate.plusMonths(12));
                    }
                    break;
                case THIRD_CLASS:
                    pilot.setPilotClass(PilotClass.EXPIRED);
                    break;
            }
        }
    }
}
