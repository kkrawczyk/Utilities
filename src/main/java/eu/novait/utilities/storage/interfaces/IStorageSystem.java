package eu.novait.utilities.storage.interfaces;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Krzysztof
 */
public interface IStorageSystem {

    public InputStream retrieveFileStream(StorageFile storageFile) throws IOException;
}
