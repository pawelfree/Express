package pl.bsb.elixir.express.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBMarshaller {

    private JAXBContext context;
    private Marshaller marshaller = null;
    private Unmarshaller unmarshaller = null;

    private JAXBMarshaller(String classNameWithPackage) {
        try {
            context = JAXBContext.newInstance(classNameWithPackage);
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBMarshaller.class.getName()).log(Level.SEVERE, "Can't create JAXB context.", ex);
        }
    }

    public static Marshaller getMarshaller(String classNameWithPackage) {
        JAXBMarshaller jaxbMarshaller = new JAXBMarshaller(classNameWithPackage);
        try {
            jaxbMarshaller.marshaller = jaxbMarshaller.context.createMarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBMarshaller.class.getName()).log(Level.SEVERE, "Can't create JAXB Marshaller.", ex);
        }
        return jaxbMarshaller.marshaller;
    }

    public static Unmarshaller getUnmarshaller(String classNameWithPackage) {
        JAXBMarshaller jaxbMarshaller = new JAXBMarshaller(classNameWithPackage);
        try {
            jaxbMarshaller.unmarshaller = jaxbMarshaller.context.createUnmarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBMarshaller.class.getName()).log(Level.SEVERE, "Can't create JAXB Unmarshaller.", ex);
        }
        return jaxbMarshaller.unmarshaller;
    }
    
    public static iso.std.iso._20022.tech.xsd.pacs_008_001.Document unmarshallSendTransferFromBase64(String toUnmarshall) {
        iso.std.iso._20022.tech.xsd.pacs_008_001.Document sendTransferDocument = null;
        try {
            sendTransferDocument = (iso.std.iso._20022.tech.xsd.pacs_008_001.Document) getUnmarshaller("iso.std.iso._20022.tech.xsd.pacs_008_001").unmarshal(new ByteArrayInputStream(Base64.decode(toUnmarshall)));
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBMarshaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sendTransferDocument;
    }
    
    public static String marshallToBase64(Object toMarshall) {
        String response = "";
        String packageName;

        if (toMarshall instanceof iso.std.iso._20022.tech.xsd.pacs_002_001.Document) {
            packageName = "iso.std.iso._20022.tech.xsd.pacs_002_001";
        } else if (toMarshall instanceof iso.std.iso._20022.tech.xsd.pacs_008_001.Document) {
            packageName = "iso.std.iso._20022.tech.xsd.pacs_008_001";
        } else if (toMarshall instanceof iso.std.iso._20022.tech.xsd.pacs_004_001.Document) {
            packageName = "iso.std.iso._20022.tech.xsd.pacs_004_001";
        } else {
            return response;
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            getMarshaller(packageName).marshal(toMarshall, baos);
            response = Base64.encode(baos.toByteArray());
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(JAXBMarshaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
