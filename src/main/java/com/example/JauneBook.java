package com.example;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JauneBook {

	@CsvBindByPosition(position = 1)
	private String ean;

	@CsvBindByPosition(position = 4)
	private float price;

	@CsvDate(value = "yyyyMMdd")
	@CsvBindByPosition(position = 10)
	private LocalDate date;

	@CsvBindByPosition(position = 12)
	private String title;

	@CsvBindByPosition(position = 13)
	private String editor;

	@CsvBindByPosition(position = 17)
	private String author;

	@CsvBindByPosition(position = 20)
	private String theme;

	@CsvBindByPosition(position = 30)
	private int inventory;
}

