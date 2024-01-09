package com.theomenden.prefabricated;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

/**
 * @author WuestMan
 */
@SuppressWarnings({"unused"})
public class ZipUtil {
	/**
	 * Compresses a string and converts to a byte array for writing.
	 *
	 * @param originalString The string to compress.
	 * @return A byte array which has been compressed using GZip.
	 */
	private static byte[] compressString(String originalString) {
		if (originalString == null || originalString.isEmpty()) {
			return null;
		}

		try(ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out)) {
			gzip.write(originalString.getBytes());
            return out.toByteArray();
		} catch (IOException e) {
			Prefab.logger.error(e.getMessage(), e.getCause());
		}

		return null;
	}

	/**
	 * De-compresses a GZip compressed byte array. Expects UTF-8 encoding.
	 *
	 * @param compressedString The byte array to de-compress.
	 * @return A string of the de-compressed data.
	 */
	private static String decompressString(byte[] compressedString) {
		StringBuilder sb = new StringBuilder();

		try(ByteArrayInputStream bis = new ByteArrayInputStream(compressedString);
		GZIPInputStream gis = new GZIPInputStream(bis);
		InputStreamReader isr = new InputStreamReader(gis, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(isr);
		Stream<String> lines = br.lines()) {
			lines.forEach(sb::append);
		} catch (IOException e) {
			Prefab.logger.error(e.getLocalizedMessage(), e.getCause());
		}

		return sb.toString();
	}

	/**
	 * De-compresses a GZip compressed byte array. Expects UTF-8 encoding.
	 *
	 * @param compressedBytes The byte array to de-compress.
	 * @return A byte array of the de-compressed data.
	 */
	private static byte[] decompressBytes(byte[] compressedBytes) {
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream(compressedBytes.length)) {
			Inflater decompressor = new Inflater();
			decompressor.setInput(compressedBytes);

			byte[] buf = new byte[1024];

			while (!decompressor.finished()) {
				int count = decompressor.inflate(buf);
				bos.write(buf, 0, count);
			}

            return bos.toByteArray();
		} catch (IOException | DataFormatException e) {
			Prefab.logger.error(e.getLocalizedMessage(), e.getCause());
		}

		return null;
	}

	/**
	 * Compresses the resource file to a local computer location.
	 *
	 * @param resourceLocation The resource location to get data from.
	 * @param fileLocation     The file location to save the compressed data too.
	 */
	public static void zipResourceToFile(String resourceLocation, String fileLocation) {
		String temp;

		try(InputStream stream = Prefab.class.getClassLoader().getResourceAsStream(resourceLocation);) {
			assert stream != null;
			temp = CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
			ZipUtil.zipStringToFile(temp, fileLocation);
		} catch (IOException e) {
			Prefab.logger.error(e.getLocalizedMessage(), e.getCause());
		}
	}

	/**
	 * Compresses a string to a file location.
	 *
	 * @param value        The string to compress.
	 * @param fileLocation The location of the file to write the compressed data too.
	 */
	public static void zipStringToFile(String value, String fileLocation) {
		try {
			byte[] compressed = ZipUtil.compressString(value);
			Files.write(compressed, new File(fileLocation));
		} catch (IOException e) {
			Prefab.logger.error(e.getLocalizedMessage(), e.getCause());
		}
	}

	/**
	 * De-compresses a resource location into a string.
	 *
	 * @param resourceLocation The resource location to de-compress.
	 * @return The de-compressed string.
	 */
	public static String decompressResource(String resourceLocation) {
		 String returnValue = "";

		try(InputStream stream = Prefab.class.getClassLoader().getResourceAsStream(resourceLocation);) {
			assert stream != null;
			byte[] buf = ByteStreams.toByteArray(stream);
			returnValue = ZipUtil.decompressString(buf);
		} catch (IOException e) {
			Prefab.logger.error(e.getLocalizedMessage(), e.getCause());
		}

		return returnValue;
	}

	/**
	 * De-compresses a resource location to a buffered image.
	 *
	 * @param resourceLocation The resource location of the image.
	 * @return A buffered image for the resource location.
	 */
	public static BufferedImage decompressImageResource(String resourceLocation) {
		BufferedImage returnValue = null;

		try(InputStream stream = Prefab.class.getClassLoader().getResourceAsStream(resourceLocation);) {
			assert stream != null;
			byte[] buf = ByteStreams.toByteArray(stream);
			buf = ZipUtil.decompressBytes(buf);

			if(buf == null) {
				throw new IOException("Could not decompress into buffer");
			}

			// The file has been decompressed, convert those bytes to a BufferedImage.
			returnValue = ImageIO.read(new ByteArrayInputStream(buf));
		} catch (IOException e) {
			Prefab.logger.error(e.getLocalizedMessage(), e.getCause());
		}

		return returnValue;
	}
}
