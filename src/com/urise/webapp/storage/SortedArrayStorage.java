package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage{

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());


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
        Resume searchKey = new Resume(uuid, "fullName");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}