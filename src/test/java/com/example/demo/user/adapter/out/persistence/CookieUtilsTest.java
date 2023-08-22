package com.example.demo.user.adapter.out.persistence;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.Serializable;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Cookie Util 테스트")
@ExtendWith(MockitoExtension.class)
class CookieUtilsTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @DisplayName("쿠키 조회 테스트")
    @Test
    void getCookie_test() {
        // Given
        Cookie[] cookies = {new Cookie("cookieName", "cookieValue")};
        when(request.getCookies()).thenReturn(cookies);

        // When
        Optional<Cookie> result = CookieUtils.getCookie(request, "cookieName");

        // Then
        assertTrue(result.isPresent());
        assertEquals("cookieValue", result.get().getValue());
    }
    @Test
    void zxc(){
        Cookie[] cookies = new Cookie[]{};
        for (Cookie cookie : cookies){
            System.out.println(1);
        }
    }

    @DisplayName("쿠키 없는 조회 테스트")
    @Test
    void getCookie_isEmpty_test() {
        // Given
        when(request.getCookies()).thenReturn(null);
        // When
        Optional<Cookie> result = CookieUtils.getCookie(request, "cookieName");
        //Then
        assertFalse(result.isPresent());
    }

    @DisplayName("쿠키 추가 테스트")
    @Test
    void addCookie_test() {
        CookieUtils.addCookie(response, "cookieName", "cookieValue", 3600);

        verify(response).addCookie(any(Cookie.class));
    }

    @DisplayName("쿠키 삭제 테스트")
    @Test
    void deleteCookie_test() {
        Cookie[] cookies = {new Cookie("cookieName", "cookieValue")};
        when(request.getCookies()).thenReturn(cookies);

        CookieUtils.deleteCookie(request, response, "cookieName");

        verify(response).addCookie(any(Cookie.class));
    }

    @DisplayName("직렬화, 역직렬화 테스트")
    @Test
    void serializeAndDeserialize_test() {
        SomeObject obj = new SomeObject("value");
        Cookie cookie = new Cookie("cookieName", CookieUtils.serialize(obj));

        SomeObject deserialized = CookieUtils.deserialize(cookie, SomeObject.class);

        assertNotNull(deserialized);
        assertEquals("value", deserialized.getValue());
    }

    private static class SomeObject implements Serializable {
        private String value;

        public SomeObject(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
