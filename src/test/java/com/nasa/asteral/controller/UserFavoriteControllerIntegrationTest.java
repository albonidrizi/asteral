package com.nasa.asteral.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserFavoriteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "testuser", authorities = { "USER" })
    void getFavoriteAsteroidsView_ShouldReturnMyFavoritesView() throws Exception {
        mockMvc.perform(get("/user/favorite/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("myFavorites"))
                .andExpect(model().attributeExists("favoriteAsteroids"));
    }

    @Test
    @WithMockUser(username = "testuser", authorities = { "USER" })
    void addAsteroidToFavorite_ShouldRedirectHome() throws Exception {
        mockMvc.perform(get("/user/favorite/add/12345"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
