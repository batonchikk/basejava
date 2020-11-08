package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        boolean available = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(r)) {
                available = true;
                System.out.println("Резюме с таким uuid уже существует");
            }
        }
        if (!available && size < 10000) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Хранилище переполнено");
        }
    }


    public Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                resume = storage[i];
            }
        }
        if (resume == null) {
            System.out.println("Резюме с таким uuid не найдено");
        }
        return resume;
    }

    public void delete(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                index = i;
            }
        }
        if (index >= 0) {
            for (int i = index; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Резюме с таким uuid не найдено");
        }
    }

    public void update(Resume resume) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                index = i;
            }
        }
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Резюме с таким uuid не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
