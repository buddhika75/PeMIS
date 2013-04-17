/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pemis.facade;

import org.pemis.entity.Image;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IT
 */
@Stateless
public class AppImageFacade extends AbstractFacade<Image> {
    @PersistenceContext(unitName = "HOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppImageFacade() {
        super(Image.class);
    }
    
    
    
    
    
    
}
