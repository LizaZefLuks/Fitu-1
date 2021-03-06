package com.linoge.models.shared;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Timo on 02.03.2017.
 */
public final class PdfBuilder {
    private static final String FONT = "src/main/resources/fonts/FreeSans.ttf";
    private static final String OUTPUT_FILE_NAME = "output.pdf";
    private static final String PATH_TO_PDF_FILES = "pdf/";
    private static final String FILE_FORMAT = ".pdf";
    private static final float FONT_SIZE = 14;

    private PdfBuilder() {

    }

    private static String createFileName(){
        return PATH_TO_PDF_FILES + "filename" + FILE_FORMAT;
    }

    private static Font getFont() throws Exception{
        BaseFont sans =
                BaseFont.createFont(
                        FONT,
                        BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED);
        return new Font(sans, FONT_SIZE);
    }

    private static void createContent(Document document) throws Exception{
        String text = "Справка, справочка. \nА тут цифирьки - #960-c от 24.07.2015";
        document.add(new Paragraph(text, getFont()));
    }

    public static ResponseEntity<byte[]> buildPDF() throws Exception{
        String fileName = createFileName();
        Document document = new Document();
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        PdfWriter.getInstance(document, fileOutputStream);

        document.open();
        createContent(document);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        setHeaders(headers);

        File temp = new File(fileName);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(temp), headers, HttpStatus.OK);
    }

    private static void setHeaders(HttpHeaders headers) {
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(OUTPUT_FILE_NAME, OUTPUT_FILE_NAME);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    }
}
