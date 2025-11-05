package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models;

import lombok.Data;

@Data
public class AddBookModel {
    CollectionOfIsbns isbn;
    String userId;

    class CollectionOfIsbns {
        String[] isbn;
    }
}
