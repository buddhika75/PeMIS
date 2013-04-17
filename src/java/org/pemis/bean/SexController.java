/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of 
 Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package org.pemis.bean;

import org.pemis.facade.SexFacade;
import org.pemis.entity.Sex;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public final class SexController implements Serializable {

    @EJB
    private SexFacade ejbFacade;
    @ManagedProperty(value = "#{sessionController}")
    SessionController sessionController;
    private List<Sex> items = null;

    public SexController() {
    }

    public SexFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(SexFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    private SexFacade getFacade() {
        return ejbFacade;
    }

    public List<Sex> getItems() {
        if (items == null) {
            items = getFacade().findBySQL("select s from Sex s");
        }
        return items;
    }

    @FacesConverter(forClass = Sex.class)
    public static class SexControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SexController controller = (SexController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sexController");
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

        /**
         *
         * @param facesContext
         * @param component
         * @param object
         * @return
         */
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Sex) {
                Sex o = (Sex) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + SexController.class.getName());
            }
        }
    }
}
