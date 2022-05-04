package com.example.vetfootprint.model;

public class AnimalModel {
    String animalName = "";
    String animelBreed = "";
    String animalAge = "";
    String animalSize = "";
    String animalMedicine = "";
    String animalTimeMedicine = "";
    String animalObs = "";
    public AnimalModel(){

    }
    public AnimalModel(String animalName, String animelBreed, String animalAge, String animalSize, String animalMedicine, String animalTimeMedicine, String animalObs) {
        this.animalName = animalName;
        this.animelBreed = animelBreed;
        this.animalAge = animalAge;
        this.animalSize = animalSize;
        this.animalMedicine = animalMedicine;
        this.animalTimeMedicine = animalTimeMedicine;
        this.animalObs = animalObs;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimelBreed() {
        return animelBreed;
    }

    public void setAnimelBreed(String animelBreed) {
        this.animelBreed = animelBreed;
    }

    public String getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(String animalAge) {
        this.animalAge = animalAge;
    }

    public String getAnimalSize() {
        return animalSize;
    }

    public void setAnimalSize(String animalSize) {
        this.animalSize = animalSize;
    }

    public String getAnimalMedicine() {
        return animalMedicine;
    }

    public void setAnimalMedicine(String animalMedicine) {
        this.animalMedicine = animalMedicine;
    }

    public String getAnimalTimeMedicine() {
        return animalTimeMedicine;
    }

    public void setAnimalTimeMedicine(String animalTimeMedicine) {
        this.animalTimeMedicine = animalTimeMedicine;
    }

    public String getAnimalObs() {
        return animalObs;
    }

    public void setAnimalObs(String animalObs) {
        this.animalObs = animalObs;
    }
}
