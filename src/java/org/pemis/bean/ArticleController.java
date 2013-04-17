/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of 
 Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package org.pemis.bean;

import org.pemis.facade.ArticleFacade;
import org.pemis.entity.Article;
import java.io.Serializable;
import java.util.Calendar;
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
public final class ArticleController  implements Serializable {

    @EJB
    private ArticleFacade ejbFacade;
    @ManagedProperty(value = "#{sessionController}")
    SessionController sessionController;
    private Article current;
    private List<Article> items = null;
    String selectText = "";

    public ArticleController() {
    }

    public ArticleFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ArticleFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public String addNewArticle(){
        setCurrent(new Article());
        return "article";
    }
    
    
    public List<Article> getLstItems() {
        return getFacade().findBySQL("Select d From Article d");
    }

    public Article getCurrent() {
        if (current == null) {
            current = new Article();
        }
        return current;
    }

    public void setCurrent(Article current) {
        this.current = current;
    }

    private ArticleFacade getFacade() {
        return ejbFacade;
    }

    /**
     *
     * @return
     */
    public List<Article> getItems() {
        String sql ;
        if(getSelectText().trim().equals("")){
            sql = "select a from Article a where a.retired = false order by a.createAt desc";
        }else{
            sql = "select a from Article a where a.retired = false and (lower(a.articleInSinhala) like '%" + getSelectText().toLowerCase() + "%' or  lower(a.articleInTamil) like '%" + getSelectText().toLowerCase() + "%' or lower(a.articleInEnglish) like '%" + getSelectText().toLowerCase() + "%' or lower(a.topicInSinhala) like '%" + getSelectText().toLowerCase() + "%' or  lower(a.topicInTamil) like '%" + getSelectText().toLowerCase() + "%' or  lower(a.topicInEnglish) like '%" + getSelectText().toLowerCase() + "%'    ) order by a.createAt desc";
        }
        items = getFacade().findBySQL(sql);
        return items;
    }

    private void recreateModel() {
        items = null;
    }


    public void prepareAdd() {
        current = new Article();
    }

    public void saveSelected() {
        if (getCurrent()!=null && getCurrent().getId()!=null && getCurrent().getId()!= 0) {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(new MessageProvider().getValue("savedOldSuccessfully"));
        } else {
            current.setCreatedAt(Calendar.getInstance().getTime());
            current.setCreater(sessionController.loggedUser);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(new MessageProvider().getValue("savedNewSuccessfully"));
        }
        getItems();
        selectText = "";
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
        recreateModel();
        getItems();
        selectText = "";
        current = null;
    }

    public String getSelectText() {
        return selectText;
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;
    }


    @FacesConverter(forClass = Article.class)
    public static class ArticleControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArticleController controller = (ArticleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "articleController");
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
            if (object instanceof Article) {
                Article o = (Article) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + ArticleController.class.getName());
            }
        }
    }
}
