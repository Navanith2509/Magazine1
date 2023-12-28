

package com.example.wordlyweek.service;

import com.example.wordlyweek.model.*;

import com.example.wordlyweek.repository.*;
import com.example.wordlyweek.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class WriterJpaService implements WriterRepository {

    @Autowired
    private WriterJpaRepository writerJpaRepository;

    @Autowired
    private MagazineJpaRepository magazineJpaRepository;

    public ArrayList<Writer> getWriters() {
        List<Writer> writerList = writerJpaRepository.findAll();
        ArrayList<Writer> writers = new ArrayList<>(writerList);
        return writers;
    }

    public Writer getWriterById(int writerId) {
        try {
            Writer writer = writerJpaRepository.findById(writerId).get();
            return writer;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Writer addWriter(Writer writer) {
        List<Integer> magazineIds = new ArrayList<>();
        for (Magazine magazine : writer.getMagazines()) {
            magazineIds.add(magazine.getMagazineId());
        }

        List<Magazine> magazines = magazineJpaRepository.findAllById(magazineIds);

        if (magazines.size() != magazineIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        writer.setMagazines(magazines);

        return writerJpaRepository.save(writer);
    }

    @Override
    public Writer updateWriter(int writerId, Writer writer) {
        try {
            Writer newWriter = writerJpaRepository.findById(writerId).get();
            if (writer.getWriterName() != null) {
                newWriter.setWriterName(writer.getWriterName());
            }
            if (writer.getBio() != null) {
                newWriter.setBio(writer.getBio());
            }
            if (writer.getMagazines() != null) {
                List<Integer> magazineIds = new ArrayList<>();
                for (Magazine magazine : writer.getMagazines()) {
                    magazineIds.add(magazine.getMagazineId());
                }
                List<Magazine> magazines = magazineJpaRepository.findAllById(magazineIds);
                if (magazines.size() != magazineIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                newWriter.setMagazines(magazines);
            }
            return writerJpaRepository.save(newWriter);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteWriter(int writerId) {
        try {
            writerJpaRepository.deleteById(writerId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Magazine> getWriterMagazines(int writerId) {
        try {
            Writer writer = writerJpaRepository.findById(writerId).get();
            return writer.getMagazines();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
// Write your code here