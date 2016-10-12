package com.emaciejko.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Unit test for MainController
 * @author Eva
 *
 */
public class MainControllerTest {
    
    private MockMvc mockMvc;

    private MainController mainController;

    @Before
    public void setup(){
        mainController = new MainController();
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();

    }

    @Test
    public void testIndex() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("view/main"));
    }


}
