package com.example.SpringSample;

import com.example.SpringSample.recipe.service.login.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    /*
      【12-4-2】@WithMockUser
     @WithMockUserを使用するとログインした時のみ表示可能な画面のテストができます。
     @WithMockUser(username="satou" ,roles={"ROLE_ADMIN"})
    */
    @Test
    @WithMockUser
    public void ユーザーリスト画面のユーザー件数のテスト() throws Exception {

        // 【12-4-2】@MockBean でメソッドの任意の戻り値を設定可能
        // UserServiceのcountメソッドの戻り値を10に設定
        when(service.count()).thenReturn(10);

        // ユーザーリスト画面のチェック
        mockMvc.perform(get("/userList"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("合計：10件")));
    }
}
