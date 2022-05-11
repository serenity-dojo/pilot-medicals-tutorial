package medicals.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MedicalsService {

    private Map<Pilot, LocalDate> lastMedical = new HashMap<>();

    public void recordDateOfMedical(Pilot pilot, LocalDate dateOfLastMedical) {
        lastMedical.put(pilot, dateOfLastMedical);
    }

    public LocalDate findDateLimitForNextMedical(Pilot pilot) {
        LocalDate dateOfLastMedical = lastMedical.get(pilot);

        LocalDate anniveraryOfLastMedical;
        if (pilot.getAge() < 40) {
            anniveraryOfLastMedical = dateOfLastMedical.plusMonths(12);
        } else {
            anniveraryOfLastMedical = dateOfLastMedical.plusMonths(6);
        }
        return anniveraryOfLastMedical.withDayOfMonth(anniveraryOfLastMedical.lengthOfMonth());
    }
}
