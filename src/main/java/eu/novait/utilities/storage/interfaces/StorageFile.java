package eu.novait.utilities.storage.interfaces;

/**
 *
 * @author Krzysztof
 */
public abstract class StorageFile extends StorageItem {

    private long size;

    public StorageFile(String name, StorageDirectory parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getPath() {
        if (this.parent == null) {
            return StorageDirectory.PATH_SEPARATOR + this.name;
        }
        return this.parent.getPath() + StorageDirectory.PATH_SEPARATOR + this.name;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }

}
