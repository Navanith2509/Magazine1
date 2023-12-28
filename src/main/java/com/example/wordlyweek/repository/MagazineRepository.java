package com.example.wordlyweek.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.wordlyweek.model.*;

public interface MagazineRepository {
    ArrayList<Magazine> getMagazines();

    Magazine getMagazineById(int magazineId);

    Magazine addMagazine(Magazine magazine);

    Magazine updateMagazine(int magazineId, Magazine magazine);

    void deleteMagazine(int magazineId);

    List<Writer> getMagazineWriters(int magazineId);
}
