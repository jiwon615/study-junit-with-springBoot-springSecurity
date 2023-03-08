package shop.mtcoding.bank.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc // Mock(가짜) 환경에 MockMvc가 등록됨
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class SecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void authentication_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/api/s/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int httpStatusCode = resultActions.andReturn().getResponse().getStatus();
        log.info("테스트: " + responseBody);
        log.info("테스트: " + httpStatusCode);

        // then
        assertThat(httpStatusCode).isEqualTo(401);
    }

    @Test
    public void authorization_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/api/admin/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int httpStatusCode = resultActions.andReturn().getResponse().getStatus();
        log.info("테스트: " + responseBody);
        log.info("테스트: " + httpStatusCode);

        // then
        assertThat(httpStatusCode).isEqualTo(401);
    }
}