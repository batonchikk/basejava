package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> hashMap = new LinkedHashMap<>();
    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    protected Object getSearchKey(Resume r) {
        return r.getUuid();
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        hashMap.put((String) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (searchKey == null) {
            hashMap.put(r.getUuid(), r);
        } else {
            hashMap.put((String) searchKey, r);
        }
    }

    @Override
    protected void doDelete(Object searchKey) {
        hashMap.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return hashMap.get(searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return hashMap.containsKey(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>();
        Set set = hashMap.entrySet();
        Iterator i = set.iterator();

        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            list.add((Resume)me.getValue());
        }
        return list;
    }

    @Override
    public int size() {
        return hashMap.size();
    }

}
