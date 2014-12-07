package eu.novait.utilities.storage.interfaces;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krzysztof
 */
public class StorageDirectory extends StorageItem {

    public static String PATH_PREFIX = "/";
    public static String PATH_SEPARATOR = "/";

    public static StorageDirectory buildFromPath(String path, String pathSeparator) {
        String[] parts = path.split(pathSeparator);
        StorageDirectory parent = null;
        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].equals("")) {
                parent = new StorageDirectory(parts[i], parent);
            }
        }
        return parent;
    }

    public StorageDirectory(String name) {
        this.name = name;
    }

    public StorageDirectory(String name, StorageDirectory parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getPath() {
        if (this.parent == null) {
            return PATH_PREFIX + this.name;
        } else {
            return this.parent.getPath() + PATH_SEPARATOR + this.name;
        }
    }
}
