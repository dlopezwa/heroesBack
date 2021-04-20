package com.form.heroesBack.export.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.form.heroesBack.export.interfaces.ExportStrategy;
import com.form.heroesBack.export.typesExport.ExportExcel;
import com.form.heroesBack.hero.adapter.HeroAdapter;
import com.form.heroesBack.hero.repository.HeroRepository;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RepositoryRestController
@RequestMapping("/exports")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ExportController {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String ATTACHMENT_FILENAME = "attachment; filename=";
    private static final String XLSX = ".xlsx";
    private static final String PDF = ".pdf";
    private static final String HERO_ENTITY = "hero";

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value = "/excel/hero")
    public ResponseEntity<Object> exportExcelHero() throws IllegalArgumentException, IOException {

        ByteArrayInputStream resource = getExportExcel().export(
            HeroAdapter.toEto(heroRepository.findAll())
            );

        HttpHeaders headers = getHeadersExcel(HERO_ENTITY, XLSX);
        InputStreamResource inputStream = new InputStreamResource(resource);
        return ResponseEntity.ok().headers(headers).body(resource.readAllBytes());

    }
    
    private ExportStrategy getExportExcel() {
        return new ExportExcel();
    }
    
    private HttpHeaders getHeadersExcel(String filename, String extension) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + WordUtils.capitalize(filename) + extension);
        return headers;
    }

    // @GetMapping(value = "/pdf/hero")
    // public ResponseEntity<Object> exportPdfHero() throws IllegalArgumentException, IOException {
    
    //     ByteArrayInputStream resource = getExportPdf().export(heroRepository.findAll(), null);   
    
    //     HttpHeaders headers = getHeadersExcel(HERO_ENTITY, PDF);
    //     return ResponseEntity.ok().headers(headers).body(new InputStreamResource(resource));
    
    // }
    // private ExportStrategy getExportPdf() {
    //     return new ExportPdf();
    // }
}
