package christmas.constants;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000);

    private final String name;
    private final int requirement;

    Badge(String name, int requirement) {
        this.name = name;
        this.requirement = requirement;
    }

    public String getName() {
        return this.name;
    }

    public int getRequirement() {
        return this.requirement;
    }
}

