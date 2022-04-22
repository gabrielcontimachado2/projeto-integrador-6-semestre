package com.example.vetfootprint.model;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserModel {

    String userNameInstitution;
    String userEmailInstituion;
    String userPasswordIstitution;
    String userCnpjInstitution;
    String userPhoneInstitution;
    String userAddressInstitution;
    String userRoleIstitution;

    public UserModel(String userNameInstitution, String userEmailInstituion, String userPasswordIstitution, String userCnpjInstitution, String userPhoneInstitution, String userAddressInstitution, String userRoleIstitution) {

        Log.d("myTag", "This is my message" + userNameInstitution + " ");

        this.userNameInstitution = userNameInstitution;
        this.userEmailInstituion = userEmailInstituion;
        this.userPasswordIstitution = userPasswordIstitution;
        this.userCnpjInstitution = userCnpjInstitution;
        this.userPhoneInstitution = userPhoneInstitution;
        this.userAddressInstitution = userAddressInstitution;
        this.userRoleIstitution = userRoleIstitution;
    }

    public String getUserNameInstitution() {
        return userNameInstitution;
    }

    public void setUserNameInstitution(String userNameInstitution) {
        this.userNameInstitution = userNameInstitution;
    }

    public String getUserEmailInstituion() {
        return userEmailInstituion;
    }

    public void setUserEmailInstituion(String userEmailInstituion) {
        this.userEmailInstituion = userEmailInstituion;
    }

    public String getUserPasswordIstitution() {
        return userPasswordIstitution;
    }

    public void setUserPasswordIstitution(String userPasswordIstitution) {
        this.userPasswordIstitution = userPasswordIstitution;
    }

    public String getUserCnpjInstitution() {
        return userCnpjInstitution;
    }

    public void setUserCnpjInstitution(String userCnpjInstitution) {
        this.userCnpjInstitution = userCnpjInstitution;
    }

    public String getUserPhoneInstitution() {
        return userPhoneInstitution;
    }

    public void setUserPhoneInstitution(String userPhoneInstitution) {
        this.userPhoneInstitution = userPhoneInstitution;
    }

    public String getUserAddressInstitution() {
        return userAddressInstitution;
    }

    public void setUserAddressInstitution(String userAddressInstitution) {
        this.userAddressInstitution = userAddressInstitution;
    }

    public String getUserRoleIstitution() {
        return userRoleIstitution;
    }

    public void setUserRoleIstitution(String userRoleIstitution) {
        this.userRoleIstitution = userRoleIstitution;
    }
}
