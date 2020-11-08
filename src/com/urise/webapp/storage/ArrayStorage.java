package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i])) {
                resume = storage[i];
            }
        }
        return resume;
    }

    public void delete(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                index = i;
            }
        }
        if (index >= 0) {
            for (int i = index; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
