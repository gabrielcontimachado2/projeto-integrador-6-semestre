package com.example.vetfootprint.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserModel {

    private DatabaseReference mDatabase;
    String userName = "";
    String userEmail = "";
    String userPassword = "";
    String institutionCnpj = "";
    String userPhone = "";
    String userAddress = "";
    String userFuction = "";
    String userCpf = "";
    String userRg = "";



    public UserModel(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void getUser(){

    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    String userRole = "";


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getInstitutionCnpj() {
        return institutionCnpj;
    }

    public void setInstitutionCnpj(String institutionCnpj) {
        this.institutionCnpj = institutionCnpj;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserFuction() {
        return userFuction;
    }

    public void setUserFuction(String userFuction) {
        this.userFuction = userFuction;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }

    public String getUserRg() {
        return userRg;
    }

    public void setUserRg(String userRg) {
        this.userRg = userRg;
    }


}
