package br.com.reclameaqui.complainsapi.cucumber.stepdefs;

import br.com.reclameaqui.complainsapi.ComplainsApiApplication;
import br.com.reclameaqui.complainsapi.cucumber.assembler.ContextAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

import static br.com.reclameaqui.complainsapi.util.JsonConverter.asJsonToClass;
import static br.com.reclameaqui.complainsapi.util.TestUtil.convertObjectToJsonBytes;
import static br.com.reclameaqui.complainsapi.util.TestUtil.executarBlocoComValorDefault;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.util.Lists.newArrayList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest(properties = "spring.datasource.type=com.zaxxer.hikari.HikariDataSource")
@AutoConfigureMockMvc
@WebAppConfiguration
@ContextConfiguration(classes = ComplainsApiApplication.class)
public abstract class StepDefs {

    @Autowired
    protected ContextAssembler contextAssembler;

    @Autowired
    private MockMvc restMockMvc;

    protected ResultActions actions;

    private ResultActions perform(Object content, MockHttpServletRequestBuilder request) throws Exception {
        return restMockMvc.perform(request.content(convertObjectToJsonBytes(content))
            .header("Origin","*")
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON));
    }

    private ResultActions performGet(String url, MediaType contentType, MediaType accept, Object... uriVars) throws Exception {
        return restMockMvc.perform(MockMvcRequestBuilders.get(url, uriVars)
                            .header("Origin","*")
                            .contentType(contentType)
                            .accept(accept));
    }

    protected ResultActions mockGet(String url, Object... uriVars) throws Exception {
        return performGet(url, APPLICATION_JSON, APPLICATION_JSON, uriVars);
    }

    protected void mockPost(String url, Object content, Object... urlVars) throws Exception {
        actions = perform(content, post(url, urlVars));
    }

    protected void mockPut(String url, Object content, Object... urlVars) throws Exception {
        actions = perform(content, put(url, urlVars));
    }

    protected List<String> obterMensagensDeErroRetornada() {
        Map erroRetornado = obterObjetoRetornado(Map.class);
        List<Map<String, String>> fieldErrors = (List) erroRetornado.get("fieldErrors");
        return getMenssageError(erroRetornado, fieldErrors, "message");
    }

    protected List<String> obterMensagensDeErroRetornadaNoDetail() {
        Map erroRetornado = obterObjetoRetornado(Map.class);
        List<Map<String, String>> fieldErrors = (List) erroRetornado.get("fieldErrors");
        return getMenssageError(erroRetornado, fieldErrors, "detail");
    }

    private List<String> getMenssageError(Map erroRetornado, List<Map<String, String>> fieldErrors, String field) {
        if (fieldErrors != null) {
            return fieldErrors.stream().map(f -> f.get(field)).collect(toList());
        }

        String mensagem = (String) erroRetornado.get(field);
        return newArrayList(mensagem);
    }

    protected <T> T obterObjetoRetornado(Class<T> classe) {
        return executarBlocoComValorDefault(() -> asJsonToClass(obterResposta().getContentAsString(), classe), null);
    }

    protected MockHttpServletResponse obterResposta() {
        MockHttpServletResponse resposta = actions.andReturn().getResponse();
        resposta.setCharacterEncoding("UTF-8");
        return resposta;
    }
}
