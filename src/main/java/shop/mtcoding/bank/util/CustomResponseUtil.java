package shop.mtcoding.bank.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.bank.dto.ResponseDto;

import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CustomResponseUtil {

    public static void unAuthentication(HttpServletResponse response, String msg) {

        try {
            ObjectMapper om = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto(-1, msg, null);
            String responseBody = om.writeValueAsString(responseDto);
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(401);
            response.getWriter().println(responseBody);
        } catch (Exception e) {
            log.error("서버 파싱 에러");
        }
    }
}
