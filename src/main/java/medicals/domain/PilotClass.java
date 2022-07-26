package medicals.domain;

public enum PilotClass {
    FIRST_CLASS("1st class"),
    SECOND_CLASS("2nd class"),
    THIRD_CLASS("3rd class"),
    EXPIRED("Expired");

    private final String label;

    PilotClass(String label) {
        this.label = label;
    }

    public static PilotClass withLabel(String expectedLabel) {
        for(PilotClass pilotClass : values()) {
            if (pilotClass.label.equalsIgnoreCase(expectedLabel)) {
                return pilotClass;
            }
        }
        throw new IllegalArgumentException("Unknown pilot class " + expectedLabel);
    }
}
