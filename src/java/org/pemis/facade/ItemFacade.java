/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pemis.facade;

import org.pemis.entity.Item;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IT
 */
@Stateless
public class ItemFacade extends AbstractFacade<Item> {
    @PersistenceContext(unitName = "HOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemFacade() {
        super(Item.class);
    }
    
    
    
    
    
    
}
