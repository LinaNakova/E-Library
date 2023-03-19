package emt.lab.Model.Request;

import java.util.List;

public class BookDto {
    public String name;
    public String category;
    public List<Long> authorIds;
    public Integer availableCopies;

    public BookDto(String name, String category, List<Long> authorIds, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorIds = authorIds;
        this.availableCopies = availableCopies;
    }
}
