package taxi.domain;

public enum Role {

    ADMINISTRATOR ("ADMINISTRATOR"), OPERATOR ("OPERATOR");

    private String abbreviation;

    private Role (String abbreviation){
        this.abbreviation = abbreviation;
    }
    public String getAbbreviation(){
        return abbreviation;
    }
    @Override
    public String toString() {
        return abbreviation;
    }
}
