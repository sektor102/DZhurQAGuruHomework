package guru.qa.dZhurHomeWork.less9;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;


public class CheckZipAndReadJson {
    private ClassLoader cl = CheckZipAndReadJson.class.getClassLoader();


    @Test
    void zipContainsPdfWithExpectedContent() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("less9/Test.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.getName().endsWith(".pdf")) continue;

                byte[] bytes = zis.readAllBytes();
                try (ByteArrayInputStream pdfIs = new ByteArrayInputStream(bytes)) {
                    PDF pdf = new PDF(pdfIs);
                    assertTrue(pdf.text.length() > 0);
                    assertEquals(58, pdf.numberOfPages);
                    assertTrue(pdf.text.contains("GAMMA РАСШИРЕННОЕ Краткое руководство"));
                }
                return;
            }
            Assertions.fail("В ZIP не найден PDF-файл");
        }
    }

    @Test
    void zipContainsXlsxWithExpectedContent() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("less9/Test.zip"))) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.getName().endsWith(".xlsx")) continue;

                byte[] bytes = zis.readAllBytes();

                try (InputStream entryIs = new ByteArrayInputStream(bytes)) {
                    XLS xlsx = new XLS(entryIs);

                    var row = xlsx.excel.getSheetAt(0).getRow(1); // строка 2 (Иванова)

                    String[] actual = {
                            row.getCell(0).getStringCellValue(),
                            row.getCell(1).getStringCellValue(),
                            row.getCell(2).getStringCellValue(),
                            row.getCell(3).getStringCellValue(),
                            row.getCell(4).getStringCellValue()
                    };

                    String[] expected = {"CN001", "Иванова", "Ирина", "Витальевна", "OU001"};
                    Assertions.assertArrayEquals(expected, actual);
                }

                return;
            }
            Assertions.fail("В ZIP не найден XLSX-файл");
        }
    }

    @Test
    void zipContainsCsvWithExpectedContent() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("less9/Test.zip"),
                java.nio.charset.Charset.forName("Windows-1251")
        )) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.getName().toLowerCase().endsWith(".csv")) continue;

                byte[] bytes = zis.readAllBytes();

                try (InputStream entryIs = new ByteArrayInputStream(bytes);
                     InputStreamReader isr = new InputStreamReader(entryIs, java.nio.charset.StandardCharsets.UTF_8);
                     com.opencsv.CSVReader csv = new com.opencsv.CSVReader(isr)) {

                    java.util.List<String[]> rows = csv.readAll();
                    assertTrue(rows.size() > 1, "CSV пустой или только заголовок");

                    String[] expected6 = {
                            "13.05.2024","16:11:53","4","25E+11","bf8ea7efa041","788197",
                            "PODELI","411111******1111","1","MC (MasterCard)","1800.00","кредит",
                            "6EN4U6","-30.00","270.00","810","67a04e12-4b05-467a-91a7-3d3a6305bc71","Baikal"
                    };
                    String[] expected7 = {
                            "13.05.2024","16:12:32","4","25E+11","bf8ea7efa041","788197",
                            "PODELI","411111******1111","1","MC (MasterCard)","4200.00","кредит",
                            "6EN4U6","-30.00","270.00","810","018fb36a-ff5f-46db-9af8-095d654783fd","Baikal"
                    };

                    org.junit.jupiter.api.Assertions.assertArrayEquals(expected6, rows.get(6));
                    org.junit.jupiter.api.Assertions.assertArrayEquals(expected7, rows.get(7));
                }

                return;
            }

            org.junit.jupiter.api.Assertions.fail("В ZIP не найден CSV-файл");
        }
    }

    @Test
    void readAndCheckJson() throws Exception {
        try (InputStream is = cl.getResourceAsStream("less9/testCompany.json")) {
            assertNotNull(is, "Файл не найден: less9/testCompany.json");

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(is);

            assertEquals("Company Ltd", root.get("name").asText());
            assertEquals("Moscow", root.get("city").asText());

            JsonNode employees = root.get("employees");
            assertTrue(employees.isArray());
            assertEquals(2, employees.size());

            JsonNode e1 = employees.get(0);
            assertEquals("CN001", e1.get("id").asText());
            assertEquals("Иванова", e1.get("lastName").asText());
            assertEquals("Ирина", e1.get("firstName").asText());
            assertEquals("OU001", e1.get("dept").asText());

            JsonNode e2 = employees.get(1);
            assertEquals("CN002", e2.get("id").asText());
            assertEquals("Сергеев", e2.get("lastName").asText());
            assertEquals("Константин", e2.get("firstName").asText());
            assertEquals("OU002", e2.get("dept").asText());
        }
    }
}

// для коммита
