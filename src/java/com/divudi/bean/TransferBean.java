/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divudi.bean;

import com.divudi.entity.Bill;
import com.divudi.entity.Location;
import com.divudi.entity.Unit;
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

    Bill bill;
    Unit unit;
    Location location;
    
    
    /**
     * Creates a new instance of TransferBean
     */
    public TransferBean() {
    }

    public Bill getBill() {

        return bill;
    }

    public void setBill(Bill bill) {

        this.bill = bill;
    }
    
    
    
}
