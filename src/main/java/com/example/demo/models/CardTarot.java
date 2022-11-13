package com.example.demo.models;

import com.example.demo.enums.TarotType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.xssf.usermodel.XSSFRow;


@Getter
@Setter
public class CardTarot {

    public CardTarot(XSSFRow row, String base64,TarotType type) {
        id = (int) row.getCell(0).getNumericCellValue();
        name = row.getCell(2).getStringCellValue();
        description = row.getCell(3).getStringCellValue();
        this.img = base64;
        this.type = type;

    }
    // id thẻ
    private int id;
    // base 64 của ảnh
    private String img;
    // tên thẻ
    private String name;
    // mô tả thẻ
    private String description;
    // loại thẻ
    @JsonIgnore
    private TarotType type;

    // kiểm tra xem thẻ có phải là Công việc hay không
    @JsonIgnore
    public boolean isCongViec() {
        return type == TarotType.CONG_VIEC;
    }
    // kiểm tra xem thẻ có phải là Tài vận hay không
    @JsonIgnore
    public boolean isTaiVan() {
        return type == TarotType.TAI_VAN;
    }

    // kiểm tra xem thẻ có phải là Tình yêu hay không
    @JsonIgnore
    public boolean isTinhYeu() {
        return type == TarotType.TINH_YEU;
    }
}
