/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of 
 Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package com.divudi.bean;

import com.divudi.facade.PatientFacade;
import com.divudi.entity.Patient;
import com.divudi.entity.Person;
import com.divudi.facade.PersonFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@ViewScoped
public final class PatientController  implements Serializable {

    @EJB
    private PatientFacade ejbFacade;
    @EJB
    private PersonFacade personFacade;
    @ManagedProperty(value = "#{sessionController}")
    SessionController sessionController;
    private Patient current;
    private List<Patient> items = null;
    List<Patient> searchedPatients;
    String selectText = "";

    public PersonFacade getPersonFacade() {
        return personFacade;
    }

    public void setPersonFacade(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    
    
    
    public List<Patient> getSearchedPatients() {
        if (getSelectText().trim().equals("")){
            searchedPatients = new ArrayList<Patient>();
            return searchedPatients;
        }
        searchedPatients = getFacade().findBySQL("SELECT m FROM Patient m WHERE m.retired=false and lower(m.person.name) like '%" + getSelectText().toLowerCase() + "%' ORDER BY m.person.name");
        return searchedPatients;
    }

    public void setSearchedPatients(List<Patient> searchedPatients) {
        this.searchedPatients = searchedPatients;
    }
    
    public PatientController() {
    }

    public PatientFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PatientFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }
    
    
    public Patient getCurrent() {
        if (current == null) {
            current = new Patient();
        }
        if(current.getPerson()==null){
            Person p = new Person();
            current.setPerson(p);
        }
        return current;
    }

    public void setCurrent(Patient current) {
        this.current = current;
    }

    private PatientFacade getFacade() {
        return ejbFacade;
    }

    public List<Patient> getItems() {
        items = getFacade().findBySQL("SELECT m FROM Patient m WHERE m.retired=false ORDER BY m.person.name");
        return items;
    }

    

    public String prepareAdd() {
        current = new Patient();
        return "add_new_patient";
    }

    public void saveSelected() {
        if(getCurrent()==null){
            JsfUtil.addErrorMessage("Nothing to Save");
            return;
        }
        if(getCurrent().getPerson().getName()==null ||  "".equals(getCurrent().getPerson().getName().trim())){
            JsfUtil.addErrorMessage("Please enter a name");
            return;
        }
        
        if(getCurrent().getPerson().getId()==null||getCurrent().getPerson().getId()==0){
            getPersonFacade().create(getCurrent().getPerson());
        }else{
            getPersonFacade().edit(getCurrent().getPerson());
        }
        if (getCurrent().getId()!=null && getCurrent().getId() != 0) {
            getFacade().edit(getCurrent());
            JsfUtil.addSuccessMessage(new MessageProvider().getValue("savedOldSuccessfully"));
        } else {
            getCurrent().setCreatedAt(Calendar.getInstance().getTime());
            getCurrent().setCreater(sessionController.loggedUser);
            getFacade().create(getCurrent());
            JsfUtil.addSuccessMessage(new MessageProvider().getValue("savedNewSuccessfully"));
        }
        setCurrent(null);
        getCurrent();
        getItems();
    }

    public void delete() {

        if (current != null) {
            current.setRetired(true);
            current.setRetiredAt(Calendar.getInstance().getTime());
            current.setRetirer(sessionController.loggedUser);
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(new MessageProvider().getValue("deleteSuccessful"));
        } else {
            JsfUtil.addErrorMessage(new MessageProvider().getValue("nothingToDelete"));
        }
    }

    public String getSelectText() {
        return selectText;
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;
    }

    @FacesConverter(forClass = Patient.class)
    public static class PatientControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PatientController controller = (PatientController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "patientController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Patient) {
                Patient o = (Patient) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + PatientController.class.getName());
            }
        }
    }
}
