
package com.example.wordlyweek.controller;

import com.example.wordlyweek.model.*;

import com.example.wordlyweek.service.WriterJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WriterController {
    @Autowired
    private WriterJpaService writerJpaService;

    @GetMapping("/magazines/writers")
    public ArrayList<Writer> getWriters() {
        return writerJpaService.getWriters();
    }

    @GetMapping("/magazines/writers/{writerId}")
    public Writer getwriterById(@PathVariable("writerId") int writerId) {
        return writerJpaService.getWriterById(writerId);
    }

    @PostMapping("/magazines/writers")
    public Writer addriter(@RequestBody Writer writer) {
        return writerJpaService.addWriter(writer);
    }

    @PutMapping("/magazines/writers/{writerId}")
    public Writer updateWriter(@PathVariable("writerId") int writerId, @RequestBody Writer writer) {
        return writerJpaService.updateWriter(writerId, writer);
    }

    @DeleteMapping("/magazines/writers/{writerId}")
    public void deleteWriter(@PathVariable("writerId") int writerId) {
        writerJpaService.deleteWriter(writerId);
    }

    @GetMapping("/writers/{writerId}/magazines")
    public List<Magazine> getWriterMagazines(@PathVariable("writerId") int writerId) {
        return writerJpaService.getWriterMagazines(writerId);
    }
}
