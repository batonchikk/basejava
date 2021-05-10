package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void deleteFromArray(int index) {
        index = Math.abs(index + 1);
        System.arraycopy(storage, index, storage, index-1, size-index);
    }

    @Override
    protected void saveInArray(int index, Resume r) {
        index = - index - 1;
        System.arraycopy(storage, index, storage, index+1, size-index);
        storage[index] = r;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        //searchKey.setFullName("name");
        //not fullName
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}