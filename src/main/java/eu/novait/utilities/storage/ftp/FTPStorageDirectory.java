/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.utilities.storage.ftp;

import eu.novait.utilities.storage.exceptions.FTPStorageNotADirectory;
import eu.novait.utilities.storage.interfaces.StorageDirectory;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Krzysztof
 */
public class FTPStorageDirectory extends StorageDirectory {

    private String user;
    private String group;

    public static FTPStorageDirectory createFromFTPFile(FTPFile file, String path) throws FTPStorageNotADirectory {
        if (file.getType() != FTPStorageSystem.FTP_STORAGE_DIRECTORY) {
            throw new FTPStorageNotADirectory();
        }
        FTPStorageDirectory fsd = new FTPStorageDirectory(file.getName(), StorageDirectory.buildFromPath(path, "/"));
        fsd.setGroup(file.getGroup());
        fsd.setUser(file.getUser());
        fsd.setCreateDate(file.getTimestamp().getTime());
        return fsd;
    }

    public FTPStorageDirectory(String name) {
        super(name);
    }

    public FTPStorageDirectory(String name, StorageDirectory parent) {
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
