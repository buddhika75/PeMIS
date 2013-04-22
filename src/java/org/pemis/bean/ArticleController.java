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
import java.util.ArrayList;
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
    Article publicNews1;
    Article publicNews2;
    Article publicNews3;
    Article publicNews4;
    Article publicFaq1;
    Article publicFaq2;
    Article publicFaq3;
    Article publicFaq4;
    Article publicResource1;
    Article publicResource2;
    Article publicResource3;
    Article publicResource4;
    Article healthNews1;
    Article healthNews2;
    Article healthNews3;
    Article healthNews4;
    Article healthFaq1;
    Article healthFaq2;
    Article healthFaq3;
    Article healthFaq4;
    Article healthResource1;
    Article healthResource2;
    Article healthResource3;
    Article healthResource4;
    Article healthBull1;
    Article healthBull2;
    Article healthBull3;
    Article healthBull4;
    List<Article> publicNews;
    List<Article> publicFaq;
    List<Article> publicResources;
    List<Article> healthNews;
    List<Article> healthFaq;
    List<Article> healthBull;
    List<Article> healthResources;
    List<Article> searchedArticles;

    public String searchArticle() {
        return "search";
    }

    public List<Article> getSearchedArticles() {
        if (getSelectText().trim().equals("")) {
            return new ArrayList<Article>();
        }
        String sql;
        sql = "select a from Article a where a.retired = false and (lower(a.articleInSinhala) like '%" + getSelectText().toLowerCase() + "%' or  lower(a.articleInTamil) like '%" + getSelectText().toLowerCase() + "%' or lower(a.articleInEnglish) like '%" + getSelectText().toLowerCase() + "%' or lower(a.topicInSinhala) like '%" + getSelectText().toLowerCase() + "%' or  lower(a.topicInTamil) like '%" + getSelectText().toLowerCase() + "%' or  lower(a.topicInEnglish) like '%" + getSelectText().toLowerCase() + "%'    ) order by a.createdAt desc";
        searchedArticles = getFacade().findBySQL(sql);
        return searchedArticles;
    }

    public void setSearchedArticles(List<Article> searchedArticles) {
        this.searchedArticles = searchedArticles;
    }

    public List<Article> getPublicNews() {
        if (publicNews != null && !publicNews.isEmpty()) {
            return publicNews;
        }
        String sql;
        if (sessionController.localeCode.equals("si")) {
            sql = "select a from Article a where a.pnews=true and a.retired=false and a.inSinhala=true order by a.createdAt desc";
        } else if (sessionController.localeCode.equals("ta")) {
            sql = "select a from Article a where a.pnews=true and a.retired=false and a.inTamil=true order by a.createdAt desc";
        } else {
            sql = "select a from Article a where a.pnews=true and a.retired=false and a.inEnglish=true order by a.createdAt desc";
        }
        publicNews = getFacade().findBySQL(sql);
        int i = 0;
        for (Article a : publicNews) {
            if (i == 0) {
                publicNews1 = a;
            } else if (i == 1) {
                publicNews2 = a;
            } else if (i == 2) {
                publicNews3 = a;
            } else if (i == 3) {
                publicNews4 = a;
            }
            i = i + 1;
        }
        return publicNews;
    }

    public Article getPublicFaq1() {
        getPublicFaq();
        return publicFaq1;
    }

    public void setPublicFaq1(Article publicFaq1) {
        this.publicFaq1 = publicFaq1;
    }

    public Article getPublicFaq2() {
        getPublicFaq();
        return publicFaq2;
    }

    public void setPublicFaq2(Article publicFaq2) {
        this.publicFaq2 = publicFaq2;
    }

    public Article getPublicFaq3() {
        getPublicFaq();
        return publicFaq3;
    }

    public void setPublicFaq3(Article publicFaq3) {
        this.publicFaq3 = publicFaq3;
    }

    public Article getPublicFaq4() {
        getPublicFaq();
        return publicFaq4;
    }

    public void setPublicFaq4(Article publicFaq4) {
        this.publicFaq4 = publicFaq4;
    }

    public Article getPublicResource1() {
        getPublicResources() ;
        return publicResource1;
    }

    public void setPublicResource1(Article publicResource1) {
        this.publicResource1 = publicResource1;
    }

    public Article getPublicResource2() {
        getPublicResources() ;
        return publicResource2;
    }

    public void setPublicResource2(Article publicResource2) {
        this.publicResource2 = publicResource2;
    }

    public Article getPublicResource3() {
        getPublicResources() ;
        return publicResource3;
    }

    public void setPublicResource3(Article publicResource3) {
        this.publicResource3 = publicResource3;
    }

    public Article getPublicResource4() {
        getPublicResources() ;
        return publicResource4;
    }

    public void setPublicResource4(Article publicResource4) {
        this.publicResource4 = publicResource4;
    }

    public Article getHealthFaq1() {
        getHealthFaq();
        return healthFaq1;
    }

    public void setHealthFaq1(Article healthFaq1) {
        this.healthFaq1 = healthFaq1;
    }

    public Article getHealthFaq2() {
        getHealthFaq();
        return healthFaq2;
    }

    public void setHealthFaq2(Article healthFaq2) {
        this.healthFaq2 = healthFaq2;
    }

    public Article getHealthFaq3() {
        getHealthFaq();
        return healthFaq3;
    }

    public void setHealthFaq3(Article healthFaq3) {
        this.healthFaq3 = healthFaq3;
    }

    public Article getHealthFaq4() {
        getHealthFaq();
        return healthFaq4;
    }

    public void setHealthFaq4(Article healthFaq4) {
        this.healthFaq4 = healthFaq4;
    }

    public Article getHealthResource1() {
        getHealthResources();
        return healthResource1;
    }

    public void setHealthResource1(Article healthResource1) {
        this.healthResource1 = healthResource1;
    }

    public Article getHealthResource2() {
        getHealthResources();
        return healthResource2;
    }

    public void setHealthResource2(Article healthResource2) {
        this.healthResource2 = healthResource2;
    }

    public Article getHealthResource3() {
        getHealthResources();
        return healthResource3;
    }

    public void setHealthResource3(Article healthResource3) {
        this.healthResource3 = healthResource3;
    }

    public Article getHealthResource4() {
        getHealthResources();
        return healthResource4;
    }

    public void setHealthResource4(Article healthResource4) {
        this.healthResource4 = healthResource4;
    }

    public Article getHealthBull1() {
        getHealthBull();
        return healthBull1;
    }

    public void setHealthBull1(Article healthBull1) {
        this.healthBull1 = healthBull1;
    }

    public Article getHealthBull2() {
        getHealthBull();
        return healthBull2;
    }

    public void setHealthBull2(Article healthBull2) {
        this.healthBull2 = healthBull2;
    }

    public Article getHealthBull3() {
        getHealthBull();
        return healthBull3;
    }

    public void setHealthBull3(Article healthBull3) {
        this.healthBull3 = healthBull3;
    }

    public Article getHealthBull4() {
        getHealthBull();
        return healthBull4;
    }

    public void setHealthBull4(Article healthBull4) {
        this.healthBull4 = healthBull4;
    }

    public List<Article> getPublicFaq() {
        if (publicFaq != null && !publicFaq.isEmpty()) {
            return publicFaq;
        }
        String sql;
        if (sessionController.localeCode.equals("si")) {
            sql = "select a from Article a where a.pfaq=true and a.retired=false and a.inSinhala=true order by a.createdAt desc";
        } else if (sessionController.localeCode.equals("ta")) {
            sql = "select a from Article a where a.pfaq=true and a.retired=false and a.inTamil=true order by a.createdAt desc";
        } else {
            sql = "select a from Article a where a.pfaq=true and a.retired=false and a.inEnglish=true order by a.createdAt desc";
        }
        publicFaq = getFacade().findBySQL(sql);
        int i = 0;
        for (Article a : publicFaq) {
            if (i == 0) {
                publicFaq1 = a;
            } else if (i == 1) {
                publicFaq2 = a;
            } else if (i == 2) {
                publicFaq3 = a;
            } else if (i == 3) {
                publicFaq4 = a;
            }
            i = i + 1;
        }
        return publicFaq;
    }

    public void setPublicFaq(List<Article> publicFaq) {
        this.publicFaq = publicFaq;
    }

    public List<Article> getPublicResources() {
        if (publicResources != null && !publicResources.isEmpty()) {
            return publicResources;
        }
        String sql;
        if (sessionController.localeCode.equals("si")) {
            sql = "select a from Article a where a.presource=true and a.retired=false and a.inSinhala=true order by a.createdAt desc";
        } else if (sessionController.localeCode.equals("ta")) {
            sql = "select a from Article a where a.presource=true and a.retired=false and a.inTamil=true order by a.createdAt desc";
        } else {
            sql = "select a from Article a where a.presource=true and a.retired=false and a.inEnglish=true order by a.createdAt desc";
        }
        publicResources = getFacade().findBySQL(sql);
        int i = 0;
        for (Article a : publicResources) {
            if (i == 0) {
                publicResource1 = a;
            } else if (i == 1) {
                publicResource2 = a;
            } else if (i == 2) {
                publicResource3 = a;
            } else if (i == 3) {
                publicResource4 = a;
            }
            i = i + 1;
        }
        return publicResources;
    }

    public void setPublicResources(List<Article> publicResources) {
        this.publicResources = publicResources;
    }

    public List<Article> getHealthFaq() {
        if (healthFaq != null && !healthFaq.isEmpty()) {
            return healthFaq;
        }
        String sql;
        if (sessionController.localeCode.equals("si")) {
            sql = "select a from Article a where a.hfaq=true and a.retired=false and a.inSinhala=true order by a.createdAt desc";
        } else if (sessionController.localeCode.equals("ta")) {
            sql = "select a from Article a where a.hfaq=true and a.retired=false and a.inTamil=true order by a.createdAt desc";
        } else {
            sql = "select a from Article a where a.hfaq=true and a.retired=false and a.inEnglish=true order by a.createdAt desc";
        }
        healthFaq = getFacade().findBySQL(sql);
        int i = 0;
        for (Article a : healthFaq) {
            if (i == 0) {
                healthFaq1 = a;
            } else if (i == 1) {
                healthFaq2 = a;
            } else if (i == 2) {
                healthFaq3 = a;
            } else if (i == 3) {
                healthFaq4 = a;
            }
            i = i + 1;
        }
        return healthFaq;
    }

    public void setHealthFaq(List<Article> healthFaq) {
        this.healthFaq = healthFaq;
    }

    public List<Article> getHealthBull() {
        if (healthBull != null && !healthBull.isEmpty()) {
            return healthBull;
        }
        String sql;
        if (sessionController.localeCode.equals("si")) {
            sql = "select a from Article a where a.hbull=true and a.retired=false and a.inSinhala=true order by a.createdAt desc";
        } else if (sessionController.localeCode.equals("ta")) {
            sql = "select a from Article a where a.hbull=true and a.retired=false and a.inTamil=true order by a.createdAt desc";
        } else {
            sql = "select a from Article a where a.hbull=true and a.retired=false and a.inEnglish=true order by a.createdAt desc";
        }
        healthBull = getFacade().findBySQL(sql);
        int i = 0;
        for (Article a : healthBull) {
            if (i == 0) {
                healthBull1 = a;
            } else if (i == 1) {
                healthBull2 = a;
            } else if (i == 2) {
                healthBull3 = a;
            } else if (i == 3) {
                healthBull4 = a;
            }
            i = i + 1;
        }
        return healthBull;
    }

    public void setHealthBull(List<Article> healthBull) {
        this.healthBull = healthBull;
    }

    public List<Article> getHealthResources() {
        if (healthResources != null && !healthResources.isEmpty()) {
            return healthResources;
        }
        String sql;
        if (sessionController.localeCode.equals("si")) {
            sql = "select a from Article a where a.hresource=true and a.retired=false and a.inSinhala=true order by a.createdAt desc";
        } else if (sessionController.localeCode.equals("ta")) {
            sql = "select a from Article a where a.hresource=true and a.retired=false and a.inTamil=true order by a.createdAt desc";
        } else {
            sql = "select a from Article a where a.hresource=true and a.retired=false and a.inEnglish=true order by a.createdAt desc";
        }
        healthResources = getFacade().findBySQL(sql);
        int i = 0;
        for (Article a : healthResources) {
            if (i == 0) {
                healthNews1 = a;
            } else if (i == 1) {
                healthResource1 = a;
            } else if (i == 2) {
                healthResource3 = a;
            } else if (i == 3) {
                healthResource4 = a;
            }
            i = i + 1;
        }
        return healthResources;
    }

    public void setHealthResources(List<Article> healthResources) {
        this.healthResources = healthResources;
    }

    public void setPublicNews(List<Article> publicNews) {
        this.publicNews = publicNews;
    }

    public List<Article> getHealthNews() {
        if (healthNews != null && !healthNews.isEmpty()) {
            return healthNews;
        }
        String sql;
        if (sessionController.localeCode.equals("si")) {
            sql = "select a from Article a where a.hnews=true and a.retired=false and a.inSinhala=true order by a.createdAt desc";
        } else if (sessionController.localeCode.equals("ta")) {
            sql = "select a from Article a where a.hnews=true and a.retired=false and a.inTamil=true order by a.createdAt desc";
        } else {
            sql = "select a from Article a where a.hnews=true and a.retired=false and a.inEnglish=true order by a.createdAt desc";
        }
        healthNews = getFacade().findBySQL(sql);
        int i = 0;
        for (Article a : healthNews) {
            if (i == 0) {
                healthNews1 = a;
            } else if (i == 1) {
                healthNews2 = a;
            } else if (i == 2) {
                healthNews3 = a;
            } else if (i == 3) {
                healthNews4 = a;
            }
            i = i + 1;
        }
        return healthNews;
    }

    public void setHealthNews(List<Article> healthNews) {
        this.healthNews = healthNews;
    }

    public Article getPublicNews1() {
        getPublicNews();
        return publicNews1;
    }

    public void setPublicNews1(Article publicNews1) {
        this.publicNews1 = publicNews1;
    }

    public Article getPublicNews2() {
        getPublicNews();
        return publicNews2;
    }

    public void setPublicNews2(Article publicNews2) {
        this.publicNews2 = publicNews2;
    }

    public Article getPublicNews3() {
        getPublicNews();
        return publicNews3;
    }

    public void setPublicNews3(Article publicNews3) {
        this.publicNews3 = publicNews3;
    }

    public Article getPublicNews4() {
        getPublicNews();
        return publicNews4;
    }

    public void setPublicNews4(Article publicNews4) {
        this.publicNews4 = publicNews4;
    }

    public Article getHealthNews1() {
        getHealthNews();
        return healthNews1;
    }

    public void setHealthNews1(Article healthNews1) {
        this.healthNews1 = healthNews1;
    }

    public Article getHealthNews2() {
        getHealthNews();
        return healthNews2;
    }

    public void setHealthNews2(Article healthNews2) {
        this.healthNews2 = healthNews2;
    }

    public Article getHealthNews3() {
        getHealthNews();
        return healthNews3;
    }

    public void setHealthNews3(Article healthNews3) {
        this.healthNews3 = healthNews3;
    }

    public Article getHealthNews4() {
        getHealthNews();
        return healthNews4;
    }

    public void setHealthNews4(Article healthNews4) {
        this.healthNews4 = healthNews4;
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

    public String displayPublicNews(int serialNo) {
        switch (serialNo) {
            case 1:
                setCurrent(publicNews1);
                break;
            case 2:
                setCurrent(publicNews2);
                break;
            case 3:
                setCurrent(publicNews3);
                break;
            case 4:
                setCurrent(publicNews4);
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

    
     public String displayPublicFaq(int serialNo) {
        switch (serialNo) {
            case 1:
                setCurrent(publicFaq1);
                break;
            case 2:
                setCurrent(publicFaq2);
                break;
            case 3:
                setCurrent(publicFaq3);
                break;
            case 4:
                setCurrent(publicFaq4);
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

    
      public String displayPublicResources(int serialNo) {
        switch (serialNo) {
            case 1:
                setCurrent(publicResource1);
                break;
            case 2:
                setCurrent(publicResource2);
                break;
            case 3:
                setCurrent(publicResource3);
                break;
            case 4:
                setCurrent(publicResource4);
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

     
    public String displayHealthNews(int serialNo) {
        switch (serialNo) {
            case 1:
                setCurrent(healthNews1);
                break;
            case 2:
                setCurrent(healthNews2);
                break;
            case 3:
                setCurrent(healthNews3);
                break;
            case 4:
                setCurrent(healthNews4);
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

    
    public String displayHealthFaq(int serialNo) {
        switch (serialNo) {
            case 1:
                setCurrent(healthFaq1);
                break;
            case 2:
                setCurrent(healthFaq2);
                break;
            case 3:
                setCurrent(healthFaq3);
                break;
            case 4:
                setCurrent(healthFaq4);
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
    
    public String displayHealthBulletin(int serialNo) {
        switch (serialNo) {
            case 1:
                setCurrent(healthBull1);
                break;
            case 2:
                setCurrent(healthBull2);
                break;
            case 3:
                setCurrent(healthBull3);
                break;
            case 4:
                setCurrent(healthBull4);
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
    
    public String displayHealthResources(int serialNo) {
        switch (serialNo) {
            case 1:
                setCurrent(healthResource1);
                break;
            case 2:
                setCurrent(healthResource2);
                break;
            case 3:
                setCurrent(healthResource3);
                break;
            case 4:
                setCurrent(healthResource4);
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
    
    private void recreateModel() {
        items = null;
    }

    public void prepareAdd() {
        current = new Article();
    }

    public String saveSelected() {
        setPublicNews(null);
        setHealthNews(null);
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
        return "article_search";
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
