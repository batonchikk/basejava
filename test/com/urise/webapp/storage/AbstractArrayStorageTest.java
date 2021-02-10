package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {

    private Storage storage = new ArrayStorage();
    static final Resume r1 = new Resume();
    static final Resume r2 = new Resume();
    static final Resume r3 = new Resume();

    @Before
    public void setUp() throws Exception {
        storage.clear();
        r1.setUuid("uuid1");
        r2.setUuid("uuid2");
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

    @Test (expected = NotExistStorageException.class)
    public void redelete() {
        storage.delete("uuid1");
        storage.delete("uuid1");
    }

    @Test (expected = NotExistStorageException.class)
    public void deleteNonExistent1() {
        storage.delete("uuid6");
    }

    @Test
    public void save() {
        Resume r = new Resume();
        r.setUuid("uuid4");
        storage.save(r);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveAgain() {
        storage.save(r1);
    }

    @Test
    public void update() {
        Resume r = new Resume();
        r.setUuid("uuid1");
        storage.update(r1);
        Assert.assertEquals(r1, r);
    }

    @Test
    public void get() {
        Assert.assertEquals(r1, storage.get("uuid1"));
        Assert.assertEquals(r2, storage.get("uuid2"));
        Assert.assertEquals(r3, storage.get("uuid3"));
    }

    @Test (expected = NotExistStorageException.class)
    public void getNonExistent() {
        Assert.assertEquals(r1, storage.get("uuid5"));

    }

    @Test
    public void getAll() {
        Resume[] storageGetAll = storage.getAll();
        Assert.assertEquals(storageGetAll.length, 3);
        Assert.assertEquals(storageGetAll[0], r1);
        Assert.assertEquals(storageGetAll[1], r2);
        Assert.assertEquals(storageGetAll[2], r3);
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