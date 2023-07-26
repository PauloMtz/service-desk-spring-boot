package com.sistema.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class JasperService {
    
    private static final String JASPER_DIRECTORY = "classpath:jasper/";
    private static final String JASPER_PREFIX = "Clientes-";
    private static final String JASPER_SUFIX = ".jasper";
    
    @Autowired
    private Connection connection;

    private Map<String, Object> params = new HashMap<>();

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public byte[] exportPDF(String code) {
        byte[] bytes = null;

        try {
            File file = ResourceUtils.getFile(JASPER_DIRECTORY.concat(JASPER_PREFIX).concat(code).concat(JASPER_SUFIX));
            JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
            bytes = JasperExportManager.exportReportToPdf(print);
        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        }

        return bytes;
    }
}
