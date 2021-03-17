package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ReaderTest {

	@Test
	public void test() throws Exception{
		File file = new File("src/test/resources/test.csv");

		Reader in = new FileReader(file);

		ColumnPositionMappingStrategy<JauneBook> strategy = new ColumnPositionMappingStrategy<>();
		strategy.setType(JauneBook.class);

		CsvToBean<JauneBook> csvToBean = new CsvToBeanBuilder<JauneBook>(in)
				.withMappingStrategy(strategy)
				.withSeparator(';')
				.withIgnoreLeadingWhiteSpace(true)
				.build();
		List<JauneBook> jauneBooks = csvToBean.parse();

		jauneBooks.stream().limit(5).forEach(System.out::println);
		System.out.println(file.getAbsolutePath());

//String newIsbn = isbn.replaceAll("#<SEPARATOR>#", "");
		//JauneBook(ean=9782012101951, prix=2.95, date=20190227, titre=MONSIEUR MADAME -
		// LES MONSIEUR MADAME VONT CHEZ LE DENTISTE, editeur=HACHETTE JEUN., auteur=HARGREAVES ADAM, theme=JEUNESSE)

		/*
		private JauneBook createJauneBook(CSVRecord csvRecord, String isbn) {
		String isbn = csvRecord.get(1);
        return JauneBook
                .builder()
                .ean(isbn)
                .prix(csvRecord.get(4))
                .date(csvRecord.get(10))
                .titre(csvRecord.get(12))
                .editeur(csvRecord.get(13))
                .auteur(csvRecord.get(17))
                .theme(csvRecord.get(20))
                .build();
    }
		 */

	}
}
