package com.divudi.bean;
// Facades
import com.divudi.entity.Area;
import com.divudi.entity.Province;
import com.divudi.facade.AreaFacade;
import com.divudi.facade.ProvinceFacade;
import java.io.Serializable;
// Entities
// Other
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.component.datalist.DataList;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@RequestScoped
public class AreaController  implements Serializable {

    /**
     * EJBs
     */
    @EJB
    ProvinceFacade provinceFacade;
    @EJB
    AreaFacade ejbFacade;
    @EJB
    AreaFacade areaFacade;
    /**
     * Lists
     */
    DataModel<Province> provinces;
    DataModel<Area> areas;
    //
    Province province;
    /**
     * IDs
     */
    Long provinceId;
    Long dpdhsId;
    Long mohId;
    Long phiId;
    Long phmId;
    Long gnId;
    /**
     *
     *
     */
    @ManagedProperty(value = "#{sessionController}")
    SessionController sessionController;

    /**
     *
     */
    /**
     * Initiate Area Controller
     */
    public AreaController() {
    }

    public DataModel<Area> getAreas() {
        return new ListDataModel<Area>(getAreaFacade().findBySQL("SELECT a FROM Area a WHERE a.retired=false ORDER BY a.name"));
    }

    public void setAreas(DataModel<Area> areas) {
        this.areas = areas;
    }

    public AreaFacade getAreaFacade() {
        return areaFacade;
    }

    public void setAreaFacade(AreaFacade areaFacade) {
        this.areaFacade = areaFacade;
    }

    public static int intValue(long value) {
        int valueInt = (int) value;
        if (valueInt != value) {
            throw new IllegalArgumentException(
                    "The long value " + value + " is not within range of the int type");
        }
        return valueInt;
    }

    public AreaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(AreaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }


    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public ProvinceFacade getProvinceFacade() {
        return provinceFacade;
    }

    public void setProvinceFacade(ProvinceFacade provinceFacade) {
        this.provinceFacade = provinceFacade;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public DataModel<Province> getProvinces() {
        return new ListDataModel<Province>(getProvinceFacade().findAll(true));
    }

    public void setProvinces(DataModel<Province> provinces) {
        this.provinces = provinces;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }


    @FacesConverter(forClass = Area.class)
    public static class AreaControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreaController controller = (AreaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areaController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Area) {
                Area o = (Area) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + AreaController.class.getName());
            }
        }
    }
}
