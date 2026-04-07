package Task4;

import java.io.IOException;

public interface View {
    void viewHeader();
    void viewBody();
    void viewFooter();
    void viewShow();
    void viewSave() throws IOException;
    void viewRestore() throws Exception;
}