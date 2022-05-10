package services.csv;

import java.io.IOException;
import java.util.List;

public interface GenericCSV <T>{

    void write (T object) throws IOException;

    List<T> read() throws IOException;
}
