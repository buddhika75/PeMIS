/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package com.divudi.bean;

import com.divudi.entity.Area;
import com.divudi.entity.Institution;
import com.divudi.entity.Person;
import com.divudi.entity.Sex;
import com.divudi.entity.WebUserRole;
import com.divudi.entity.WebUser;
import com.divudi.entity.Unit;
import com.divudi.facade.AppImageFacade;
import com.divudi.facade.PersonFacade;
import com.divudi.facade.WebUserFacade;
import com.divudi.facade.WebUserRoleFacade;
import com.divudi.facade.AreaFacade;
import com.divudi.facade.InstitutionFacade;
import com.divudi.facade.SexFacade;
import com.divudi.facade.UnitFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.omg.PortableInterceptor.ACTIVE;
import org.primefaces.event.CaptureEvent;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@RequestScoped
public class ConnectionController implements Serializable {

    byte[] photo;
    @EJB
    WebUserFacade uFacade;
    @EJB
    PersonFacade pFacade;
    @EJB
    WebUserRoleFacade rFacade;
    @EJB
    InstitutionFacade institutionFacade;
    @EJB
    UnitFacade unitFacade;
    @EJB
    AreaFacade areaFacade;
    @EJB
    WebUserRoleFacade roleFacade;
    @EJB
    SexFacade sexFacade;
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    @ManagedProperty(value = "#{menu}")
    private Menu menu;
    @ManagedProperty(value = "#{imageController}")
    private ImageController imageController;
    @EJB
    AppImageFacade imageFacade;
    //
    WebUser current;
    Person currentPerson;
    String userName;
    String passord;
    String newPassword;
    String newPasswordConfirm;
    String newPersonName;
    String newUserName;
    String newDesignation;
    String newInstitution;
    String newPasswordHint;
    WebUserRole newRole;
    //
    boolean logged;
    boolean activated;
    WebUserRole role;
    String displayName;
//
    Institution institution;
    Unit unit;
    Area area;
    DataModel<Institution> institutions;
    DataModel<Unit> units;
    DataModel<Area> areas;

    /**
     * Creates a new instance of ConnectionController
     */
    public SexFacade getSexFacade() {
        return sexFacade;
    }

    public void setSexFacade(SexFacade sexFacade) {
        this.sexFacade = sexFacade;
    }

    
    
    public WebUserRole getNewRole() {
        return newRole;
    }

    public void setNewRole(WebUserRole newRole) {
        this.newRole = newRole;
    }

    
    
    
    public ConnectionController() {
    }

    public WebUserRoleFacade getRoleFacade() {
        return roleFacade;
    }

    public void setRoleFacade(WebUserRoleFacade roleFacade) {
        this.roleFacade = roleFacade;
    }

    public WebUserRole getRole() {
        return role;
    }

    public void setRole(WebUserRole role) {
        this.role = role;
    }

    public Person getCurrentPerson() {
        if (currentPerson == null) {
            currentPerson = new Person();
        }
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public AppImageFacade getImageFacade() {
        return imageFacade;
    }

    public void setImageFacade(AppImageFacade imageFacade) {
        this.imageFacade = imageFacade;
    }

    public ImageController getImageController() {
        return imageController;
    }

    public void setImageController(ImageController imageController) {
        this.imageController = imageController;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void oncapture(CaptureEvent captureEvent) {
        photo = captureEvent.getData();
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    private WebUserFacade getFacede() {
        return uFacade;
    }

    public String loginAction() {
        if (login()) {
            menu.createMenu();
            return "";
        } else {
            JsfUtil.addErrorMessage("Login Failure. Please try again");
            return "";
        }
    }

    private boolean login() {
        if (isFirstVisit()) {
            prepareFirstVisit();
            return true;
        } else {
//            JsfUtil.addSuccessMessage("Checking Old Users");
            return checkUsers();
        }
    }

    private void prepareFirstVisit() {
        WebUser user = new WebUser();
        Person person = new Person();
        person.setName(userName);
        pFacade.create(person);
        WebUserRole adminRole;
        WebUserRole addingRole;

        addingRole = new WebUserRole();
        addingRole.setName("Administrator");
        rFacade.create(addingRole);
        adminRole=addingRole;

        addingRole = new WebUserRole();
        addingRole.setName("Doctor");
        rFacade.create(addingRole);

        addingRole = new WebUserRole();
        addingRole.setName("Nurse");
        rFacade.create(addingRole);

        Institution addingIns=new Institution();
        addingIns.setName(userName+ " Hospital");
        institutionFacade.create(addingIns);

        Sex s = new Sex();
        s.setName("Male");
        getSexFacade().create(s);
        
        s= new Sex();
        s.setName("Female");
        getSexFacade().create(s);
        
        s=new Sex();
        s.setName("Other");
        getSexFacade().create(s);
        
        
        
        user.setName(HOSecurity.encrypt(userName));
        user.setWebUserPassword(HOSecurity.hash(passord));
        user.setWebUserPerson(person);
        user.setActivated(true);
        user.setInstitution(addingIns);
        user.setRole(adminRole);
        uFacade.create(user);


    }

    public String registerUser() {
        if (newUserName.trim().equals("")) {
            JsfUtil.addErrorMessage("Plese enter a user name");
            return "";
        }
        if (newPassword.trim().equals("")) {
            JsfUtil.addErrorMessage("Plese enter a password");
            return "";
        }        
        if (!userNameAvailable(newUserName)) {
            JsfUtil.addErrorMessage("User name already Exists. Plese enter another user name");
            return "";
        }
        if (!newPassword.equals(newPasswordConfirm)) {
            JsfUtil.addErrorMessage("Password and Re-entered password are not maching");
            return "";
        }
        if(newRole==null){
            JsfUtil.addErrorMessage("Please select a role");
            return "";
        }
        Person person = new Person();
        person.setName(newPersonName);
        person.setInstitution(sessionController.getLoggedUser().getInstitution());
        pFacade.create(person);
        
        WebUser user = new WebUser();
        user.setName(HOSecurity.encrypt(newUserName));
        user.setWebUserPassword(HOSecurity.hash(newPassword));
        user.setWebUserPerson(person);
        user.setActivated(false);
        user.setRole(newRole);
        uFacade.create(user);

        JsfUtil.addSuccessMessage("New User Registered. You will be able to access the system when the administrater activate your account.");
        return "";
    }

    public String registePersonAsUser() {
        if (currentPerson == null) {
            JsfUtil.addErrorMessage("Plese select a person");
            return "";
        }
        if (!userNameAvailable(newUserName)) {
            JsfUtil.addErrorMessage("User name already Exists. Plese enter another user name");
            return "";
        }
        if (!newPassword.equals(newPasswordConfirm)) {
            JsfUtil.addErrorMessage("Password and Re-entered password are not maching");
            return "";
        }
        WebUser user = getCurrent();
        user.setWebUserPerson(currentPerson);
        user.setName(HOSecurity.encrypt(newUserName));
        user.setWebUserPassword(HOSecurity.hash(newPassword));
        user.setActivated(true);
        uFacade.create(user);
        JsfUtil.addSuccessMessage(currentPerson.getName() + " was registered as a new user. Please give the previlages.");
        return "admin_user_previlages";
    }

    public String changePassword() {
        WebUser user = sessionController.loggedUser;
        if (!HOSecurity.matchPassword(passord, user.getWebUserPassword())) {
            JsfUtil.addErrorMessage("The old password you entered is incorrect");
            return "";
        }
        if (!newPassword.equals(newPasswordConfirm)) {
            JsfUtil.addErrorMessage("Password and Re-entered password are not maching");
            return "";
        }

        user.setWebUserPassword(HOSecurity.hash(newPassword));
        uFacade.edit(user);
        //
        JsfUtil.addSuccessMessage("Password changed");
        return "index";
    }

    public Boolean userNameAvailable(String userName) {
        Boolean available = true;
        List<WebUser> allUsers = getFacede().findAll();
        for (WebUser w : allUsers) {
            if (userName.toLowerCase().equals(HOSecurity.decrypt(w.getName()).toLowerCase())) {
                available = false;
            }
        }
        return available;
    }

    private boolean isFirstVisit() {
        if (getFacede().count() <= 0) {
//            JsfUtil.addSuccessMessage("First Visit");
            return true;
        } else {
//            JsfUtil.addSuccessMessage("Not, Not First Visit");
            return false;
        }

    }

    private boolean checkUsers() {
//        JsfUtil.addSuccessMessage("Going to check users");
        String temSQL;
        temSQL = "SELECT u FROM WebUser u WHERE u.retired = false";
        List<WebUser> allUsers = getFacede().findBySQL(temSQL);
        for (WebUser u : allUsers) {
            if (HOSecurity.decrypt(u.getName()).equalsIgnoreCase(userName)) {
//                JsfUtil.addSuccessMessage("A user found");

                if (HOSecurity.matchPassword(passord, u.getWebUserPassword())) {
                    sessionController.setLoggedUser(u);
                    sessionController.setLogged(Boolean.TRUE);
                    sessionController.setActivated(u.isActivated());
                    sessionController.setLoggedRole(u.getRole());
                    setLanguage(u);
                    JsfUtil.addSuccessMessage("Logged successfully");
                    return true;
                }
            }
        }
        return false;
    }

    private void setLanguage(WebUser u) {
        String localeCode = u.getDefLocale();
        if (localeCode == null || localeCode.equals("")) {
            localeCode = "si_LK";
        }
        Locale myLocale;
        if (localeCode.equals("si_LK")) {
            System.out.println("Sinhala");
            myLocale = new Locale("si", "LK");
        } else if (localeCode.equals("ta_LK")) {
            System.out.println("Tamil");
            myLocale = new Locale("ta", "LK");
        } else {
            System.out.println("English");
            myLocale = new Locale("en");
        }
        FacesContext.getCurrentInstance().getViewRoot().setLocale(myLocale);
    }

    public void logout() {
        sessionController.setLoggedUser(null);
        sessionController.setLogged(false);
        sessionController.setActivated(false);
        sessionController.setLoggedRole(null);
    }

    public WebUser getCurrent() {
        if (current == null) {
            current = new WebUser();
            Person p = new Person();
            current.setWebUserPerson(p);
        }
        return current;
    }

    public void setCurrent(WebUser current) {
        this.current = current;
    }

    public WebUserFacade getEjbFacade() {
        return uFacade;
    }

    public void setEjbFacade(WebUserFacade ejbFacade) {
        this.uFacade = ejbFacade;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewDesignation() {
        return newDesignation;
    }

    public void setNewDesignation(String newDesignation) {
        this.newDesignation = newDesignation;
    }

    public String getNewInstitution() {
        return newInstitution;
    }

    public void setNewInstitution(String newInstitution) {
        this.newInstitution = newInstitution;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getNewPasswordHint() {
        return newPasswordHint;
    }

    public void setNewPasswordHint(String newPasswordHint) {
        this.newPasswordHint = newPasswordHint;
    }

    public String getNewPersonName() {
        return newPersonName;
    }

    public void setNewPersonName(String newPersonName) {
        this.newPersonName = newPersonName;
    }

    public PersonFacade getpFacade() {
        return pFacade;
    }

    public void setpFacade(PersonFacade pFacade) {
        this.pFacade = pFacade;
    }

    public WebUserFacade getuFacade() {
        return uFacade;
    }

    public void setuFacade(WebUserFacade uFacade) {
        this.uFacade = uFacade;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public boolean isActivated() {
        return sessionController.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
        sessionController.setLogged(activated);
    }

    public boolean isLogged() {
        return sessionController.logged;
    }

    public void setLogged(boolean logged) {
        sessionController.setLogged(logged);
        this.logged = logged;
    }

    public WebUserRoleFacade getrFacade() {
        return rFacade;
    }

    public void setrFacade(WebUserRoleFacade rFacade) {
        this.rFacade = rFacade;
    }

    public String getDisplayName() {
        return HOSecurity.decrypt(sessionController.getLoggedUser().getName());
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public AreaFacade getAreaFacade() {
        return areaFacade;
    }

    public void setAreaFacade(AreaFacade areaFacade) {
        this.areaFacade = areaFacade;
    }

    public DataModel<Area> getAreas() {
        return new ListDataModel<Area>(getAreaFacade().findAll());
    }

    public void setAreas(DataModel<Area> areas) {
        this.areas = areas;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public InstitutionFacade getInstitutionFacade() {
        return institutionFacade;
    }

    public void setInstitutionFacade(InstitutionFacade institutionFacade) {
        this.institutionFacade = institutionFacade;
    }

    public DataModel<Institution> getInstitutions() {
        return new ListDataModel<Institution>(getInstitutionFacade().findAll());
    }

    public void setInstitutions(DataModel<Institution> institutions) {
        this.institutions = institutions;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public UnitFacade getUnitFacade() {
        return unitFacade;
    }

    public void setUnitFacade(UnitFacade unitFacade) {
        this.unitFacade = unitFacade;
    }

    public DataModel<Unit> getUnits() {
        if (getInstitution() != null) {
            return new ListDataModel<Unit>(getUnitFacade().findBySQL("SELECT u FROM Unit u WHERE u.retired=false AND u.institution.id = " + getInstitution().getId()));
        } else {
            return null;
        }
    }

    public void setUnits(DataModel<Unit> units) {
        this.units = units;
    }
}
