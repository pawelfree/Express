package pl.bsb.elixir.express.entity.agent;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import pl.bsb.elixir.express.util.Base64;

/**
 *
 * @author paweld
 */
@NamedQuery(name = "getStatementsByTransactionId", query = "SELECT s FROM Statement s WHERE s.transactionId = :transactionId ORDER BY s.id ASC")
@Entity
@Table(name = "Statements")
public class Statement implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Lob
  @NotNull
  @Column(length = 4096)
  private String statementData;
  @NotNull
  private String transactionId;
  @NotNull
  private String messageId;
  @NotNull
  @Enumerated(EnumType.STRING)
  private InternalStatus status;

  public Statement() {
  }

  public Statement(String statementData, String transactionId, String messageId, InternalStatus status) {
    this.statementData = statementData;
    this.transactionId = transactionId;
    this.messageId = messageId;
    this.status = status;
  }

  public String getPrettyFormatedXML() {
    return transcode(prettyFormat(new String(Base64.decode(statementData)), 2));
  }

  private static String transcode(String input) {
    return input.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\\n", "<br/>").replaceAll(" ", "&nbsp;");
  }

  private static String prettyFormat(String input, int indent) {
    try {
      Source xmlInput = new StreamSource(new StringReader(input));
      StringWriter stringWriter = new StringWriter();
      StreamResult xmlOutput = new StreamResult(stringWriter);
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      transformerFactory.setAttribute("indent-number", indent);
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
      transformer.transform(xmlInput, xmlOutput);
      return xmlOutput.getWriter().toString();
    } catch (TransformerFactoryConfigurationError | TransformerException e) {
      throw new RuntimeException(e); // simple exception handling, please review it 
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Statement)) {
      return false;
    }
    Statement other = (Statement) object;
    return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
  }

  @Override
  public String toString() {
    return "pl.bsb.elixir.express.entity.Statement[ id=" + id + " ]";
  }

  public String getStatementData() {
    return statementData;
  }

  public void setStatementData(String statementData) {
    this.statementData = statementData;
  }

  public InternalStatus getStatus() {
    return status;
  }

  public void setStatus(InternalStatus status) {
    this.status = status;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }
}
