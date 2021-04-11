package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {

    protected HashMap<String, Resume> hashMap = new HashMap<>();
    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {

    }

    @Override
    protected void doSave(Resume r, Object searchKey) {

    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    public void update(Resume r) {
        if (hashMap.containsKey(r.getUuid())) {
            hashMap.put(r.getUuid(), r);
        }
    }

    @Override
    public void save(Resume r) {
        hashMap.put(r.getUuid(), r);
    }

    @Override
    public Resume get(String uuid) {
        return hashMap.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        hashMap.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) hashMap.clone();
    }

    @Override
    public int size() {
        return hashMap.size();
    }

}
