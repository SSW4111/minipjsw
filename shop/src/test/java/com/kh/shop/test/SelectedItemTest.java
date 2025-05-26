package com.kh.shop.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SelectedItemTest {
//
    @Autowired
    private MockMvc mockMvc; //가상처리객체
    //카트
//    @Test
//    void test() throws Exception {    
//        mockMvc.perform(get("/cart/selectList") //가상처리 겟요청
//            .param("cartNoList", "3") //파라미터
//            .sessionAttr("usersEmail", "oo54941@gmail.com")) //세션
//            .andDo(print()) //프린트 콘솔출력
//            .andExpect(status().isOk()) //200인지
//            .andExpect(model().attributeExists("selectedItemVO")); //바인딩테스트
//    }
    //단건
    @Test
    void testItemDetailSelect() throws Exception {
        mockMvc.perform(get("/order/selectList") 
                .param("itemNo", "1")    
                .param("itemQty", "2")
                .param("itemIoNo", "21")
                .sessionAttr("usersEmail", "oo54941@gmail.com"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("itemDetailSelectVO")); 

    }
}
