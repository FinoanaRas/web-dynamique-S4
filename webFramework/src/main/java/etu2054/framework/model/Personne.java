package etu2054.framework.model;

import etu2054.framework.annotations.UrlAnnot;

public class Personne {
    String nom;
    int age;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Personne() {
    }

    @UrlAnnot(url = "/info")
    public void showInfo(){

    }
}
