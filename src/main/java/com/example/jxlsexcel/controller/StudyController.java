package com.example.jxlsexcel.controller;

import com.example.jxlsexcel.dto.StudyDto;
import com.example.jxlsexcel.util.ClassPathUtils;
import com.example.jxlsexcel.util.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/study")
public class StudyController {

    @GetMapping
    public String study(Model model) {
        model.addAttribute("list", sampleData());
        return "study";
    }

    @GetMapping("/excel/sample")
    public void excelSample(HttpServletResponse response) {
        ClassPathResource classPathResource = new ClassPathResource(ClassPathUtils.STUDY_EXCEL_SAMPLE_PATH);

        try (InputStream inputStream = new BufferedInputStream(classPathResource.getInputStream())) {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, FileUtils.getExcelFileName("studySample"));
            OutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int n;

            while ((n = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, n);
            }
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/excel/download")
    public void excelDownload(HttpServletResponse response) {
        String title = "Study List";
        ClassPathResource classPathResource = new ClassPathResource(ClassPathUtils.STUDY_EXCEL_DOWNLOAD_PATH);

        try (InputStream inputStream = new BufferedInputStream(classPathResource.getInputStream())) {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, FileUtils.getExcelFileName("studyList"));
            OutputStream outputStream = response.getOutputStream();

            Context context = new Context();
            context.putVar("title", title);
            context.putVar("list", sampleData());
            JxlsHelper.getInstance().processTemplate(inputStream, outputStream, context);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseBody
    @PostMapping("/excel/upload")
    public void excelUpload(MultipartFile excelFile) {
        Assert.isTrue(!excelFile.isEmpty(), "excelFile is Empty");

        List<StudyDto> studyDtos = new ArrayList<>();
        ClassPathResource classPathResource = new ClassPathResource(ClassPathUtils.STUDY_EXCEL_UPLOAD_PATH);

        try (InputStream inputStream = new BufferedInputStream(classPathResource.getInputStream());
            InputStream multipartInputStream = excelFile.getInputStream()) {
            XLSReader xlsReader = ReaderBuilder.buildFromXML(inputStream);

            Map<String, Object> beans = new HashMap<>();
            beans.put("studyList", studyDtos);

            xlsReader.read(multipartInputStream, beans);
            System.out.println(studyDtos);
        } catch (IOException | SAXException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private List<StudyDto> sampleData() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        StudyDto studyDto1 = StudyDto.builder()
                .id(1L)
                .title("java study")
                .content("java 공부하기")
                .createdAt(LocalDateTime.parse(LocalDateTime.now().format(dateTimeFormatter)))
                .build();
        StudyDto studyDto2 = StudyDto.builder()
                .id(2L)
                .title("spring study")
                .content("spring 공부하기")
                .createdAt(LocalDateTime.parse(LocalDateTime.now().format(dateTimeFormatter)))
                .build();
        return Arrays.asList(studyDto1, studyDto2);
    }
}
