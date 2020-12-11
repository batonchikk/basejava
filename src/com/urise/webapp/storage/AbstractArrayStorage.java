package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
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
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " was not found");
        } else {
            deleteFromArray(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume with uuid " + r.getUuid() + " already exists");
        } else if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            saveInArray(index, r);
            size++;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume with uuid " + r.getUuid() + " was not found");
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " was not found");
            return null;
        }
        return storage[Math.abs(index)];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);
    protected abstract void deleteFromArray(int index);
    protected abstract void saveInArray(int index, Resume r);

}