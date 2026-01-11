package model;

public record DivisionResult(String bonesTo, String meatTo, String skinTo, String headTo) {
    @Override
    public String toString() {
        return "РезультатРаздела{" +
                "кости='" + bonesTo + '\'' +
                ", мясо='" + meatTo + '\'' +
                ", кожа='" + skinTo + '\'' +
                ", голова='" + headTo + '\'' +
                '}';
    }
}