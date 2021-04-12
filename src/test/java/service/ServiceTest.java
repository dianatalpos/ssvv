package service;

import domain.Tema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @Mock
    private StudentXMLRepository studentXmlRepo;
    @Mock
    private StudentValidator studentValidator;
    @Mock
    private NotaXMLRepository notaXmlRepo;
    @Mock
    private NotaValidator notaValidator;
    @Mock
    private TemaXMLRepository temaXmlRepo;
    @Mock
    private TemaValidator temaValidator;
    private Service service;

    @Before
    public void setup(){
        service = new Service(studentXmlRepo, temaXmlRepo, notaXmlRepo);
    }

    @Test
    public void testSaveTemaIfFails(){
        Mockito.when(temaXmlRepo.save(any())).thenReturn(null);

        int response = this.service.saveTema(1, "nume", 5, 2);

        Assert.assertEquals(1, response);
    }

    @Test
    public void testSaveTema(){
        Tema tema = Mockito.mock(Tema.class);
        Mockito.when(temaXmlRepo.save(any())).thenReturn(tema);

        int response = this.service.saveTema(2, "dscds", 1, 6);

        Assert.assertEquals(0, response);
    }

    @Test
    public void testSaveTemaIfIdInvalid(){
        Mockito.when(temaXmlRepo.save(any())).thenReturn(null);

        int response = this.service.saveTema(-1, "description", 5, 2);

        Assert.assertEquals(1, response);
    }

    @Test
    public void testSaveTemaIfDescriptionInvalid(){
        Mockito.when(temaXmlRepo.save(any())).thenReturn(null);

        int response = this.service.saveTema(3, "", 5, 2);

        Assert.assertEquals(1, response);
    }

    @Test
    public void testSaveTemaIfDeadlineAndStartlineInvalid(){
        Mockito.when(temaXmlRepo.save(any())).thenReturn(null);

        int response = this.service.saveTema(4, "description", 4, 5);

        Assert.assertEquals(1, response);
    }

    @Test
    public void testSaveTemaIfStartlineInvalid(){
        Mockito.when(temaXmlRepo.save(any())).thenReturn(null);

        int response = this.service.saveTema(4, "description", 4, 0);

        Assert.assertEquals(1, response);
    }

    @Test
    public void testSaveTemaIfDeadlineInvalid(){
        Mockito.when(temaXmlRepo.save(any())).thenReturn(null);

        int response = this.service.saveTema(4, "description", 15, 2);

        Assert.assertEquals(1, response);
    }
}
