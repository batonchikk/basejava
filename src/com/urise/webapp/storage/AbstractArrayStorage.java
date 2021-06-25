package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveInArray((Integer) searchKey, r);
            size++;
        }
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteFromArray((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    public List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
}

    protected abstract Object getSearchKey(String uuid);
    protected abstract void deleteFromArray(int index);
    protected abstract void saveInArray(int index, Resume r);

}