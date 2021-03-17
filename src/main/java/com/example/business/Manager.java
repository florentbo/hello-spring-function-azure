package com.example.business;

import static com.example.connector.FtpConnector.STOCK_ARCHIVE_PATH;

import com.example.connector.FtpConnector;
import com.example.repo.Book;
import com.example.repo.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Manager {

	private final BookRepository repository;
	private final FtpConnector ftpConnector;

	public Manager(BookRepository repository, FtpConnector ftpConnector) {
		this.repository = repository;
		this.ftpConnector = ftpConnector;
	}

	public Book findByIsbn(String isbn) {
		log.info("202003150919-findByIsbn isbn: {}", isbn);
		return repository.findByIsbn(isbn);
	}

	public String getFileName(){
		final FtpConnector.DownloadFile downloadFile = new FtpConnector.DownloadFile(this.ftpConnector,
																					 "UPDATEDB_20210309_232211.TXT",STOCK_ARCHIVE_PATH);
		return downloadFile.get().getName();
	}

}
