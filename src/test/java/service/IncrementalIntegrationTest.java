package service;

import domain.Nota;
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
public class IncrementalIntegrationTest {

    private static final int ID = 1;
    private static final int VAL_NOTA = 10;
    private static final int GRUPA = 937;
    private static final int STARTLINE = 6;
    private static final String NUME = "nume";
    private static final String DESCRIERE = "dscds";
    private static final String FEEDBACK = "no feedback";
    private static final int SAPT_PREDARE = 3;
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

    @Mock
    Tema tema;

    @Mock
    Student student;

    @Mock
    Nota nota;

    @Before
    public void setup(){
        service = new Service(studentXmlRepo, temaXmlRepo, notaXmlRepo);
        Mockito.when(temaXmlRepo.save(any())).thenReturn(tema);
        Mockito.when(studentXmlRepo.save(any())).thenReturn(student);
        Mockito.when(notaXmlRepo.save(any())).thenReturn(nota);
        Mockito.when(studentXmlRepo.findOne(any())).thenReturn(student);
        Mockito.when(temaXmlRepo.findOne(any())).thenReturn(tema);
    }

    @Test
    public void testAddStudent(){
        int response = this.service.saveStudent(ID, NUME, GRUPA);
        Assert.assertEquals(0, response);

    }

    @Test
    public void testAddStudentAndAssignment(){
        int response = this.service.saveStudent(ID, NUME, GRUPA);
        Assert.assertEquals(0, response);

        response = this.service.saveTema(ID, DESCRIERE, ID, STARTLINE);
        Assert.assertEquals(0, response);
    }

    @Test
    public void testAddStudentAndAssignmentAndGrade(){
        int response = this.service.saveStudent(ID, NUME, GRUPA);
        Assert.assertEquals(0, response);

        response = this.service.saveTema(ID, DESCRIERE, ID, STARTLINE);
        Assert.assertEquals(0, response);

        response = service.saveNota(ID, ID, VAL_NOTA, SAPT_PREDARE, FEEDBACK);
        Assert.assertEquals(0, response);
    }
}
