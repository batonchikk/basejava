package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ListStorageTest {

    private ListStorage storage = new ListStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume R1 = new Resume(UUID_1);
    private static final Resume R2 = new Resume(UUID_2);
    private static final Resume R3 = new Resume(UUID_3);

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
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume r = new Resume("uuid1");
        storage.update(r);
        assertSame(r, storage.get("uuid1"));
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
    public void get() {
        assertEquals(R1, storage.get("uuid1"));
        assertEquals(R2, storage.get("uuid2"));
        assertEquals(R2, storage.get("uuid2"));
    }

    @Test (expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("uuid5");
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
    public void getAll() {
        Resume[] storageGetAll = storage.getAll();
        assertEquals(storageGetAll.length, 3);
        assertEquals(storageGetAll[0], R1);
        assertEquals(storageGetAll[1], R2);
        assertEquals(storageGetAll[2], R3);
    }

    @Test
    public void size() {
        assertSize(3);
    }
}