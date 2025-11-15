package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models;

import lombok.Data;

import java.util.List;

@Data
public class AddBookRequest {
    String userId;
    List<IsbnId> collectionOfIsbns;
}
