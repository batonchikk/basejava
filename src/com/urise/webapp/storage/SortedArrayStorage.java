package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume with uuid " + r.getUuid() + " was not found");
        } else {
            storage[index] = r;
        }
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume with uuid " + r.getUuid() + " already exists");
        } else if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            index = Math.abs(index + 1);
            System.arraycopy(storage, index, storage, index+1, size-index);
            storage[index] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " was not found");
        } else {
            index = Math.abs(index + 1);
            System.arraycopy(storage, index, storage, index-1, size-index);
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}