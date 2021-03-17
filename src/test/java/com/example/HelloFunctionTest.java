package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.example.business.Manager;
import com.example.model.Greeting;
import com.example.model.User;
import com.example.repo.Book;

@DataMongoTest
public class HelloFunctionTest {

	@Test@Disabled
	public void test() {
		final Manager mock = mock(Manager.class);
		when(mock.findByIsbn("9791034747894")).thenReturn(Book.builder().title("foo").build());
		Greeting result = new HelloFunction(mock).apply(new User("foo"));
		assertThat(result.getMessage()).contains("Hello");






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

	/*@Test
	void ftpConnector() {

		//System.out.println(tempFile.getName());

	}*/
}
