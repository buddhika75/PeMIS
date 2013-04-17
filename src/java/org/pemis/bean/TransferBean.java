/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pemis.bean;

import org.pemis.entity.Location;
import org.pemis.entity.Unit;
import org.pemis.entity.WebUser;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Buddhika
 */
@ManagedBean
@SessionScoped
public class TransferBean  implements Serializable {

    Unit unit;
    Location location;
    WebUser webUser;

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }
    
    

    
    /**
     * Creates a new instance of TransferBean
     */
    public TransferBean() {
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    
    
    
}
