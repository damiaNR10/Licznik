package com.example.damia.licznik;

import java.io.Serializable;
import java.util.List;

public class SaveValues implements Serializable{
    SaveValues(String nazwa){
        this.nazwa = nazwa;
    }

    public String brutto;
    public String finalna;
    public String nazwa;
    public String netto;
    public String vat;
    public String dochodowy;

    public String getNetto() {
        return netto;
    }

    public void setNetto(String netto) {
        this.netto = netto;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getDochodowy() {
        return dochodowy;
    }

    public void setDochodowy(String dochodowy) {
        this.dochodowy = dochodowy;
    }

    public String getBrutto() {
        return brutto;
    }

    public void setBrutto(String brutto) {
        this.brutto = brutto;
    }

    public String getFinalna() {
        return finalna;
    }

    public void setFinalna(String finalna) {
        this.finalna = finalna;
    }



    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }


}
