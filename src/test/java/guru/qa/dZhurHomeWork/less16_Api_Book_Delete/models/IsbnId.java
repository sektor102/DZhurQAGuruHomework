package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsbnId {

    private String isbn;

    @JsonProperty("books")
    private void unpackNested(List<IsbnId> books) {
        // если пришёл JSON вида { "books": [ { "isbn": "..." } ] }
        if (books != null && !books.isEmpty()) {
            this.isbn = books.get(0).getIsbn(); // достаём первый ISBN
        }
    }
}
