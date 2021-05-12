package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private ArrayList<Resume> arrayList = new ArrayList<>();
    @Override
    public void clear() {
        arrayList.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return arrayList;
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    protected Object getSearchKey(Resume r) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getUuid().equals(r.getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        arrayList.set((Integer)searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        arrayList.add(r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        arrayList.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return arrayList.get((Integer)searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
