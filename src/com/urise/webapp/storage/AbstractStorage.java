package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(Resume r);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r);
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r);
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        Resume r = new Resume();
        r.setUuid(uuid);
        Object searchKey = getExistedSearchKey(r);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        Resume r = new Resume();
        r.setUuid(uuid);
        Object searchKey = getExistedSearchKey(r);
        return doGet(searchKey);
    }

    private Object getExistedSearchKey(Resume r) {
        Object searchKey = getSearchKey(r);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(Resume r) {
        Object searchKey = getSearchKey(r);
        if (isExist(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        return searchKey;
    }


}
