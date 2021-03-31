package fr.enseeiht.tderocke.account_manager;

public class Depense {

    public static final String UNE_FOIS = "UNE_FOIS";
    public static final String JOURNALIER = "JOURNALIER";
    public static final String HEBDOMADAIRE = "HEBDOMADAIRE";
    public static final String MENSUEL = "MENSUEL";
    public static final String ANNUEL = "ANNUEL";

    private int id;
    private String Nom;
    private String Type;
    private String Date;
    private String frequency;
    private float value;


    public Depense(int id, String nom, String type, String date, String frequency, float value) {
        this.id = id;
        Nom = nom;
        Type = type;
        Date = date;
        this.frequency = frequency;
        this.value = value;
    }

    public Depense() {
    }

    @Override
    public String toString() {
        return "Depense{" +
                "id=" + id +
                ", Nom='" + Nom + '\'' +
                ", Type='" + Type + '\'' +
                ", Date=" + Date + '\'' +
                ", Frequency=" + frequency +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
