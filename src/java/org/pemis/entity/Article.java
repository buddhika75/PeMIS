/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pemis.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Buddhika
 */
@Entity
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Created Properties
    @ManyToOne
    WebUser creater;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date createdAt;
    //Retairing properties
    boolean retired;
    @ManyToOne
    WebUser retirer;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date retiredAt;
    String retireComments;
    @ManyToOne
    Article parentConcept;
    Boolean defaultArticle;
    Boolean inSinhala;
    Boolean inTamil;
    Boolean inEnglish;
    @Transient
    String articleText;
    @Transient
    String articleTopic;
    @Transient
    String articleLead;
    @Lob
    String articleInSinhala;
    @Lob
    String articleInTamil;
    @Lob
    String articleInEnglish;
    @Lob
    String leadingInSinhala;
    @Lob
    String leadingInTamil;
    @Lob
    String leadingInEnglish;
    @Lob
    String linkUrl;
    Boolean linkOnly;
    Boolean articleOnly;
    String topicInSinhala;
    String topicInEnglish;
    String topicInTamil;
    Boolean forPublic;
    Boolean forProfessionals;
    
    Boolean pnews;
    Boolean pfaq;
    Boolean presource;
    Boolean hnews;
    Boolean hfaq;
    Boolean hbull;
    Boolean hresource;

    public Boolean getPnews() {
        return pnews;
    }

    public void setPnews(Boolean pnews) {
        this.pnews = pnews;
    }

    public Boolean getPfaq() {
        return pfaq;
    }

    public void setPfaq(Boolean pfaq) {
        this.pfaq = pfaq;
    }

    public Boolean getPresource() {
        return presource;
    }

    public void setPresource(Boolean presource) {
        this.presource = presource;
    }

    public Boolean getHnews() {
        return hnews;
    }

    public void setHnews(Boolean hnews) {
        this.hnews = hnews;
    }

    public Boolean getHfaq() {
        return hfaq;
    }

    public void setHfaq(Boolean hfaq) {
        this.hfaq = hfaq;
    }

    public Boolean getHbull() {
        return hbull;
    }

    public void setHbull(Boolean hbull) {
        this.hbull = hbull;
    }

    public Boolean getHresource() {
        return hresource;
    }

    public void setHresource(Boolean hresource) {
        this.hresource = hresource;
    }
    
    

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Boolean getLinkOnly() {
        return linkOnly;
    }

    public void setLinkOnly(Boolean linkOnly) {
        this.linkOnly = linkOnly;
    }

    public Boolean getArticleOnly() {
        return articleOnly;
    }

    public void setArticleOnly(Boolean articleOnly) {
        this.articleOnly = articleOnly;
    }


    
    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getArticleTopic() {
        return articleTopic;
    }

    public void setArticleTopic(String articleTopic) {
        this.articleTopic = articleTopic;
    }

    public String getArticleLead() {
        return articleLead;
    }

    public void setArticleLead(String articleLead) {
        this.articleLead = articleLead;
    }

    public Boolean getForPublic() {
        return forPublic;
    }

    public void setForPublic(Boolean forPublic) {
        this.forPublic = forPublic;
    }

    public Boolean getForProfessionals() {
        return forProfessionals;
    }

    public void setForProfessionals(Boolean forProfessionals) {
        this.forProfessionals = forProfessionals;
    }

    public WebUser getCreater() {
        return creater;
    }

    public void setCreater(WebUser creater) {
        this.creater = creater;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public WebUser getRetirer() {
        return retirer;
    }

    public void setRetirer(WebUser retirer) {
        this.retirer = retirer;
    }

    public Date getRetiredAt() {
        return retiredAt;
    }

    public void setRetiredAt(Date retiredAt) {
        this.retiredAt = retiredAt;
    }

    public String getRetireComments() {
        return retireComments;
    }

    public void setRetireComments(String retireComments) {
        this.retireComments = retireComments;
    }

    public Article getParentConcept() {
        return parentConcept;
    }

    public void setParentConcept(Article parentConcept) {
        this.parentConcept = parentConcept;
    }

    public Boolean getDefaultArticle() {
        return defaultArticle;
    }

    public void setDefaultArticle(Boolean defaultArticle) {
        this.defaultArticle = defaultArticle;
    }

    public Boolean getInSinhala() {
        return inSinhala;
    }

    public void setInSinhala(Boolean inSinhala) {
        this.inSinhala = inSinhala;
    }

    public Boolean getInTamil() {
        return inTamil;
    }

    public void setInTamil(Boolean inTamil) {
        this.inTamil = inTamil;
    }

    public Boolean getInEnglish() {
        return inEnglish;
    }

    public void setInEnglish(Boolean inEnglish) {
        this.inEnglish = inEnglish;
    }

    public String getArticleInSinhala() {
        return articleInSinhala;
    }

    public void setArticleInSinhala(String articleInSinhala) {
        this.articleInSinhala = articleInSinhala;
    }

    public String getArticleInTamil() {
        return articleInTamil;
    }

    public void setArticleInTamil(String articleInTamil) {
        this.articleInTamil = articleInTamil;
    }

    public String getArticleInEnglish() {
        return articleInEnglish;
    }

    public void setArticleInEnglish(String articleInEnglish) {
        this.articleInEnglish = articleInEnglish;
    }

    public String getLeadingInSinhala() {
        return leadingInSinhala;
    }

    public void setLeadingInSinhala(String leadingInSinhala) {
        this.leadingInSinhala = leadingInSinhala;
    }

    public String getLeadingInTamil() {
        return leadingInTamil;
    }

    public void setLeadingInTamil(String leadingInTamil) {
        this.leadingInTamil = leadingInTamil;
    }

    public String getLeadingInEnglish() {
        return leadingInEnglish;
    }

    public void setLeadingInEnglish(String leadingInEnglish) {
        this.leadingInEnglish = leadingInEnglish;
    }

    public String getTopicInSinhala() {
        return topicInSinhala;
    }

    public void setTopicInSinhala(String topicInSinhala) {
        this.topicInSinhala = topicInSinhala;
    }

    public String getTopicInEnglish() {
        return topicInEnglish;
    }

    public void setTopicInEnglish(String topicInEnglish) {
        this.topicInEnglish = topicInEnglish;
    }

    public String getTopicInTamil() {
        return topicInTamil;
    }

    public void setTopicInTamil(String topicInTamil) {
        this.topicInTamil = topicInTamil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.divudi.entity.Concept[ id=" + id + " ]";
    }
}
