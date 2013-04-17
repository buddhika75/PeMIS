/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pemis.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

/**
 *
 * @author IT
 */
@Entity
@Inheritance
public class DesignationCategory extends Category implements Serializable {

    public DesignationCategory() {
    }
    
}
