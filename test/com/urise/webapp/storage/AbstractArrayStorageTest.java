package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {
    private Storage storage = new ArrayStorage();


    @Before
    public void setUp() throws Exception {
        storage.clear();
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0,storage.size());
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void save() {
        Resume r = new Resume();
        r.setUuid("uuid4");
        storage.save(r);
        Assert.assertEquals(4, storage.size());
    }

    @Test (expected = NotExistStorageException.class)
    public void update() {
        Resume r = new Resume();
        r.setUuid("uuid4");
        storage.update(r);
    }

    @Test
    public void get() {
        Resume r = new Resume();
        r.setUuid("uuid2");
        Assert.assertEquals(r, storage.get("uuid2"));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(storage.getAll(), storage.getAll());

    }

    @Test
    public void StorageException() {
        try {
            for (int i = storage.size(); i < 10000;i++) {
                Resume r = new Resume();
                r.setUuid(String.valueOf(i));
                storage.save(r);
            }
        } catch (StorageException e) {
            Assert.fail("Overflow occurred ahead of time");
        }
    }
}