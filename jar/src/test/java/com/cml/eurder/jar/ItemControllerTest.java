package com.cml.eurder.jar;

import com.cml.eurder.service.item.CreateItemDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.cml.eurder.domain.item.Currencies.EURO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllItemsTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("[]", result.getResponse().getContentAsString());
    }


    @WithMockUser(authorities = {"ADD_ITEM"})
    @Test
    void createItemTest() throws Exception {
        CreateItemDto createItemDto = new CreateItemDto("Smartphone", "description", 10, 500, EURO);
        mvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createItemDto))
                    )
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"id\":1,\"name\":\"Smartphone\",\"description\":\"description\",\"stockAmount\":10,\"price\":500,\"currency\":\"EURO\",\"shippingDate\":\"2020-04-26\",\"inStock\":true}"));
    }

    @WithMockUser(authorities = {"ADD_ITEM", "UPDATE_ITEM"})
    @Test
    void updateItemTest() throws Exception {
        CreateItemDto createItemDto = new CreateItemDto("Smartphone", "description", 10, 500, EURO);
        CreateItemDto updateItemDto = new CreateItemDto("Smartphone", "UPDATED", 10, 500, EURO);

        mvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createItemDto))
        );
        long id  = 1;
        mvc.perform(put("/items/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateItemDto))
        ).andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Smartphone\",\"description\":\"UPDATED\",\"stockAmount\":10,\"price\":500,\"currency\":\"EURO\",\"shippingDate\":\"2020-04-26\",\"inStock\":true}"));
    }

    @WithMockUser(authorities = {"ADD_ITEM", "DELETE_ITEM"})
    @Test
    void deleteItemTest() throws Exception {
        CreateItemDto createItemDto = new CreateItemDto("Smartphone", "description", 10, 500, EURO);
        mvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createItemDto))
        );
        long id  = 1;

        mvc.perform(delete("/items/" + id))
                .andExpect(status().isOk());

        mvc.perform(get("/items"))
                .andExpect(content().string("[]"));
    }

}