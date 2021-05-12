package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteFromArray(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void saveInArray(int index, Resume r) {
        storage[size] = r;
    }

    protected Object getSearchKey(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
