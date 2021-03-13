package fr.enseeiht.tderocke.account_manager;

public class Depense {

    public static final int UNE_FOIS = 200;
    public static final int JOURNALIER = 201;
    public static final int HEBDOMADAIRE = 202;
    public static final int MENSUEL = 203;
    public static final int ANNUEL = 204;

    private int id;
    private String Nom;
    private String Type;
    private int Date;
    private int frequency;
    private float value;

    public Depense(int id, String nom, String type, int date, int frequency, float value) {
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

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
