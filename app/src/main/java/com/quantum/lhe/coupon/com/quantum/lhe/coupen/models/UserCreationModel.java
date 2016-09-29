package com.quantum.lhe.coupon.com.quantum.lhe.coupen.models;

import java.io.Serializable;

/**
 * Created by Sharjeel on 9/2/2016.
 */

public class UserCreationModel implements Serializable {
    private boolean acceptTerms;

    private String confirmPassword;

    private UserDataModel userData;

    private String password;

    public boolean getAcceptTerms ()
    {
        return acceptTerms;
    }

    public void setAcceptTerms (boolean acceptTerms)
    {
        this.acceptTerms = acceptTerms;
    }

    public String getConfirmPassword ()
    {
        return confirmPassword;
    }

    public void setConfirmPassword (String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

    public UserDataModel getUserData ()
    {
        return userData;
    }

    public void setUserData (UserDataModel userData)
    {
        this.userData = userData;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [acceptTerms = "+acceptTerms+", confirmPassword = "+confirmPassword+", userData = "+userData+", password = "+password+"]";
    }
}
