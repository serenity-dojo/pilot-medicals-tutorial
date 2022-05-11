package medicals.domain;

import lombok.*;

@Data @Builder
public class Pilot {
    private final String pilotName;
    private PilotClass pilotClass;
    private int age;

}
