package fr.enseeiht.tderocke.account_manager;

public class DepenseAffichee {

    private int id;
    private String Nom;
    private String Date;
    private String frequency;
    private float value;

    @Override
    public String toString() {
        return "DepenseAffichee{" +
                "id=" + id +
                ", Nom='" + Nom + '\'' +
                ", Date='" + Date + '\'' +
                ", frequency='" + frequency + '\'' +
                ", value=" + value +
                '}';
    }

    public DepenseAffichee(int id, String nom, String date, String frequency, float value) {
        this.id = id;
        Nom = nom;
        Date = date;
        this.frequency = frequency;
        this.value = value;
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
