/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.utilities.storage.interfaces;

import java.util.Date;

/**
 *
 * @author Krzysztof
 */
public class StorageItem {
    protected String name;
    protected StorageDirectory parent;
    private Date createDate;

    public StorageItem() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the parent
     */
    public StorageDirectory getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(StorageDirectory parent) {
        this.parent = parent;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}
