package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {

    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String FULLNAME_1 = "John Wick";
    private static final String FULLNAME_2 = "Vin Diesel";
    private static final String FULLNAME_3 = "Lana Rhodes";
    private static final Resume R1 = new Resume(UUID_1, FULLNAME_1);
    private static final Resume R2 = new Resume(UUID_2, FULLNAME_2);
    private static final Resume R3 = new Resume(UUID_3, FULLNAME_3);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test (expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        assertSize(2);
        storage.get("uuid1");
    }

    @Test (expected = NotExistStorageException.class)
    public void deleteNonExistent() {
        storage.delete("uuid6");
    }

    @Test
    public void save() {
        Resume r = new Resume();
        r.setUuid("uuid4");
        storage.save(r);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(R1);
    }

    @Test
    public void update() {
        Resume r = new Resume("uuid1", "John Wick");
        storage.update(r);
        assertSame(r, storage.get("uuid1"));
    }

    @Test
    public void get() {
        assertEquals(R1, storage.get("uuid1"));
        assertEquals(R2, storage.get("uuid2"));
        assertEquals(R3, storage.get("uuid3"));
    }

    @Test (expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("uuid5");
    }

    @Test
    public void getAllSorted() {
        List<Resume> storageGetAll = storage.getAllSorted();
        assertEquals(storageGetAll.size(), 3);
        assertEquals(storageGetAll.get(0), R1);
        assertEquals(storageGetAll.get(1), R3);
        assertEquals(storageGetAll.get(2), R2);
    }
}