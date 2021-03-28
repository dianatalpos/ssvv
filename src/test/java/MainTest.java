import console.UI;
import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;
import validation.ValidationException;

import static org.junit.Assert.*;


public class MainTest {

    Validator<Student> studentValidator;
    Validator<Tema> temaValidator;
    Validator<Nota> notaValidator;
    StudentXMLRepository fileRepository1;
    TemaXMLRepository fileRepository2;
    NotaXMLRepository fileRepository3;
    Service service;

    @Before
    public void setUp() throws Exception {
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();
        fileRepository1 = new StudentXMLRepository(studentValidator, "studentitest.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "temetest.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "notatest.xml");
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @After
    public void tearDown() {
        studentValidator = null;
        temaValidator = null;
        notaValidator = null;
        fileRepository1 = null;
        fileRepository2 = null;
        fileRepository3 = null;
        service = null;

        System.gc();
    }

    @Test
    public void createStudentSuccessfully() {
        assertEquals(1, service.saveStudent(4, "Name", 937));
        service.deleteStudent(4);
    }

    @Test
    public void createStudentFails() {
        assertEquals(0, service.saveStudent(1, "Name", 937));
    }
}
