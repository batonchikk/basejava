package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class MapStorageTest {

    protected HashMap<String, Resume> hashMap = new HashMap<>();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume R1 = new Resume(UUID_1);
    private static final Resume R2 = new Resume(UUID_2);
    private static final Resume R3 = new Resume(UUID_3);

    @Before
    public void setUp() throws Exception {
        hashMap.clear();

        hashMap.put(UUID_1, R1);
        hashMap.put(UUID_2, R2);
        hashMap.put(UUID_3, R3);
    }

    @Test
    public void clear() {
        hashMap.clear();
        assertEquals(hashMap.size(), 0);
    }

    @Test
    public void update() {
    }

    @Test
    public void save() {
    }

    @Test
    public void get() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void size() {
        assertEquals(hashMap.size(), 3);
    }

    @Test
    public void getIndex() {
    }
}