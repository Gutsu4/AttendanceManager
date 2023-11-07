package com.example.workManagement.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PathControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldAuthenticateWithCustomAuthentication() throws Exception {
        mockMvc.perform(formLogin("/login").user("12").password("user"))
                .andExpect(status().is3xxRedirection()) // ログイン後にリダイレクトされることを期待します
                .andExpect(authenticated().withUsername("12")); // カスタムユーザー名で認証されたことを確認します
    }

    @Test
    void logout() throws Exception{
        mockMvc.perform(get("/logout"))
                .andExpect(status().is(200));
    }

    @Test
    void mainmenu() throws Exception{
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("useId",12);
        mockMvc.perform(post("/user/mainmenu"));
    }

    @Test
    void attendance_in() throws Exception{
        mockMvc.perform(get("/user/attendance_in"))
                .andExpect(status().is(200));
    }

    @Test
    void workplace_regist() throws Exception{
        mockMvc.perform(get("/user/workplace_regist"))
                .andExpect(status().is(200));
    }

    @Test
    void contact_regist() throws Exception{
        mockMvc.perform(get("/user/contact_regist"))
                .andExpect(status().is(200));
    }

    @Test
    void attendance_all_view() throws Exception{
        mockMvc.perform(get("/user/attendance_all_view"))
                .andExpect(status().is(200));
    }

    @Test
    void attendance_history_view() throws Exception{
        mockMvc.perform(get("/user/attendance_history_view"))
                .andExpect(status().is(200));
    }

    @Test
    void adminMainMenu() throws Exception{
        mockMvc.perform(get("/admin/admin_menu"))
                .andExpect(status().is(200));
    }

    @Test
    void admin_user_manager() throws Exception{
        mockMvc.perform(get("/admin/admin_user_manager"))
                .andExpect(status().is(200));
    }

    @Test
    void admin_workplace_manager() throws Exception{
        mockMvc.perform(get("/admin/admin_workplace_manager"))
                .andExpect(status().is(200));
    }

    @Test
    void admin_attendance_total_hours() throws Exception{
        mockMvc.perform(get("/admin/admin_attendance_total_hours"))
                .andExpect(status().is(200));
    }
}