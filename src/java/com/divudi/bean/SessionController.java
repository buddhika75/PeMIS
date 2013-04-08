/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package com.divudi.bean;

import com.divudi.entity.WebUser;
import com.divudi.entity.WebUserRole;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@SessionScoped
public class SessionController  implements Serializable {

    WebUser loggedUser = null;
    WebUserRole loggedRole=null;
    boolean logged = false;
    boolean activated = false;
    
    String primeTheme;
    String localeCode;
    String defLocale;

    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
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
        if (primeTheme==null || primeTheme.equals("")) primeTheme = "sunny";
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
