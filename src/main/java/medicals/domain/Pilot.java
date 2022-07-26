package medicals.domain;

import lombok.*;

import java.time.LocalDate;

@Data @Builder
public class Pilot {
    private final String pilotName;
    private PilotClass pilotClass;
    private long age;
    private LocalDate birthDate;

}
