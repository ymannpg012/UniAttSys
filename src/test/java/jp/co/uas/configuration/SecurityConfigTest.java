
package jp.co.uas.configuration;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void unauthenticatedUserCannotAccessDashboard()
            throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void authenticatedUserCanAccessDashboard() throws Exception {
    	var result = mockMvc.perform(formLogin()
                .user("test")
                .password("testtest"))
                .andExpect(authenticated())
                .andReturn();

        mockMvc.perform(get("/dashboard")
                .session((org.springframework.mock.web.MockHttpSession)
                        result.getRequest().getSession(false)))
                .andExpect(status().isOk());
    }

}
