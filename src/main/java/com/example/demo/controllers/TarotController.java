package com.example.demo.controllers;


import com.example.demo.enums.TarotType;
import com.example.demo.models.CardTarot;
import com.example.demo.services.ExcelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/tarot")
@AllArgsConstructor
public class TarotController {

    final ExcelService excelService;

    @GetMapping("/{type}")
    @CrossOrigin(origins = "*")
    public Optional<CardTarot> getTarot (@PathVariable("type") TarotType type) {
        if (type == TarotType.CONG_VIEC) {
            // lấy số lượng tổng của tarot công việc
            var COUNT =  excelService.getCards().stream().filter(CardTarot::isCongViec).count();
            // tìm các thẻ tài vận và lấy ngẫu nhiên 1 thẻ
            return excelService.getCards().stream().filter(CardTarot::isCongViec).skip((int)(COUNT * Math.random())).findFirst();
        }
        if (type == TarotType.TAI_VAN) {
            // lấy số lượng tổng của tarot tài vận
            var COUNT =  excelService.getCards().stream().filter(CardTarot::isTaiVan).count();
            // tìm các thẻ tài vận và lấy ngẫu nhiên 1 thẻ
            return excelService.getCards().stream().filter(CardTarot::isTaiVan).skip((int)(COUNT * Math.random())).findFirst();
        }
        if (type == TarotType.TINH_YEU) {
            // lấy số lượng tổng của tarot tinh yêu
            var COUNT =  excelService.getCards().stream().filter(CardTarot::isTinhYeu).count();
            // tìm các thẻ tài vận và lấy ngẫu nhiên 1 thẻ
            return excelService.getCards().stream().filter(CardTarot::isTinhYeu).skip((int)(COUNT * Math.random())).findFirst();
        }
        return null;
    }
}
