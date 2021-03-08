package com.example.repo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


@ToString
@Getter
@Builder
public class Book {

    @Id
    private final String isbn;

    private final String shopfifyId;
    private final String title;
    private final String description;
    private final String image;
    private final String inventoryItemId;
    private final LocalDate date;
    private final LocalDate bdGestUpdate;
}


