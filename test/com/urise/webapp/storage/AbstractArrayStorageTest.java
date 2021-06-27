package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void storageException() {
        try {
            for (int i = storage.size(); i < 10000;i++) {
                storage.save(new Resume(String.valueOf(i), "fullname"));
            }
        } catch (StorageException e) {
            Assert.fail("Overflow occurred ahead of time");
        }
    }
}