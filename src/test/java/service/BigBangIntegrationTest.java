package service;

import domain.Nota;
import domain.Pair;
import domain.Student;
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
public class BigBangIntegrationTest {

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
    public void testSaveTema(){
        Tema tema = Mockito.mock(Tema.class);
        Mockito.when(temaXmlRepo.save(any())).thenReturn(tema);

        int response = this.service.saveTema(2, "dscds", 1, 6);

        Assert.assertEquals(0, response);
    }

    @Test
    public void testSaveStudent() {
        Student student = Mockito.mock(Student.class);
        Mockito.when(studentXmlRepo.save(any())).thenReturn(student);

        int response = this.service.saveStudent(1, "nume", 937);

        Assert.assertEquals(0, response);
    }

    @Test
    public void testSaveNota() {

        Mockito.when(studentXmlRepo.findOne(any())).thenReturn(Mockito.mock(Student.class));
        Mockito.when(temaXmlRepo.findOne(any())).thenReturn(Mockito.mock(Tema.class));
        Mockito.when(temaXmlRepo.findOne(any()).getDeadline()).thenReturn(4);
        Mockito.when(notaXmlRepo.save(any())).thenReturn(Mockito.mock(Nota.class));

        int response = service.saveNota(1, 1, 10, 3, "no feedback");

        Assert.assertEquals(0, response);
    }

    @Test
    public void testIntegration() {
        Tema tema = new Tema(2, "dscds", 1, 6);
        Mockito.when(temaXmlRepo.save(any())).thenReturn(tema);
        this.service.saveTema(2, "dscds", 1, 6);
        Mockito.verify(temaXmlRepo).save(tema);

        Student student = new Student(1, "nume", 937);
        Mockito.when(studentXmlRepo.save(any())).thenReturn(student);
        this.service.saveStudent(1, "nume", 937);
        Mockito.verify(studentXmlRepo).save(student);

        Nota nota = new Nota(new Pair<>(1, 1), 10, 3, "no feedback");
        Mockito.when(studentXmlRepo.findOne(any())).thenReturn(student);
        Mockito.when(temaXmlRepo.findOne(any())).thenReturn(tema);
        Mockito.when(notaXmlRepo.save(any())).thenReturn(nota);

        int response = service.saveNota(1, 1, 10, 3, "no feedback");

        Assert.assertEquals(0, response);
    }

}
