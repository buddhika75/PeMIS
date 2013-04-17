/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pemis.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.pemis.entity.Article;

/**
 *
 * @author Buddhika
 */
@Stateless
public class ArticleFacade extends AbstractFacade<Article> {
    @PersistenceContext(unitName = "HOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }
    
}
