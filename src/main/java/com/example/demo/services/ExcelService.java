package com.example.demo.services;

import com.example.demo.enums.TarotType;
import com.example.demo.models.CardTarot;
import jakarta.annotation.PostConstruct;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExcelService {
    // đường dẫn file excel
    @Value("classpath:data.xlsx")
    Resource resourceFile;

    // danh sách thẻ
    private List<CardTarot> cards;
    public ExcelService() {
        this.cards = new ArrayList<>();
    }

    @PostConstruct
    private void readExcelFile() throws IOException {
        XSSFSheet worksheet = null;
        int currentPictureIndex = 0;
        // đọc file excel
        XSSFWorkbook workbook = new XSSFWorkbook(resourceFile.getInputStream());
        List<String> pictures = readAllImg(workbook);
        // đọc sheet công việc
        worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            var row = worksheet.getRow(i);
            if(row!=null){

                try {
                    if (row.getCell(0).toString().isEmpty()) {
                        break;
                    }
                    // khởi tạo thẻ
                    CardTarot card = new CardTarot(worksheet.getRow(i), pictures.get(currentPictureIndex++), TarotType.CONG_VIEC);
                    // thêm thẻ vào danh sách
                    cards.add(card);
                }catch (Exception e){
                    break;
                }
            }
        }
        // đọc sheet tài vận
        worksheet = workbook.getSheetAt(1);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            var row = worksheet.getRow(i);
            if(row!=null){

                try {
                    if (row.getCell(0).toString().isEmpty()) {
                        break;
                    }
                    // khởi tạo thẻ
                    CardTarot card = new CardTarot(worksheet.getRow(i), pictures.get(currentPictureIndex++), TarotType.TAI_VAN);
                    // thêm thẻ vào danh sách
                    cards.add(card);
                }catch (Exception e){
                    break;
                }
            }
        }
        // đọc sheet tình yêu
        worksheet = workbook.getSheetAt(2);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            var row = worksheet.getRow(i);
            if(row!=null){

                try {
                    if (row.getCell(0).toString().isEmpty()) {
                        break;
                    }
                    // khởi tạo thẻ
                    CardTarot card = new CardTarot(worksheet.getRow(i), pictures.get(currentPictureIndex++), TarotType.TINH_YEU);
                    // thêm thẻ vào danh sách
                    cards.add(card);
                }catch (Exception e){
                    break;
                }
            }
        }
        // đọc file excel xong
        System.out.println("Read excel file successfully!");
    }
    // đọc tất cả ảnh trong file excel
    private List<String> readAllImg(@NotNull XSSFWorkbook workbook) throws IOException {
        List<XSSFPictureData> lst = workbook.getAllPictures();
        List<String> pictures = new ArrayList<>();
        for (XSSFPictureData pic : lst) {
            byte[] data = pic.getData();
            // chuyển ảnh sang base64
            String base64 = Base64Utils.encodeToString(data);
            // thêm ảnh vào danh sách
            pictures.add(base64);
        }
        return pictures;
    }

    public List<CardTarot> getCards() {
        return cards;
    }
}
