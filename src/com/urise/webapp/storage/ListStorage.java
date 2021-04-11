package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    protected ArrayList<Resume> arrayList = new ArrayList<>();
    @Override
    public void clear() {
        arrayList.clear();
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
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getUuid().equals(uuid)) {
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
