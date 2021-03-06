package eu.novait.utilities.storage.ftp;

import eu.novait.utilities.storage.exceptions.FTPStorageNotADirectory;
import eu.novait.utilities.storage.exceptions.FTPStorageNotAFile;
import eu.novait.utilities.storage.interfaces.IStorageSystem;
import eu.novait.utilities.storage.interfaces.StorageDirectory;
import eu.novait.utilities.storage.interfaces.StorageFile;
import eu.novait.utilities.storage.interfaces.StorageItem;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Krzysztof
 */
public class FTPStorageSystem implements IStorageSystem {

    public static final int FTP_STORAGE_FILE = 0;
    public static final int FTP_STORAGE_DIRECTORY = 1;

    protected FTPClient ftp;

    protected String host;
    protected long port;
    protected String username;
    protected String password;

    public FTPStorageSystem(String host, long port, String username, String password) {
        Logger.getLogger("").log(Level.INFO, "host: " + host + " port: " + port + " user: " + username + " password: " + password);
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        ftp = new FTPClient();
    }
    
    public boolean completePending() throws IOException{
        return ftp.completePendingCommand();
    }
    
    public String getCWD() throws IOException{
        return ftp.printWorkingDirectory();
    }

    public void setCWD(String cwd) {
        try {
            ftp.cwd(cwd);
        } catch (IOException ex) {
            Logger.getLogger(FTPStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<StorageItem> listDir() {
        List<StorageItem> ret = new ArrayList<StorageItem>();
        try {
            this.connectIfNeeded();
            for (FTPFile f : this.ftp.listFiles()) {
                if (f.getType() == FTPStorageSystem.FTP_STORAGE_FILE) {
                    FTPStorageFile fsf = FTPStorageFile.createFromFTPFile(f, ftp.printWorkingDirectory());
                    ret.add(fsf);
                } else if (f.getType() == FTPStorageSystem.FTP_STORAGE_DIRECTORY) {
                    if (!f.getName().equals(".") && !f.getName().equals("..")) {
                        FTPStorageDirectory fsd = FTPStorageDirectory.createFromFTPFile(f, ftp.printWorkingDirectory());
                        ret.add(fsd);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FTPStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FTPStorageNotAFile ex) {
            Logger.getLogger(FTPStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FTPStorageNotADirectory ex) {
            Logger.getLogger(FTPStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public List<StorageItem> listDir(String dir) {
        List<StorageItem> ret = new ArrayList<StorageItem>();
        try {
            this.connectIfNeeded();
            for (FTPFile f : this.ftp.listFiles(dir)) {
                if (f.getType() == FTPStorageSystem.FTP_STORAGE_FILE) {
                    FTPStorageFile fsf = FTPStorageFile.createFromFTPFile(f, dir);
                    ret.add(fsf);
                } else if (f.getType() == FTPStorageSystem.FTP_STORAGE_DIRECTORY) {
                    if (!f.getName().equals(".") && !f.getName().equals("..")) {
                        FTPStorageDirectory fsd = FTPStorageDirectory.createFromFTPFile(f, dir);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FTPStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FTPStorageNotAFile ex) {
            Logger.getLogger(FTPStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FTPStorageNotADirectory ex) {
            Logger.getLogger(FTPStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    protected void connectIfNeeded() throws IOException {
        if (!ftp.isConnected()) {
            Logger.getLogger(FTPStorageSystem.class.getName()).log(Level.INFO, "Not connected - trying to connect");
            ftp.connect(this.host, (int) this.port);
            if (this.username != null && this.password != null) {
                ftp.login(username, password);
            }
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
        } else {
            Logger.getLogger(FTPStorageSystem.class.getName()).log(Level.INFO, "Connected - no action needed");
        }
    }

    @Override
    public InputStream retrieveFileStream(StorageFile storageFile) throws IOException {
        InputStream is = ftp.retrieveFileStream(storageFile.getPath());
        return is;
    }

}
