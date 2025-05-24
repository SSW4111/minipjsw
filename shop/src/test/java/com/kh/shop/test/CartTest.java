package com.kh.shop.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class CartTest {

    @Autowired
    private MockMvc mockMvc;   //테스트에서 HTTP 요청 보내는 역할.

    private MockHttpSession session;   //가짜세션

    private ObjectMapper objectMapper = new ObjectMapper(); //json  

    @BeforeEach //테스트전에 실행
     public void setup() {
        session = new MockHttpSession();
        session.setAttribute("usersEmail", "oo54941@gmail.com"); 
    }

//    @Test
//    public void add() throws Exception {
//        CartDto cartDto = new CartDto();
//        cartDto.setItemNo(1);
//        cartDto.setItemIoNo(21);
//        cartDto.setCartQty(5);
//
//        mockMvc.perform(post("/rest/cart/add")
//                .session(session)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(cartDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("ok"));
//    }
//    
    @Test
    public void list() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersEmail", "oo54941@gmail.com");

        mockMvc.perform(get("/rest/cart/list")
                .session(session)
                .param("page", "1")      
                .param("size", "10"))    
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray()) 
            .andDo(result -> {
                String json = result.getResponse().getContentAsString();
                System.out.println("응답 JSON: " + json);
            });
    }

//    @Test
//    public void update() throws Exception {
//        mockMvc.perform(post("/rest/cart/update")
//                .param("cartQty", "3")   
//                .param("cartNo", "2"))   
//            .andExpect(status().isOk())
//            .andExpect(content().string("ok"));
//    }

    
//    @Test
//    public void delete() throws Exception {
//        // 세션 설정
//        MockHttpSession session = new MockHttpSession();
//        session.setAttribute("usersEmail", "oo54941@gmail.com");
//
//        mockMvc.perform(post("/rest/cart/delete")
//                .session(session)
//                .param("cartNo", "2"))  
//            .andExpect(status().isOk())
//            .andExpect(content().string("ok"));
//    }

}
