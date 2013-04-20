/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package org.pemis.bean;

import org.pemis.entity.WebUser;
import org.pemis.entity.WebUserRole;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@SessionScoped
public class SessionController implements Serializable {

    WebUser loggedUser = null;
    WebUserRole loggedRole = null;
    boolean logged = false;
    boolean activated = false;
    String primeTheme;
    String localeCode;
    String defLocale;
    Boolean userTech;
    Boolean userPharmacologist;
    Boolean userAdmin;
    Boolean userBmi;

    public Boolean getUserTech() {
        if (isActivated() == false) {
            return false;
        }
        if (getLoggedRole().getName().equalsIgnoreCase("Tech")||getLoggedRole().getName().equalsIgnoreCase("Pharmacologist")||getLoggedRole().getName().equalsIgnoreCase("Administrator")) {
            return true;
        }
        return false;
    }

    public void setUserTech(Boolean userTech) {
        this.userTech = userTech;
    }

    public Boolean getUserBmi() {
        if (isActivated() == false) {
            return false;
        }
        if (getLoggedRole().getName().equalsIgnoreCase("Bmi")) {
            return true;
        }
        return false;
    }

    public void setUserBmi(Boolean userBmi) {
        this.userBmi = userBmi;
    }

    
    public Boolean getUserPharmacologist() {
         if (isActivated() == false) {
            return false;
        }
        if (getLoggedRole().getName().equalsIgnoreCase("Pharmacologist")||getLoggedRole().getName().equalsIgnoreCase("Administrator")) {
            return true;
        }
        return false;
    }

    public void setUserPharmacologist(Boolean userPharmacologist) {
        this.userPharmacologist = userPharmacologist;
    }

    public Boolean getUserAdmin() {
         if (isActivated() == false) {
            return false;
        }
        if (getLoggedRole().getName().equalsIgnoreCase("Administrator")) {
            return true;
        }
        return false;
    }

    public void setUserAdmin(Boolean userAdmin) {
        this.userAdmin = userAdmin;
    }

    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }

    @PostConstruct
    public void init(){
        setLocaleCode("en");
    }
    
    public String getDefLocale() {
        defLocale = "en";
        if (getLoggedUser() != null) {
            if (getLoggedUser().getDefLocale() != null) {
                if (!getLoggedUser().getDefLocale().equals("")) {
                    return getLoggedUser().getDefLocale();
                }
            }
        }
        return defLocale;
    }

    public void setDefLocale(String defLocale) {
        this.defLocale = defLocale;
    }

    public String getPrimeTheme() {
        if (primeTheme == null || primeTheme.equals("")) {
            primeTheme = "sunny";
        }
        if (getLoggedUser() != null) {
            if (getLoggedUser().getPrimeTheme() != null) {
                if (!getLoggedUser().getPrimeTheme().equals("")) {
                    return getLoggedUser().getPrimeTheme();
                }
            }
        }
        return primeTheme;
    }

    public void setPrimeTheme(String primeTheme) {
        this.primeTheme = primeTheme;
    }

    /**
     *
     * @return
     */
    public WebUser getLoggedUser() {
        return loggedUser;
    }

    /**
     *
     * @param loggedUser
     */
    public void setLoggedUser(WebUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    /**
     *
     * @return
     */
    public boolean isLogged() {
        return logged;
    }

    /**
     * Set whether user
     *
     * @param logged
     */
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * Get whether user is activated
     *
     * @return
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Mark logged user as activated
     *
     * @param activated
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public WebUserRole getLoggedRole() {
        return loggedRole;
    }

    public void setLoggedRole(WebUserRole loggedRole) {
        this.loggedRole = loggedRole;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }
}
