/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pemis.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author IT
 */
@Entity
@Inheritance
public class Manufacturer extends Institution implements Serializable {
    
}
