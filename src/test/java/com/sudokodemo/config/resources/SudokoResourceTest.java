package com.sudokodemo.config.resources;

import com.sudokodemo.config.TestContext;
import com.sudokodemo.util.TestUtil;
import com.sudokodemo.config.api.SudokoService;
import com.sudokodemo.config.dto.BasicSudokoDTO;
import com.sudokodemo.config.dto.SudokoDTO;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 *
 * @author SARAT
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MockServletContext.class, TestContext.class})
@WebAppConfiguration
public class SudokoResourceTest {

    private MockMvc mvc;
    private final String URL = "/sudoko/rest/api";

    @Mock
    SudokoService service;

    @Autowired
    MessageSource messageSource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(new SudokoResource(messageSource, service)).build();

    }

    @Test
    public void getIndex() throws Exception {
        mvc.perform(get(URL).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello Sudoko Application")));
    }

    @Test
    public void validateSudokoBoard() throws Exception {
        String sudokoBoardAsString = "..5....3....9...8....57.....9.7....3.7.13..5.3.2......2...8......1..94259....78..";
        BasicSudokoDTO inputSudokoDTO = new BasicSudokoDTO();
        inputSudokoDTO.setSudokoBoardAsString(sudokoBoardAsString);

        BasicSudokoDTO outputSudokoDTO = new BasicSudokoDTO();
        outputSudokoDTO.setSudokoBoardAsString(sudokoBoardAsString);
        outputSudokoDTO.setIsValidBoard(Boolean.TRUE);

        when(service.createSudokoBoard(any(BasicSudokoDTO.class))).thenReturn(outputSudokoDTO);

        mvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(inputSudokoDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.sudokoBoardAsString", is(sudokoBoardAsString)))
                .andExpect(jsonPath("$.isValidBoard", is(Boolean.TRUE)));

        ArgumentCaptor<BasicSudokoDTO> dtoCaptor = ArgumentCaptor.forClass(BasicSudokoDTO.class);
        verify(service, times(1)).createSudokoBoard(dtoCaptor.capture());
        verifyNoMoreInteractions(service);

    }

    @Test
    public void solveSudoko() throws Exception {

        String solutionSudokoBoardAsString = "913257648468391752527486931356724189742918563189635427875162394231549876694873215";
        BasicSudokoDTO outputSudokoDTO = new BasicSudokoDTO();
        outputSudokoDTO.setSudokoBoadSolutionString(solutionSudokoBoardAsString);
        outputSudokoDTO.setSolutionExists(Boolean.TRUE);
        outputSudokoDTO.setId(1);

        when(service.solveSudoko(1)).thenReturn(outputSudokoDTO);

        mvc.perform(get(URL.concat("/solve/1")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.sudokoBoadSolutionString", is(solutionSudokoBoardAsString)))
                .andExpect(jsonPath("$.solutionExists", is(Boolean.TRUE)))
                .andExpect(jsonPath("$.id", is(1)));

    }

    @Test
    public void successiveMoves() throws Exception {
        SudokoDTO sudokoDTO = new SudokoDTO();
        sudokoDTO.setRow(0);
        sudokoDTO.setColumn(1);
        sudokoDTO.setCellValue(1);

        SudokoDTO outputSudokoDTO = new SudokoDTO();
        outputSudokoDTO.setRow(0);
        outputSudokoDTO.setColumn(1);
        outputSudokoDTO.setCellValue(1);
        outputSudokoDTO.setIsSudokoFinished(Boolean.TRUE);
        outputSudokoDTO.setIsValidMove(Boolean.TRUE);
        when(service.successiveMoves(any(SudokoDTO.class))).thenReturn(outputSudokoDTO);
        mvc.perform(post(URL.concat("/successiveMoves")).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sudokoDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.isValidMove", is(Boolean.TRUE)))
                .andExpect(jsonPath("$.row", is(0)))
                .andExpect(jsonPath("$.column", is(1)))
                .andExpect(jsonPath("$.cellValue", is(1)));
    }

    @Test
    public void validateSudokoBoardInvalidSudokoBoardAsString() throws Exception {
        String sudokoBoardAsString = "..5....3....9...8....57.....9.7....3.7.13..5.3.2......2...8......1..94259....78.X";
        BasicSudokoDTO inputSudokoDTO = new BasicSudokoDTO();
        inputSudokoDTO.setSudokoBoardAsString(sudokoBoardAsString);

        mvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(inputSudokoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
                .andExpect(jsonPath("$.fieldErrors[0].field", is("sudokoBoardAsString")))
                .andExpect(jsonPath("$.fieldErrors[0].message", is(
                                        "Allowed Numbers 1 to 9 or Dot and length must me 81"
                                )));
        verifyZeroInteractions(service);
    }

   
    @Test
    public void successiveMovesInvalidData() throws Exception {
        SudokoDTO sudokoDTO = new SudokoDTO();
        sudokoDTO.setId(1);

        mvc.perform(post(URL.concat("/successiveMoves")).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sudokoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.fieldErrors", hasSize(3)))
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder(
                                        "row", "cellValue", "column")))
                .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder(
                                        "Row can not be blank",
                                        "Cell Value can not be blank", "Column can not be blank"
                                )));
        verifyZeroInteractions(service);
    }

    @Test
    public void successiveMovesOutOfRangeRowColumnCellValue() throws Exception {
        SudokoDTO sudokoDTO = new SudokoDTO();
        sudokoDTO.setRow(10);
        sudokoDTO.setColumn(11);
        sudokoDTO.setCellValue(11);

        mvc.perform(post(URL.concat("/successiveMoves")).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sudokoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.fieldErrors", hasSize(3)))
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder(
                                        "row", "cellValue", "column")))
                .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder(
                                        "Row must between 0 and 8", "Column must between 0 and 8",
                                        "Cell Value must between 0 and 8"
                                )));
        verifyZeroInteractions(service);
    }
}
