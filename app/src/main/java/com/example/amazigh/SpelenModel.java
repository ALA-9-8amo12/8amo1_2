package com.example.amazigh;

public class SpelenModel {
    String Foto_bestandsnaam;
    String Geluids_bestandsnaam;
    String AMAZIGH;
    String NL;

    public SpelenModel(){}

    public SpelenModel(String Foto_bestandsnaam, String Geluids_bestandsnaam, String NL, String AMAZIGH){
        this.Foto_bestandsnaam = Foto_bestandsnaam;
        this.Geluids_bestandsnaam = Geluids_bestandsnaam;
        this.AMAZIGH = AMAZIGH;
        this.NL = NL;
    }

    public String getFoto_bestandsnaam(){return Foto_bestandsnaam;}
    public String getGeluids_bestandsnaam(){return Geluids_bestandsnaam;}
    public String getAMAZIGH(){return AMAZIGH;}
    public String getNL(){return NL;}

    public void setFoto_bestandsnaam(String Foto_bestandsnaam) { this.Foto_bestandsnaam = Foto_bestandsnaam; }
    public void setGeluids_bestandsnaam(String Geluids_bestandsnaam) { this.Geluids_bestandsnaam = Geluids_bestandsnaam; }
    public void setAMAZIGH(String AMAZIGH) { this.AMAZIGH = AMAZIGH; }
    public void setNL(String NL) { this.NL = NL; }

}
