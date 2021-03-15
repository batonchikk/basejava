package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    protected ArrayList<Resume> arrayList = new ArrayList<>();
    @Override
    public void clear() {
        arrayList.clear();
    }

    @Override
    public void update(Resume r) {
        int index = arrayList.indexOf(r);
        if (index == -1) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            arrayList.set(index, r);
        }
    }

    @Override
    public void save(Resume r) {
        int index = arrayList.indexOf(r);
        if (index != -1) {
            throw new ExistStorageException(r.getUuid());
        } else {
            arrayList.add(r);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        } else {
            return arrayList.get(index);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        } else {
            arrayList.remove(index);
        }

    }

    @Override
    public Resume[] getAll() {
        return arrayList.toArray(new Resume[arrayList.size()]);
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    protected int getIndex(String uuid) {
        int index = -1;
        Resume resume = new Resume(uuid);
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals(resume)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
