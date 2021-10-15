package com.book.catalogservice.controller;

import com.book.catalogservice.entity.CatalogEntity;
import com.book.catalogservice.service.CatalogService;
import com.book.catalogservice.vo.ResponseCatalog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "it's working";
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<CatalogEntity> allCatalogs = catalogService.getAllCatalogs();
        List<ResponseCatalog> res = new ArrayList<>();
        allCatalogs.forEach(v -> {
            res.add(new ModelMapper().map(v, ResponseCatalog.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
