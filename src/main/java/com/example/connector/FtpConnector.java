package com.example.connector;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FtpConnector {
	public static final String STOCK_PATH = "/jaune/stock/";
	public static final String STOCK_ARCHIVE_PATH = STOCK_PATH + "/archive/";


	private final String server;
	private final int port;
	private final String user;
	private final String password;
	private FTPClient ftpClient;

	public FtpConnector(String server, int port, String user, String password) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	private void open() throws IOException {
		ftpClient = new FTPClient();

		ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

		ftpClient.connect(server, port);
		int reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
			throw new IOException("Exception in connecting to FTP Server");
		}

		ftpClient.login(user, password);
		ftpClient.enterLocalPassiveMode();
	}

	public void close() throws IOException {
		ftpClient.disconnect();
	}

	private FTPFile[] listFiles(String path) throws IOException {
		return ftpClient.listFiles(path);
	}

	public void retrieveFile(String remote, OutputStream local) {
		try {
			ftpClient.retrieveFile(remote, local);
		} catch (Exception ioe) {
			log.error("retrieve ftp file: {} problem: {}", remote, ioe.getMessage());
		}
	}

	public void rename(String from, String to) throws IOException {
		ftpClient.rename(from, to);
	}

	public boolean isConnected() {
		return ftpClient.isConnected();
	}

	public static class ListFiles extends FtpCommand<FTPFile[]> {

		private final String stockPath;

		public ListFiles(FtpConnector ftpConnector, String stockPath) {
			super(ftpConnector);
			this.stockPath = stockPath;
		}

		@Override
		protected void executeCommand() throws IOException {
			set(getFtpConnector().listFiles(stockPath));
		}
	}

	public static class DownloadFile extends FtpCommand<File> {
		private final String source;
		private final String stockPath;

		public DownloadFile(FtpConnector ftpConnector, String source, String stockPath) {
			super(ftpConnector);
			this.source = source;
			this.stockPath = stockPath;
		}

		@Override
		protected void executeCommand() throws IOException {

			String tmpdir = Files.createTempDirectory("tmpDirPrefix").toFile().getAbsolutePath();
			File tempFile = File.createTempFile("/stock/", ".csv", new File(tmpdir));
			FileOutputStream out = new FileOutputStream(tempFile);
			log.info("starting retrieveFile of file {} ", source);
			getFtpConnector().retrieveFile(stockPath + source, out);
			out.close();
			set(tempFile);
		}
	}

	public static class ArchiveFile extends FtpCommand<Boolean> {

		private final String fileName;

		public ArchiveFile(FtpConnector ftpConnector, String fileName) {
			super(ftpConnector);
			this.fileName = fileName;
		}

		@Override
		protected void executeCommand() throws IOException {
			String from = STOCK_PATH + fileName;
			String to = STOCK_ARCHIVE_PATH + fileName;
			log.info("starting rename of file {} from {} to {}", fileName, from, to);
			getFtpConnector().rename(from, to);
			log.info("file {} has been successfully archived", fileName);
		}
	}

	static abstract class FtpCommand<T> {
		private T t;

		private final FtpConnector ftpConnector;

		protected FtpCommand(FtpConnector ftpConnector) {
			this.ftpConnector = ftpConnector;
		}

		protected FtpConnector getFtpConnector() {
			return ftpConnector;
		}

		public T get() {
			execute();
			return t;
		}

		public void set(T t) {
			this.t = t;
		}

		private void execute() {

			try {
				log.info("starting FTP connexion open");
				ftpConnector.open();
				executeCommand();
			} catch (IOException ioe) {
				log.error("error during ftp connexion: {}", ioe.getMessage());
			} finally {
				if (ftpConnector.isConnected()) {
					try {
						log.info("starting FTP connexion close");
						ftpConnector.close();
						log.info("finishing FTP connexion close");
					} catch (IOException ioe) {
						log.error("close ftp file problem: {}", ioe.getMessage());
					}
				}
			}
		}

		protected abstract void executeCommand() throws IOException;
	}
}
