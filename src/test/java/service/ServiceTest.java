package service;

import domain.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

public class ServiceTest {

    private StudentXMLRepository studentXmlRepo;
    private StudentValidator studentValidator;
    private NotaXMLRepository notaXmlRepo;
    private NotaValidator notaValidator;
    private TemaXMLRepository temaXmlRepo;
    private TemaValidator temaValidator;
    private Service service;

    @Before
    public void setup(){
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();

        studentXmlRepo = new StudentXMLRepository(studentValidator, "studentitest.xml");
        temaXmlRepo = new TemaXMLRepository(temaValidator, "temetest.xml");
        notaXmlRepo = new NotaXMLRepository(notaValidator, "notatest.xml");
        service = new Service(studentXmlRepo, temaXmlRepo, notaXmlRepo);
    }

    @Test
    public void testSaveStudent(){
        this.service.deleteStudent(1);

        int response = this.service.saveStudent(1, "nume", 935);

        Assert.assertEquals(1, response);
    }

    @Test
    public void testSaveStudentIfFails(){
        int response = this.service.saveStudent(-1, "", 1);

        Assert.assertEquals(1, response);
    }
}
