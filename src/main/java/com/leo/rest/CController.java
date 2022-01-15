package com.leo.rest;

import com.leo.CGood;
import com.leo.COrder;
import com.leo.CUser;
import com.leo.config.CConfigHibernate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;

@RestController()
public class CController {
    private static ArrayList<CUser> usermas = new ArrayList<>();
    private static ArrayList<CGood> goodmas = new ArrayList<>();
    private static ArrayList<COrder> ordermas = new ArrayList<>();
    public void CController(){
    }
    @GetMapping("/fromxlsx")
    public void fromxlsx(){
        loading();
        try {
            Session session = CConfigHibernate.getSessionFactory().openSession();
            session.beginTransaction();
            for (int i = 0; i < usermas.size(); i++){
                session.save(usermas.get(i));
            }
            for (int i = 0; i < goodmas.size(); i++){
                session.save(goodmas.get(i));
            }
            for (int i = 0; i < ordermas.size(); i++){
                session.save(ordermas.get(i));
            }

            session.getTransaction().commit();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void loading(){
        File f = new File("D:\\java\\files","Magazin.xlsx");
        Lusermas(f);
        Lgoodmas(f);
        Lordermas(f);
    }
    private static void Lusermas(File file){
        try (XSSFWorkbook wb = new XSSFWorkbook(file)){
            Sheet sheet = wb.getSheet("Пользователи");
            Integer rows = sheet.getLastRowNum();
            Row row;
            String login, name;
            LocalDate datebirth;
            UUID id;
            boolean gender = false;
            for (int i = 1; i<=rows; i++){
                row = sheet.getRow(i);
                if (row == null){
                    continue;
                }
                gender = false;
                id = UUID.fromString(row.getCell(0).getStringCellValue());
                login = row.getCell(1).getStringCellValue();
                name = row.getCell(2).getStringCellValue();
                if (row.getCell(3).getStringCellValue().equals("м")){
                    gender = true;}
                datebirth = row.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                usermas.add(new CUser(id,login,name,gender,datebirth));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void Lgoodmas(File file){
        try (XSSFWorkbook wb = new XSSFWorkbook(file)){
            Sheet sheet = wb.getSheet("Товары");
            Integer rows = sheet.getLastRowNum();
            Row row;
            String category, name;
            double price;
            UUID id;
            for (int i = 1; i<rows; i++){
                row = sheet.getRow(i);
                if (row == null){
                    continue;
                }
                id = UUID.fromString(row.getCell(0).getStringCellValue());
                name = row.getCell(1).getStringCellValue();
                price = row.getCell(2).getNumericCellValue();
                category = row.getCell(3).getStringCellValue();
                goodmas.add(new CGood(id,name,price,category));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void Lordermas(File file){
        try (XSSFWorkbook wb = new XSSFWorkbook(file)){
            Sheet sheet = wb.getSheet("Покупки");
            Integer rows = sheet.getLastRowNum();
            Row row;
            LocalDate date;
            UUID id1, id2;
            for (int i = 1; i<rows; i++){
                row = sheet.getRow(i);
                if (row == null){
                    continue;
                }
                if (row.getCell(0).getStringCellValue().equals("")){
                    continue;
                }
                id1 = UUID.fromString(row.getCell(0).getStringCellValue());
                id2 = UUID.fromString(row.getCell(1).getStringCellValue());
                date = row.getCell(2).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                ordermas.add(new COrder(UUID.randomUUID(), id1, id2, date));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
