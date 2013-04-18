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
public final class ArticleController implements Serializable {

    @EJB
    private ArticleFacade ejbFacade;
    @ManagedProperty(value = "#{sessionController}")
    SessionController sessionController;
    private Article current;
    Article public1;
    Article public2;
    Article public3;
    Article public4;
    Article prof1;
    Article prof2;
    Article prof3;
    Article prof4;
    List<Article> publicArticles;
    List<Article> professionalArticles;

    public List<Article> getPublicArticles() {
        return publicArticles;
    }

    public void setPublicArticles(List<Article> publicArticles) {
        this.publicArticles = publicArticles;
    }

    public List<Article> getProfessionalArticles() {
        return professionalArticles;
    }

    public void setProfessionalArticles(List<Article> professionalArticles) {
        this.professionalArticles = professionalArticles;
    }
    
    

    public Article getPublic1() {
        return public1;
    }

    public void setPublic1(Article public1) {
        this.public1 = public1;
    }

    public Article getPublic2() {
        return public2;
    }

    public void setPublic2(Article public2) {
        this.public2 = public2;
    }

    public Article getPublic3() {
        return public3;
    }

    public void setPublic3(Article public3) {
        this.public3 = public3;
    }

    public Article getPublic4() {
        return public4;
    }

    public void setPublic4(Article public4) {
        this.public4 = public4;
    }

    public Article getProf1() {
        return prof1;
    }

    public void setProf1(Article prof1) {
        this.prof1 = prof1;
    }

    public Article getProf2() {
        return prof2;
    }

    public void setProf2(Article prof2) {
        this.prof2 = prof2;
    }

    public Article getProf3() {
        return prof3;
    }

    public void setProf3(Article prof3) {
        this.prof3 = prof3;
    }

    public Article getProf4() {
        return prof4;
    }

    public void setProf4(Article prof4) {
        this.prof4 = prof4;
    }
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

    public String addNewArticle() {
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
        String sql;
        if (getSelectText().trim().equals("")) {
            sql = "select a from Article a where a.retired = false order by a.createdAt desc";
        } else {
            sql = "select a from Article a where a.retired = false and (lower(a.articleInSinhala) like '%" + getSelectText().toLowerCase() + "%' or  lower(a.articleInTamil) like '%" + getSelectText().toLowerCase() + "%' or lower(a.articleInEnglish) like '%" + getSelectText().toLowerCase() + "%' or lower(a.topicInSinhala) like '%" + getSelectText().toLowerCase() + "%' or  lower(a.topicInTamil) like '%" + getSelectText().toLowerCase() + "%' or  lower(a.topicInEnglish) like '%" + getSelectText().toLowerCase() + "%'    ) order by a.createdAt desc";
        }
        items = getFacade().findBySQL(sql);
        return items;
    }

    public String displayPublicArticle(int serialNo) {
        switch (serialNo) {
            case 1:
                setCurrent(public1);
                break;
            case 2:
                setCurrent(public2);
                break;
            case 3:
                setCurrent(public3);
                break;
            case 4:
                setCurrent(public4);
                break;
            default:
                setCurrent(null);
        }
        if (current != null) {
            return "display_article";
        } else {
            JsfUtil.addErrorMessage("Please select an article to display");
            return "";
        }
    }

    public String publicArticles() {
        String sql;
        if (sessionController.localeCode.equals("si")) {
            sql = "select a from Article a where a.retired = false and a.forPublic = true and a.articleInSinhala = true order by a.createdAt desc";
        } else if (sessionController.localeCode.equals("ta")) {
            sql = "select a from Article a where a.retired = false and a.forPublic = true and a.articleInTamil = true order by a.createdAt desc";
        } else {
            sql = "select a from Article a where a.retired = false and a.forPublic = true and a.articleInEnglish = true order by a.createdAt desc";
        }
        List<Article> pas = getFacade().findBySQL(sql);
        int i = 0;
        for (Article a : pas) {
            if (i == 0) {
                public1 = a;
            } else if (i == 1) {
                public2 = a;
            } else if (i == 2) {
                public3 = a;
            } else if (i == 3) {
                public4 = a;
            }
            i = i + 1;
        }
        return "public";
    }

    private void recreateModel() {
        items = null;
    }

    public void prepareAdd() {
        current = new Article();
    }

    public void saveSelected() {
        if (getCurrent() != null && getCurrent().getId() != null && getCurrent().getId() != 0) {
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
