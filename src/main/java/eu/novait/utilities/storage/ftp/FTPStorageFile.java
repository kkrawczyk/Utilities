/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.utilities.storage.ftp;

import eu.novait.utilities.storage.exceptions.FTPStorageNotAFile;
import eu.novait.utilities.storage.interfaces.StorageDirectory;
import eu.novait.utilities.storage.interfaces.StorageFile;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Krzysztof
 */
public class FTPStorageFile extends StorageFile{
    
    private String user;
    private String group;
    
    public static FTPStorageFile createFromFTPFile(FTPFile file, String path) throws FTPStorageNotAFile{
        if(file.getType()!=FTPStorageSystem.FTP_STORAGE_FILE){
            throw new FTPStorageNotAFile();
        }
        FTPStorageFile fsf = new FTPStorageFile(file.getName(), StorageDirectory.buildFromPath(path, "/"));
        fsf.setGroup(file.getGroup());
        fsf.setUser(file.getUser());
        fsf.setCreateDate(file.getTimestamp().getTime());
        fsf.setSize(file.getSize());
        return fsf;
    }

    public FTPStorageFile(String name, StorageDirectory parent) {
        super(name, parent);
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }
    
}
