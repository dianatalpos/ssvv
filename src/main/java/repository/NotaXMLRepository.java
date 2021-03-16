package repository;

import domain.Nota;
import domain.Pair;
import domain.Student;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NotaXMLRepository extends AbstractXMLRepository<Pair<Integer, Integer>, Nota> {

    public NotaXMLRepository(Validator<Nota> validator, String XMLfilename) {
        super(validator, XMLfilename);
        loadFromXmlFile();
    }

    protected Element getElementFromEntity(Nota nota, Document XMLdocument) {
        Element element = XMLdocument.createElement("nota");
        element.setAttribute("IDStudent", nota.getID().getObject1().toString());
        element.setAttribute("IDTema", nota.getID().getObject2().toString());

        element.appendChild(createElement(XMLdocument, "Nota", String.valueOf(nota.getNota())));
        element.appendChild(createElement(XMLdocument, "SaptamanaPredare", String.valueOf(nota.getSaptamanaPredare())));
        element.appendChild(createElement(XMLdocument, "Feedback", nota.getFeedback()));

        return element;
    }

    protected Nota getEntityFromNode(Element node) {
        String IDStudent = node.getAttributeNode("IDStudent").getValue();
        String IDTema= node.getAttributeNode("IDTema").getValue();
        double nota = Double.parseDouble(node.getElementsByTagName("Nota").item(0).getTextContent());
        int saptamanaPredare = Integer.parseInt(node.getElementsByTagName("SaptamanaPredare").item(0).getTextContent());
        String feedback = node.getElementsByTagName("Feedback").item(0).getTextContent();

        return new Nota(new Pair(IDStudent, IDTema), nota, saptamanaPredare, feedback);
    }

    public void createFile(Nota notaObj) {
        Integer idStudent = notaObj.getID().getObject1();
        StudentValidator sval = new StudentValidator();
        TemaValidator tval = new TemaValidator();
        StudentFileRepository srepo = new StudentFileRepository(sval, "studenti.txt");
        TemaFileRepository trepo = new TemaFileRepository(tval, "teme.txt");

        Student student = srepo.findOne(idStudent);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(student.getNume() + ".txt", false))) {
            super.findAll().forEach(nota -> {
                if (nota.getID().getObject1().equals(idStudent)) {
                    try {
                        bw.write("Tema: " + nota.getID().getObject2() + "\n");
                        bw.write("Nota: " + nota.getNota() + "\n");
                        bw.write("Predata in saptamana: " + nota.getSaptamanaPredare() + "\n");
                        bw.write("Deadline: " + trepo.findOne(nota.getID().getObject2()).getDeadline() + "\n");
                        bw.write("Feedback: " + nota.getFeedback() + "\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
