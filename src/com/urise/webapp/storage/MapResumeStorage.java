package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage{

    private Map<String, Resume> hashMap = new LinkedHashMap<>();
    @Override
    protected Object getSearchKey(String uuid) {
        return hashMap.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Object resume) {
        hashMap.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Object resume) {
        hashMap.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object resume) {
        hashMap.remove(((Resume) resume).getUuid());
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(hashMap.values());
    }

    @Override
    public int size() {
        return hashMap.size();
    }
}
